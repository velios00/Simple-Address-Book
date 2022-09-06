package Modello;

import java.sql.SQLException;
import java.util.ArrayList;

import Controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * Indica uno degli indirizzi di posta elettronica associati al contatto.
 */
public class Email {

	/** stringa contenente l’indirizzo di posta elettronica. */
	String email;
	
	/** indica se è l’e-mail principale del contatto. */
	boolean main;
	
	/** lista degli account di messaging collegati alla e-mail. */
	ArrayList<MessagingAccount> messaging;
	
	/**
	 * Instantiates a new email.
	 *
	 * @param controller the controller
	 * @param newEmail the new email
	 * @param principale the principale
	 */
	public Email(Controller controller, String newEmail, String principale){
		
		email = newEmail;
		main = (principale.equals("t")  || principale.equals("true"));
		messaging = new ArrayList<MessagingAccount>();
		try {
			messaging = controller.caricaMessagingEmail(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the string.
	 *
	 * @return the string
	 */
	public String getString() {
		return email;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Main.
	 *
	 * @return true, if successful
	 */
	public boolean main() {
		return main;
	}
	
	public void setMain(boolean b) {
		main = b;
	}
	/**
	 * Gets the messaging.
	 *
	 * @return the messaging
	 */
	public ArrayList<MessagingAccount> getMessaging() {
		return messaging;
	}
}


