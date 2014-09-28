package se.lajv.floggitWebshop.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import se.lajv.floggitWebshop.Interface.CartInterface;

public class CartService implements CartInterface {

	Scanner input = new Scanner(System.in);
	Connection connection = null;
	PreparedStatement preparedstatement = null;
	ResultSet resultset = null;
	Statement stmt = null;
/*
	@Override
	public void get(String username, String password, String email) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/floggit", username, password);
			String query = "SELECT id, email_address FROM Customer WHERE email_address = '" + email + "'";
			Statement s = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();
			int customerId = 0;
			while (rs.next()) {
				int idVal = rs.getInt("id");
				customerId = idVal;
			}
			String query2 = "SELECT customer_id, product_id, quantity FROM Cart WHERE customer_id = '" + customerId + "' group by product_id";
			Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeQuery(query2);
			ResultSet resultset = stmt.getResultSet();
			while(resultset.next()){
				String productId = resultset.getString("product_id");
				String quantity = resultset.getString("quantity");
				System.out.println("You have product id: " + productId + " Quantity: " + quantity);
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

	}*/
	public void getAllCustomer(String username, String password, String email) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/floggit", username, password);
			stmt = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			resultset = stmt.executeQuery("SELECT * FROM Products");
			while (resultset.next()) {
				int id = resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				String rrp = resultset.getString("rrp");
				System.out.println(id + "\t" + name + "\t" + description + "\t" + rrp);
			}
			System.out.println("Here is all your products");
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
	
	@Override
	public void remove(String username, String password, String email) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/floggit", username, password);
			System.out.println("Choose what product to remove, id: ");
			int productId = input.nextInt();
			input.nextLine();
			preparedstatement = connection
					.prepareStatement("DELETE FROM Cart WHERE product_id = ?");
			preparedstatement.setInt(1, productId);
			preparedstatement.executeUpdate();
			System.out.println("Delete successful!\n");

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
