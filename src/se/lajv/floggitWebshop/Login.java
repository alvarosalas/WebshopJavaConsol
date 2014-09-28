package se.lajv.floggitWebshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
	Scanner input = new Scanner(System.in);
	Connection connection = null;
	PreparedStatement preparedstatement = null;
	ResultSet resultset = null;
	Statement stmt = null;
	String emailVal = null;
	String passVal = null;
	public void UserLogin(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/floggit", "userdummy", "1234");
			stmt = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			resultset = stmt
					.executeQuery("SELECT email_address, password FROM Customer WHERE email_address = '"
							+ username + "' and password = '" + password + "'");
			while (resultset.next()) {
				String emailVal = resultset.getString("email_address");
				String passVal = resultset.getString("password");
				this.emailVal = emailVal;
				this.passVal = passVal;
			}
			if (emailVal.isEmpty()) {
			}
			if (passVal.isEmpty()) {
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null) {
					resultset.close();
				}
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
