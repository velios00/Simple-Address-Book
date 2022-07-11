package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnessioneDatabase.ConnessionePG;

public class DAOgruppo {
	
	
	private Connection conn;
	ResultSet result;
	
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
	
	public void modificaGruppoDb(String nomeGruppo, String descrizioneGruppo, String IDGruppo, String email) {
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement queryModificaGruppo = conn.prepareStatement("UPDATE R_GROUP SET GROUPNAME = '" + nomeGruppo + "', DESCRIPTION = '" + descrizioneGruppo + "' WHERE GROUPID = " + IDGruppo + " AND R_USER = '" + email + "'");
			System.out.println(queryModificaGruppo);
			queryModificaGruppo.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}