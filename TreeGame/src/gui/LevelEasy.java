package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Formatter;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import bartek.Giza.JDBC;
import logicEngine.Mechanics;

public class LevelEasy extends JFrame{
	
	final int BOARD_WIDTH = 280;
	final int BOARD_HEIGHT = 280;
	final int BOARD_X_POSITION = 50;
	final int BOARD_Y_POSITION = 70;
	final int LENGTH = 6;
	
	public LevelEasy(int number) throws SQLException{
		super("Level ³atwy");
		int[][] table = JDBC.wczytajPlansze(1,number);
		JDBC.wyswietlDaneZBazy(table, 6);
		
		JButton bWyjscie;
		JLabel lTimerText, lTime;
		JPanel board, boardBG, topValues, leftValues;
		Image image;
		
		Timer timer1= new Timer();
		
		JLabel information = new JLabel("Here you can see important errors...");
		information.setBounds(5, 5, 240, 20);
		information.setForeground(Color.BLACK);
		
		topValues = new JPanel();
		topValues.setBounds(BOARD_X_POSITION, 30, BOARD_WIDTH, 35);
		topValues.setLayout(new GridLayout(1,LENGTH));
		
		JLabel[] topLabels = new JLabel[7];
		for(int i=0;i<6;i++) {
			topLabels[i] = new JLabel(Integer.toString(table[0][1+i]));
			topLabels[i].setFont(new Font("SansSerif", Font.BOLD, 20));
			topLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			topValues.add(topLabels[i]);
		}
		
		leftValues = new JPanel();
		leftValues.setBounds(10, BOARD_Y_POSITION, 35, BOARD_HEIGHT);
		leftValues.setLayout(new GridLayout(LENGTH,1));
		
		JLabel[] leftLabels = new JLabel[7];
		for(int i=0;i<6;i++) {
			leftLabels[i] = new JLabel(Integer.toString(table[1+i][0]));
			leftLabels[i].setFont(new Font("SansSerif", Font.BOLD, 20));
			leftLabels[i].setVerticalAlignment(SwingConstants.CENTER);
			leftLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			leftValues.add(leftLabels[i]);
		}
		
		boardBG = new JPanel();
		boardBG.setBounds(BOARD_X_POSITION - 5, BOARD_Y_POSITION - 5, BOARD_WIDTH + 10, BOARD_HEIGHT + 10);
		boardBG.setBackground(new Color(0,0,0));
		
		board = new JPanel();
		board.setBounds(BOARD_X_POSITION, BOARD_Y_POSITION, BOARD_WIDTH, BOARD_HEIGHT);
		board.setBackground(new Color(0,255,64));
		board.setLayout(new GridLayout(LENGTH,LENGTH));
		
		lTimerText = new JLabel("Timer: ");
		lTimerText.setBounds(240, 10, 100, 20);
		lTimerText.setForeground(Color.GRAY);
		lTimerText.setFont(new Font("SansSerif", Font.BOLD, 15));
		add(lTimerText);
		
		lTime = new JLabel("0");
		lTime.setBounds(300, 10, 100, 20);
		lTime.setForeground(Color.GRAY);
		lTime.setFont(new Font("SansSerif", Font.BOLD, 15));
		add(lTime);
		
		MyTimerTask myTimerTask = new MyTimerTask(lTime);
		
		timer1.schedule(myTimerTask,1,1000); //timer wykonuje metode run co 1 sekunde
		
		JButton[][] tabButton = new JButton[7][7];
		for(int i=1;i<7;i++) {
			for(int j=1;j<7;j++) {
				if(table[i][j]==0) {
					tabButton[i][j] = new JButton();
					image = new ImageIcon(this.getClass().getResource("/pustePole.png")).getImage();
					tabButton[i][j].setIcon(new ImageIcon(image));
					board.add(tabButton[i][j]);
					tabButton[i][j].addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent event) {
							for (int i = 1; i < 7; i++){
						        for (int j= 1; j < 7; j++){
						            if (event.getSource() == tabButton[i][j]){
						            	if(table[i][j]==0) {
						            		table[i][j]=1;
						            		Image image = new ImageIcon(this.getClass().getResource("/trawa.png")).getImage();
						            		image = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
											tabButton[i][j].setIcon(new ImageIcon(image));
											//JDBC.wyswietlDaneZBazy(table, 6);
						            	}
						            	else if(table[i][j]==1) {
						            		table[i][j]=2;
						            		Image image = new ImageIcon(this.getClass().getResource("/namiot.png")).getImage();
						            		image = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
											tabButton[i][j].setIcon(new ImageIcon(image));
											//JDBC.wyswietlDaneZBazy(table, 6);
											}
						            	else if(table[i][j]==2) {
						            		table[i][j]=0;
						            		Image image = new ImageIcon(this.getClass().getResource("/pustePole.png")).getImage();
						            		image = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
											tabButton[i][j].setIcon(new ImageIcon(image));
											//JDBC.wyswietlDaneZBazy(table, 6);
											
						            	}
						            	if(Mechanics.checkGameMechanics(table,topLabels, leftLabels, information, i,j, LENGTH)==1) {
						            		dispose();
						            		WinnerWindow winnerWindow = new WinnerWindow(myTimerTask);
						            		winnerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						            		winnerWindow.setVisible(true);
						            		winnerWindow.setLocationRelativeTo(null);
						            	}
						            }                                       
						        }
							}
						}
					});
				}
				else if(table[i][j]==4) {
					
					tabButton[i][j] = new JButton();
					image = new ImageIcon(this.getClass().getResource("/drzewo.png")).getImage();
					image = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
					tabButton[i][j].setIcon(new ImageIcon(image));
					board.add(tabButton[i][j]);
				}
			}
		}
		
		add(information);
		add(leftValues);
		add(topValues);
		add(board);
		add(boardBG);
		
		bWyjscie = new JButton("Wyjœcie");
		bWyjscie.setBounds(220, 380, 130, 40);
		bWyjscie.setFont(new Font("SansSerif", Font.BOLD, 15));
		add(bWyjscie);
		
		bWyjscie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				MainWindow mainWindow = new MainWindow();
				mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainWindow.setVisible(true);
				mainWindow.setLocationRelativeTo(null);
			}
		});
		
		
		
		setSize(400, 500);
		setLayout(null);
	}
}
