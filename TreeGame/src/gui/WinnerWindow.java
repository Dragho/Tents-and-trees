package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WinnerWindow extends JFrame implements ActionListener {

	public WinnerWindow() {
			super("CONGRATULATIONS!");
			
			setSize(400, 500);
			setLayout(null);
			
			JLabel text = new JLabel("YOU WIN!");
			text.setBounds(40, 100, 200, 200);
			text.setFont(new Font("SansSerif", Font.BOLD, 40));
			add(text);
			
			JButton bWyjscie = new JButton("Wyjœcie");
			bWyjscie.setBounds(220, 380, 130, 40);
			bWyjscie.setFont(new Font("SansSerif", Font.BOLD, 15));
			add(bWyjscie);
			bWyjscie.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		dispose();
		Window window = new Window();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
	}
}
