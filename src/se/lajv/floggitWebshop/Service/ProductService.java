package se.lajv.floggitWebshop.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import se.lajv.floggitWebshop.Interface.ProductInterface;

public class ProductService implements ProductInterface {
	Scanner input = new Scanner(System.in);
	Connection connection = null;
	PreparedStatement preparedstatement = null;
	ResultSet resultset = null;
	Statement stmt = null;

	@Override
	public void addToCart(String username, String password, String email) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/floggit", username, password);
			System.out.println("Id: ");
			int productId = input.nextInt();
			input.nextLine();
			System.out.println("Quantity:");
			int quantityVal = input.nextInt();
			input.nextLine();
			String getCustomerId = "SELECT id, email_address FROM Customer WHERE email_address = '" + email + "'";
			Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt1.executeQuery(getCustomerId);
			ResultSet rs = stmt1.getResultSet();
			int customerId = 0;
			while (rs.next()) {
				int idVal = rs.getInt("id");
				customerId = idVal;
			}
			String ifProductExsist = "SELECT product_id FROM Cart WHERE customer_id = '"+ customerId +"' and product_id = '" + productId + "'";
			Statement stmt2 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt2.executeQuery(ifProductExsist);
			ResultSet rs3 = stmt2.getResultSet();
			int productExistId = 0;
			while(rs3.next()){
				int productvalue = rs3.getInt("product_id");
				productExistId = productvalue;
			}
			if(productExistId != 0){
				String CheckCurrentQuantity = "SELECT quantity FROM Cart WHERE product_id = " + productId + " and customer_id = " + customerId;
				Statement stmt3 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt3.executeQuery(CheckCurrentQuantity);
				ResultSet rs2 = stmt3.getResultSet();
				int quantityInCart = 0;
				while (rs2.next()) {
					int quantityAlreadyInCartVal = rs2.getInt("quantity");
					quantityInCart = quantityAlreadyInCartVal;
				}
				quantityVal += quantityInCart;
				String updateQuantity = "UPDATE Cart SET quantity = '" + quantityVal + "' WHERE product_id = " + productId + " and customer_id = " + customerId;
				Statement stmt4 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt4.executeUpdate(updateQuantity);
			}else{
				preparedstatement = connection.prepareStatement("INSERT INTO Cart (customer_id, product_id, quantity) VALUES(?,?,?)");
				preparedstatement.setInt(1, customerId);
				preparedstatement.setInt(2, productId);
				preparedstatement.setInt(3, quantityVal);
				preparedstatement.executeUpdate();
			}
			System.out.println("Insert successful! new quantity value in cart is " + quantityVal + "\n");
			
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
	public void add(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/floggit", username, password);
			System.out.println("Product Name: ");
			String productName = input.nextLine();
			System.out.println("Description: ");
			String description = input.nextLine();
			System.out.println("Cost: ");
			int cost = input.nextInt();
			System.out.println("RRP: ");
			int rrp = input.nextInt();
			System.out.println("Category Id: ");
			int categoryId = input.nextInt();
			preparedstatement = connection
					.prepareStatement("INSERT INTO products (name,description,cost,rrp) VALUES(?,?,?,?)");
			preparedstatement.setString(1, productName);
			preparedstatement.setString(2, description);
			preparedstatement.setInt(3, cost);
			preparedstatement.setInt(4, rrp);
			preparedstatement.executeUpdate();
			System.out.println("Insert successful!\n");
			String query = "SELECT id FROM Products WHERE name = '" + productName + "'";
			Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeQuery(query);
			ResultSet rs = stmt.getResultSet();
			int productIdVal = 0;
			while(rs.next()){
				int prodId = rs.getInt("id");
				productIdVal = prodId;
			}
			System.out.println(productIdVal);
			preparedstatement = connection.prepareStatement("INSERT INTO productcategory (product_id, category_id) VALUES(?,?)");
			preparedstatement.setInt(1, productIdVal);
			preparedstatement.setInt(2, categoryId);
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

	@Override
	public void update(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/floggit", username, password);
			System.out.println("id: ");
			int id = input.nextInt();
			input.nextLine();
			System.out.println("Product Name: ");
			String productName = input.nextLine();
			System.out.println("Description: ");
			String description = input.nextLine();
			System.out.println("Cost: ");
			int cost = input.nextInt();
			System.out.println("RRP: ");
			int rrp = input.nextInt();
			preparedstatement = connection
					.prepareStatement("UPDATE Products SET name = ?,description = ?,cost = ?,rrp = ? WHERE id = ?");
			preparedstatement.setString(1, productName);
			preparedstatement.setString(2, description);
			preparedstatement.setInt(3, cost);
			preparedstatement.setInt(4, rrp);
			preparedstatement.setInt(5, id);
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
			resultset = stmt.executeQuery("SELECT * FROM Products");
			while (resultset.next()) {
				int id = resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				String cost = resultset.getString("cost");
				String rrp = resultset.getString("rrp");
				System.out.println(id + "\t" + name + "\t" + description + "\t"
						+ cost + "\t" + rrp);
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
	public void remove(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/floggit", "tom", "tom");
			System.out.println("id: ");
			int id = input.nextInt();
			input.nextLine();
			preparedstatement = connection
					.prepareStatement("DELETE FROM productcategory WHERE product_id = ?");
			preparedstatement.setInt(1, id);
			preparedstatement.executeUpdate();
			preparedstatement = connection
					.prepareStatement("DELETE FROM products WHERE id = ?");
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
