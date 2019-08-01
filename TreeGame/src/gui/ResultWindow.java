package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bartek.Giza.JDBC;

class ResultWindow extends JFrame implements ActionListener{
	JButton bLevelEasy, bLevelMed, bLevelHard, bExit, bResultTime;
	JLabel menu;
	
	public ResultWindow() {
		super("Resultset");
		initWindow();
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
		
		bExit = new JButton("Exit");
		bExit.setBounds(220, 380, 130, 40);
		bExit.setFont(new Font("SansSerif", Font.BOLD, 15));
		add(bExit);
		bExit.addActionListener(this);
		
		menu = new JLabel("HIGHSCORES");
		menu.setBounds(120, 20, 250, 50);
		menu.setFont(new Font("SansSerif", Font.BOLD, 20));
	  //lWyswietlDate.setForeground(new Color(200,40,200));
		add(menu);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if(source == bLevelEasy) {
			dispose();
			JFrame resultset = new JFrame("Results");//TO DO: THIS IS SUPPOSED TO BE ONE USE CLASS TO SHOW ONLY TIME RESULTS
			resultset.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			resultset.setVisible(true);
			resultset.setSize(400, 500);
			resultset.setLocationRelativeTo(null);
			resultset.setLayout(null);
			Time[] times = MainWindow.getTime();
			
			JPanel boardBG = new JPanel();
			boardBG.setBounds(90, 50, 200, 320);
			boardBG.setBackground(new Color(200,200,200));
						
			JPanel board = new JPanel();
			board.setBounds(100, 60, 220, 300);
			board.setOpaque(false);
			
			
			board.setLayout(new GridLayout(10,2));

			JLabel[] lTimes = new JLabel[10];
			JLabel[] lTimesNumbers = new JLabel[10];
			for(int i=0;i<10;i++) {
				lTimes[i] = new JLabel();
				lTimesNumbers[i] = new JLabel();
				lTimes[i].setText(times[i].toString());
				lTimesNumbers[i].setText("Level nr "+(i+1)+":");
				board.add(lTimesNumbers[i]);
				board.add(lTimes[i]);
				
			}
			
			JButton bExitResultWindow = new JButton("Exit");
			bExitResultWindow.setBounds(220, 380, 130, 40);
			bExitResultWindow.setFont(new Font("SansSerif", Font.BOLD, 15));
			bExitResultWindow.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					resultset.dispose();
					ResultWindow resultWindow = new ResultWindow();
					resultWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					resultWindow.setVisible(true);
					resultWindow.setLocationRelativeTo(null);
				}
				
			});
			
			
			resultset.add(board);
			resultset.add(boardBG);
			resultset.add(bExitResultWindow);
			
			
			
		}
		else if(source == bLevelMed) {
			
		}
		else if(source == bLevelHard) {
			
		}
		else if(source == bExit) {
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
}