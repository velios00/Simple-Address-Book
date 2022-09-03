package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnessioneDatabase.ConnessionePG;

// TODO: Auto-generated Javadoc
/**
 * DAO (Database Access Object) che si occupa di recuperare dal database l'utente, i contatti, i gruppi e le chiamate della rubrica.
 * La classe permette anche l'inserimento di un nuovo utente all'interno del database.
 */
public class DAOutente {
	
	/** Contiente la connessione stabilita con il database */
	private Connection conn;
	
	/** Eventuale risultato della query effettuata nel database */
	ResultSet result;
	
	/**
	 * Cerca utente.
	 *
	 * @param email the email
	 * @return the result set
	 */
	public ResultSet cercaUtente(String email) {
		
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryCercaUtente = conn.prepareStatement("SELECT * FROM R_user WHERE email = '"+email+"'");
			System.out.println(queryCercaUtente);
			result = queryCercaUtente.executeQuery();
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
			return  null;
		}
		return result;
	}
	
	/**
	 * Inserisci utente.
	 *
	 * @param email the email
	 * @param password the password
	 * @param nickName the nick name
	 * @throws SQLException the SQL exception
	 */
	public void inserisciUtente(String email, String password, String nickName) throws SQLException {
		
		conn = ConnessionePG.getInstance().getConnection();
		PreparedStatement queryInserisciUtente = conn.prepareStatement("INSERT INTO R_user VALUES('"+email+"', '"+password+"', '"+nickName+"', default)");
		System.out.println(queryInserisciUtente);
		queryInserisciUtente.executeUpdate();
		conn.close();

	}
	
	/**
	 * Cerca contatti.
	 *
	 * @param email the email
	 * @return the result set
	 */
	public ResultSet cercaContatti(String email) {
		
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryCercaContatti = conn.prepareStatement("SELECT * FROM Contact WHERE R_user = '"+email+"'");
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
	 * Cerca gruppi.
	 *
	 * @param email the email
	 * @return the result set
	 */
	public ResultSet cercaGruppi(String email) {
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryCercaContatti = conn.prepareStatement("SELECT * FROM R_Group WHERE R_user = '"+email+"'");
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
	 * Cerca chiamate.
	 *
	 * @param email the email
	 * @return the result set
	 */
	public ResultSet cercaChiamate(String email) {
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryCercaContatti = conn.prepareStatement("SELECT * FROM PhoneCall WHERE R_user = '"+email+"'");
			System.out.println(queryCercaContatti);
			result = queryCercaContatti.executeQuery();
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
			return  null;
		}
		return result;
	}
}
