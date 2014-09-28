
package se.lajv.floggitWebshop;

import java.sql.SQLException;
import java.util.Scanner;
import se.lajv.floggitWebshop.Service.CustomerService;
import se.lajv.floggitWebshop.Service.OrderService;

public class Main {
	public static void main(String[] args) throws SQLException {
		System.out.println("Press 'Enter' to start!");
		Login login = new Login();
		OrderService order = new OrderService();
		Console console = new Console();
		CustomerService customer = new CustomerService();
		Menus.MainLogin();
		String username = null;
		String password = null;
		String email = null;
		Scanner input = new Scanner(System.in);
		String LoginChoice = input.nextLine();
		if (LoginChoice.equals("1")) {
			System.out.println("Please type your username (Email):");
			username = input.nextLine();
			email = username;
			System.out.println("\nPlease type your password:");
			password = input.nextLine();
		}
		if (LoginChoice.equals("2")) {
			customer.add("userdummy", "1234");
			System.out.println("Please type your email to login:");
			username = input.nextLine();
			email = username;
			System.out.println("\nPlease type your password:");
			password = input.nextLine();
		}
		if (username.equals("tom")) {
			CheckConnection c = new CheckConnection(username, password);
			if (c.isConnected()) {
				for (;;) {
					Menus.AdminMainMenu();
					String MenuChoice = input.nextLine();
					if (MenuChoice.equals("1")) {
						console.ConsoleAdminProd(username, password);
					}
					if (MenuChoice.equals("2")) {
						console.ConsoleAdminDept(username, password);
					}

					if (MenuChoice.equals("3")) {
						console.ConsoleAdminStaff(username, password);
					}

					if (MenuChoice.equals("4")) {
						console.ConsoleAdminCate(username, password);
					}

					if (MenuChoice.equals("5")) {
						console.ConsoleAdminCust(username, password);
					}

					if (MenuChoice.equals("6")) {
						Menus.AdminOrderMenu();
						int costumerMenuChoice = input.nextInt();
						input.nextLine();
						switch (costumerMenuChoice) {
						case 1:
							order.getOrder(username, password);
							break;
						case 2:
							order.removeOrder(username, password);
							break;
						case 3:
							break;
						}
					}

					if (MenuChoice.equals("!q")) {
						System.out.println("Bye bye!");
						System.exit(1);
					}
				}
			}
		} else {
			login.UserLogin(username, password);
			username = "userdummy";
			password = "1234";
			for (;;) {
				Menus.CustomerMainMenu();
				String MenuChoice = input.nextLine();
				if (MenuChoice.equals("1")) {
					console.ConsoleCustomerProd(username, password, email);
				}
				if (MenuChoice.equals("2")) {
					console.ConsoleCustomerCart(username, password, email);
				}
				if (MenuChoice.equals("!q")) {
					System.out.println("Bye bye!");
					System.exit(1);
				}
			}
		}
		input.close();
	}
}
