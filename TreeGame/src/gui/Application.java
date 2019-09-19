package gui;

import java.sql.SQLException;

import javax.swing.JFrame;
public class Application {
	
//TO DO: generic void function to set default close operations etc..
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		MainWindow mainWindow = new MainWindow();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		mainWindow.setLocationRelativeTo(null);

	}
/*
 * MAIN:
 * Add 2 other levels
 * Correct connection to mysql database
 * 
 * SIDE:
 * bExit should be default
 * Simplify class Mechanics
 * Add some graphical details
 * 
 * PROS FROM MAKING THIS PROJECT:
 * - encapsulation
 * - awt gui
 * - jdbc connection with db
 * - generic functions
 * - a lot of fun :)
 */
}
