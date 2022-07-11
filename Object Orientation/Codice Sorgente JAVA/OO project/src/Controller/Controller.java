package Controller;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.postgresql.util.PSQLException;

import DAOs.DAOgruppo;
import DAOs.DAOcontatto;
import DAOs.DAOemail;
import DAOs.DAOutente;
import Modello.Utente;
import Modello.Contatto;
import Modello.Email;
import Modello.Gruppo;
import Modello.Indirizzo;
import Modello.MessagingAccount;
import Modello.NumeriTel;
import Modello.PhoneCall;

public class Controller {
	
	
	public Utente accessoUtente(String email, String password) throws SQLException{
		
		ResultSet result = new DAOutente().cercaUtente(email);
		if(result.next() == false) { 
			JOptionPane.showMessageDialog(new JFrame(), "Nessun account collegato a "+email, "Errore", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		Utente currentUser = new Utente(this, result.getString(1), result.getString(2),result.getString(3));
		if(password.equals(currentUser.password)) {
			return currentUser;
		}
		else {
			JOptionPane.showMessageDialog(new JFrame(), "La password non è corretta!", "Errore", JOptionPane.WARNING_MESSAGE);
			return null;
		}
	}
	
	public Utente registraUtente(String email, String password, String nickName) {
		
		try {
			new DAOutente().inserisciUtente(email, password, nickName);
		} catch (PSQLException e1) {
			if(e1.getMessage().contains("emailformat"))
				JOptionPane.showMessageDialog(new JFrame(),"E-mail non valida" , "Errore", JOptionPane.WARNING_MESSAGE);
			else if (e1.getMessage().contains("r_user_pkey"))
				JOptionPane.showMessageDialog(new JFrame(),"E-mail in uso da un altro account" , "Errore", JOptionPane.WARNING_MESSAGE);
			else if (e1.getMessage().contains("passwordlen"))
				JOptionPane.showMessageDialog(new JFrame(),"La password deve essere almeno di 8 caratteri" , "Errore", JOptionPane.WARNING_MESSAGE);
			else if (e1.getMessage().contains("usernamelen"))
				JOptionPane.showMessageDialog(new JFrame(),"Il nome utente deve essere di almeno 3 caratteri" , "Errore", JOptionPane.WARNING_MESSAGE);
			else
				e1.printStackTrace();
			return null;
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return new Utente(this, email, password, nickName);
	}
	
	public ArrayList<Contatto> caricaContatti(String email) throws SQLException{
		
		ArrayList<Contatto> contatti = new ArrayList<Contatto>();
		ResultSet result = new DAOutente().cercaContatti(email);
		while(result.next()) {
			Contatto currCont = new Contatto(this, result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(1)); //cera string
			contatti.add(currCont);
		}
		return contatti;
	}
	
	public ArrayList<Gruppo> caricaGruppiUtente(Utente user, String email) throws SQLException{
		
		ArrayList<Gruppo> gruppi = new ArrayList<Gruppo>();
		ResultSet result = new DAOutente().cercaGruppi(email);
		while(result.next()) {
			Gruppo currGroup = new Gruppo(this, user, result.getString(1), result.getString(2), result.getString(3), result.getString(4));
			gruppi.add(currGroup);
		}
		return gruppi;
	}
	
	public ArrayList<PhoneCall> caricaChiamate(String email) throws SQLException{
		
		ArrayList<PhoneCall> chiamate = new ArrayList<PhoneCall>();
		ResultSet result = new DAOutente().cercaChiamate(email);
		while(result.next()) {
			PhoneCall currCall = new PhoneCall(result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
			chiamate.add(currCall);
		}
		return chiamate;
	}
	
	public ArrayList<Indirizzo> caricaIndirizziContatto(int contactID) throws SQLException{
		
		ArrayList<Indirizzo> addresses = new ArrayList<Indirizzo>();
		ResultSet result = new DAOcontatto().cercaIndirizzi(contactID);
		while(result.next()) {
			Indirizzo currAD = new Indirizzo(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(9));
			addresses.add(currAD);
		}
		return addresses;
	}
	
	public ArrayList<NumeriTel> caricaNumeriContatto(int contactID) throws SQLException{
		
		ArrayList<NumeriTel> numbers = new ArrayList<NumeriTel>();
		ResultSet result = new DAOcontatto().cercaNumeriTel(contactID);
		while(result.next()) {
			NumeriTel currNum = new NumeriTel(result.getString(1), result.getString(2), result.getString(3));
			numbers.add(currNum);
		}
		return numbers;
	}
	
	public ArrayList<Email> caricaEmailContatto(int contactID) throws SQLException{
		
		ArrayList<Email> emails = new ArrayList<Email>();
		ResultSet result = new DAOcontatto().cercaEmail(contactID);
		while(result.next()) {
			Email currEmail = new Email(this, result.getString(1), result.getString(2));
			emails.add(currEmail);
		}
		return emails;
	}
	
	public ArrayList<MessagingAccount> caricaMessagingEmail(String contactEmail) throws SQLException{
		
		ArrayList<MessagingAccount> accounts = new ArrayList<MessagingAccount>();
		ResultSet result = new DAOemail().cercaMessaging(contactEmail);
		while(result.next()) {
			MessagingAccount currAccount = new MessagingAccount(result.getString(1), result.getString(2), result.getString(4));
			accounts.add(currAccount);
		}
		return accounts;
	}
	
	public String getMainEmail(int contactID) {
		
		ResultSet result;
		DAOcontatto DAO = new DAOcontatto();
		
		result = DAO.getMainEmail(contactID);
		try {
			if (result.next())
				return result.getString(1);
			else return "";
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getPhoneNumber(int contactID, String tipo) {
		
		ResultSet result;
		DAOcontatto DAO = new DAOcontatto();
		
		result = DAO.getPhoneNumber(contactID, tipo);
		try {
			if (result.next())
				return result.getString(1);
			else return "";
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Integer> cercaIDContattiGruppo(String groupID) throws SQLException{
		
		ArrayList<Integer> conIDs = new ArrayList<Integer>();
		ResultSet result = new DAOgruppo().cercaContattiGruppo(groupID);
		while(result.next()) {
			Integer currID = result.getInt(1);
			conIDs.add(currID);
		}
		return conIDs;
	}
	
	public Contatto aggiungiContatto(String userEmail, String nome, String cognome, String profilePic, Boolean fav) throws SQLException {
		
		ResultSet result;
		result = new DAOcontatto().inserisciContatto(userEmail, nome, cognome, profilePic, fav);
		result.next();
		return new Contatto(nome, cognome, profilePic, fav.toString(), result.getInt(1)); //cera get string
	}
	
	public void eliminaContatto(int ID) {
		
		new DAOcontatto().eliminaContatto(ID);
	}
	
	public NumeriTel getPhoneNumber(String type, String numero, Boolean linkednumber, int contactID) throws SQLException {
		
		NumeriTel tel;
		DAOcontatto dao = new DAOcontatto();
		ResultSet result = dao.cercaPhoneNumber(numero);
		if(result.next()) {
			tel = new NumeriTel(result.getString(1), result.getString(2), result.getString(3));
		}
		else 
		{
			tel = new NumeriTel(type, numero, linkednumber.toString());
			dao.inserisciPhoneNumber(type, numero, linkednumber);
		}
		dao.inserisciAssignedPhone(numero, contactID);
		return tel;
	}
	
	public Indirizzo getAddress(String street, String cap, String citta, String province, String naz, int contactID, Boolean main) throws SQLException {
		
		Indirizzo address;
		DAOcontatto dao = new DAOcontatto();
		ResultSet result = dao.cercaAddress(street, cap);
		if(result.next())
			address = new Indirizzo(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), main.toString());
		else 
		{
			address = new Indirizzo(street, cap, citta, province, naz, main.toString());
			dao.inserisciAddress(street, cap, citta, province, naz);
		}
		dao.inserisciAssignedAddress(contactID, street, cap, main);
		return address;
	}
	
	public Email aggiungiEmail(String email, Boolean main, int contactID) throws SQLException {

		new DAOcontatto().inserisciEmail(email, main, contactID);
		return new Email(this, email, main.toString());
	}
	
	public ArrayList<Contatto> searchContactList(ArrayList<Contatto> contactList, String str) {
		
		ArrayList<Contatto> tmpList = new ArrayList<Contatto>();
		for(Contatto con : contactList)
			if(con.getName().toLowerCase().contains(str.toLowerCase())) {
				tmpList.add(con);
				break;
			}
		for(Contatto con : contactList) 
			if(con.getSurname().toLowerCase().contains(str.toLowerCase())) {
				tmpList.add(con);
				break;
			}
		for(Contatto con : contactList)
			for(NumeriTel tel : con.numeri)
				if(tel.getNumber().toLowerCase().contains(str.toLowerCase())) {
					tmpList.add(con);
					break;
				}
		for(Contatto con : contactList)
			for(Email e : con.emails)
				if(e.getString().toLowerCase().contains(str.toLowerCase())) {
					tmpList.add(con);
					break;
				}
		for(Contatto con : contactList)
			for(Indirizzo ind : con.indirizzi)
				if(ind.getString().toLowerCase().contains(str.toLowerCase()))
					tmpList.add(con);
		return tmpList;
	}
	
	public void confirmConToGrp(Contatto addingContact, Gruppo intoGroup) {
		new DAOgruppo().aggiungiContattoGruppo(addingContact.getID(), intoGroup.getID());
		intoGroup.getPartecipanti().add(addingContact);
		addingContact.getGruppi().add(intoGroup);
		
		}
	
	public void removeConToGrp(Contatto removingContact, Gruppo intoGroup) {
		new DAOgruppo().rimuoviContattoGruppo(removingContact.getID(), intoGroup.getID());
		intoGroup.getPartecipanti().remove(removingContact);
		removingContact.getGruppi().remove(intoGroup);
	}
	
	public void creaGruppo(String nomeGruppo, String descrizioneGruppo, Utente utentex) {
		ResultSet result = new DAOgruppo().creaGruppoDb(nomeGruppo, descrizioneGruppo, utentex.email);
		try {
			result.next();
			Gruppo gruppox = new Gruppo(this, utentex, result.getString(1), nomeGruppo, result.getString(3), descrizioneGruppo);
			utentex.getGruppi().add(gruppox);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void eliminaGruppo(Gruppo removingGrp, Utente utentex) {
		new DAOgruppo().eliminaGruppoDb(removingGrp.getID());
		utentex.getGruppi().remove(removingGrp);
		for(Contatto contatto1 : removingGrp.getPartecipanti()) {
			contatto1.getGruppi().remove(removingGrp);
		}
			
	}
	
	public void modificaGruppo(String nomeGruppo, String descrizioneGruppo, Gruppo gruppo1, Utente utentex) {
		new DAOgruppo().modificaGruppoDb(nomeGruppo, descrizioneGruppo, gruppo1.getID(),utentex.email);
	}
}
