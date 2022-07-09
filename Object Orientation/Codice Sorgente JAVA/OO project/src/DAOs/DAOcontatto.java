package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnessioneDatabase.ConnessionePG;

public class DAOcontatto {

	private Connection conn;
	ResultSet result;
	
	public ResultSet getMainEmail(String contactID) {
		
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
	
	public ResultSet getPhoneNumber(String contactID, String tipo) {
		
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
	
	public ResultSet cercaIndirizzi(String contactID) {
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
	
	public ResultSet cercaNumeriTel(String contactID) {
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
	
	public ResultSet cercaEmail(String contactID) {
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
}
