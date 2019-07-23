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
	JButton bPoziomLatwy, bPoziomSredni, bPoziomTrudny, bWyjscie;
	JLabel menu;
	public Window() {
		super("My window");
		
		setSize(400, 500);
		setLayout(null);
		
		bPoziomLatwy = new JButton("Poziom £atwy");
		bPoziomLatwy.setBounds(100, 120, 180, 50);
		bPoziomLatwy.setFont(new Font("SansSerif", Font.BOLD, 15));
		bPoziomLatwy.setForeground(Color.green);
		add(bPoziomLatwy);
		bPoziomLatwy.addActionListener(this);
		
		bPoziomSredni = new JButton("Poziom Œredni");
		bPoziomSredni.setBounds(100, 180, 180, 50);
		bPoziomSredni.setFont(new Font("SansSerif", Font.BOLD, 15));
		bPoziomSredni.setForeground(Color.orange);
		add(bPoziomSredni);
		bPoziomSredni.addActionListener(this);
		
		bPoziomTrudny = new JButton("Poziom Trudny");
		bPoziomTrudny.setBounds(100, 240, 180, 50);
		bPoziomTrudny.setFont(new Font("SansSerif", Font.BOLD, 15));
		bPoziomTrudny.setForeground(Color.red);
		add(bPoziomTrudny);
		bPoziomTrudny.addActionListener(this);
		
		bWyjscie = new JButton("Wyjœcie");
		bWyjscie.setBounds(220, 380, 130, 40);
		bWyjscie.setFont(new Font("SansSerif", Font.BOLD, 15));
		add(bWyjscie);
		bWyjscie.addActionListener(this);
		
		menu = new JLabel("MENU G£ÓWNE");
		menu.setBounds(115, 20, 250, 50);
		menu.setFont(new Font("SansSerif", Font.BOLD, 20));
	  //lWyswietlDate.setForeground(new Color(200,40,200));
		add(menu);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if(source == bPoziomLatwy) {
			dispose();
			LevelEasy levelEasy;
			try {
				levelEasy = new LevelEasy();
				levelEasy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				levelEasy.setVisible(true);
				levelEasy.setLocationRelativeTo(null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(source == bPoziomSredni) {
			
		}
		else if(source == bPoziomTrudny) {
			
		}
		else if(source == bWyjscie) {
			System.exit(0);
		}
		
		
		
	}

}
