import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;

import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ships extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	boolean[] ship = new boolean[100];
	boolean[] border = new boolean[100];
	boolean gameInProgress = false;
	boolean hit = false;
	boolean down = false;
	boolean up = false;
	int hitPosition = 0;
	int posun = 0;
	Position aktivniPole = new Position();

	public Ships() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 460);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		int pocetLodi[] = new int[5];
		for (int i = 0; i < 5; i++) {
			if (i < 2) {
				pocetLodi[i] = i + 1;
			} else {
				pocetLodi[i] = 2;
			}
		}
		Position typLodi = new Position();

		JButton letadlo = new JButton("Letadlova lod " + pocetLodi[0] + "x");
		letadlo.setToolTipText("P\u0159es 5 pol\u00ED\u010Dek");
		contentPane.add(letadlo);
		JButton bitevni = new JButton("Bitevni lod 2x");
		bitevni.setToolTipText("P\u0159es 4 pol\u00ED\u010Dka");
		contentPane.add(bitevni);
		JButton kriznik = new JButton("Kriznik 2x");
		kriznik.setToolTipText("P\u0159es 3 pol\u00ED\u010Dka");
		contentPane.add(kriznik);
		JButton ponorka = new JButton("Ponorka 2x");
		ponorka.setToolTipText("P\u0159es 2 pol\u00ED\u010Dka");
		contentPane.add(ponorka);
		JButton torpedo = new JButton("Torpedoborec 2x");
		torpedo.setToolTipText("P\u0159es 1 pol\u00ED\u010Dko");
		contentPane.add(torpedo);

		letadlo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pocetLodi[0]--;
				letadlo.setText("Letadlova lod " + pocetLodi[0] + "x");
				typLodi.setPozice(5);
				letadlo.setEnabled(false);
				bitevni.setEnabled(false);
				kriznik.setEnabled(false);
				ponorka.setEnabled(false);
				torpedo.setEnabled(false);

			}
		});
		bitevni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pocetLodi[1]--;
				bitevni.setText("Bitevni lod " + pocetLodi[1] + "x");
				typLodi.setPozice(4);
				letadlo.setEnabled(false);
				bitevni.setEnabled(false);
				kriznik.setEnabled(false);
				ponorka.setEnabled(false);
				torpedo.setEnabled(false);
			}
		});

		kriznik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pocetLodi[2]--;
				typLodi.setPozice(3);
				kriznik.setText("Kriznik " + pocetLodi[2] + "x");
				letadlo.setEnabled(false);
				bitevni.setEnabled(false);
				kriznik.setEnabled(false);
				ponorka.setEnabled(false);
				torpedo.setEnabled(false);
			}
		});

		ponorka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pocetLodi[3]--;
				typLodi.setPozice(2);
				ponorka.setText("Ponorka " + pocetLodi[3] + "x");
				letadlo.setEnabled(false);
				bitevni.setEnabled(false);
				kriznik.setEnabled(false);
				ponorka.setEnabled(false);
				torpedo.setEnabled(false);
			}
		});

		torpedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				typLodi.setPozice(1);
				pocetLodi[4]--;
				torpedo.setText("Torpedoborec " + pocetLodi[4] + "x");
				letadlo.setEnabled(false);
				bitevni.setEnabled(false);
				kriznik.setEnabled(false);
				ponorka.setEnabled(false);
				torpedo.setEnabled(false);
			}
		});

		JButton start = new JButton(
				"                                                      Po v\u00FDb\u011Bru lod\u00ED klikn\u011Bte.                                                      ");
		start.setEnabled(false);
		contentPane.add(start);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 100; i++) {
					getContentPane().getComponent(i + 6).setEnabled(true);
				}
				start.setEnabled(false);
				Game.EnemyStart();
			}
		});

		int CisloTlacitka = 0;
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				ship[CisloTlacitka] = false;
				JButton b = new JButton();
				b.setName(Integer.toString(CisloTlacitka));
				b.setBackground(Color.white);
				b.setEnabled(true);
				if (CisloTlacitka < 10) {
					b.setText(0 + b.getName());
				} else {
					b.setText(b.getName());
				}
				b.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent evt) {
						if (!gameInProgress) {
							int y = 0;
							bln blocked = new bln();
							blocked.setBln(false);
							try {
								for (int i = typLodi.getPozice(); i > 0; i--) {
									if ((ship[Integer.parseInt(b.getName()) - y]
											|| border[Integer.parseInt(b.getName()) - y])) {
										blocked.setBln(true);
										break;
									}
									y += 10;
								}
							} catch (ArrayIndexOutOfBoundsException eee) {
								blocked.setBln(true);
							}
							if (blocked.isBln() == false
									&& (Integer.parseInt(b.getName()) >= (typLodi.getPozice() - 1) * 10)
									&& typLodi.getPozice() != 0) {
								int x = -6;
								try {
									for (int r = typLodi.getPozice(); r > 0; r--) {
										contentPane.getComponent(Integer.parseInt(b.getName()) - x)
												.setBackground(Color.green);
										y += 10;
										x += 10;
									}
								} catch (ArrayIndexOutOfBoundsException eee) {

								}
							}
						}
					}

					public void mouseExited(MouseEvent evt) {
						if (!gameInProgress) {
							for (int i = 0; i < 100; i++) {
								if (ship[i]) {
									contentPane.getComponent(i + 6).setBackground(Color.LIGHT_GRAY);
								} else if (border[i]) {
									contentPane.getComponent(i + 6).setBackground(Color.cyan);
								} else {
									contentPane.getComponent(i + 6).setBackground(Color.WHITE);
								}

							}
						}
					}
				});
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int y = 0;
						bln blocked = new bln();
						blocked.setBln(false);
						try {
							for (int i = typLodi.getPozice(); i > 0; i--) {
								if ((ship[Integer.parseInt(b.getName()) - y]
										|| border[Integer.parseInt(b.getName()) - y])) {
									blocked.setBln(true);
									break;
								}
								y += 10;
							}
						} catch (ArrayIndexOutOfBoundsException eee) {
							blocked.setBln(true);
						}
						if (blocked.isBln() == false
								&& (Integer.parseInt(b.getName()) >= (typLodi.getPozice() - 1) * 10)
								&& typLodi.getPozice() != 0) {
							int x = -6;
							int z = 0;
							for (typLodi.getPozice(); typLodi.getPozice() > 0; typLodi
									.setPozice(typLodi.getPozice() - 1)) {
								contentPane.getComponent(Integer.parseInt(b.getName()) - x)
										.setBackground(Color.LIGHT_GRAY);
								contentPane.getComponent(Integer.parseInt(b.getName()) - x).setEnabled(false);
								if (((Integer.parseInt(b.getName()) - z + 1) % 10 != 0)) {
									contentPane.getComponent(Integer.parseInt(b.getName()) - x + 1).setEnabled(false);
								}
								if (((Integer.parseInt(b.getName()) - z) % 10 != 0)) {
									contentPane.getComponent(Integer.parseInt(b.getName()) - x - 1).setEnabled(false);
								}
								ship[Integer.parseInt(b.getName()) - z] = true;
								if (((Integer.parseInt(b.getName()) - z + 1) % 10 != 0)) {
									border[Integer.parseInt(b.getName()) - z + 1] = true;
								}
								if (((Integer.parseInt(b.getName()) - z) % 10 != 0)) {
									border[Integer.parseInt(b.getName()) - z - 1] = true;
								}
								x += 10;
								z += 10;
							}
							try {
								contentPane.getComponent(Integer.parseInt(b.getName()) - x).setEnabled(false);
								if (((Integer.parseInt(b.getName()) - z + 1) % 10 != 0)) {
									contentPane.getComponent(Integer.parseInt(b.getName()) - x + 1).setEnabled(false);
								}
								if (((Integer.parseInt(b.getName()) - z) % 10 != 0)) {
									contentPane.getComponent(Integer.parseInt(b.getName()) - x - 1).setEnabled(false);
								}
								border[Integer.parseInt(b.getName()) - z] = true;
								if (((Integer.parseInt(b.getName()) - z + 1) % 10 != 0)) {
									border[Integer.parseInt(b.getName()) - z + 1] = true;
								}
								if (((Integer.parseInt(b.getName()) - z) % 10 != 0)) {
									border[Integer.parseInt(b.getName()) - z - 1] = true;
								}
							} catch (ArrayIndexOutOfBoundsException eee) {

							}
							try {
								contentPane.getComponent(Integer.parseInt(b.getName()) + 16).setEnabled(false);
								border[Integer.parseInt(b.getName()) + 10] = true;
								if (((Integer.parseInt(b.getName()) + 1) % 10 != 0)) {
									contentPane.getComponent(Integer.parseInt(b.getName()) + 17).setEnabled(false);
									border[Integer.parseInt(b.getName()) + 11] = true;
								}
								if (((Integer.parseInt(b.getName())) % 10 != 0)) {
									contentPane.getComponent(Integer.parseInt(b.getName()) + 15).setEnabled(false);
									border[Integer.parseInt(b.getName()) + 9] = true;

								}
							} catch (ArrayIndexOutOfBoundsException eee) {

							}
							for (int i = 0; i < 100; i++) {
								if (border[i]) {
									contentPane.getComponent(i + 6).setBackground(Color.cyan);

								}
							}
							if (pocetLodi[0] > 0) {
								letadlo.setEnabled(true);
							}
							if (pocetLodi[1] > 0) {
								bitevni.setEnabled(true);
							}
							if (pocetLodi[2] > 0) {
								kriznik.setEnabled(true);
							}
							if (pocetLodi[3] > 0) {
								ponorka.setEnabled(true);
							}
							if (pocetLodi[4] > 0) {
								torpedo.setEnabled(true);
							}
							int pocet = 0;
							for (int i = 0; i < 5; i++) {
								if (pocetLodi[i] == 0) {
									pocet++;
								}

							}
							if (pocet == 5) {
								start.setEnabled(true);
							}

						}
					}
				});
				getContentPane().add(b);
				CisloTlacitka++;
			}

		}

	}

	public void Attack() throws InterruptedException {
		Random r = new Random();
		int btn = 0;
		if (hit && down && ((hitPosition + (posun * 10)) < 100)) {
			btn = hitPosition + (posun * 10);
			if (!getContentPane().getComponent(btn + 6).isEnabled()) {
				posun = 1;
				down = false;
				up = true;
			}
		} else if (!up) {
			posun = 1;
			down = false;
			up = true;
		}
		if (hit && up && ((hitPosition - (posun * 10))) >= 0) {
			btn = hitPosition - (posun * 10);
			if (!getContentPane().getComponent(btn + 6).isEnabled()) {
				up = false;
				down = false;
				hit = false;
				posun = 0;
			}
		} else if (hit && up && ((hitPosition - (posun * 10))) < 0) {
			up = false;
			down = false;
			hit = false;
			posun = 0;

		}
		if (!hit) {
			posun = 0;
			hit = false;
			up = false;
			down = false;
			do {
				btn = r.nextInt(100);
			} while (!getContentPane().getComponent(btn + 6).isEnabled());
		}
		gameInProgress = true;
		if (ship[btn]) {
			if (hit == false) {
				hit = true;
				down = true;
				hitPosition = btn;
			}
			posun++;
			aktivniPole.setPozice(aktivniPole.getPozice() + 1);
			getContentPane().getComponent(btn + 6).setBackground(Color.red);
			getContentPane().getComponent(btn + 6).setEnabled(false);
			if ((btn) % 10 != 0) {
				getContentPane().getComponent(btn + 5).setEnabled(false);
			}
			if ((btn + 1) % 10 != 0) {
				getContentPane().getComponent(btn + 7).setEnabled(false);
			}
			int nahoreCislo = -10;
			int doleCislo = -10;

			for (int i = 0; (btn - i) >= 0; i += 10) {
				if ((btn - i - 10) < 0) {
					for (int x = 0; (btn + x) < 100; x += 10) {
						try {
							if (ship[btn + x] && !getContentPane().getComponent(btn + x + 6).isEnabled()
									&& ship[btn + x + 10] == false && nahoreCislo == -10) {
								doleCislo = btn + x + 10;
								boolean full = true;
								for (int u = doleCislo; u >= 0; u -= 10) {
									if (ship[u] && getContentPane().getComponent(u + 6).isEnabled()) {
										full = false;
										break;
									}
								}
								if (full) {
									try {
										getContentPane().getComponent(doleCislo + 6).setEnabled(false);
										if ((doleCislo + 1) % 10 != 0) {
											getContentPane().getComponent(doleCislo + 7).setEnabled(false);
										}
									} catch (ArrayIndexOutOfBoundsException eee) {

									}
									try {
										if (doleCislo % 10 != 0) {
											getContentPane().getComponent(doleCislo + 5).setEnabled(false);
										}
									} catch (ArrayIndexOutOfBoundsException eee) {

									}

								}
							}
						} catch (ArrayIndexOutOfBoundsException eee) {

						}
					}

				} else if (ship[btn - i] && !getContentPane().getComponent(btn - i + 6).isEnabled()
						&& ship[btn - i - 10] == false) {
					nahoreCislo = btn - i - 10;

					for (int x = 0; (btn + x) < 100; x += 10) {
						if ((btn + x + 10) >= 100) {
							if (ship[btn + x] && !getContentPane().getComponent(btn + x + 6).isEnabled()) {
								try {
									getContentPane().getComponent(nahoreCislo + 6).setEnabled(false);
									if ((nahoreCislo + 1) % 10 != 0) {
										getContentPane().getComponent(nahoreCislo + 7).setEnabled(false);
									}
								} catch (ArrayIndexOutOfBoundsException eee) {
								}
								try {
									if (nahoreCislo % 10 != 0) {
										getContentPane().getComponent(nahoreCislo + 5).setEnabled(false);
									}
								} catch (ArrayIndexOutOfBoundsException eee) {

								}
							}
						} else if (ship[btn + x] && !getContentPane().getComponent(btn + x + 6).isEnabled()
								&& ship[btn + x + 10] == false) {
							doleCislo = btn + x + 10;
							boolean full = true;
							for (int u = nahoreCislo + 10; u <= (doleCislo - 10); u += 10) {
								if (ship[u] && getContentPane().getComponent(u + 6).isEnabled()) {
									full = false;
									break;
								}
							}
							if (full) {
								up = false;
								down = false;
								hit = false;
								posun = 0;
								try {
									getContentPane().getComponent(nahoreCislo + 6).setEnabled(false);
									if ((nahoreCislo + 1) % 10 != 0) {
										getContentPane().getComponent(nahoreCislo + 7).setEnabled(false);
									}
								} catch (ArrayIndexOutOfBoundsException eee) {
								}
								try {
									if (nahoreCislo % 10 != 0) {
										getContentPane().getComponent(nahoreCislo + 5).setEnabled(false);
									}
								} catch (ArrayIndexOutOfBoundsException eee) {

								}
								try {
									getContentPane().getComponent(doleCislo + 6).setEnabled(false);
									if ((doleCislo + 1) % 10 != 0) {
										getContentPane().getComponent(doleCislo + 7).setEnabled(false);
									}
								} catch (ArrayIndexOutOfBoundsException eee) {

								}
								try {
									if (doleCislo % 10 != 0) {
										getContentPane().getComponent(doleCislo + 5).setEnabled(false);
									}
								} catch (ArrayIndexOutOfBoundsException eee) {

								}
							}

						}
					}
					break;

				}

			}
		}
		if (!ship[btn]) {
			Game.enemyPlaying = false;
			if (down) {
				down = false;
				up = true;
				posun = 1;
			} else if (up) {
				up = false;
				down = false;
				hit = false;
				posun = 0;
			}
			getContentPane().getComponent(btn + 6).setEnabled(false);
			getContentPane().getComponent(btn + 6).setBackground(Color.green);

		}
		if (aktivniPole.getPozice() == 25) {
			Game.GameOver(false);
		} else if (ship[btn]) {
			Game.enemyPlaying = true;
			Game.anotherTry();
		}

	}

}
