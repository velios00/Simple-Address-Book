package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ConnessioneDatabase.ConnessionePG;

// TODO: Auto-generated Javadoc
/**
 * DAO (Database Access Object) che si occupa di gestire i contatti all'interno del database, ovvero aggiungerne, eliminarne o modificarne uno.
 * La classe è in grado anche di gestire nel database le infomazioni relative al contatto, ovvero aggiungere, rimuovere o modificare indirizzi, numeri di telfono ed email.
 * I metodi includono anche il recupero di infomazioni di un contatto dal database.
 */
public class DAOcontatto {

	/** Contiente la connessione stabilita con il database */
	private Connection conn;
	
	/** Eventuale risultato della query effettuata nel database */
	ResultSet result;
	
	/**
	 * Gets the main email.
	 *
	 * @param contactID the contact ID
	 * @return the main email
	 */
	public ResultSet getMainEmail(int contactID) {
		
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement query = conn.prepareStatement("SELECT email FROM Email WHERE contactID = '"+contactID+"' and main = true");
			System.out.println(query);
			result = query.executeQuery();
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
			return  null;
		}
		return result;
	}
	
	/**
	 * Gets the phone number.
	 *
	 * @param contactID the contact ID
	 * @param tipo the tipo
	 * @return the phone number
	 */
	public ResultSet getPhoneNumber(int contactID, String tipo) {
		
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement query = conn.prepareStatement("SELECT PN.phoneNumber "
															+ "FROM PhoneNumber PN, AssignedPhone AP "
															+ "WHERE AP.contactID = '"+contactID+"' and PN.phoneType = '"+tipo+"' ORDER BY PN.linkednumber ASC");
			System.out.println(query);
			result = query.executeQuery();
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
			return  null;
		}
		return result;
	}
	
	/**
	 * Cerca indirizzi.
	 *
	 * @param contactID the contact ID
	 * @return the result set
	 */
	public ResultSet cercaIndirizzi(int contactID) {
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryCercaContatti = conn.prepareStatement("SELECT * FROM Address AD, AssignedAddress AA "
																		+ "WHERE AA.contactID = '"+contactID+"' and AD.street = AA.addressstr and AD.zipcode = AA.addresszip ORDER BY AA.main DESC");
			System.out.println(queryCercaContatti);
			result = queryCercaContatti.executeQuery();
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
			return  null;
		}
		return result;
	}
	
	/**
	 * Cerca numeri tel.
	 *
	 * @param contactID the contact ID
	 * @return the result set
	 */
	public ResultSet cercaNumeriTel(int contactID) {
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryCercaContatti = conn.prepareStatement("SELECT * FROM PhoneNumber PN, AssignedPhone AP "
																		+ "WHERE AP.contactID = '"+contactID+"' and AP.phoneNumber = PN.phoneNumber ORDER BY PN.linkednumber ASC");
			System.out.println(queryCercaContatti);
			result = queryCercaContatti.executeQuery();
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
			return  null;
		}
		return result;
	}
	
	/**
	 * Cerca email.
	 *
	 * @param contactID the contact ID
	 * @return the result set
	 */
	public ResultSet cercaEmail(int contactID) {
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryCercaContatti = conn.prepareStatement("SELECT * FROM Email "
																		+ "WHERE contactID = '"+contactID+"' ORDER BY main DESC");
			System.out.println(queryCercaContatti);
			result = queryCercaContatti.executeQuery();
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
			return  null;
		}
		return result;
	}
	
	/**
	 * Inserisci contatto.
	 *
	 * @param userEmail the user email
	 * @param nome the nome
	 * @param cognome the cognome
	 * @param profilePic the profile pic
	 * @param fav the fav
	 * @return the result set
	 * @throws SQLException the SQL exception
	 */
	public ResultSet inserisciContatto(String userEmail, String nome, String cognome, String profilePic, Boolean fav) throws SQLException {
		
		conn = ConnessionePG.getInstance().getConnection();
		PreparedStatement queryInserisciContatto = conn.prepareStatement(
			"INSERT INTO Contact VALUES(default, '"+nome+"', '"+cognome+"', '"+profilePic+"', '"+fav.toString()+"', '"+userEmail+"')", Statement.RETURN_GENERATED_KEYS);
		System.out.println(queryInserisciContatto);
		queryInserisciContatto.executeUpdate();
		result = queryInserisciContatto.getGeneratedKeys();
		conn.close();
		return result;
	}
	
	/**
	 * Elimina contatto.
	 *
	 * @param ID the id
	 */
	public void eliminaContatto(int ID) {
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryEliminaContatto = conn.prepareStatement("DELETE FROM Contact WHERE ContactID = '"+ID+"'");
			System.out.println(queryEliminaContatto);
			queryEliminaContatto.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserisci phone number.
	 *
	 * @param type the type
	 * @param numero the numero
	 * @param linkednumber the linkednumber
	 * @throws SQLException the SQL exception
	 */
	public void inserisciPhoneNumber(String type, String numero, Boolean linkednumber) throws SQLException {
		conn = ConnessionePG.getInstance().getConnection();
		PreparedStatement queryInserisciPhoneNumber = conn.prepareStatement(
			"INSERT INTO PhoneNumber VALUES('"+type+"', '"+numero+"', '"+linkednumber.toString()+"')");
		System.out.println(queryInserisciPhoneNumber);
		queryInserisciPhoneNumber.executeUpdate();	
		conn.close();
	}
	
	/**
	 * Inserisci assigned phone.
	 *
	 * @param numero the numero
	 * @param contactID the contact ID
	 * @throws SQLException the SQL exception
	 */
	public void inserisciAssignedPhone(String numero, int contactID) throws SQLException {
		
		conn = ConnessionePG.getInstance().getConnection();
		PreparedStatement queryInserisciAssignedPhone = conn.prepareStatement("INSERT INTO AssignedPhone VALUES('"+numero+"', '"+contactID+"')");
		System.out.println(queryInserisciAssignedPhone);
		queryInserisciAssignedPhone.executeUpdate();		
		conn.close();
	}
	
	/**
	 * Elimina phone number.
	 *
	 * @param number the number
	 */
	public void eliminaPhoneNumber(String number) {
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryEliminaPhoneNumber = conn.prepareStatement("DELETE FROM PhoneNumber WHERE ContactID = '"+number+"'");
			System.out.println(queryEliminaPhoneNumber);
			queryEliminaPhoneNumber.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserisci email.
	 *
	 * @param email the email
	 * @param main the main
	 * @param contactID the contact ID
	 * @throws SQLException the SQL exception
	 */
	public void inserisciEmail(String email, Boolean main, int contactID) throws SQLException {
		conn = ConnessionePG.getInstance().getConnection();
		PreparedStatement queryInserisciEmail = conn.prepareStatement(
			"INSERT INTO Email VALUES('"+email+"', '"+main.toString()+"', '"+contactID+"')");
		System.out.println(queryInserisciEmail);
		queryInserisciEmail.executeUpdate();
		conn.close();
	}
	
	/**
	 * Inserisci address.
	 *
	 * @param street the street
	 * @param cap the cap
	 * @param citta the citta
	 * @param province the province
	 * @param naz the naz
	 * @throws SQLException the SQL exception
	 */
	public void inserisciAddress(String street, String cap, String citta, String province, String naz) throws SQLException {
		
		conn = ConnessionePG.getInstance().getConnection();
		PreparedStatement queryInserisciEmail = conn.prepareStatement(
			"INSERT INTO Address VALUES('"+street+"', '"+cap+"', '"+citta+"', '"+province+"', '"+naz+"')");
		System.out.println(queryInserisciEmail);
		queryInserisciEmail.executeUpdate();
		conn.close();
	}
	
	/**
	 * Inserisci assigned address.
	 *
	 * @param contactID the contact ID
	 * @param street the street
	 * @param cap the cap
	 * @param main the main
	 * @throws SQLException the SQL exception
	 */
	public void inserisciAssignedAddress(int contactID, String street, String cap, Boolean main) throws SQLException {
		
		conn = ConnessionePG.getInstance().getConnection();
		PreparedStatement queryInserisciAssignedAddress = conn.prepareStatement(
				"INSERT INTO AssignedAddress VALUES('"+contactID+"', '"+street+"', '"+cap+"', "+main.toString()+")");
		System.out.println(queryInserisciAssignedAddress);
		queryInserisciAssignedAddress.executeUpdate();
		conn.close();
	}
	
	/**
	 * Elimina address.
	 *
	 * @param street the street
	 * @param cap the cap
	 */
	public void eliminaAddress(String street, String cap) {
		
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryEliminaAddress = conn.prepareStatement("DELETE FROM Address WHERE street = '"+street+"' and zipCode = '"+cap+"'");
			System.out.println(queryEliminaAddress);
			queryEliminaAddress.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cerca phone number.
	 *
	 * @param number the number
	 * @return the result set
	 */
	public ResultSet cercaPhoneNumber(String number) {
		
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryCercaPhoneNumber = conn.prepareStatement("SELECT * FROM PhoneNumber WHERE phoneNumber = '"+number+"'");
			System.out.println(queryCercaPhoneNumber);
			result = queryCercaPhoneNumber.executeQuery();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
/**
 * Cerca address.
 *
 * @param street the street
 * @param cap the cap
 * @return the result set
 */
public ResultSet cercaAddress(String street, String cap) {
		
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryCercaAddress = conn.prepareStatement("SELECT * FROM Address WHERE street = '"+street+"' and zipCode = '"+cap+"'");
			System.out.println(queryCercaAddress);
			result = queryCercaAddress.executeQuery();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

public ResultSet cercaEmail(String email) {
	try {
		conn = ConnessionePG.getInstance().getConnection();
		PreparedStatement queryCercaEmail = conn.prepareStatement("SELECT * FROM email WHERE email = '"+email+"'");
		System.out.println(queryCercaEmail);
		result = queryCercaEmail.executeQuery();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
}

public void setContactEmail(String email, int contactID) {
	try {
		conn = ConnessionePG.getInstance().getConnection();
		PreparedStatement querySetEmail = conn.prepareStatement("UPDATE Email SET contactID = "+contactID+" WHERE email = '"+email+"'");
		System.out.println(querySetEmail);
		querySetEmail.executeUpdate();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public void setMainEmail(String email, boolean b) {
	try {
		conn = ConnessionePG.getInstance().getConnection();
		PreparedStatement querySetMain;
		if(b)
			querySetMain = conn.prepareStatement("UPDATE Email SET main = true WHERE email = '"+email+"'");
		else
			querySetMain = conn.prepareStatement("UPDATE Email SET main = false WHERE email = '"+email+"'");
		System.out.println(querySetMain);
		querySetMain.executeUpdate();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

	public void setLinked(String number, boolean b) {
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement querySetLinked;
			if(b)
				querySetLinked = conn.prepareStatement("UPDATE PhoneNumber SET linkednumber = true WHERE phoneNumber = '"+number+"'");
			else
				querySetLinked = conn.prepareStatement("UPDATE PhoneNumber SET linkednumber = false WHERE phoneNumber = '"+number+"'");
			System.out.println(querySetLinked);
			querySetLinked.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

