package testing;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		final ImageIcon icon = new ImageIcon ("cs-logo.gif");
		 final JButton ib = new JButton ("Press", icon);
		 ib.setVerticalTextPosition (JButton.CENTER);
		 ib.setHorizontalTextPosition (JButton.LEFT);
		 ib.setMnemonic('p');  // Alt-p will press button
		
		 final JFrame frame = new JFrame ("Image Button");
		 frame.getContentPane().add (ib);
		 frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); // Requires Java 1.3
		 frame.pack();
		 frame.setVisible (true);
	}

}
