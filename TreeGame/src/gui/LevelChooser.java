package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelChooser extends JFrame implements ActionListener{
	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = -4847262403549783480L;

	JButton bPoziomLatwy, bPoziomSredni, bPoziomTrudny, bWyjscie;
	JButton[] tabButton;
	JLabel menu;
	public LevelChooser() {
		super("Choose level");
		
		setSize(400, 500);
		setLayout(null);
		
		
		JPanel board = new JPanel();
		board.setBounds(70, 70, 200, 400);
		board.setBackground(new Color(40,40,40));
		
		//add(board);
		board.setLayout(new GridLayout(10,1));
		
		JButton[] tabButton = new JButton[10];
		
		for(int i=0;i<=9;i++) {
			tabButton[i] = new JButton(Integer.toString(i+1));
			board.add(tabButton[i]);
			tabButton[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					
					for(int i=0;i<10;i++) {
						if(event.getSource() == tabButton[i]) {
							dispose();
							LevelEasy levelEasy;
							try {
								levelEasy = new LevelEasy(i);//HERE WE PASS NUMBER VARIABLE
								levelEasy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								levelEasy.setVisible(true);
								levelEasy.setLocationRelativeTo(null);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					
					
					
				}
				
			});
		}
		
		
		
		ScrollPane scroll = new ScrollPane();
		scroll.setWheelScrollingEnabled(true);
		scroll.setBounds(50, 50, 300, 150);
		scroll.add(board);
		add(scroll);
		
		
		bWyjscie = new JButton("Wyjœcie");
		bWyjscie.setBounds(220, 380, 130, 40);
		bWyjscie.setFont(new Font("SansSerif", Font.BOLD, 15));
		add(bWyjscie);
		bWyjscie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if(event.getSource() == bWyjscie) {
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
					return;
					
				}
				
			}
			
		});
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
