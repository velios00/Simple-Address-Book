package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.postgresql.util.PSQLException;

import DAOs.DAOutente;
import Modello.Utente;

public class Controller {
	
	
	public Utente accessoUtente(String email, String password) throws SQLException{
		
		ResultSet result = new DAOutente().cercaUtente(email);
		if(result.next() == false) { 
			JOptionPane.showMessageDialog(new JFrame(), "Nessun account collegato a "+email, "Errore", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		Utente currentUser = new Utente(result.getString(1), result.getString(2),result.getString(3));
		if(password.equals(currentUser.password)) {
			return currentUser;
		}
		else {
			JOptionPane.showMessageDialog(new JFrame(), "La password non è corretta!", "Errore", JOptionPane.WARNING_MESSAGE);
			return null;
		}
	}
	
	public Utente registraUtente(String email, String password, String nickName) {
		
		try {
			new DAOutente().inserisciUtente(email, password, nickName);
		} catch (PSQLException e1) {
			if(e1.getMessage().contains("emailformat"))
				JOptionPane.showMessageDialog(new JFrame(),"E-mail non valida" , "Errore", JOptionPane.WARNING_MESSAGE);
			else if (e1.getMessage().contains("r_user_pkey"))
				JOptionPane.showMessageDialog(new JFrame(),"E-mail in uso da un altro account" , "Errore", JOptionPane.WARNING_MESSAGE);
			else if (e1.getMessage().contains("passwordlen"))
				JOptionPane.showMessageDialog(new JFrame(),"La password deve essere almeno di 8 caratteri" , "Errore", JOptionPane.WARNING_MESSAGE);
			else if (e1.getMessage().contains("usernamelen"))
				JOptionPane.showMessageDialog(new JFrame(),"Il nome utente deve essere di almeno 3 caratteri" , "Errore", JOptionPane.WARNING_MESSAGE);
			else
				e1.printStackTrace();
			return null;
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return new Utente(email, password, nickName);
	}
}
