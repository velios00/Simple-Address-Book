package Modello;

import java.sql.SQLException;
import java.util.ArrayList;

import Controller.Controller;

public class Gruppo {
	
	Utente user;
	String ID;
	String nome;
	String dataCreazione;
	String descrizione;
	ArrayList<Contatto> partecipanti;
	
	public Gruppo(Controller controller, Utente utente, String groupID, String name, String creationDate, String description){
		
		user = utente;
		ID = groupID;
		nome = name;
		dataCreazione = creationDate;
		descrizione = description;
		partecipanti = new ArrayList<Contatto>();
		ArrayList<String> contactIDs;
		try {
			contactIDs = controller.cercaIDContattiGruppo(ID);
			for(String conID : contactIDs) {
				Contatto currentCon = cercaContattoUtente(conID);
				partecipanti.add(currentCon);
				currentCon.gruppi.add(this);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Contatto cercaContattoUtente(String contactID) {
		for(Contatto con : user.contatti)
			if(con.contactID.equals(contactID))
				return con;
		return null;
	}
	
	public String getName() {
		return nome;
	}
}
