package se.lajv.floggitWebshop;


public class Menus {

	public static void MainLogin() {
		System.out.println("Hello!");
		System.out.println("1. Login");
		System.out.println("2. Create new costumer");
	}

	public static void AdminMainMenu() {
		System.out.println("\nMenu");
		System.out.println("1. Products");
		System.out.println("2. Departments");
		System.out.println("3. Staff");
		System.out.println("4. Catagory");
		System.out.println("5. Customer");
		System.out.println("6. Orders");
		System.out.println("type !q to quit");
	}

	public static void AdminChoiceMenu() {
		System.out.println("\n Menu");
		System.out.println("1. Add");
		System.out.println("2. Update");
		System.out.println("3. Get all");
		System.out.println("4. Remove");
		System.out.println("5. Go back");
	}

	public static void AdminOrderMenu() {
		System.out.println("\n Menu");
		System.out.println("1. Get all");
		System.out.println("2. Remove");
		System.out.println("5. Go back");
	}

	public static void CustomerMainMenu() {
		System.out.println("\nMenu");
		System.out.println("1. Product");
		System.out.println("2. Cart");
		System.out.println("type !q to quit");
	}

	public static void CustomerProductMenu() {
		System.out.println("\nMenu");
		System.out.println("1. Add");
		System.out.println("2. Go Back");
	}

	public static void CustomerCartMenu() {
		System.out.println("\nMenu");
		System.out.println("1. Get all");
		System.out.println("2. Remove");
		System.out.println("3. Place Order");
		System.out.println("4. Go Back");
	}

	public static void QuitChoice() {
		System.out.println("Bye bye!");
		System.exit(1);
	}

}
