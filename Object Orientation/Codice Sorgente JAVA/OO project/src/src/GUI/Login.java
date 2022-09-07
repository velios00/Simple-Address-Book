package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Controller;
import Modello.Utente;

// TODO: Auto-generated Javadoc
/**
 * Questa e' la classe Login, rappresenta l'interfaccia grafica della sezione, appunto, di login nella rubrica.
 * Contiene diversi {@link JButton} e {@link JTextField}, a cui sono associati eventi che permettono l'accesso alla rubrica,
 * ovvero la comparsa della {@link Registrazione} e della {@link Home}.
 */
public class Login extends JFrame {
	
	/** The btn accedi. */
	JButton btnAccedi;
	
	/** The btn registrati. */
	JButton btnRegistrati;
	
	/** The pass field. */
	JTextField emailField, passField;
	
	/** The controller. */
	Controller controller;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new Login();
	}
	
	/**
	 * Instantiates a new login.
	 */
	Login(){
		
		JFrame login = new JFrame();
		JPanel mainPanel = new JPanel();
		JLabel label1 = new JLabel("E-mail:");
		JLabel label2 = new JLabel("Password:");
		emailField = new JTextField("simoneveniero00@gmail.com");
		passField = new JTextField("simone00");
		controller = new Controller();
		btnAccedi = new JButton("Accedi");
		btnRegistrati = new JButton("Registrati");
		
		btnRegistrati.setBounds(170, 125, 100, 30);
		btnAccedi.setBounds(280, 125, 100, 30);
		btnRegistrati.addActionListener(e -> goToRegistrazione(login));
		btnAccedi.addActionListener(e -> {
			try {
				goToHome(login);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		emailField.setPreferredSize(new Dimension(150, 25));
		passField.setPreferredSize(new Dimension(150, 25));
		
		mainPanel.add(label1);
		mainPanel.add(emailField);
		mainPanel.add(label2);
		mainPanel.add(passField);
		mainPanel.setBounds(60, 25, 250, 70);
		mainPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		login.setLayout(null);
		login.setVisible(true);
		login.setTitle("Login");
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setSize(420, 210);
		login.add(mainPanel);
		login.add(btnRegistrati);
		login.add(btnAccedi);

	}
	
	/**
	 * Go to registrazione.
	 *
	 * @param chiamante the chiamante
	 */
	private void goToRegistrazione(JFrame chiamante) {
		System.out.println("Registrazione...");
		new Registrazione(controller, chiamante);
	}
	
	/**
	 * Go to home.
	 *
	 * @param chiamante the chiamante
	 * @throws SQLException the SQL exception
	 */
	private void goToHome(JFrame chiamante) throws SQLException {
		System.out.println(emailField.getText());
		System.out.println("Accesso...");
		Utente user = controller.accessoUtente(emailField.getText(), passField.getText());
		if(user != null)
			new Home(controller, chiamante, user);
	}
}
