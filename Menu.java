import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Rectangle;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class Menu implements MouseListener, MouseMotionListener {

	boolean startGame = false;
	boolean keyStart = true;
	boolean keyStart2 = false;
	boolean hoverStart = false;
	boolean hoverDiff = false;
	boolean hoverQuit = false;

	boolean hardDiff = false;
	boolean continueToPlay = true;

	boolean won;

	String easyOrHard = "Easy";

	Rectangle start = new Rectangle(130, 130, 240, 30);
	Rectangle difficulty = new Rectangle(130, 180, 240, 30);
	Rectangle quit = new Rectangle(130, 230, 240, 30);

	Ball b;
	Paddle p1, p2;

	public void setObjects(Ball b, Paddle p1, Paddle p2) {
		this.b = b;
		this.p1 = p1;
		this.p2 = p2;
	}

	public void setWin(boolean win) {
		won = win;
	}

	public void drawMenu(Graphics g) {
		if(!startGame) {
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString("Ping Pong Game", start.x+50, start.y-16);

			g.setColor(Color.RED);
			if(hoverStart)
				g.setColor(Color.WHITE);

			g.fillRect(start.x, start.y, start.width, start.height);
			g.setColor(Color.WHITE);
			if(hoverStart)
				g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 14));
			g.drawString("Start", start.x+100, start.y+20);

			g.setColor(Color.RED);
			if(hoverDiff)
				g.setColor(Color.WHITE);
			g.fillRect(difficulty.x, difficulty.y, difficulty.width, difficulty.height);

			if(!hoverDiff) {
				g.setColor(Color.WHITE);
				g.drawString("Difficulty", difficulty.x+90, difficulty.y+20);
			} else {
				g.setColor(Color.RED);
				g.drawString(easyOrHard, difficulty.x+103, difficulty.y+20);
			}

			g.setColor(Color.RED);
			if(hoverQuit)
				g.setColor(Color.WHITE);
			g.fillRect(quit.x, quit.y, quit.width, quit.height);
			g.setColor(Color.WHITE);
			if(hoverQuit)
				g.setColor(Color.RED);
			g.drawString("Exit", quit.x+105, quit.y+20);
		}else{
			b.draw(g);
			g.setColor(Color.CYAN);
			p1.draw(g);
			g.setColor(Color.PINK);
			p2.draw(g);//--------------------------------------------------------------------------------

			g.setFont(new Font("Arial", Font.BOLD, 10));
			g.drawString("Player", 10, 40);
			g.drawString("Comp.", 455, 40);

			g.drawString(""+Ball.p1Score, 15, 55);
			g.drawString(""+Ball.p2Score, 475, 55);

			if(keyStart || keyStart2)
				g.drawString("Press any key to continue...", 180, 380);
			else {
				if(continueToPlay)
					g.drawString("Press space to pause...", 180, 380);
				else
					g.drawString("Press space to continue...", 180, 380);
			}
		}
	}

	public void mouseMoved(MouseEvent e) {
		if(e.getX() > start.x && e.getX() < start.x + start.width &&
			e.getY() > start.y && e.getY() < start.y + start.height)
			hoverStart = true;
		else
			hoverStart = false;

		if(e.getX() > difficulty.x && e.getX() < difficulty.x + difficulty.width &&
			e.getY() > difficulty.y && e.getY() < difficulty.y + difficulty.height)
			hoverDiff = true;
		else
			hoverDiff = false;

		if(e.getX() > quit.x && e.getX() < quit.x + quit.width &&
			e.getY() > quit.y && e.getY() < quit.y + quit.height)
			hoverQuit = true;
		else
			hoverQuit = false;
	}

	public void mousePressed(MouseEvent e) {
		if(e.getX() > start.x && e.getX() < start.x + start.width &&
			e.getY() > start.y && e.getY() < start.y + start.height) {
			startGame = true;
		}
			
		if(e.getX() > difficulty.x && e.getX() < difficulty.x + difficulty.width &&
			e.getY() > difficulty.y && e.getY() < difficulty.y + difficulty.height) {
			if(!hardDiff) {
				b.setDifficulty(3);
				p1.setDifficulty(4);
				p2.setDifficulty(3);//--------------------------------------------------------------------------------
				easyOrHard = "Hard";
				hardDiff = true;
			} else {
				b.setDifficulty(5);
				p1.setDifficulty(5);
				p2.setDifficulty(5);//--------------------------------------------------------------------------------
				easyOrHard = "Easy";
				hardDiff = false;
			}
		}

		if(e.getX() > quit.x && e.getX() < quit.x + quit.width &&
			e.getY() > quit.y && e.getY() < quit.y + quit.height)
			System.exit(0);
	}

	public void mouseClicked(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {}

}