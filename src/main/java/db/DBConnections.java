package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnections {
	public Connection con=null;
	
	public void createConnection(String dbUrl,String dbUsername,String dbPassWord) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(dbUrl,dbUsername,dbPassWord);  
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
