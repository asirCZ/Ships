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
		for (int i = 0; i < 5; i++) {
			if (i < 2) {
				pocetLodi[i] = i + 1;
			} else {
				pocetLodi[i] = 2;
			}
		}
		Random r = new Random();
		JLabel instrukce = new JLabel();
		System.out.println(pocetLodi[0]);
		for (int i = 0; i < pocetLodi.length;) {
			if (pocetLodi[i] == 0) {
				i++;
			}
			int pozice = r.nextInt(100);
			int velikost = Math.abs((i - 5));

		}
		instrukce.setText(
				"                                                           Zde hádáte lodì protivníka                                                           ");
		getContentPane().add(instrukce);
		int CisloTlacitka = 0;
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
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

					}

				});
				if (ship[btn]) {
					b.setBackground(Color.red);
				}
				getContentPane().add(b);
				CisloTlacitka++;
			}
		}
	}

}
