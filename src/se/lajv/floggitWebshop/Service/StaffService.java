package se.lajv.floggitWebshop.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import se.lajv.floggitWebshop.Interface.WebshopInterface;

public class StaffService implements WebshopInterface{

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
			System.out.println("Date of Birth: ");
			int dob = input.nextInt();	
			System.out.println("Postcode: ");
			int postcode = input.nextInt();
			System.out.println("Mobile: ");
			int mobile = input.nextInt();
			System.out.println("Salary: ");
			int salary = input.nextInt();
			preparedstatement = connection
					.prepareStatement("INSERT INTO Staff (firstname, surname, dob, street_address, town, postcode, mobile, email, salary) VALUES(?,?,?,?,?,?,?,?,?)");
			preparedstatement.setString(1, firstname);
			preparedstatement.setString(2, surname);
			preparedstatement.setInt(3, dob);
			preparedstatement.setString(4, street_address);
			preparedstatement.setString(5, town);
			preparedstatement.setInt(6, postcode);
			preparedstatement.setInt(7, mobile);
			preparedstatement.setString(8, email);
			preparedstatement.setInt(9, salary);
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
			System.out.println("Date of birth: ");
			int dob = input.nextInt();
			System.out.println("Mobile: ");
			int mobile = input.nextInt();
			System.out.println("Postcode: ");
			int postcode = input.nextInt();
			System.out.println("Salary: ");
			int salary = input.nextInt();
			preparedstatement = connection
					.prepareStatement("UPDATE Products SET firstname = ?,surname = ?,dob = ?,street_address = ?,town = ?,postcode = ?,mobile = ?,email = ?,salary = ? WHERE id = ?");
			preparedstatement.setString(1, firstname);
			preparedstatement.setString(2, surname);
			preparedstatement.setInt(3, dob);
			preparedstatement.setString(4, street_address);
			preparedstatement.setString(5, town);
			preparedstatement.setInt(6, postcode);
			preparedstatement.setInt(7, mobile);
			preparedstatement.setString(8, email);
			preparedstatement.setInt(9, salary);
			preparedstatement.setInt(10, id);
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
			resultset = stmt.executeQuery("SELECT * FROM Staff");
			while (resultset.next()) {
				int id = resultset.getInt("id");
				String firstname = resultset.getString("firstname");
				String surname = resultset.getString("surname");
				int dob = resultset.getInt("dob");
				String street_address = resultset.getString("street_address");
				String town = resultset.getString("town");
				String postcode = resultset.getString("postcode");
				String mobile = resultset.getString("mobile");
				String email = resultset.getString("email");
				int salary = resultset.getInt("salary");
				System.out.println(id + "\t" + firstname + "\t" + surname + "\t" + dob + "\t" + street_address + "\t" + town + "\t" + postcode + "\t" + mobile + "\t" + email + "\t" + salary);
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
					.prepareStatement("DELETE FROM Staff WHERE id = ?");
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
