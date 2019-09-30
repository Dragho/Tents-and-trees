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
			initWindow(myTimerTask, number, level);
			if(isNewRecord(myTimerTask, number, level))
				updateDB(myTimerTask, number, level);
	}
	
	private void initWindow(MyTimerTask myTimerTask, int number, int levelNumber) {
		JLabel lWinText, lTextTime, lEndTime, lBestTextTime, lBestTime;
		
		setSize(400, 500);
		setLayout(null);
		
		lWinText = new JLabel("YOU WIN!");
		lWinText.setBounds(110, 70, 160, 30);
		lWinText.setFont(new Font("SansSerif", Font.BOLD, 30));
		add(lWinText);
		
		lTextTime = new JLabel("Your time:");
		lTextTime.setBounds(40,200,160,20);
		lTextTime.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(lTextTime);
		
		lEndTime = new JLabel("time");
		lEndTime.setBounds(220, 200, 100, 20);
		lEndTime.setFont(new Font("SansSerif", Font.BOLD, 20));
		Formatter formatter = new Formatter();
		
	   	formatter.format("%d  :  %02d",myTimerTask.minutes, myTimerTask.seconds);
	   	String formattedString = formatter.toString();
	   	lEndTime.setText(formattedString);
		add(lEndTime);
		
		lBestTextTime = new JLabel("Your best time:");
		lBestTextTime.setBounds(40, 230, 160, 20);
		lBestTextTime.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(lBestTextTime);
		
		lBestTime = new JLabel("best time");
		lBestTime.setBounds(220, 230, 100, 20);
		lBestTime.setFont(new Font("SansSerif", Font.BOLD, 20));
		formatter = new Formatter();
		
		Time[] times = MainWindow.getTime(levelNumber);
		
		formatter.format("%d  :  %02d",times[number].getMinutes(), times[number].getSeconds());
	   	formattedString = formatter.toString();
	   	lBestTime.setText(formattedString);
		add(lBestTime);
		
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
			e.printStackTrace();
		}
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		mainWindow.setLocationRelativeTo(null);
	}
	
	private boolean isNewRecord(MyTimerTask myTimerTask, int number, int level) throws ClassNotFoundException, SQLException {
		String query;
		String URLConnection = "jdbc:mysql://localhost:3306/myfirstschema?user=newuser&password=asdQWE";
		if(level == 1) 
			query = "SELECT time FROM resulttimeeasy where id = "+(number+1)+";";
		else if(level == 2)
			query = "SELECT time FROM resulttimemed where id = "+(number+1)+";";
		else if (level == 3)
			query = "SELECT time FROM resulttimehard where id = "+(number+1)+";";
		else {
			System.err.println("Wrong levelNumber(MainWindow.java)");
			return false;
		}

		Connection conn = DriverManager.getConnection(URLConnection);
        
        //Ustawiamy sterownik MySQL
        Class.forName("com.mysql.jdbc.Driver");
        
        //Uruchamiamy zapytanie do bazy danych
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        int oldTime = 0;
        while(rs.next()) {
        	String str = rs.getTime(1)+"";
        	int sec = (str.charAt(6)-48)*10+(str.charAt(7)-48);
        	int min = (str.charAt(3)-48)*1000+(str.charAt(4)-48)*10;
        	oldTime=min+sec;
        }
        
        int newTime = myTimerTask.minutes*100 + myTimerTask.seconds;
        
        if(oldTime==0)
        	return true;
        
		return newTime<oldTime;
	}
	
	
	private void updateDB(MyTimerTask myTimerTask, int number, int level) throws SQLException, ClassNotFoundException {
		String query;
		String URLConnection = "jdbc:mysql://localhost:3306/myfirstschema?user=newuser&password=asdQWE";
		
		if(level == 1)
			query = "Update resulttimeeasy ";
		else if(level == 2)
			query = "Update resulttimemed ";
		else if(level == 3)
			query = "Update resulttimehard ";
		else {
			System.err.println("Wrong levelNumber(WinnerWindow.java)");
			return;
		}
		
		if(myTimerTask.minutes>0 && myTimerTask.seconds<10) 
			query = query + "Set time = "+myTimerTask.minutes+"0"+myTimerTask.seconds+" Where id = "+(number+1)+";";
		else 
			query = query + "Set time = "+myTimerTask.minutes+myTimerTask.seconds+" Where id = "+(number+1)+";";
		
		System.out.println(query);
		Connection conn = DriverManager.getConnection(URLConnection);
        
        //Ustawiamy sterownik MySQL
        Class.forName("com.mysql.jdbc.Driver");
        
        //Uruchamiamy zapytanie do bazy danych
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
	}
}
