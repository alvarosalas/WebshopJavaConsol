package se.lajv.floggitWebshop.Interface;

public interface WebshopInterface {
	
	public void add(String username, String password);

	public void update(String username, String password);

	public void get(String username, String password);

	public void remove(String username, String password);
}
