import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.event.KeyEvent;

public class Paddle implements Runnable {

	int x, y, yDirection;
	int difficulty = 7;

	int certainPosition;

	boolean ai = false;
	boolean pause = false;

	Rectangle paddle;
	Rectangle ball;

	public Paddle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;

		paddle = new Rectangle(x, y, width, height);
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public void setDifficulty(int diff) {
		difficulty = diff;
	}

	public void setPaddlePosition(int xPos, int yPos) {
		paddle.x = xPos;
		paddle.y = yPos;
	}

	public void setYDirection(int ydir) {
		yDirection = ydir;
	}

	public void move() {
		paddle.y += yDirection;

		if(paddle.y < 20)
			paddle.y = 20;

		if(paddle.y > 350)
			paddle.y = 350;
	}

	public void setAI(Rectangle ball, int certainPosition) {
		this.ball = ball;
		this.certainPosition = certainPosition;

		ai = true;
	}

	public void auto() {
		int ballPosition = ball.x + ball.width;
		int centerOfPaddle = paddle.y + (paddle.height / 2);

		if(!pause) {
			if(ballPosition > certainPosition) {

				if(centerOfPaddle < ball.y) {
					setYDirection(1);
				}

				if(centerOfPaddle > ball.y) {
					setYDirection(-1);
				}

			} else {
				setYDirection(0);
			}
		} else {
			setYDirection(0);
		}
	}

	public void draw(Graphics g) {
		g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
	}

	public void run() {
		try {
			while(true) {
				if(ai)
					auto();
				move();
				Thread.sleep(difficulty);
			}
		} catch(Exception e) {System.err.println(e.getMessage()); }
	}
}