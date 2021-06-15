public class Game {
	static Ships frame1 = new Ships();
	static Protivnik frame2 = new Protivnik();
	public static boolean enemyPlaying = false;

	public static void main(String[] args) {
		try {
			frame1.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void EnemyStart() {
		frame2.setVisible(true);
	}

	public static void Attack() throws InterruptedException {
		frame1.Attack();
	}

	public static void anotherTry() {
		frame2.getContentPane().getComponent(101).setEnabled(true);
	}

	public static void GameOver(boolean PlayerWon) {
		frame1.setVisible(false);
		frame2.setVisible(false);
		GameResults frame3 = new GameResults(PlayerWon);
		frame3.setVisible(true);
	}

}
