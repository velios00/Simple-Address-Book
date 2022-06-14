package Modello;

import java.util.ArrayList;

public class Utente {
	
	public String email;
	public String nickname;
	public String password;
	String note;
	ArrayList<Contatto> contatti;
	ArrayList<Gruppo> gruppi;
	ArrayList<PhoneCall> calls;
	
	
	public Utente(String newEmail, String newPassword, String newNickname){
		
		email = newEmail;
		password = newPassword;
		nickname = newNickname;
		note = "";
	}
	
	public String getNotes() {
		return note;
	}
	
	//TODO Inizializzazione array
}