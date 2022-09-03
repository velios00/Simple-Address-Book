package Modello;

import java.sql.SQLException;
import java.util.ArrayList;

import Controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * Rappresenta l'utente a cui apprtiene una rubrica
 */
public class Utente {
	
	/** Stringa contenente l 'e-mail (valida) del proprietario della rubrica. */
	public String email;
	
	/** Nome identificativo del proprietario della rubrica. Deve essere almeno di 3 caratteri. */
	public String nickname;
	
	/** Password di accesso alla rubrica. Deve essere di almeno 8 caratteri.*/
	public String password;
	
	/** Note o memo scritte dall置tente. */
	String note;
	
	/** Lista dei contatti gestiti dall置tente. */
	public ArrayList<Contatto> contatti;
	
	/** Lista dei gruppi creati dall置tente. */
	ArrayList<Gruppo> gruppi;
	
	/** Lista delle chiamate in entrata, in uscita o perse registrate per l置tente. */
	public ArrayList<PhoneCall> calls;
	
	
	/**
	 * Instantiates a new utente.
	 *
	 * @param controller the controller
	 * @param newEmail the new email
	 * @param newPassword the new password
	 * @param newNickname the new nickname
	 */
	public Utente(Controller controller, String newEmail, String newPassword, String newNickname){
		
		email = newEmail;
		password = newPassword;
		nickname = newNickname;
		note = "";
		try {
			contatti = controller.caricaContatti(email);
			gruppi = controller.caricaGruppiUtente(this, email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public String getNotes() {
		return note;
	}
	
	/**
	 * Gets the gruppi.
	 *
	 * @return the gruppi
	 */
	public ArrayList<Gruppo> getGruppi() {
		return gruppi;
	}
	
	/**
	 * Gets the calls.
	 *
	 * @return the calls
	 */
	public ArrayList<PhoneCall> getCalls(){
		return calls;
	}
}