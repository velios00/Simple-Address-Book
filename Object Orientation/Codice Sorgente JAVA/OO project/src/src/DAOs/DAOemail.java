package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnessioneDatabase.ConnessionePG;

// TODO: Auto-generated Javadoc
/**
 * DAO (Database Access Object) che si occupa di recuperare dal database gli account di messaging collegati ad una email.
 */
public class DAOemail {
	
	/** Contiente la connessione stabilita con il database */
	private Connection conn;
	
	/** Eventuale risultato della query effettuata nel database */
	ResultSet result;
	
	/**
	 * Cerca messaging.
	 *
	 * @param contactEmail the contact email
	 * @return the result set
	 */
	public ResultSet cercaMessaging(String contactEmail) {
		
		try {
			conn = ConnessionePG.getInstance().getConnection();
			PreparedStatement query = conn.prepareStatement("SELECT * FROM Messaging WHERE assemail = '"+contactEmail+"'");
			System.out.println(query);
			result = query.executeQuery();
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
			return  null;
		}
		return result;
	}
}
