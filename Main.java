import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Main extends JFrame {

	Image dbImage;
	Graphics dbg;

	Ball b = new Ball(243, 193);
	Paddle p1 = new Paddle(15, 140, 10, 50);
	Paddle p2 = new Paddle(475, 140, 10, 50);

	Menu menu = new Menu();
	Level level = new Level();

	Thread t1 = new Thread(b);
	Thread t2 = new Thread(p1);
	Thread t3 = new Thread(p2);
	Thread t7 = new Thread(level);

	int 
	GWIDTH = 500,
	GHEIGHT = 400;

	Dimension screenSize = new Dimension(GWIDTH, GHEIGHT);

	public Main() {

		p2.setAI(b.ball, 300);//--------------------------------------------------------------------------------

		menu.setObjects(b, p1, p2);
		level.addMenu(menu);

		setTitle("Ping Pong Game");
		setSize(screenSize);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(Color.DARK_GRAY);
		setLocationRelativeTo(null);
		addKeyListener(new AL());
		addMouseListener(menu);
		addMouseMotionListener(menu);
	}

	public static void main(String[] args) {
		new Main();
	}

	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		draw(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}

	public void draw(Graphics g) {
		menu.drawMenu(g);

		collision();

		repaint();
	}

	public void startGame()
	{
		t1.start();
		t2.start();
		t3.start();
		t7.start();
	}

	public void collision() {
		if(b.ball.intersects(p1.paddle))
			b.setXDirection(1);

		if(b.ball.intersects(p2.paddle))//--------------------------------------------------------------------------------
			b.setXDirection(-1);
	}

	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {

			if(menu.startGame && menu.keyStart) {
				startGame();
				menu.keyStart = false;
			} else if(menu.startGame && menu.keyStart2) {
				b.setPause(false);//continue
				p2.setPause(false);//continue//--------------------------------------------------------------------------------
				menu.keyStart2 = false;
			}

			if(e.getKeyCode() == e.VK_SPACE) {
				//Pause
				if(menu.continueToPlay) {
					b.setPause(true);
					p2.setPause(true);//--------------------------------------------------------------------------------
					menu.continueToPlay = false;
				} else {//Continue
					b.setPause(false);//continue
					p2.setPause(false);//continue//--------------------------------------------------------------------------------
					menu.continueToPlay = true;
				}
			}

			if(e.getKeyCode() == e.VK_ESCAPE) {
				menu.startGame = false;
				menu.keyStart2 = true;
				b.setPause(true);
				p2.setPause(true);//--------------------------------------------------------------------------------
				menu.continueToPlay = true;

				b.setBallPosition(243, 193);
				b.setScores(0, 0);
				p1.setPaddlePosition(15, 140);
				p2.setPaddlePosition(475, 140);//--------------------------------------------------------------------------------
			}

			if(menu.continueToPlay) {
				if(e.getKeyCode() == e.VK_W)
					p1.setYDirection(-1);

				if(e.getKeyCode() == e.VK_S)
					p1.setYDirection(1);
			}
		}

		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == e.VK_W)
				p1.setYDirection(0);

			if(e.getKeyCode() == e.VK_S)
				p1.setYDirection(0);
		}
	}
}