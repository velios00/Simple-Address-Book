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

public class Login extends JFrame {
	
	JButton btnAccedi;
	JButton btnRegistrati;
	JTextField emailField, passField;
	Controller controller;
	
	public static void main(String[] args) {
		new Login();
	}
	
	Login(){
		
		JFrame login = new JFrame();
		JPanel mainPanel = new JPanel();
		JLabel label1 = new JLabel("E-mail:");
		JLabel label2 = new JLabel("Password:");
		emailField = new JTextField("gianmarcolembo@gmail.com");
		passField = new JTextField("password");
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
	
	private void goToRegistrazione(JFrame chiamante) {
		System.out.println("Registrazione...");
		new Registrazione(controller, chiamante);
	}
	
	private void goToHome(JFrame chiamante) throws SQLException {
		System.out.println(emailField.getText());
		System.out.println("Accesso...");
		Utente user = controller.accessoUtente(emailField.getText(), passField.getText());
		if(user != null)
			new Home(controller, chiamante, user);
	}
}
