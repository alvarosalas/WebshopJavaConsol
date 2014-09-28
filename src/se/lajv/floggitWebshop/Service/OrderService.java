package se.lajv.floggitWebshop.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import se.lajv.floggitWebshop.Interface.OrderInterface;

public class OrderService implements OrderInterface {

	Scanner input = new Scanner(System.in);
	Connection connection = null;
	PreparedStatement preparedstatement = null;
	ResultSet resultset = null;
	Statement stmt = null;

	public void addToOrder(String username, String password, String email) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/floggit", "userdummy", "1234");
			String query = "SELECT id, email_address FROM Customer WHERE email_address = '"
					+ email + "'";
			Statement s = connection
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();
			int costumerId = 0;
			while (rs.next()) {
				int idVal = rs.getInt("id");
				costumerId = idVal;
			}
			preparedstatement = connection
					.prepareStatement("insert into Orders (customer_id, product_id, quantity) select customer_id, product_id, quantity from cart WHERE customer_id = ?");
			preparedstatement.setInt(1, costumerId);
			preparedstatement.executeUpdate();
			System.out.println("Insert successful!\n");
			preparedstatement = connection.prepareStatement("delete from cart where customer_id = ?");
			preparedstatement.setInt(1, costumerId);
			preparedstatement.executeUpdate();
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
	
	
	
	public void removeOrder(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/floggit", "tom", "tom");
			System.out.println("Customer id: ");
			int id = input.nextInt();
			input.nextLine();
			preparedstatement = connection
					.prepareStatement("DELETE FROM Orders WHERE customer_id = ?");
			preparedstatement.setInt(1, id);
			preparedstatement.executeUpdate();
			System.out.println("Delete successful!\n");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("Access denied, incorrect Username and/or Passowrd");
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
	public void getOrder(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/floggit", "tom", "tom");
			stmt = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			resultset = stmt.executeQuery("SELECT * FROM Orders");
			while (resultset.next()) {
				int costumer = resultset.getInt("customer_id");
				int product = resultset.getInt("product_id");
				int quantity = resultset.getInt("quantity");
				System.out.println("Customer Id: " + costumer + "\t" + "Product Id: " + product + "\t"
						+ "Quantity: " + quantity);
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

}
