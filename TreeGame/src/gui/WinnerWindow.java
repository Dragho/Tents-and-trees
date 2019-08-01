package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Formatter;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bartek.Giza.JDBC;

public class WinnerWindow extends JFrame implements ActionListener {

	public WinnerWindow(MyTimerTask myTimerTask, int number, int level) throws ClassNotFoundException, SQLException {
			super("CONGRATULATIONS!");
			initWindow(myTimerTask);
			MainWindow.updateDB(myTimerTask, number, level);
			
	}
	
	private void initWindow(MyTimerTask myTimerTask) {
		JLabel lText, lTime, lEndTime;
		
		setSize(400, 500);
		setLayout(null);
		
		lText = new JLabel("YOU WIN!");
		lText.setBounds(80, 80, 100, 200);
		lText.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(lText);
		
		lTime = new JLabel("Your time:");
		lTime.setBounds(40,200,100,20);
		lTime.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(lTime);
		
		lEndTime = new JLabel("time");
		lEndTime.setBounds(120, 200, 100, 20);
		lEndTime.setFont(new Font("SansSerif", Font.BOLD, 15));
		Formatter formatter = new Formatter();
		
	   	formatter.format("%d  :  %02d",myTimerTask.minutes, myTimerTask.seconds);
	   	String formattedString = formatter.toString();
	   	lEndTime.setText(formattedString);
		add(lEndTime);
		
		//JLabel bestTime = new JLabel("Your best time:");

		JButton bWyjscie = new JButton("Wyjœcie");
		bWyjscie.setBounds(220, 380, 130, 40);
		bWyjscie.setFont(new Font("SansSerif", Font.BOLD, 15));
		add(bWyjscie);
		bWyjscie.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		dispose();
		MainWindow mainWindow=null;
		try {
			mainWindow = new MainWindow();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		mainWindow.setLocationRelativeTo(null);
	}
}
