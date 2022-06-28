package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public void eliminaContatto(String ID) {
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
	
	public void inserisciPhoneNumber(String type, String numero, Boolean linkednumber) throws SQLException {
		conn = ConnessionePG.getInstance().getConnection();
		PreparedStatement queryInserisciPhoneNumber = conn.prepareStatement(
			"INSERT INTO PhoneNumber VALUES('"+type+"', '"+numero+"', '"+linkednumber.toString()+"')");
		System.out.println(queryInserisciPhoneNumber);
		queryInserisciPhoneNumber.executeUpdate();	
		conn.close();
	}
	
	public void inserisciAssignedPhone(String numero, String contactID) throws SQLException {
		
		conn = ConnessionePG.getInstance().getConnection();
		PreparedStatement queryInserisciAssignedPhone = conn.prepareStatement("INSERT INTO AssignedPhone VALUES('"+numero+"', '"+contactID+"')");
		System.out.println(queryInserisciAssignedPhone);
		queryInserisciAssignedPhone.executeUpdate();		
		conn.close();
	}
	
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
	
	public void inserisciEmail(String email, Boolean main, String contactID) throws SQLException {
		conn = ConnessionePG.getInstance().getConnection();
		PreparedStatement queryInserisciEmail = conn.prepareStatement(
			"INSERT INTO Email VALUES('"+email+"', '"+main.toString()+"', '"+contactID+"')");
		System.out.println(queryInserisciEmail);
		queryInserisciEmail.executeUpdate();
		conn.close();
	}
	
	public void inserisciAddress(String street, String cap, String citta, String province, String naz) throws SQLException {
		
		conn = ConnessionePG.getInstance().getConnection();
		PreparedStatement queryInserisciEmail = conn.prepareStatement(
			"INSERT INTO Address VALUES('"+street+"', '"+cap+"', '"+citta+"', '"+province+"', '"+naz+"')");
		System.out.println(queryInserisciEmail);
		queryInserisciEmail.executeUpdate();
		conn.close();
	}
	
	public void inserisciAssignedAddress(String contactID, String street, String cap, Boolean main) throws SQLException {
		
		conn = ConnessionePG.getInstance().getConnection();
		PreparedStatement queryInserisciAssignedAddress = conn.prepareStatement(
				"INSERT INTO AssignedAddress VALUES('"+contactID+"', '"+street+"', '"+cap+"', "+main.toString()+")");
		System.out.println(queryInserisciAssignedAddress);
		queryInserisciAssignedAddress.executeUpdate();
		conn.close();
	}
	
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
}

