package se.lajv.floggitWebshop.Service;



import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import se.lajv.floggitWebshop.Interface.WebshopInterface;

public class CustomerService implements WebshopInterface {

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
			System.out.println("First Name: ");
			String firstname = input.nextLine();
			System.out.println("Surname: ");
			String surname = input.nextLine();	
			System.out.println("Street address: ");
			String street_address = input.nextLine();
			System.out.println("Town: ");
			String town = input.nextLine();
			System.out.println("Email: ");
			String email = input.nextLine();
			System.out.println("Password: ");
			String password1 = input.nextLine();
			System.out.println("Postcode: ");
			int postcode = input.nextInt();
			System.out.println("Telephone number: ");
			int telephone = input.nextInt();
			preparedstatement = connection
					.prepareStatement("INSERT INTO Customer (firstname, surname, street_address, town, postcode, email_address, password, telephone_number) VALUES(?,?,?,?,?,?,?,?)");
			preparedstatement.setString(1, firstname);
			preparedstatement.setString(2, surname);
			preparedstatement.setString(3, street_address);
			preparedstatement.setString(4, town);
			preparedstatement.setInt(5, postcode);
			preparedstatement.setString(6, email);
			preparedstatement.setString(7, password1);
			preparedstatement.setInt(8, telephone);
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
			System.out.println("First Name: ");
			String firstname = input.nextLine();
			System.out.println("Surname: ");
			String surname = input.nextLine();	
			System.out.println("Street address: ");
			String street_address = input.nextLine();
			System.out.println("Town: ");
			String town = input.nextLine();
			System.out.println("Email: ");
			String email = input.nextLine();
			System.out.println("Password: ");
			String password1 = input.nextLine();
			System.out.println("Postcode: ");
			int postcode = input.nextInt();
			System.out.println("Telephone number: ");
			int telephone = input.nextInt();
			preparedstatement = connection
					.prepareStatement("UPDATE Customer SET firstname = ?,surname = ?,street_address = ?,town = ?,postcode = ?,email = ?,password = ?, telephone_number = ? WHERE id = ?");
			preparedstatement.setString(1, firstname);
			preparedstatement.setString(2, surname);
			preparedstatement.setString(3, street_address);
			preparedstatement.setString(4, town);
			preparedstatement.setInt(5, postcode);
			preparedstatement.setString(6, email);
			preparedstatement.setString(7, password1);
			preparedstatement.setInt(8, telephone);
			preparedstatement.setInt(9, id);
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
			resultset = stmt.executeQuery("SELECT * FROM Customer");
			while (resultset.next()) {
				int id = resultset.getInt("id");
				String firstname = resultset.getString("firstname");
				String surname = resultset.getString("surname");
				String street_address = resultset.getString("street_address");
				String town = resultset.getString("town");
				String postcode = resultset.getString("postcode");
				String email = resultset.getString("email_address");
				String password1 = resultset.getString("password");
				int telephone = resultset.getInt("telephone_number");
				System.out.println(id + "\t" + firstname + "\t" + surname + "\t" + street_address + "\t" + town + "\t" + postcode + "\t" + email + "\t" + password1 + "\t" + telephone);
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
					.prepareStatement("DELETE FROM Customer WHERE id = ?");
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
