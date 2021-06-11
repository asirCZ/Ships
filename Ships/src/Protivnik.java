import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Protivnik extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Protivnik() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(660, 100, 560, 460);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		boolean ship[] = new boolean[100];
		boolean border[] = new boolean[100];
		int pocetLodi[] = new int[5];
		Position aktivniPole = new Position();
		for (int i = 0; i < 5; i++) {
			if (i < 2) {
				pocetLodi[i] = i + 1;
			} else {
				pocetLodi[i] = 2;
			}
		}
		Random r = new Random();
		JLabel instrukce = new JLabel();
		for (int i = 0; i < pocetLodi.length;) {
			int pozice = r.nextInt(100);
			int velikost = Math.abs((i - 5));
			int y = 0;
			bln blocked = new bln();
			blocked.setBln(false);
			try {
				for (int x = velikost; x > 0; x--) {
					if ((ship[pozice - y] || border[pozice - y])) {
						blocked.setBln(true);
						break;
					}
					y += 10;
				}
			} catch (Exception ArrayIndexOutOfBoundsException) {
				blocked.setBln(true);
			}
			int a = 0;
			if (velikost != 0 && pozice > (velikost - 1) * 10 && blocked.isBln() == false) {
				for (int z = velikost; z > 0; z--) {
					ship[pozice - a] = true;

					if ((pozice - a) % 10 != 0) {
						border[pozice - a - 1] = true;
					}
					if ((pozice - a + 1) % 10 != 0) {
						border[pozice - a + 1] = true;
					}
					a += 10;

				}

				try {
					border[pozice + 10] = true;
					if ((pozice + 10) % 10 != 0) {
						border[pozice + 9] = true;
					}
					if ((pozice + 11) % 10 != 0) {
						border[pozice + 11] = true;
					}
				} catch (Exception ArrayIndexOutOfBoundsException) {

				}

				try {
					border[pozice - a] = true;
					if ((pozice - a) % 10 != 0) {
						border[pozice - a - 1] = true;
					}
					if ((pozice - a + 1) % 10 != 0) {
						border[pozice - a + 1] = true;
					}
				} catch (Exception ArrayIndexOutOfBoundsException) {

				}

				pocetLodi[i]--;
			}
			if (pocetLodi[i] == 0) {
				i++;
			}
		}
		instrukce.setText(
				"                                                           Zde hádáte lodì protivníka                                                           ");
		int CisloTlacitka = 0;
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				int btn = CisloTlacitka;
				JButton b = new JButton();
				b.setName(Integer.toString(CisloTlacitka));
				b.setBackground(Color.white);
				b.setEnabled(true);
				if (CisloTlacitka < 10) {
					b.setText(0 + b.getName());
				} else {
					b.setText(b.getName());
				}
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (ship[btn]) {
							aktivniPole.setPozice(aktivniPole.getPozice() + 1);
							b.setBackground(Color.red);
							b.setEnabled(false);
							if ((btn) % 10 != 0) {
								getContentPane().getComponent(btn - 1).setEnabled(false);
							}
							if ((btn + 1) % 10 != 0) {
								getContentPane().getComponent(btn + 1).setEnabled(false);
							}
							int nahoreCislo = -10;
							int doleCislo = -10;

							for (int i = 0; (btn - i) >= 0; i += 10) {
								System.out.println(btn - i - 10);
								if ((btn - i - 10) < 0) {
									for (int x = 0; (btn + x) < 100; x += 10) {
										try {
											if (ship[btn + x] && !getContentPane().getComponent(btn + x).isEnabled()
													&& ship[btn + x + 10] == false && nahoreCislo == -10) {
												doleCislo = btn + x + 10;
												boolean full = true;
												for (int u = doleCislo; u > 0; u -= 10) {
													if (ship[u] && getContentPane().getComponent(u).isEnabled()) {
														full = false;
														break;
													}
												}
												if (full) {
													try {
														getContentPane().getComponent(doleCislo).setEnabled(false);
														if ((doleCislo + 1) % 10 != 0) {
															getContentPane().getComponent(doleCislo + 1)
																	.setEnabled(false);
														}
													} catch (Exception ArrayIndexOutOfBoundsException) {

													}
													try {
														if (doleCislo % 10 != 0) {
															getContentPane().getComponent(doleCislo - 1)
																	.setEnabled(false);
														}
													} catch (Exception ArrayIndexOutOfBoundsException) {

													}

												}
											}
										} catch (Exception ArrayIndexOutOfBoundsException) {

										}
									}

								} else if (ship[btn - i] && !getContentPane().getComponent(btn - i).isEnabled()
										&& ship[btn - i - 10] == false) {
									nahoreCislo = btn - i - 10;
									System.out.println("NahoreCislo " + nahoreCislo);

									for (int x = 0; (btn + x) < 100; x += 10) {
										if ((btn + x + 10) >= 100) {
											if (ship[btn + x] && !getContentPane().getComponent(btn + x).isEnabled()) {
												try {
													getContentPane().getComponent(nahoreCislo).setEnabled(false);
													if ((nahoreCislo + 1) % 10 != 0) {
														getContentPane().getComponent(nahoreCislo + 1)
																.setEnabled(false);
													}
												} catch (Exception ArrayIndexOutOfBoundsException) {
												}
												try {
													if (nahoreCislo % 10 != 0) {
														getContentPane().getComponent(nahoreCislo - 1)
																.setEnabled(false);
													}
												} catch (Exception ArrayIndexOutOfBoundsException) {

												}
											}
										} else if (ship[btn + x] && !getContentPane().getComponent(btn + x).isEnabled()
												&& ship[btn + x + 10] == false) {
											doleCislo = btn + x + 10;
											System.out.println("DoleCislo" + doleCislo);
											boolean full = true;
											for (int u = nahoreCislo + 10; u < (doleCislo - 10); u += 10) {
												if (ship[u] && getContentPane().getComponent(u).isEnabled()) {
													full = false;
													break;
												}
											}
											if (full) {
												try {
													getContentPane().getComponent(nahoreCislo).setEnabled(false);
													if ((nahoreCislo + 1) % 10 != 0) {
														getContentPane().getComponent(nahoreCislo + 1)
																.setEnabled(false);
													}
												} catch (Exception ArrayIndexOutOfBoundsException) {
												}
												try {
													if (nahoreCislo % 10 != 0) {
														getContentPane().getComponent(nahoreCislo - 1)
																.setEnabled(false);
													}
												} catch (Exception ArrayIndexOutOfBoundsException) {

												}
												try {
													getContentPane().getComponent(doleCislo).setEnabled(false);
													if ((doleCislo + 1) % 10 != 0) {
														getContentPane().getComponent(doleCislo + 1).setEnabled(false);
													}
												} catch (Exception ArrayIndexOutOfBoundsException) {

												}
												try {
													if (doleCislo % 10 != 0) {
														getContentPane().getComponent(doleCislo - 1).setEnabled(false);
													}
												} catch (Exception ArrayIndexOutOfBoundsException) {

												}
											}

										}
									}
									break;

								}

							}

						}
						if (!ship[btn]) {
							getContentPane().getComponent(btn).setEnabled(false);

						}
						if (aktivniPole.getPozice() == 25) {
							Game.EnemyWin();
						}
					}
				});

				getContentPane().add(b);
				CisloTlacitka++;
			}
		}
		getContentPane().add(instrukce);
	}

}
