package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

import Controller.Controller;
import Modello.Utente;

public class AddContatto extends JFrame{
	
	Utente user;
	Controller controller;
	
	AddContatto(Controller ctrll, Utente utente){
		
		user = utente;
		controller = ctrll;
		JFrame window = new JFrame();
		
		JPanel dataPanel  = new JPanel();
		dataPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label1 = new JLabel("Nome");
		JLabel label2 = new JLabel("Cognome");
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(150, 25));
		JTextField surnameText = new JTextField();
		surnameText.setPreferredSize(new Dimension(150, 25));
		dataPanel.add(label1);
		dataPanel.add(nameText);
		dataPanel.add(label2);
		dataPanel.add(surnameText);
		dataPanel.setBounds(5, 5, 175, 125);
		dataPanel.setBackground(Color.GRAY);
		
		JPanel numberPanel = new JPanel();
		numberPanel.setLayout(new GridLayout(2, 2, 20, 5));
		JLabel text1 = new JLabel("Cellulare");
		JLabel text2 = new JLabel("Telefono fisso");
		JTextField mobileText = new JTextField();
		mobileText.setPreferredSize(new Dimension(100, 25));
		JTextField landlineText = new JTextField();
		landlineText.setPreferredSize(new Dimension(100, 25));
		var scroll = new JScrollPane(numberPanel);
		numberPanel.add(text1);
		numberPanel.add(text2);
		numberPanel.add(mobileText);
		numberPanel.add(landlineText);
		numberPanel.setBounds(5, 150, 200, 50);
		numberPanel.setBackground(Color.GRAY);
		scroll.setBounds(5, 150, 200, 50);
		
		JPanel emailPanel = new JPanel();
		JLabel emailLabel = new JLabel("E-mail");
		JTextField emailText = new JTextField();
		emailText.setPreferredSize(new Dimension(150, 25));
		emailPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		emailPanel.setBackground(Color.GRAY);
		emailPanel.setBounds(250, 150, 175, 75);
		emailPanel.add(emailLabel);
		emailPanel.add(emailText);
		
		JPanel addressPanel = new JPanel();
		
		addressPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		addressPanel.setBackground(Color.GRAY);
		addressPanel.setBounds(5, 275, 300, 250);
		
		window.setVisible(true);
		window.setLayout(null);
		window.setTitle("Nuovo contatto");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setSize(480, 640);
		window.add(scroll);
		window.add(dataPanel);
		window.add(emailPanel);
		window.add(addressPanel);
	}
}
