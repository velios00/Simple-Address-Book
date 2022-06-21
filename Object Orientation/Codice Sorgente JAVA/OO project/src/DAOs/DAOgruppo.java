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
}
