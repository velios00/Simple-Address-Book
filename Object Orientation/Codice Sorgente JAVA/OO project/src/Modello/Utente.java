package Modello;

import java.sql.SQLException;
import java.util.ArrayList;

import Controller.Controller;

public class Utente {
	
	public String email;
	public String nickname;
	public String password;
	String note;
	public ArrayList<Contatto> contatti;
	ArrayList<Gruppo> gruppi;
	ArrayList<PhoneCall> calls;
	
	
	public Utente(Controller controller, String newEmail, String newPassword, String newNickname){
		
		email = newEmail;
		password = newPassword;
		nickname = newNickname;
		note = "";
		try {
			contatti = controller.caricaContatti(email);
			gruppi = controller.caricaGruppiUtente(this, email);
			calls = controller.caricaChiamate(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getNotes() {
		return note;
	}
	
	public ArrayList<Gruppo> getGruppi() {
		return gruppi;
	}
	
	public ArrayList<PhoneCall> getCalls(){
		return calls;
	}
	//TODO Inizializzazione array
}