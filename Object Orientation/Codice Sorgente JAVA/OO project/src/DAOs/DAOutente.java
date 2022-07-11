package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnessioneDatabase.ConnessionePG;

public class DAOutente {
	
	private Connection conn;
	ResultSet result;
	
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
	
	public void inserisciUtente(String email, String password, String nickName) throws SQLException {
		
		conn = ConnessionePG.getInstance().getConnection();
		PreparedStatement queryInserisciUtente = conn.prepareStatement("INSERT INTO R_user VALUES('"+email+"', '"+password+"', '"+nickName+"', default)");
		System.out.println(queryInserisciUtente);
		queryInserisciUtente.executeUpdate();
		conn.close();

	}
	
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
