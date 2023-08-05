


public class Level implements Runnable {

	int level = 0;

	Ball[] ball = new Ball[2];
	Paddle[] paddle = new Paddle[3];

	Menu menu;

	public void addBall(Ball b) {

	}

	public void addMenu(Menu m) {
		menu = m;
	}

	public void checkScores() {

		if(Ball.p2Score == 3) {
			menu.setWin(false);
			Ball.p1Score = Ball.p2Score = 0;
		} else if(Ball.p1Score == 3) {
			menu.setWin(true);
			level++;
			Ball.p1Score = Ball.p2Score = 0;
		}
	}

	public void run() {
		try {
			while(true) {

				checkScores();

				Thread.sleep(7);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}