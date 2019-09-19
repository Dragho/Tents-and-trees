package logicEngine;

import java.awt.Color;

import javax.swing.JLabel;

import bartek.Giza.JDBC;

public class Mechanics {
	private static boolean treeNeighbor(int[][] table, int x, int y) {
		if(x+1<=6 && table[x+1][y]==2) {
			return false;
		}
		else if(y+1<=6 && table[x][y+1]==2) {
			return false;
		}
		else if(x+1<=6 && y+1<=6 && table[x+1][y+1]==2) {
			return false;
		}
		else if(x-1>=1 && table[x-1][y]==2) {
			return false;
		}
		else if(y-1>=1 && table[x][y-1]==2) {
			return false;
		}
		else if(x-1>=1 && y-1>=1 && table[x-1][y-1]==2) {
			return false;
		}
		else if(x+1<=6 && y-1>=1 && table[x+1][y-1]==2) {
			return false;
		}
		else if(x-1>=1 && y+1<=6 && table[x-1][y+1]==2) {
			return false;
		}
		return true;
		
	}
	
	public static int checkGameMechanics(int[][] table,JLabel[] topLabels, JLabel[] leftLabels, JLabel information, int x, int y, int length) {
		//System.out.println("Here we do some logics");
		JDBC.wyswietlDaneZBazy(table, 6);
		boolean error=false;
		boolean neighborError=false;
		
		//sprawdzanie gornych wartosci    [wiersz][kolumna]
		for(int i=1,suma=0;i<length+1;i++) {
			if(table[i][y]==2) {
				suma++;
			}
			if(table[0][y]>=suma) {
				topLabels[y-1].setForeground(Color.BLACK);
				error=false;
			}
		}
		
		for(int i=1,suma=0;i<length+1;i++) {
			if(table[i][y]==2) {
				suma++;
				System.out.println("To sie wykonuje");
			}
			if(table[0][y]<suma) {
				topLabels[y-1].setForeground(Color.RED);
				error=true;
			}
		}
		
		//sprawdzanie lewych wartosci
		for(int i=1,suma=0;i<length+1;i++) {
			if(table[x][i]==2)suma++;
			
			if(table[x][0]>=suma) {
				leftLabels[x-1].setForeground(Color.BLACK);
				error=false;
			}
		}
		
		for(int i=1,suma=0;i<length+1;i++) {
			if(table[x][i]==2)suma++;
			
			if(table[x][0]<suma) {
				leftLabels[x-1].setForeground(Color.RED);
				error=true;
			}
		}
		
		//sprawdzanie czy nie sasiaduja ze soba jakies namioty
		if(table[x][y]==2) {
			if(x+1<=6 && table[x+1][y]==2) {
				neighborError=true;
				information.setText("Two tents can't be near each other");
				information.setForeground(Color.RED);
			}
			else if(y+1<=6 && table[x][y+1]==2) {
				neighborError=true;
				information.setText("Two tents can't be near each other");
				information.setForeground(Color.RED);
			}
			else if(x+1<=6 && y+1<=6 && table[x+1][y+1]==2) {
				neighborError=true;
				information.setText("Two tents can't be near each other");
				information.setForeground(Color.RED);
			}
			else if(x-1>=1 && table[x-1][y]==2) {
				neighborError=true;
				information.setText("Two tents can't be near each other");
				information.setForeground(Color.RED);
			}
			else if(y-1>=1 && table[x][y-1]==2) {
				neighborError=true;
				information.setText("Two tents can't be near each other");
				information.setForeground(Color.RED);
			}
			else if(x-1>=1 && y-1>=1 && table[x-1][y-1]==2) {
				neighborError=true;
				information.setText("Two tents can't be near each other");
				information.setForeground(Color.RED);
			}
			else if(x+1<=6 && y-1>=1 && table[x+1][y-1]==2) {
				neighborError=true;
				information.setText("Two tents can't be near each other");
				information.setForeground(Color.RED);
			}
			else if(x-1>=1 && y+1<=6 && table[x-1][y+1]==2) {
				neighborError=true;
				information.setText("Two tents can't be near each other");
				information.setForeground(Color.RED);
			}
			else {
				neighborError=false;
				
			}
			
		}
		if(neighborError==false) {
			information.setText("Here you can see important errors...");
			information.setForeground(Color.BLACK);
		}
		//sprawdzanie czy liczba namiotow w wierszach i kolumnach sie zgadza
		
		
		
		//sprawdzanie czy wszystkie pola nie sa puste
		if(error==false && neighborError==false) {
			for(int i=1;i<7;i++) {
				for(int j=1,suma=0;j<7;j++) {
					if(table[i][j]==0)return 0;
				}
			}
		
			for(int i=1;i<7;i++) {
				int suma=0;
				for(int j=1;j<7;j++) {
					if(table[i][j]==2)suma++;
					else if(table[i][j]==4)
						if(!treeNeighbor(table,i,j)) {
							information.setText("At least one of the trees is not connected to any tent!");
							information.setForeground(Color.RED);
						}
				}
				if(table[i][0]!=suma) {
					information.setText("Number of tents in rows isn't correct!");
					information.setForeground(Color.RED);
					return 0;
				}
			}
			for(int i=1;i<7;i++) {
				int suma=0;
				for(int j=1;j<7;j++) {
					if(table[j][i]==2)suma++;
				}
				if(table[0][i]!=suma) {
					information.setText("Number of tents in columns isn't correct!");
					information.setForeground(Color.RED);
					return 0;
				}
			}
			
			System.out.println("VICTORY!");
			return 1;
		}
		return 0;
	}
}
