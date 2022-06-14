package Modello;

import java.util.ArrayList;

public class Gruppo {

	String nome;
	String dataCreazione;
	String descrizione;
	ArrayList<Contatto> partecipanti;
	
	Gruppo(String name, String creationDate, String description){
		nome = name;
		dataCreazione = creationDate;
		descrizione = description;
		//TODO Inizializzazione array contatti
	}
}
