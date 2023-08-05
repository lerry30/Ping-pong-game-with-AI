import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import java.util.Random;

public class Ball implements Runnable {

	int x, y, xDirection, yDirection;

	static int p1Score, p2Score;
	int difficulty = 5;

	boolean pause = false;

	Rectangle ball;

	public Ball(int x, int y) {
		p1Score = p2Score = 0;
		this.x = x;
		this.y = y;

		Random r = new Random();
		int randX = r.nextInt(1);

		if(randX == 0)
			randX--;
		setXDirection(randX);

		int randY = r.nextInt(1);

		if(randY == 0)
			randY--;
		setYDirection(randY);

		ball = new Rectangle(this.x, this.y, 7, 7);
	}

	public void setBallPosition(int xPos, int yPos) {
		ball.x = xPos;
		ball.y = yPos;
	}

	public void setScores(int fScore, int sScore) {
		p1Score = fScore;
		p2Score = sScore;
	}

	public void setDifficulty(int diff) {
		difficulty = diff;
	}

	public void setXDirection(int xdir) {
		xDirection = xdir;
	}

	public void setYDirection(int ydir) {
		yDirection = ydir;
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(ball.x, ball.y, ball.width, ball.height);
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
	public void move() {
		if(!pause) {
			ball.x += xDirection;
			ball.y += yDirection;

			if(ball.x <= 0) {
				setXDirection(1);
				p2Score++;
			}

			if(ball.x >= 493) {
				setXDirection(-1);
				p1Score++;
			}

			if(ball.y <= 20)
				setYDirection(1);

			if(ball.y >= 393)
				setYDirection(-1);
		}
	}

	public void run() {
		try {
			while(true) {

				move();

				Thread.sleep(difficulty);
			}
		} catch (Exception e) { System.err.println(e.getMessage()); }
	}
}