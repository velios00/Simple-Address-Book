package Modello;

import java.sql.SQLException;
import java.util.ArrayList;

import Controller.Controller;

public class Contatto {

	String nome;
	String cognome;
	String profilePic;
	public String contactID;
	boolean preferito;
	ArrayList<Gruppo> gruppi;
	ArrayList<Indirizzo> indirizzi;
	ArrayList<NumeriTel> numeri;
	public ArrayList<Email> emails;
	
	public Contatto(Controller controller, String name, String surname, String proPicPath, String favorite, String ID){
		nome = name;
		cognome = surname;
		profilePic = proPicPath;
		contactID = ID;
		preferito = favorite.equals("t");
		gruppi = new ArrayList<Gruppo>();
		indirizzi = new ArrayList<Indirizzo>();
		numeri = new ArrayList<NumeriTel>();
		emails = new ArrayList<Email>();
		try {
			indirizzi = controller.caricaIndirizziContatto(ID);
			numeri = controller.caricaNumeriContatto(ID);
			emails = controller.caricaEmailContatto(ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return nome;
	}
	
	public String getSurname() {
		return cognome;
	}
	
	public ArrayList<Gruppo> getGruppi(){
		return gruppi;
	}
	
	public String getMainEmail() {
		
		String email = new String("");
		for(Email e : emails)
			if(e.main)
				email = e.getString();
		return email;
		//return emails.get(0).email;
	}
	
	public void setGruppi(Utente user) {
		
		for (Gruppo group : user.gruppi)
			for(Contatto con : group.partecipanti)
				if (con.contactID == contactID) {
					gruppi.add(group);
					break;
				}	
	}
}
