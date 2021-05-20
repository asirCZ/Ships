import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 475, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		int pocetLodi[] = new int[5];
		for (int i = 0; i < 5; i++) {
			pocetLodi[i] = i + 1;
		}

		JButton letadlo = new JButton("Letadlova lod " + pocetLodi[0] + "x");
		letadlo.setToolTipText("P\u0159es 5 pol\u00ED\u010Dek");
		contentPane.add(letadlo);
		letadlo.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mp) {
				pocetLodi[0]--;
				letadlo.setText("Letadlova lod " + pocetLodi[0] + "x");
				if (pocetLodi[0] == 0) {
					letadlo.setEnabled(false);
				}
			}
		});

		JButton bitevni = new JButton("Bitevni lod 2x");
		bitevni.setToolTipText("P\u0159es 4 pol\u00ED\u010Dka");
		contentPane.add(bitevni);

		JButton kriznik = new JButton("Kriznik 3x");
		kriznik.setToolTipText("P\u0159es 3 pol\u00ED\u010Dka");
		contentPane.add(kriznik);

		JButton ponorka = new JButton("Ponorka 4x");
		ponorka.setToolTipText("P\u0159es 2 pol\u00ED\u010Dka");
		contentPane.add(ponorka);

		JButton torpedo = new JButton("Torpedoborec 5x");
		torpedo.setToolTipText("P\u0159es 1 pol\u00ED\u010Dko");
		contentPane.add(torpedo);

		JButton start = new JButton(
				"                                                      Po v\u00FDb\u011Bru lod\u00ED klikn\u011Bte.                                                      ");
		contentPane.add(start);

		boolean ship;
		int CisloTlacitka = 0;
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				CisloTlacitka++;
				JButton b = new JButton();
				b.setName(Integer.toString(CisloTlacitka));
				if (CisloTlacitka < 10) {
					b.setText(0 + b.getName());
				} else {
					b.setText(b.getName());
				}
				getContentPane().add(b, "cell " + col + " " + row + ",grow");
				b.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent mp) {
						b.setEnabled(false);
					}
				});

			}
		}

	}

}
