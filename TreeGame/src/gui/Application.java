package gui;

import java.sql.SQLException;

import javax.swing.JFrame;
public class Application {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		MainWindow mainWindow = new MainWindow();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		mainWindow.setLocationRelativeTo(null);

	}
/*
 * MAIN:
 * add functionality to correct result times in db
 * Add 2 other levels
 * Correct connection to mysql database
 * 
 * SIDE:
 * strange stuff are happening in ResultWindow class
 * bExit should be public 
 * Simplify class Mechanics
 * Change class Mechanics to inner or local or anonymous class (we'll see about that)
 * Upgrade graphically end game window and save last time per level
 * Add some graphical details
 */
}
