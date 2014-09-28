package se.lajv.floggitWebshop.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import se.lajv.floggitWebshop.Interface.WebshopInterface;

public class CategoryService implements WebshopInterface {

	Scanner input = new Scanner(System.in);
	Connection connection = null;
	PreparedStatement preparedstatement = null;
	ResultSet resultset = null;
	Statement stmt = null;

	@Override
	public void add(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/floggit", username, password);
			System.out.println("Category: ");
			String category = input.nextLine();
			System.out.println("Staff responsible id: ");
			int staffId = input.nextInt();
			preparedstatement = connection
					.prepareStatement("INSERT INTO Category (category, staff_responsible) VALUES(?,?)");
			preparedstatement.setString(1, category);
			preparedstatement.setInt(2, staffId);
			preparedstatement.executeUpdate();
			System.out.println("Insert successful!\n");
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
	public void update(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/floggit", username, password);
			System.out.println("id: ");
			int id = input.nextInt();
			input.nextLine();
			System.out.println("Category: ");
			String category = input.nextLine();
			System.out.println("Staff responsible id: ");
			int staffId = input.nextInt();
			preparedstatement = connection
					.prepareStatement("UPDATE Category SET category = ?,staff_responsible = ? WHERE id = ?");
			preparedstatement.setString(1, category);
			preparedstatement.setInt(2, staffId);
			preparedstatement.setInt(3, id);
			preparedstatement.executeUpdate();
			System.out.println("Update successful!\n");
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
	public void get(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/floggit", username, password);
			stmt = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			resultset = stmt.executeQuery("SELECT * FROM Category");
			while (resultset.next()) {
				int id = resultset.getInt("id");
				String category = resultset.getString("category");
				int staff_responsible = resultset.getInt("staff_responsible");
				System.out.println(id + "\t" + category + "\t"
						+ staff_responsible);
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

	@Override
	public void remove(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/floggit", username, password);
			System.out.println("id: ");
			int id = input.nextInt();
			input.nextLine();
			preparedstatement = connection
					.prepareStatement("DELETE FROM Category WHERE id = ?");
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

}
