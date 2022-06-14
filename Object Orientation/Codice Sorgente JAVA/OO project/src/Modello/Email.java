package Modello;

import java.util.ArrayList;

public class Email {

	String email;
	boolean main;
	ArrayList<MessagingAccount> messaging;
	
	Email(String newEmail, String principale){
		
		email = newEmail;
		main = principale.equals("true");
		//TODO Inizializzazione array di account
	}
}
