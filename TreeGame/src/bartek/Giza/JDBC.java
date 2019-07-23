package bartek.Giza;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

	static String dataFromDatabase;
	
	static public int[][] wczytajPlansze(int level) throws SQLException {
		String URLConnection = "jdbc:mysql://localhost:3306/myfirstschema?user=newuser&password=asdQWE";
		String query = "Select * FROM myfirstschema.leveleasy";
		int length=0;
		if(level==1) {
			query = "Select * FROM myfirstschema.leveleasy";
			length=6;
		}
		else if(level==2) {
			query = "Select * FROM myfirstschema.levelmedium";
			length=10;
		}
		else if(level==3) {
			query = "Select * FROM myfirstschema.levelhard";
			length=15;
		}
		else {
			System.err.println("ERROR zla wartosc level'u");
			System.exit(0);
		}
        Connection conn = null;
       
        try {

                //Ustawiamy dane dotycz¹ce pod³¹czenia
                conn = DriverManager.getConnection(URLConnection);
               
                //Ustawiamy sterownik MySQL
                Class.forName("com.mysql.jdbc.Driver");
                
                //Uruchamiamy zapytanie do bazy danych
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

              
                int tab[][] = processingArray(rs,length);
                
                conn.close();
                return tab;
                
        }
        //Wyrzuæ wyj¹tki jê¿eli nast¹pi¹ b³êdy z pod³¹czeniem do bazy danych lub blêdy zapytania o dane
        catch(ClassNotFoundException wyjatek) {
                System.out.println("Problem ze sterownikiem");
        }

        catch(SQLException wyjatek) {
                //e.printStackTrace();
                System.out.println("Problem z logowaniem\nProsze sprawdzic:\n nazwê u¿ytkownika, has³o, nazwê bazy danych lub adres IP serwera");
                System.out.println("SQLException: " + wyjatek.getMessage());
            System.out.println("SQLState: " + wyjatek.getSQLState());
            System.out.println("VendorError: " + wyjatek.getErrorCode());
        }
        
        return null;
	}
	
	static int[][] processingArray(ResultSet rs, int len) throws SQLException{
		int [][] table = new int[len+1][len+1];
		int j=0;
		while (rs.next()) {	
			 
			for(int i=0;i<len;i++) {
				if(j<=len-1) {
					table[j+1][i+1]=rs.getInt(i+2);
				}
				else if(j==len){
					table[0][i+1]=rs.getInt(i+2);
				}
				else {
					table[i+1][0]=rs.getInt(i+2);
				}
			}
			j++;
		}
		table[0][0]=0;
		
		return table;
	}
	
	static public void wyswietlDaneZBazy(int[][] table,int length) {
		  
		for(int i=0;i<length+1;i++) {
			for(int j=0;j<length+1;j++) {
				System.out.print(table[i][j]+" ");
			}
			System.out.println();
		}
		
	}

}