package Modello;

import java.sql.SQLException;
import java.util.ArrayList;

import Controller.Controller;

public class Email {

	String email;
	boolean main;
	ArrayList<MessagingAccount> messaging;
	
	public Email(Controller controller, String newEmail, String principale){
		
		email = newEmail;
		main = principale.equals("t");
		messaging = new ArrayList<MessagingAccount>();
		try {
			messaging = controller.caricaMessagingEmail(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getString() {
		return email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean main() {
		return main;
	}
}


