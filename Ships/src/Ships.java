import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JMenuBar;
import java.awt.Button;
import java.awt.Color;

import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("unused")
public class Ships extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ships frame = new Ships();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
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
		boolean[] ship = new boolean[100];
		boolean[] border = new boolean[100];
		JButton letadlo = new JButton("Letadlova lod " + pocetLodi[0] + "x");
		letadlo.setToolTipText("P\u0159es 5 pol\u00ED\u010Dek");
		contentPane.add(letadlo);
		letadlo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pocetLodi[0]--;
				letadlo.setText("Letadlova lod " + pocetLodi[0] + "x");
				typLodi.setPozice(5);
				if (pocetLodi[0] == 0) {
					letadlo.setEnabled(false);
				}
			}
		});

		JButton bitevni = new JButton("Bitevni lod 2x");
		bitevni.setToolTipText("P\u0159es 4 pol\u00ED\u010Dka");
		contentPane.add(bitevni);
		bitevni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pocetLodi[1]--;
				bitevni.setText("Bitevni lod " + pocetLodi[1] + "x");
				typLodi.setPozice(4);
				if (pocetLodi[1] == 0) {
					bitevni.setEnabled(false);
				}
			}
		});

		JButton kriznik = new JButton("Kriznik 2x");
		kriznik.setToolTipText("P\u0159es 3 pol\u00ED\u010Dka");
		contentPane.add(kriznik);
		kriznik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pocetLodi[2]--;
				typLodi.setPozice(3);
				kriznik.setText("Kriznik " + pocetLodi[2] + "x");
				if (pocetLodi[2] == 0) {
					kriznik.setEnabled(false);
				}
			}
		});

		JButton ponorka = new JButton("Ponorka 2x");
		ponorka.setToolTipText("P\u0159es 2 pol\u00ED\u010Dka");
		contentPane.add(ponorka);
		ponorka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pocetLodi[3]--;
				typLodi.setPozice(2);
				ponorka.setText("Ponorka " + pocetLodi[3] + "x");
				if (pocetLodi[3] == 0) {
					ponorka.setEnabled(false);
				}
			}
		});

		JButton torpedo = new JButton("Torpedoborec 2x");
		torpedo.setToolTipText("P\u0159es 1 pol\u00ED\u010Dko");
		contentPane.add(torpedo);
		torpedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				typLodi.setPozice(1);
				pocetLodi[4]--;
				torpedo.setText("Torpedoborec " + pocetLodi[4] + "x");
				if (pocetLodi[4] == 0) {
					torpedo.setEnabled(false);
				}
			}
		});

		JButton start = new JButton(
				"                                                      Po v\u00FDb\u011Bru lod\u00ED klikn\u011Bte.                                                      ");
		start.setEnabled(false);
		contentPane.add(start);
		int CisloTlacitka = 0;
		Position p = new Position();
		Position q = new Position();
		int MainButton = p.getPozice();
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				int btn = CisloTlacitka;
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
						} catch (Exception ArrayIndexOutOfBoundsException) {
							blocked.setBln(true);
						}
						if (blocked.isBln() == false
								&& (Integer.parseInt(b.getName()) >= (typLodi.getPozice() - 1) * 10)
								&& typLodi.getPozice() != 0) {
							int x = -6;
							int z = 0;
							try {
								for (int r = typLodi.getPozice(); r > 0; r--) {
									contentPane.getComponent(Integer.parseInt(b.getName()) - x)
											.setBackground(Color.green);
									y += 10;
									x += 10;
								}
							} catch (Exception ArrayIndexOutOfBoundsException) {

							}
						}
					}

					public void mouseExited(MouseEvent evt) {
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
						} catch (Exception ArrayIndexOutOfBoundsException) {
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
								System.out.println(Integer.parseInt(b.getName()) - z - 1);
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
							} catch (Exception ArrayIndexOutOfBoundsException) {

							}
							try {
								contentPane.getComponent(Integer.parseInt(b.getName()) + 16).setEnabled(false);
								border[Integer.parseInt(b.getName()) + 10] = true;
								if (((Integer.parseInt(b.getName()) + 1) % 10 != 0)) {
									contentPane.getComponent(Integer.parseInt(b.getName()) + 17).setEnabled(false);
									border[Integer.parseInt(b.getName()) + 11] = true;
									if (((Integer.parseInt(b.getName()) + 3) % 10 != 0)) {
										contentPane.getComponent(Integer.parseInt(b.getName()) + 15).setEnabled(false);
										border[Integer.parseInt(b.getName()) + 9] = true;
									}
								}
							} catch (Exception ArrayIndexOutOfBoundsException) {

							}
							for (int i = 0; i < 100; i++) {
								if (border[i]) {
									System.out.println(i + " " + border[i]);
									contentPane.getComponent(i + 6).setBackground(Color.cyan);

								}
							}
						}
					}
				});
				getContentPane().add(b);
				CisloTlacitka++;
			}

		}
	}

}
