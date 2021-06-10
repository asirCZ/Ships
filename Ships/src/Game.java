public class Game {
	static Ships frame1 = new Ships();
	static Protivnik frame2 = new Protivnik();

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

	public static void EnemyWin() {
		frame1.setVisible(false);
		frame2.setVisible(false);
	}

}
