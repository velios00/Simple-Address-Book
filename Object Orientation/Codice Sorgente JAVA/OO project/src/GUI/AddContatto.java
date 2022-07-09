package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
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
	ArrayList<JTextField> mobilesText;
	ArrayList<JTextField> landlinesText;
	ArrayList<JTextField> emailsText;
	ArrayList<ArrayList<JTextField>> addressesText;
	
	AddContatto(Controller ctrll, Utente utente){
		
		user = utente;
		controller = ctrll;
		mobilesText = new ArrayList<JTextField>();
		landlinesText = new ArrayList<JTextField>();
		emailsText = new ArrayList<JTextField>();
		addressesText = new ArrayList<ArrayList<JTextField>>();
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
		
		JPanel numberPanel = createNumberPanel();
		numberPanel.setPreferredSize(new Dimension(250, 75));
		JScrollPane scrollNumber = createScrollableFields(5, 150, 250, 100, numberPanel);
		JButton addNumbers = new JButton("Aggiungi");
		addNumbers.setBounds(5, 255, 120, 20);
		addNumbers.addActionListener(e -> addNumberField(numberPanel));
		
		JPanel emailPanel = createEmailPanel();
		emailPanel.setPreferredSize(new Dimension(190, 75));
		JScrollPane scrollEmail = createScrollableFields(300, 150, 190, 100, emailPanel);
		JButton addEmail = new JButton("Aggiungi");
		addEmail.setBounds(300, 255, 120, 20);
		addEmail.addActionListener(e -> addEmailField(emailPanel));
		
		JPanel addressPanel = createAddressPanel();
		addressPanel.setPreferredSize(new Dimension(275, 175));
		JScrollPane scrollAddress = createScrollableFields(5, 275, 275, 250, addressPanel);
		JButton addAddress = new JButton("Aggiungi");
		addAddress.setBounds(5, 525, 120, 20);
		addAddress.addActionListener(e -> addAddressField(addressPanel));
		
		window.setVisible(true);
		window.setLayout(null);
		window.setTitle("Nuovo contatto");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setSize(560, 640);
		window.add(scrollNumber);
		window.add(dataPanel);
		window.add(scrollEmail);
		window.add(scrollAddress);
		window.add(addEmail);
		window.add(addNumbers);
		window.add(addAddress);
	}
	
	private JScrollPane createScrollableFields(int x, int y, int larg, int lun, JPanel comp) {
		
		JScrollPane scroll = new JScrollPane(comp);
		scroll.setBounds(x, y, larg, lun);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		return scroll;
	}
	
	private JPanel createNumberPanel() {
		
		mobilesText.add(createTextField(100, 25));
		landlinesText.add(createTextField(100, 25));
		JTextField mobileText = mobilesText.get(0);
		JTextField landlineText = landlinesText.get(0);
		
		JPanel firstPanel1 = new JPanel();
		JPanel firstPanel2 = new JPanel();
		firstPanel1.setPreferredSize(new Dimension(110, 60));
		firstPanel2.setPreferredSize(new Dimension(110, 60));
		firstPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		firstPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel numberPanel = new JPanel();
		JLabel text1 = new JLabel("Cellulare");
		JLabel text2 = new JLabel("Telefono fisso");
		
		firstPanel1.add(text1);
		firstPanel2.add(text2);
		firstPanel1.add(mobileText);
		firstPanel2.add(landlineText);
		
		numberPanel.add(firstPanel1);
		numberPanel.add(firstPanel2);
		numberPanel.setBackground(Color.GRAY);
		numberPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		return numberPanel;
	}
	
	private JPanel createEmailPanel() {
		
		JPanel emailPanel = new JPanel();
		JLabel emailLabel = new JLabel("E-mail");
		emailsText.add(createTextField(150, 25));
		JTextField emailField = emailsText.get(0);
		
		emailPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		emailPanel.setBackground(Color.GRAY);
		emailPanel.add(emailLabel);
		emailPanel.add(emailField);
		return emailPanel;
	}
	
	private JPanel createAddressPanel() {
		
		JPanel addressPanel = new JPanel();
		JLabel streetLabel = new JLabel("Strada");
		JLabel nLabel = new JLabel("n.");
		JLabel capLabel = new JLabel("CAP");
		JLabel citLabel = new JLabel("Città");
		JLabel nazLabel = new JLabel("Nazione");
		addressesText.add(createAddressFields());
		ArrayList<JTextField> addressField = addressesText.get(0);
		
		addressPanel.add(streetLabel);
		addressPanel.add(addressField.get(0));
		addressPanel.add(nLabel);
		addressPanel.add(addressField.get(1));
		addressPanel.add(capLabel);
		addressPanel.add(addressField.get(2));
		addressPanel.add(citLabel);
		addressPanel.add(addressField.get(3));
		addressPanel.add(addressField.get(4));
		addressPanel.add(nazLabel);
		addressPanel.add(addressField.get(5));
		addressPanel.setBackground(Color.GRAY);
		addressPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel gapPanel = new JPanel();
		gapPanel.setPreferredSize(new Dimension(270, 10));
		addressPanel.add(gapPanel);
		
		return addressPanel;
	}
	
	public JTextField createTextField(int larg, int lung) {
		
		JTextField nuovo = new JTextField();
		nuovo.setPreferredSize(new Dimension(larg, lung));
		return nuovo;
	}
	
	public ArrayList<JTextField> createAddressFields(){
		
		ArrayList<JTextField> fields = new ArrayList<JTextField>();
		fields.add(createTextField(120, 25));
		fields.add(createTextField(30, 25));
		fields.add(createTextField(75, 25));
		fields.add(createTextField(90, 25));
		fields.add(createTextField(30, 25));
		fields.add(createTextField(80, 25));
		return fields;
	}
	
	private void addEmailField(JPanel panel) {
		
		JTextField newField = createTextField(150, 25);
		emailsText.add(newField);
		panel.add(newField);
		panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()+30));
		panel.revalidate();
		panel.repaint();
	}
	
	private void addNumberField(JPanel panel) {
		
		JTextField newField1 = createTextField(100, 25);
		JTextField newField2 = createTextField(100, 25);
		mobilesText.add(newField1);
		landlinesText.add(newField2);
		panel.add(newField1);
		panel.add(newField2);
		panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()+30));
		panel.revalidate();
		panel.repaint();
	}
	
	private void addAddressField(JPanel panel) {

		JLabel streetLabel = new JLabel("Strada");
		JLabel nLabel = new JLabel("n.");
		JLabel capLabel = new JLabel("CAP");
		JLabel citLabel = new JLabel("Città");
		JLabel nazLabel = new JLabel("Nazione");
		
		ArrayList<JTextField> newAddress = createAddressFields();
		addressesText.add(newAddress);
		
		panel.add(streetLabel);
		panel.add(newAddress.get(0));
		panel.add(nLabel);
		panel.add(newAddress.get(1));
		panel.add(capLabel);
		panel.add(newAddress.get(2));
		panel.add(citLabel);
		panel.add(newAddress.get(3));
		panel.add(newAddress.get(4));
		panel.add(nazLabel);
		panel.add(newAddress.get(5));
		JPanel gapPanel = new JPanel();
		gapPanel.setPreferredSize(new Dimension(270, 10));
		panel.add(gapPanel);
		panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()+105));
		panel.revalidate();
		panel.repaint();
	}
}




