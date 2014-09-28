package se.lajv.floggitWebshop;


import java.util.Scanner;

import se.lajv.floggitWebshop.Service.CartService;
import se.lajv.floggitWebshop.Service.CategoryService;
import se.lajv.floggitWebshop.Service.CustomerService;
import se.lajv.floggitWebshop.Service.DepartmentService;
import se.lajv.floggitWebshop.Service.OrderService;
import se.lajv.floggitWebshop.Service.ProductService;
import se.lajv.floggitWebshop.Service.StaffService;

public class Console {

	DepartmentService dept = new DepartmentService();
	ProductService prod = new ProductService();
	CartService cart = new CartService();
	StaffService staff = new StaffService();
	CategoryService category = new CategoryService();
	OrderService order = new OrderService();
	CustomerService customer = new CustomerService();
	Scanner input = new Scanner(System.in);
	String username = null;
	String password = null;
	String email = null;

	public void ConsoleAdminProd(String username, String password) {

		Menus.AdminChoiceMenu();
		int prodMenuChoice = input.nextInt();
		input.nextLine();
		switch (prodMenuChoice) {
		case 1:
			prod.add(username, password);
			break;
		case 2:
			prod.update(username, password);
			break;
		case 3:
			prod.get(username, password);
			break;
		case 4:
			prod.remove(username, password);
			break;
		case 5:
			break;

		}

	}

	public void ConsoleAdminDept(String username, String password) {
		
		Menus.AdminChoiceMenu();
		int deptMenuChoice = input.nextInt();
		input.nextLine();
		switch (deptMenuChoice) {
		case 1:
			dept.add(username, password);
			break;
		case 2:
			dept.update(username, password);
			break;
		case 3:
			dept.get(username, password);
			break;
		case 4:
			dept.remove(username, password);
			break;
		case 5:
			break;
		}
		
	}

	public void ConsoleAdminStaff(String username, String password) {
		
		Menus.AdminChoiceMenu();
		int staffMenuChoice = input.nextInt();
		input.nextLine();
		switch (staffMenuChoice) {
		case 1:
			staff.add(username, password);
			break;
		case 2:
			staff.update(username, password);
			break;
		case 3:
			staff.get(username, password);
			break;
		case 4:
			staff.remove(username, password);
			break;
		case 5:
			break;
		}
		
	}

	public void ConsoleAdminCate(String username, String password) {
		
		Menus.AdminChoiceMenu();
		int categoryMenuChoice = input.nextInt();
		input.nextLine();
		switch (categoryMenuChoice) {
		case 1:
			category.add(username, password);
			break;
		case 2:
			category.update(username, password);
			break;
		case 3:
			category.get(username, password);
			break;
		case 4:
			category.remove(username, password);
			break;
		case 5:
			break;
		}
		
	}

	public void ConsoleAdminCust(String username, String password) {
		
		Menus.AdminChoiceMenu();
		int costumerMenuChoice = input.nextInt();
		input.nextLine();
		switch (costumerMenuChoice) {
		case 1:
			customer.add(username, password);
			break;
		case 2:
			customer.update(username, password);
			break;
		case 3:
			customer.get(username, password);
			break;
		case 4:
			customer.remove(username, password);
			break;
		case 5:
			break;
		}
		
	}

	public void ConsoleCustomerProd(String username, String password, String email) {
		
		username = "userdummy";
		password = "1234";
	
		prod.get(username, password);
		Menus.CustomerProductMenu();
		int AddProdChoice = input.nextInt();
		input.nextLine();
		switch (AddProdChoice) {
		case 1:
			prod.addToCart(username, password, email);
			break;
		case 2:
			break;
		}
		
	}

	public void ConsoleCustomerCart(String username, String password, String email) {
		
		Menus.CustomerCartMenu();
		int cartChoice = input.nextInt();
		input.nextLine();
		switch (cartChoice) {
		case 1:
			cart.getAllCustomer(username, password, email);
			break;
		case 2:
			cart.remove(username, password, email);
			break;
		case 3:
			order.addToOrder(username, password, email);
			break;
		case 4:
			break;
		}
		
	}
}
