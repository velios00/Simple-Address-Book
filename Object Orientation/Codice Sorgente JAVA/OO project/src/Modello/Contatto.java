package Modello;

import java.util.ArrayList;

public class Contatto {

	String nome;
	String cognome;
	String profilePic;
	boolean preferito;
	ArrayList<Gruppo> gruppi;
	ArrayList<Indirizzo> indirizzi;
	ArrayList<NumeriTel> numeri;
	ArrayList<Email> email;
	
	Contatto(String name, String surname, String proPicPath, String favorite, String ID){
		nome = name;
		cognome = surname;
		profilePic = proPicPath;
		preferito = favorite.equals("true");
		//TODO Inizializzazione Array tramite ID
	}
}
