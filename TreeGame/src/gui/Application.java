package gui;

import javax.swing.JFrame;
public class Application {

	public static void main(String[] args) {
		
		Window window = new Window();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setLocationRelativeTo(null);

	}
/*
 * 1)English naming
 * 2)Simplify class Mechanics
 * 3)Change class Mechanics to inner or local or anonymous class (we'll see about that)
 * 4)Expand database and add randomness to levels/add another window with levels to choose(1 database each type levels)
 * 5)Add 2 other levels
 * 6)Upgrade graphically end game window and save last time per level 
 * 7)Add database with clock history
 * 8)Correct connection to mysql database
 * 9)Add some graphical details
 * .
 * .
 * .
 */
}
