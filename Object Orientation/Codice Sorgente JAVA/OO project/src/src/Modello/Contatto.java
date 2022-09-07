package Modello;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * Indica un contatto salvato nella rubrica
 */
public class Contatto {

	/** Nome del contatto */
	String nome;
	
	/** Cognome del contatto */
	String cognome;
	
	/** Percorso sul disco del file contenente l’immagine profilo del contatto. */
	String profilePic;
	
	/** ID del contatto nel database */
	int contactID;
	
	/** Indica se il contatto è inserito tra i preferiti. */
	boolean preferito;
	
	/** Lista dei gruppi a cui il contatto appartiene all'interno della rubrica. */
	public ArrayList<Gruppo> gruppi;
	
	/** Lista degli indirizzi associati al contatto. */
	public ArrayList<Indirizzo> indirizzi;
	
	/** Lista dei numeri di telefono associati al contatto. */
	public ArrayList<NumeriTel> numeri;
	
	/** Lista delle email appartenenti al contatto. */
	public ArrayList<Email> emails;
	
	/**
	 * Instantiates a new contatto.
	 *
	 * @param name the name
	 * @param surname the surname
	 * @param proPicPath the pro pic path
	 * @param favorite the favorite
	 * @param ID the id
	 */
	public Contatto(String name, String surname, String proPicPath, String favorite, int ID) {
		nome = name;
		cognome = surname;
		profilePic = new File(ifDefaultPic(proPicPath)).getAbsolutePath();
		contactID = ID;
		preferito = (favorite.equals("t")  || favorite.equals("true"));
		gruppi = new ArrayList<Gruppo>();
		indirizzi = new ArrayList<Indirizzo>();
		numeri = new ArrayList<NumeriTel>();
		emails = new ArrayList<Email>();
	}

	/**
	 * Instantiates a new contatto.
	 *
	 * @param controller the controller
	 * @param name the name
	 * @param surname the surname
	 * @param proPicPath the pro pic path
	 * @param favorite the favorite
	 * @param ID the id
	 */
	public Contatto(Controller controller, String name, String surname, String proPicPath, String favorite, int ID){
		nome = name;
		cognome = surname;
		profilePic = new File(ifDefaultPic(proPicPath)).getAbsolutePath();
		contactID = ID;
		preferito = (favorite.equals("t") || favorite.equals("true"));
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
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return nome;
	}
	
	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return cognome;
	}
	
	/**
	 * Gets the gruppi.
	 *
	 * @return the gruppi
	 */
	public ArrayList<Gruppo> getGruppi(){
		return gruppi;
	}
	
	/**
	 * Gets the main email.
	 *
	 * @return the main email
	 */
	public String getMainEmail() {
		
		String email = new String("");
		for(Email e : emails)
			if(e.main)
				email = e.getString();
		return email;
	}
	
	/**
	 * Gets the tel.
	 *
	 * @param type the type
	 * @return the tel
	 */
	public String getTel(String type) {
		
		for(NumeriTel tel : numeri)
			if(tel.tipo.equals(type))
				return tel.numero;
		return "";
	}
	
	/**
	 * Gets the image path.
	 *
	 * @return the image path
	 */
	public String getImagePath() {
		return profilePic;
	}
	
	/**
	 * If default pic.
	 *
	 * @param str the str
	 * @return the string
	 */
	public String ifDefaultPic(String str) {
		
		if(str.equals("C:\\Users\\Velios\\Desktop\\Uni\\cd\\defaultpic"))
			return new String("defaultpic.jpg");
		else
			return str;
	}
	
	/**
	 * Checks if is favorite.
	 *
	 * @return true, if is favorite
	 */
	public boolean isFavorite() {
		return preferito;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override 
	public String toString() {
		return getName() + ' ' + getSurname();
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getID() {
		return contactID;
	}
	
	public ArrayList<Email> getEmails() {
		return emails;
	}
	
	public ArrayList<Indirizzo> getAddress() {
		return indirizzi;
	}
	
	public ArrayList<NumeriTel> getNumeriTel(){
		return numeri;
	}
}
