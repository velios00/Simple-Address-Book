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

// TODO: Auto-generated Javadoc
/**
 * Gestisce la logica e gli algoritmi del programma, viene instanziato una sola volta all'inizio.
 */
public class Controller {
	
	
	/**
	 * Questo metodo permette l'accesso all'account di un {@link Utente} tramite l'email e la password.
	 * Se l'email è stata trovata nel database e la password corrispondente è corretta, restituisce un {@link Utente}, null altrimenti
	 *
	 * @param email email dell'account.
	 * @param password password digitata dall'utente.
	 * @return {@link Utente} utente trovato oppure null.
	 * @throws SQLException SQL exception
	 */
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
	
	/**
	 * Questo metodo permette la creazione di un nuovo account tramite una email, una password e un nickname.
	 * Controlla che i dati inseriti siano validi e non violino alcun vincolo del database.
	 * Se la registrazione è andata a buon fine restituisce un {@link Utente}, null altrimenti.
	 *
	 * @param email email del nuovo account
	 * @param password password  da associare al nuovo account
	 * @param nickName nickname dell'account
	 * @return il nuovo {@link Utente} oppure null
	 */
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
	
	/**
	 * Trova nel database ed inizializza tutti i {@link Contatto} associati ad un {@link Utente} tramite l'email.
	 *
	 * @param email email dell'utente a cui sono associati i contatti da caricare.
	 * @return array list dei contatti dell'utente.
	 * @throws SQLException SQL exception
	 */
	public ArrayList<Contatto> caricaContatti(String email) throws SQLException{
		
		ArrayList<Contatto> contatti = new ArrayList<Contatto>();
		ResultSet result = new DAOutente().cercaContatti(email);
		while(result.next()) {
			Contatto currCont = new Contatto(this, result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(1)); //cera string
			contatti.add(currCont);
		}
		return contatti;
	}
	
	/**
	 * Trova nel database ed inizializza tutti i {@link Gruppo} associati ad un {@link Utente} tramite l'email.
	 *
	 * @param user utente a cui sono associati i gruppi da caricare.
	 * @return array list dei gruppi dell'utente
	 * @throws SQLException SQL exception
	 */
	public ArrayList<Gruppo> caricaGruppiUtente(Utente user, String email) throws SQLException{
		
		ArrayList<Gruppo> gruppi = new ArrayList<Gruppo>();
		ResultSet result = new DAOutente().cercaGruppi(email);
		while(result.next()) {
			Gruppo currGroup = new Gruppo(this, user, result.getString(1), result.getString(2), result.getString(3), result.getString(4));
			gruppi.add(currGroup);
		}
		return gruppi;
	}
	
	/**
	 * Trova nel database ed inizializza tutte le {@link PhoneCall} associate ad un {@link Utente} tramite l'email.
	 *
	 * @param email email dell'utente a cui sono associate le chiamate da caricare
	 * @return array list delle chiamate associate all'utente.
	 * @throws SQLException SQL exception
	 */
	public ArrayList<PhoneCall> caricaChiamate(String email) throws SQLException{
		
		ArrayList<PhoneCall> chiamate = new ArrayList<PhoneCall>();
		ResultSet result = new DAOutente().cercaChiamate(email);
		while(result.next()) {
			PhoneCall currCall = new PhoneCall(result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
			chiamate.add(currCall);
		}
		return chiamate;
	}
	
	/**
	 * Trova nel database ed inizializza tutti gli {@link Indirizzo} associati ad un {@link Contatto} tramite l'ID.
	 *
	 * @param contactID ID del contatto a cui sono associati gli indirizzi da caricare. 
	 * @return array list degli indirizzi associati al contatto.
	 * @throws SQLException SQL exception
	 */
	public ArrayList<Indirizzo> caricaIndirizziContatto(int contactID) throws SQLException{
		
		ArrayList<Indirizzo> addresses = new ArrayList<Indirizzo>();
		ResultSet result = new DAOcontatto().cercaIndirizzi(contactID);
		while(result.next()) {
			Indirizzo currAD = new Indirizzo(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(9));
			addresses.add(currAD);
		}
		return addresses;
	}
	
	/**
	 * Trova nel database ed inizializza tutti i {@link NumeriTel} appartenenti ad un {@link Contatto} tramite l'ID
	 *
	 * @param contactID ID del contatto a cui sono associati i numeri da caricare.
	 * @return array list dei numeri di telefono appartenenti al contatto.
	 * @throws SQLException SQL exception
	 */
	public ArrayList<NumeriTel> caricaNumeriContatto(int contactID) throws SQLException{
		
		ArrayList<NumeriTel> numbers = new ArrayList<NumeriTel>();
		ResultSet result = new DAOcontatto().cercaNumeriTel(contactID);
		while(result.next()) {
			NumeriTel currNum = new NumeriTel(result.getString(1), result.getString(2), result.getString(3));
			numbers.add(currNum);
		}
		return numbers;
	}
	
	/**
	 * Trova nel database ed inizializza tutte le {@link Email} appartenenti ad un {@link Contatto}
	 *
	 * @param contactID ID del contatto a cui sono associate le email da caricare.
	 * @return array list delle email appartenenti al contatto
	 * @throws SQLException SQL exception
	 */
	public ArrayList<Email> caricaEmailContatto(int contactID) throws SQLException{
		
		ArrayList<Email> emails = new ArrayList<Email>();
		ResultSet result = new DAOcontatto().cercaEmail(contactID);
		while(result.next()) {
			Email currEmail = new Email(this, result.getString(1), result.getString(2));
			emails.add(currEmail);
		}
		return emails;
	}
	
	/**
	 * Trova nel database ed inizializza tutti i {@link MessagingAccount} collegati ad una {@link Email}
	 *
	 * @param contactEmail l'email del contatto a cui sono associati gli account di messaging
	 * @return array list degli account di messaging collegati all'email
	 * @throws SQLException SQL exception
	 */
	public ArrayList<MessagingAccount> caricaMessagingEmail(String contactEmail) throws SQLException{
		
		ArrayList<MessagingAccount> accounts = new ArrayList<MessagingAccount>();
		ResultSet result = new DAOemail().cercaMessaging(contactEmail);
		while(result.next()) {
			MessagingAccount currAccount = new MessagingAccount(result.getString(1), result.getString(2), result.getString(4));
			accounts.add(currAccount);
		}
		return accounts;
	}
	
	/**
	 * Trova nel database la {@link Email} principale di un contatto
	 *
	 * @param contactID ID del contatto
	 * @return l'email principale
	 */
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
	
	/**
	 * Trova il {@link NumeriTel} principale di un contatto
	 *
	 * @param contactID ID del contatto
	 * @param tipo tipo del numero di telefono da trovare, può essere mobile o fisso
	 * @return il numero di telefono principale
	 */
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
	
	/**
	 * Trova nel database gli ID dei contatti partecipanti ad un gruppo
	 *
	 * @param groupID ID del gruppo di cui trovare gli ID dei contatti
	 * @return array list di {@link Integer} contenenti gli ID dei contatti
	 * @throws SQLException SQL exception
	 */
	public ArrayList<Integer> cercaIDContattiGruppo(String groupID) throws SQLException{
		
		ArrayList<Integer> conIDs = new ArrayList<Integer>();
		ResultSet result = new DAOgruppo().cercaContattiGruppo(groupID);
		while(result.next()) {
			Integer currID = result.getInt(1);
			conIDs.add(currID);
		}
		return conIDs;
	}
	
	/**
	 * Aggiunge un contatto alla rubrica. Il metodo si occupa di inserire il nuovo contatto nel database. 
	 * Restituisce il nuovo {@link Contatto}. Restituisce null se l'accesso al database non è andato a buon fine.
	 *
	 * @param userEmail email dell'utente proprietario della rubrica in cui inserire il contatto.
	 * @param nome nome del contatto.
	 * @param cognome cognome del contatto.
	 * @param profilePic percorso della directory dove si trova l'immagine del profilo.
	 * @param fav se il contatto è tra i preferiti.
	 * @return il nuovo contatto creato.
	 * @throws SQLException the SQL exception
	 */
	public Contatto aggiungiContatto(String userEmail, String nome, String cognome, String profilePic, Boolean fav) throws SQLException {
		
		ResultSet result;
		result = new DAOcontatto().inserisciContatto(userEmail, nome, cognome, profilePic, fav);
		result.next();
		return new Contatto(nome, cognome, profilePic, fav.toString(), result.getInt(1)); //cera get string
	}
	
	/**
	 * Elimina un {@link Contatto} dalla rubrica. Il metodo si occupa  di eliminare il contatto dal database.
	 *
	 * @param ID ID del contatto da eliminare
	 */
	public void eliminaContatto(int ID) {
		
		new DAOcontatto().eliminaContatto(ID);
	}
	
	/**
	 * Aggiunge un numero all'elenco dei numeri di telefono di un contatto. Il metodo controlla prima se il numero di telefono è già presente nel database.
	 * Restituisce un nuovo oggetto {@link NumeriTel}
	 * 
	 * @param type tipo del nuovo numero di telefono da trovare, può essere mobile o fisso
	 * @param numero nuovo numero di telefono
	 * @param linkednumber se il numero di telefono è di reindirizzamento
	 * @param contactID ID contatto a cui aggiungere il nuovo numero di telefono
	 * @return il nuovo numero di telefono
	 * @throws SQLException SQL exception
	 */
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
	
	/**
	 * Aggiunge un indirizzo all'elenco degli indirizzi fisici di un contatto. Il metodo controlla prima se il l'indirizzo è già presente nel database.
	 * Restituisce un nuovo oggetto {@link Indirizzo}.
	 *
	 * @param street Stringa contenente nome della strada e numero civico
	 * @param cap Stringa contenente il CAP
	 * @param citta città dell'indirizzo
	 * @param province provincia della città
	 * @param naz nazione
	 * @param contactID ID del contatto a cui aggiungere il nuovo indirizzo
	 * @param main se il nuovo indirizzo è principale per quel contatto
	 * @return il nuovo indirizzo
	 * @throws SQLException SQL exception
	 */
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
	
	/**
	 * Aggiunge una email all'elenco deglle email di un contatto. Il metodo controlla prima se l'email è già presente nel database.
	 * Restituisce un nuovo oggetto {@link Email}.
	 *
	 * @param email Stringa contenente l'email
	 * @param main se l'email è principale per quel contatto
	 * @param contactID ID del contatto a cui associare la nuova email
	 * @return la nuova email
	 * @throws SQLException SQL exception
	 */
	public Email aggiungiEmail(String email, Boolean main, int contactID) throws SQLException {
		
		Email em;
		DAOcontatto dao = new DAOcontatto();
		ResultSet result = dao.cercaEmail(email);
		if(result.next())
		{
			em = new Email(this, result.getString(1), result.getString(2));
			dao.setContactEmail(email, contactID);
		}
		else
		{
			em = new Email(this, email, main.toString());
			dao.inserisciEmail(email, main, contactID);
		}
		return em;
	}
	
	/**
	 * Effettua una ricerca in una lista di contatti.
	 * Per ogni {@link Contatto} all'interno della lista il metodo controlla se una stringa è contenuta all'interno delle informazioni del contatto stesso.
	 * Restituisce una nuova lista contenente i contatti che soddisfano la condizione.
	 *
	 * @param contactList lista dei contatti in cui effettuare la ricerca
	 * @param str sottostringa da ricercare nei contatti
	 * @return arraylist dei contatti in cui è stata trovata la sottostringa all'interno delle relative informazioni.
	 */
	public ArrayList<Contatto> searchContactList(ArrayList<Contatto> contactList, String str) {
		
		
		ArrayList<Contatto> tmpList = new ArrayList<Contatto>();
		for(Contatto con : contactList)
			if(con.getName().toLowerCase().contains(str.toLowerCase())) {
				tmpList.add(con);
				
			}
		for(Contatto con : contactList) 
			if(!tmpList.contains(con) && con.getSurname().toLowerCase().contains(str.toLowerCase())) {
				tmpList.add(con);
				
			}
		for(Contatto con : contactList)
			for(NumeriTel tel : con.numeri)
				if(!tmpList.contains(con) && tel.getNumber().toLowerCase().contains(str.toLowerCase())) {
					tmpList.add(con);
					
				}
		for(Contatto con : contactList)
			for(Email e : con.emails)
				if(!tmpList.contains(con) && e.getString().toLowerCase().contains(str.toLowerCase())) {
					tmpList.add(con);
					
				}
		for(Contatto con : contactList)
			for(Indirizzo ind : con.indirizzi)
				if(!tmpList.contains(con) && ind.getString().toLowerCase().contains(str.toLowerCase()))
					tmpList.add(con);
		return tmpList;
	}
	
	/**
	 * Aggiunge un {@link Contatto} ad un {@link Gruppo}. Il metodo si occupa di modificare anche il database.
	 *
	 * @param addingContact il contatto da aggiungere nel gruppo
	 * @param intoGroup il gruppo a cui aggiungere il contatto
	 */
	public void confirmConToGrp(Contatto addingContact, Gruppo intoGroup) {
		new DAOgruppo().aggiungiContattoGruppo(addingContact.getID(), intoGroup.getID());
		intoGroup.getPartecipanti().add(addingContact);
		addingContact.getGruppi().add(intoGroup);
		
		}
	
	/**
	 * Rimuove un {@link Contatto} da un {@link Gruppo}. Il metodo si occupa di modificare aanche il database.
	 *
	 * @param removingContact il contatto da rimuovere dal gruppo
	 * @param intoGroup il gruppo da cui rimuovere il contatto
	 */
	public void removeConToGrp(Contatto removingContact, Gruppo intoGroup) {
		new DAOgruppo().rimuoviContattoGruppo(removingContact.getID(), intoGroup.getID());
		intoGroup.getPartecipanti().remove(removingContact);
		removingContact.getGruppi().remove(intoGroup);
	}
	
	/**
	 * Crea un nuovo {@link Gruppo}. Il metodo si occupa anche di inserire il nuovo gruppo nel database.
	 *
	 * @param nomeGruppo nome del nuovo gruppo
	 * @param descrizioneGruppo descrizione del gruppo
	 * @param utentex utente al quale appartiene il gruppo
	 */
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
	
	/**
	 * Rimuove un {@link Gruppo} dalla rubrica. Il metodo si occupa anche di rimuovere il gruppo dal database.
	 *
	 * @param removingGrp il gruppo da rimuovere dalla rubrica
	 * @param utentex utente al quale appartiene i gruppo da rimuovere
	 */
	public void eliminaGruppo(Gruppo removingGrp, Utente utentex) {
		new DAOgruppo().eliminaGruppoDb(removingGrp.getID());
		utentex.getGruppi().remove(removingGrp);
		for(Contatto contatto1 : removingGrp.getPartecipanti()) {
			contatto1.getGruppi().remove(removingGrp);
		}
			
	}
	
	/**
	 * Modifica le informazioni di un {@link Gruppo}. Il metodo si occupa di modificare anche il database.
	 *
	 * @param nomeGruppo nuovo nome del gruppo
	 * @param descrizioneGruppo nuova descrizione del gruppo
	 * @param gruppo1 gruppo da modificare
	 */
	public void modificaGruppo(String nomeGruppo, String descrizioneGruppo, Gruppo gruppo1) {
		gruppo1.setName(nomeGruppo);
		gruppo1.setDesc(descrizioneGruppo);
		new DAOgruppo().modificaGruppoDb(gruppo1.getName(), gruppo1.getDesc(), gruppo1.getID());
	}
	
	public void setMainEmail(Email em, boolean b) {
		em.setMain(b);
		new DAOcontatto().setMainEmail(em.getEmail(), b);
	}
	
	public void setLinked(NumeriTel tel, boolean b) {
		tel.setLinked(b);
		new DAOcontatto().setLinked(tel.getNumber(), b);
	}
}
