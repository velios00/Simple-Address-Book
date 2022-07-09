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
			Contatto currCont = new Contatto(this, result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(1));
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
	
	public ArrayList<Indirizzo> caricaIndirizziContatto(String contactID) throws SQLException{
		
		ArrayList<Indirizzo> addresses = new ArrayList<Indirizzo>();
		ResultSet result = new DAOcontatto().cercaIndirizzi(contactID);
		while(result.next()) {
			Indirizzo currAD = new Indirizzo(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
			addresses.add(currAD);
		}
		return addresses;
	}
	
	public ArrayList<NumeriTel> caricaNumeriContatto(String contactID) throws SQLException{
		
		ArrayList<NumeriTel> numbers = new ArrayList<NumeriTel>();
		ResultSet result = new DAOcontatto().cercaNumeriTel(contactID);
		while(result.next()) {
			NumeriTel currNum = new NumeriTel(result.getString(1), result.getString(2), result.getString(3));
			numbers.add(currNum);
		}
		return numbers;
	}
	
	public ArrayList<Email> caricaEmailContatto(String contactID) throws SQLException{
		
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
	
	public String getMainEmail(String contactID) {
		
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
	
	public String getPhoneNumber(String contactID, String tipo) {
		
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
	
	public ArrayList<String> cercaIDContattiGruppo(String groupID) throws SQLException{
		
		ArrayList<String> conIDs = new ArrayList<String>();
		ResultSet result = new DAOgruppo().cercaContattiGruppo(groupID);
		while(result.next()) {
			String currID = result.getString(1);
			conIDs.add(currID);
		}
		return conIDs;
	}
}
