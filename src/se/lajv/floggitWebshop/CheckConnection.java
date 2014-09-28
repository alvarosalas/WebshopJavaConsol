package se.lajv.floggitWebshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class CheckConnection {
	private boolean connection = false;
	public CheckConnection(String username, String password) {
		
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/floggit";
		final String USER = username;
		final String PASS = password;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		{
			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				System.out.println("Connected to database. As user "+ USER + "\n");
				connection = !conn.isClosed();
				conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Access denied, incorrect Username and/or Password");
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public boolean isConnected(){
		return connection;
	}
}
