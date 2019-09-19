package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bartek.Giza.JDBC;

public class MainWindow extends JFrame implements ActionListener{
	JButton bLevelEasy, bLevelMed, bLevelHard, bExit, bResultTime;
	JLabel menu;
	static Time[] time;
	
	
	public MainWindow() throws ClassNotFoundException, SQLException {
		super("My window");
		
		initResults();
		initWindow();

	}

	static Time[] getTime() {
		return time;
	}

	private void initWindow() {
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
		
		bResultTime = new JButton("Time resultset");
		bResultTime.setBounds(60, 380, 150, 40);
		bResultTime.setFont(new Font("SansSerif", Font.BOLD, 15));
		add(bResultTime);
		bResultTime.addActionListener(this);
		
		bExit = new JButton("Exit");
		bExit.setBounds(220, 380, 130, 40);
		bExit.setFont(new Font("SansSerif", Font.BOLD, 15));
		add(bExit);
		bExit.addActionListener(this);
		
		menu = new JLabel("MAIN MENU");
		menu.setBounds(140, 20, 250, 50);
		menu.setFont(new Font("SansSerif", Font.BOLD, 20));
	  //lWyswietlDate.setForeground(new Color(200,40,200));
		add(menu);
	}
	
	private void initResults() throws ClassNotFoundException, SQLException {
		time = JDBC.getResultTimes();
	}

	static void updateDB(MyTimerTask myTimerTask, int number, int level) throws SQLException, ClassNotFoundException {
		String query;
		String URLConnection = "jdbc:mysql://localhost:3306/myfirstschema?user=newuser&password=asdQWE";
		if(myTimerTask.minutes>0 && myTimerTask.seconds<10) {
			query = "Update resulttime Set time = "+myTimerTask.minutes+"0"+myTimerTask.seconds+" Where id = "+(number+1)+";";
		}
		else {
			query = "Update resulttime Set time = "+myTimerTask.minutes+myTimerTask.seconds+" Where id = "+(number+1)+";";
		}
		
		System.out.println(query);
		Connection conn = DriverManager.getConnection(URLConnection);
        
        //Ustawiamy sterownik MySQL
        Class.forName("com.mysql.jdbc.Driver");
        
        //Uruchamiamy zapytanie do bazy danych
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
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
		else if(source == bResultTime) {
			dispose();
			ResultWindow resultWindow = new ResultWindow();
			resultWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			resultWindow.setVisible(true);
			resultWindow.setLocationRelativeTo(null);
		}
		else if(source == bExit) {
			System.exit(0);
		}	
	}

	public static boolean isNewRecord(MyTimerTask myTimerTask, int number, int level) throws ClassNotFoundException, SQLException {
		String query;
		String URLConnection = "jdbc:mysql://localhost:3306/myfirstschema?user=newuser&password=asdQWE";
		query = "SELECT time FROM resulttime where id = "+(number+1)+";";

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
}
