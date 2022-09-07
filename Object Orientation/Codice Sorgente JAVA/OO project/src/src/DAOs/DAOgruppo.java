package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnessioneDatabase.ConnessionePG;

// TODO: Auto-generated Javadoc
/**
 * DAO (Database Access Object) che si occupa di gestire i gruppi all'interno del database, ovvero aggiungerne, eliminarne  o modificarne uno.
 * La classe è in grado anche di recuperare le informazioni di un gruppo.
 */
public class DAOgruppo {
	
	
	/** Contiente la connessione stabilita con il database */
	private Connection conn;
	
	/** Eventuale risultato della query effettuata nel database */
	ResultSet result;
	
	/**
	 * Cerca contatti gruppo.
	 *
	 * @param groupID the group ID
	 * @return the result set
	 */
	public ResultSet cercaContattiGruppo(String groupID) {
		
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryCercaUtente = conn.prepareStatement("SELECT contactID FROM Participant WHERE groupID = '"+groupID+"'");
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
	 * Aggiungi contatto gruppo.
	 *
	 * @param contactID the contact ID
	 * @param groupID the group ID
	 */
	public void aggiungiContattoGruppo(int contactID, String groupID) {
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryAggiungiAGruppo = conn.prepareStatement("INSERT INTO PARTICIPANT VALUES ('" + groupID + "', '" + contactID + "', CURRENT_DATE)");
			System.out.println(queryAggiungiAGruppo);
			queryAggiungiAGruppo.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
	}
	}

	/**
	 * Rimuovi contatto gruppo.
	 *
	 * @param contactID the contact ID
	 * @param groupID the group ID
	 */
	public void rimuoviContattoGruppo(int contactID, String groupID) {
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryRimuoviAGruppo = conn.prepareStatement("DELETE FROM PARTICIPANT WHERE GROUPID = '" + groupID + "' AND CONTACTID = '" + contactID + "'");
			System.out.println(queryRimuoviAGruppo);
			queryRimuoviAGruppo.executeUpdate();
			conn.close();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
}
	
	/**
	 * Crea gruppo db.
	 *
	 * @param nomeGruppo the nome gruppo
	 * @param descrizioneGruppo the descrizione gruppo
	 * @param email the email
	 * @return the result set
	 */
	public ResultSet creaGruppoDb(String nomeGruppo, String descrizioneGruppo, String email) {
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryCreaGruppo = conn.prepareStatement("INSERT INTO R_GROUP VALUES (DEFAULT, '" + nomeGruppo + "', CURRENT_DATE, '" + descrizioneGruppo + "', '" + email + "') RETURNING *");
			System.out.println(queryCreaGruppo);
			result = queryCreaGruppo.executeQuery();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Elimina gruppo db.
	 *
	 * @param groupID the group ID
	 */
	public void eliminaGruppoDb(String groupID) {
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryEliminaGruppo = conn.prepareStatement("DELETE FROM R_GROUP WHERE GROUPID = '" + groupID + "'");
			System.out.println(queryEliminaGruppo);
			queryEliminaGruppo.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Modifica gruppo db.
	 *
	 * @param nomeGruppo the nome gruppo
	 * @param descrizioneGruppo the descrizione gruppo
	 * @param IDGruppo the ID gruppo
	 */
	public void modificaGruppoDb(String nomeGruppo, String descrizioneGruppo, String IDGruppo) {
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryModificaGruppo = conn.prepareStatement("UPDATE R_GROUP SET GROUPNAME = '" + nomeGruppo + "', DESCRIPTION = '" + descrizioneGruppo + "' WHERE GROUPID = " + IDGruppo);
			System.out.println(queryModificaGruppo);
			queryModificaGruppo.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}