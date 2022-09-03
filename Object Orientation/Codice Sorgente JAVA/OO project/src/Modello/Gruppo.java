package Modello;

import java.sql.SQLException;
import java.util.ArrayList;

import Controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * Gruppo a cui possono appartenere uno o più contatti all’interno della rubrica
 */
public class Gruppo {
	
	/** Utente che ha creato il gruppo */
	Utente user;
	
	/** ID del gruppo nella base di dati */
	String ID;
	
	/** Nome del gruppo all’interno della rubrica. */
	String nome;
	
	/** data della creazione del gruppo, formato GG/MM/AAAA. */
	String dataCreazione;
	
	/** Stringa contenente una breve descrizione del gruppo. */
	String descrizione;
	
	/** Lista dei contatti che appartengono al gruppo. */
	ArrayList<Contatto> partecipanti;
	
	/**
	 * Instantiates a new gruppo.
	 *
	 * @param controller the controller
	 * @param utente the utente
	 * @param groupID the group ID
	 * @param name the name
	 * @param creationDate the creation date
	 * @param description the description
	 */
	public Gruppo(Controller controller, Utente utente, String groupID, String name, String creationDate, String description){
		
		user = utente;
		ID = groupID;
		nome = name;
		dataCreazione = creationDate;
		descrizione = description;
		partecipanti = new ArrayList<Contatto>();
		ArrayList<Integer> contactIDs;
		try {
			contactIDs = controller.cercaIDContattiGruppo(ID);
			for(int conID : contactIDs) {
				Contatto currentCon = cercaContattoUtente(conID);
				partecipanti.add(currentCon);
				currentCon.gruppi.add(this);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cerca contatto utente.
	 *
	 * @param contactID the contact ID
	 * @return the contatto
	 */
	private Contatto cercaContattoUtente(int contactID) {
		for(Contatto con : user.contatti)
			if(con.getID()==contactID)
				return con;
		return null;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return nome;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param newName the new name
	 */
	public void setName(String newName) {
		if(!newName.isBlank())
			nome = newName;
	}
	
	/**
	 * Gets the partecipanti.
	 *
	 * @return the partecipanti
	 */
	public ArrayList<Contatto> getPartecipanti() {
		return partecipanti;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return dataCreazione;
	}
	
	/**
	 * Gets the desc.
	 *
	 * @return the desc
	 */
	public String getDesc() {
		return descrizione;
	}
	
	/**
	 * Sets the desc.
	 *
	 * @param newDesc the new desc
	 */
	public void setDesc(String newDesc) {
			descrizione = newDesc;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getID() {
		return ID;
	}
}
