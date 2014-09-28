package se.lajv.floggitWebshop.Interface;

public interface OrderInterface {
	public void addToOrder(String username, String password, String email);
	
	public void removeOrder(String username, String password);
	
	public void getOrder(String username, String password);

}
