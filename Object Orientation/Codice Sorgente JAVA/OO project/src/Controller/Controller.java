package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
}
