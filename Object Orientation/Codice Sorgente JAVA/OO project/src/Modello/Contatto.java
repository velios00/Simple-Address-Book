package Modello;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Controller.Controller;

public class Contatto {

	String nome;
	String cognome;
	String profilePic;
	public String contactID;
	boolean preferito;
	public ArrayList<Gruppo> gruppi;
	public ArrayList<Indirizzo> indirizzi;
	public ArrayList<NumeriTel> numeri;
	public ArrayList<Email> emails;
	
	public Contatto(String name, String surname, String proPicPath, String favorite, String ID) {
		nome = name;
		cognome = surname;
		profilePic = new File(ifDefaultPic(proPicPath)).getAbsolutePath();
		contactID = ID;
		preferito = favorite.equals("t");
		gruppi = new ArrayList<Gruppo>();
		indirizzi = new ArrayList<Indirizzo>();
		numeri = new ArrayList<NumeriTel>();
		emails = new ArrayList<Email>();
	}

	public Contatto(Controller controller, String name, String surname, String proPicPath, String favorite, String ID){
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
	}
	
	public String getTel(String type) {
		
		for(NumeriTel tel : numeri)
			if(tel.tipo.equals(type))
				return tel.numero;
		return "";
	}
	
	public String getImagePath() {
		return profilePic;
	}
	
	public String ifDefaultPic(String str) {
		
		if(str.equals("C:\\Users\\Velios\\Desktop\\Uni\\cd\\defaultpic"))
			return new String("defaultpic.jpg");
		else
			return str;
	}
	public boolean isFavorite() {
		return preferito;
	}
}
