package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bartek.Giza.JDBC;

public class Window extends JFrame implements ActionListener{
	JButton bLevelEasy, bLevelMed, bLevelHard, bExit;
	JLabel menu;
	public Window() {
		super("My window");
		
		setSize(400, 500);
		setLayout(null);
		
		bLevelEasy = new JButton("Level easy");
		bLevelEasy.setBounds(100, 120, 180, 50);
		bLevelEasy.setFont(new Font("SansSerif", Font.BOLD, 15));
		bLevelEasy.setForeground(Color.green);
		add(bLevelEasy);
		bLevelEasy.addActionListener(this);
		
		bLevelMed = new JButton("Level medium");
		bLevelMed.setBounds(100, 180, 180, 50);
		bLevelMed.setFont(new Font("SansSerif", Font.BOLD, 15));
		bLevelMed.setForeground(Color.orange);
		add(bLevelMed);
		bLevelMed.addActionListener(this);
		
		bLevelHard = new JButton("Level hard");
		bLevelHard.setBounds(100, 240, 180, 50);
		bLevelHard.setFont(new Font("SansSerif", Font.BOLD, 15));
		bLevelHard.setForeground(Color.red);
		add(bLevelHard);
		bLevelHard.addActionListener(this);
		
		bExit = new JButton("Exit");
		bExit.setBounds(220, 380, 130, 40);
		bExit.setFont(new Font("SansSerif", Font.BOLD, 15));
		add(bExit);
		bExit.addActionListener(this);
		
		menu = new JLabel("MAIN MENU");
		menu.setBounds(115, 20, 250, 50);
		menu.setFont(new Font("SansSerif", Font.BOLD, 20));
	  //lWyswietlDate.setForeground(new Color(200,40,200));
		add(menu);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if(source == bLevelEasy) {
			dispose();
			LevelChooser levelChooser;
			
				levelChooser = new LevelChooser();
				levelChooser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				levelChooser.setVisible(true);
				levelChooser.setLocationRelativeTo(null);
			
			
		}
		else if(source == bLevelMed) {
			
		}
		else if(source == bLevelHard) {
			
		}
		else if(source == bExit) {
			System.exit(0);
		}
		
		
		
	}

}
