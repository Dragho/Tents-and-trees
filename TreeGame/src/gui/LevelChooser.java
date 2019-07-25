package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class LevelChooser extends JFrame implements ActionListener{
	JButton bPoziomLatwy, bPoziomSredni, bPoziomTrudny, bWyjscie;
	JButton[] tabButton;
	JLabel menu;
	public LevelChooser() {
		super("Choose level");
		
		setSize(400, 500);
		setLayout(null);
		
		
		JPanel board = new JPanel();
		board.setBounds(70, 70, 200, 600);
		board.setBackground(new Color(40,40,40));
		
		//add(board);
		board.setLayout(new GridLayout(20,1));
		
		JButton[] tabButton = new JButton[20];
		
		for(int i=1;i<=20;i++) {
			tabButton[i-1] = new JButton(Integer.toString(i));
			board.add(tabButton[i-1]);
			tabButton[i-1].addActionListener(this);
		}
		
		
		
		ScrollPane scroll = new ScrollPane();
		scroll.setWheelScrollingEnabled(true);
		scroll.setBounds(50, 50, 300, 300);
		scroll.add(board);
		add(scroll);
		
		
		bWyjscie = new JButton("Wyjœcie");
		bWyjscie.setBounds(220, 380, 130, 40);
		bWyjscie.setFont(new Font("SansSerif", Font.BOLD, 15));
		add(bWyjscie);
		bWyjscie.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if(source == bWyjscie) {
			dispose();
			Window window = new Window();
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setVisible(true);
			window.setLocationRelativeTo(null);
			return;
			
		}
		for(int i=0;i<20;i++) {
			if(source == tabButton[i]) {
				dispose();
				LevelEasy levelEasy;
				try {
					levelEasy = new LevelEasy(i);//HERE WE PASS NUMBER VARIABLE
					levelEasy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					levelEasy.setVisible(true);
					levelEasy.setLocationRelativeTo(null);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	
}
