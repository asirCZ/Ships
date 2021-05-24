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
			pocetLodi[i] = i + 1;
		}
		boolean[] shipType = new boolean[5];
		boolean[] ship = new boolean[100];
		boolean[] shipBorder = new boolean[100];
		JButton letadlo = new JButton("Letadlova lod " + pocetLodi[0] + "x");
		letadlo.setToolTipText("P\u0159es 5 pol\u00ED\u010Dek");
		contentPane.add(letadlo);
		letadlo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pocetLodi[0]--;
				letadlo.setText("Letadlova lod " + pocetLodi[0] + "x");
				shipType[0] = true;
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
				shipType[1] = true;
				if (pocetLodi[1] == 0) {
					bitevni.setEnabled(false);
				}
			}
		});

		JButton kriznik = new JButton("Kriznik 3x");
		kriznik.setToolTipText("P\u0159es 3 pol\u00ED\u010Dka");
		contentPane.add(kriznik);
		kriznik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pocetLodi[2]--;
				shipType[2] = true;
				kriznik.setText("Kriznik " + pocetLodi[2] + "x");
				if (pocetLodi[2] == 0) {
					kriznik.setEnabled(false);
				}
			}
		});

		JButton ponorka = new JButton("Ponorka 4x");
		ponorka.setToolTipText("P\u0159es 2 pol\u00ED\u010Dka");
		contentPane.add(ponorka);
		ponorka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pocetLodi[3]--;
				shipType[3] = true;
				ponorka.setText("Ponorka " + pocetLodi[3] + "x");
				if (pocetLodi[3] == 0) {
					ponorka.setEnabled(false);
				}
			}
		});

		JButton torpedo = new JButton("Torpedoborec 5x");
		torpedo.setToolTipText("P\u0159es 1 pol\u00ED\u010Dko");
		contentPane.add(torpedo);
		torpedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shipType[4] = true;
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
		Position border = new Position();
		int MainButton = p.getPozice();
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				int btn = CisloTlacitka;
				ship[CisloTlacitka] = false;
				JButton b = new JButton();
				b.setName(Integer.toString(CisloTlacitka));
				b.setBackground(Color.WHITE);
				b.setEnabled(true);
				if (CisloTlacitka < 10) {
					b.setText(0 + b.getName());
				} else {
					b.setText(b.getName());
				}
				// contentPane.getComponent(CisloTlacitka + 5).setEnabled(false);
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (shipType[0]) {
							q.setPozice(MainButton);
							b.setEnabled(false);
							b.setBackground(Color.GREEN);
							p.setPozice(btn);
							// contentPane.getComponent(p.getPozice() + 6).setEnabled(false);
							ship[p.getPozice()] = true;
							for (int i = 0; i < 100; i++) {
								if (i == p.getPozice() + 1 || i == p.getPozice() - 1 || i == p.getPozice() - 10
										|| i == p.getPozice() + 10) {
									contentPane.getComponent(i + 6).setEnabled(true);
								} else {
									contentPane.getComponent(i + 6).setEnabled(false);
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
