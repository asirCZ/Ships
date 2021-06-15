import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class GameResults extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public GameResults(boolean PlayerWon) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Message = new JLabel();
		Message.setHorizontalAlignment(SwingConstants.CENTER);
		if(PlayerWon) {
			Message.setText("Vyhr\u00E1l jsi, gratuluji!");
			Message.setFont(new Font("Tahoma", Font.PLAIN, 40));
		}else if(!PlayerWon) {
			Message.setText("Vyhrál nad tebou poèítat haha!");
			Message.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		Message.setBounds(4, 10, 342, 49);
		contentPane.add(Message);

		JButton Exit = new JButton("Ukon\u010Dit hru");
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Exit.setBounds(120, 80, 119, 44);
		contentPane.add(Exit);
	}

}
