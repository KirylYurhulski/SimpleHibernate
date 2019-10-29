package ru.hibernatetutorial.SimpleHibernate;

/**
 * Main Class
 *
 */
public class App {
	public static void main(String[] args) {
		
		User user = new User();
		user.setName("Test User 3");	
		System.out.println("Add user with ID = " + new ManageUser(user).save());
	}
}
