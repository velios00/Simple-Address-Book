package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnessioneDatabase.ConnessionePG;

public class DAOemail {
	
	private Connection conn;
	ResultSet result;
	
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
