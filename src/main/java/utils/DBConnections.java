package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pages.BasePage;

public class DBConnections extends BasePage {
	private Statement statement;
	private static ResultSet result;

	public Statement setConnection() {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://okmry52647dns.eastus.cloudapp.azure.com/bmf-abhishek-jun19-dev?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC ",
					"sdetuser1", "Welcome123$");
			st = con.createStatement();
			getReport("info", "DataBase connection established successfully");
		} catch (SQLException | ClassNotFoundException e) {
			getReport("fail", "DataBase connection failed with exception " + e);
		}

		return st;
	}

	public void getDataFromDataBase(String data) {
		if(statement==null) {
			statement=setConnection();
		}
		String output = null;
		try {
			if(getData("query").equals(getData("query1")))
				result = statement.executeQuery(getData("query"));
			while (result.next()) {
				output = result.getString(data);
			}
			getReport("info", "Data fetched from " + getData("query") + " and result for " + data + " is " + output);
			result.first();
		} catch (SQLException e) {
			getReport("fail", "Data fetching failed with exception " + e);
		}
		setData("query1", "");
		setData("dbOutput", output);
	}
	
	public void queryBuilder(String statements,String data) {
		String query=statements+"'"+getData(data)+"'";
		setData("query", query);
		setData("query1", query);
	}
}
