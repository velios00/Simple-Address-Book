package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

import Controller.Controller;
import DAOs.DAOcontatto;
import Modello.Contatto;
import Modello.Email;
import Modello.Indirizzo;
import Modello.NumeriTel;
import Modello.Utente;

public class AddContatto extends JFrame{
	
	Utente user;
	Controller controller;
	LayerContatti chiamante;
	JTextField nameText;
	JTextField surnameText;
	ArrayList<JTextField> mobilesText;
	ArrayList<JTextField> landlinesText;
	ArrayList<JTextField> emailsText;
	ArrayList<ArrayList<JTextField>> addressesText;
	
	AddContatto(Controller ctrll, Utente utente, LayerContatti caller){
		
		user = utente;
		controller = ctrll;
		chiamante = caller;
		mobilesText = new ArrayList<JTextField>();
		landlinesText = new ArrayList<JTextField>();
		emailsText = new ArrayList<JTextField>();
		addressesText = new ArrayList<ArrayList<JTextField>>();
		JFrame window = new JFrame();
		JButton btnCrea = new JButton("Crea");
		btnCrea.setBounds(400, 520, 100, 30);
		btnCrea.addActionListener(e -> addContact(window));
		
		JPanel dataPanel  = new JPanel();
		dataPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label1 = new JLabel("Nome");
		JLabel label2 = new JLabel("Cognome");
		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(150, 25));
		surnameText = new JTextField();
		surnameText.setPreferredSize(new Dimension(150, 25));
		dataPanel.add(label1);
		dataPanel.add(nameText);
		dataPanel.add(label2);
		dataPanel.add(surnameText);
		dataPanel.setBounds(5, 5, 175, 125);
		dataPanel.setBackground(Color.GRAY);
		
		JPanel mobilePanel = new JPanel();
		JPanel landlinePanel = new JPanel();
		JPanel numberPanel = createNumberPanel(mobilePanel, landlinePanel);
		numberPanel.setPreferredSize(new Dimension(250, 75));
		JScrollPane scrollNumber = createScrollableFields(5, 150, 250, 100, numberPanel);
		JButton addMobile = new JButton("Aggiungi");
		JButton addLandline = new JButton("Aggiungi");
		addMobile.setBounds(5, 255, 120, 20);
		addMobile.addActionListener(e -> addNumberField(numberPanel, mobilePanel, 0));
		addLandline.setBounds(130, 255, 120, 20);
		addLandline.addActionListener(e -> addNumberField(numberPanel, landlinePanel, 1));
		
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
		window.add(addMobile);
		window.add(addLandline);
		window.add(addAddress);
		window.add(btnCrea);
	}
	
	private JScrollPane createScrollableFields(int x, int y, int larg, int lun, JPanel comp) {
		
		JScrollPane scroll = new JScrollPane(comp);
		scroll.setBounds(x, y, larg, lun);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		return scroll;
	}
	
	private JPanel createNumberPanel(JPanel firstPanel1, JPanel firstPanel2) {
		
		mobilesText.add(createTextField(100, 25));
		landlinesText.add(createTextField(100, 25));
		JTextField mobileText = mobilesText.get(0);
		JTextField landlineText = landlinesText.get(0);
		
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
	
	private void addNumberField(JPanel parentPanel, JPanel panel, int type) {
		
		JTextField newField = createTextField(100, 25);
		if(type == 0)
			mobilesText.add(newField);
		else 
			landlinesText.add(newField);
		panel.add(newField);
		parentPanel.setPreferredSize(new Dimension(parentPanel.getWidth(), parentPanel.getHeight()+30));
		panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()+30));
		panel.revalidate();
		parentPanel.revalidate();
		panel.repaint();
		parentPanel.repaint();
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
	
	public void addContact(JFrame window) {
		
		Contatto nuovoCont;
		ArrayList<NumeriTel> contNumbers = new ArrayList<NumeriTel>();
		ArrayList<Indirizzo> contAddress = new ArrayList<Indirizzo>();
		ArrayList<Email> contEmail = new ArrayList<Email>();
		//CONTATTO/////////////////////////////////////////////////////////////////////////////
		if(!(nameText.getText()+surnameText.getText()).isBlank()) {
			try {
				nuovoCont = controller.aggiungiContatto(user.email, nameText.getText(), surnameText.getText(), "default", false);
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}
		}
		else {
			JOptionPane.showMessageDialog(new JFrame(),"Inserire correttamente il nome del contatto" , "Errore", JOptionPane.WARNING_MESSAGE);
			return;
		}
		try {
			//NUMERI MOBILI/////////////////////////////////////////////////////////////////////////////
			for(JTextField text : mobilesText)
				if(!text.getText().isBlank())
					contNumbers.add(controller.getPhoneNumber("MOBILE", text.getText(), false, nuovoCont.contactID));
			//NUMERI FISSI//////////////////////////////////////////////////////////////////////////////
			for(JTextField text : landlinesText)
				if(!text.getText().isBlank())
					contNumbers.add(controller.getPhoneNumber("LANDLINE", text.getText(), false, nuovoCont.contactID));
			//INDIRIZZI//////////////////////////////////////////////////////////////////////////////
			for(ArrayList<JTextField> t : addressesText)
				if(!isAddressEmpty(t))
					contAddress.add(controller.getAddress(t.get(0).getText()+", "+t.get(1).getText(), t.get(2).getText(), t.get(3).getText(), t.get(4).getText(), t.get(5).getText(), nuovoCont.contactID, true));
			//EMAILS//////////////////////////////////////////////////////////////////////////////
			for(JTextField text : emailsText)
				if(!text.getText().isBlank())
					contEmail.add(controller.aggiungiEmail(text.getText(), false, nuovoCont.contactID));
		} catch (SQLException e) {
			new DAOcontatto().eliminaContatto(nuovoCont.contactID);
			if(e.getMessage().contains("emailformat"))
				JOptionPane.showMessageDialog(new JFrame(),"Indirizzo E-mail non valido" , "Errore", JOptionPane.WARNING_MESSAGE);
			else if(e.getMessage().contains("character(2)"))
				JOptionPane.showMessageDialog(new JFrame(),"Indirizzo non inserito correttamente" , "Errore", JOptionPane.WARNING_MESSAGE);
			else
				e.printStackTrace();
			return;
		}

		//INSERIMENTO////////////////////////////////////////////////////////////////////////////
		nuovoCont.numeri = contNumbers;
		nuovoCont.indirizzi = contAddress;
		nuovoCont.emails = contEmail;
		user.contatti.add(nuovoCont);
		chiamante.aggiornaContatti();
		window.dispose();
	}
	
	private boolean isAddressEmpty(ArrayList<JTextField> address) {
		
		String str = new String(address.get(0).getText()+address.get(1).getText()+address.get(2).getText());
		return str.isBlank();
	}
}





