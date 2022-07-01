package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
	ButtonGroup mobileGroup;
	ButtonGroup landlineGroup;
	ButtonGroup emailGroup;
	ButtonGroup addressGroup;
	ArrayList<JRadioButton> mobilesBtn;
	ArrayList<JRadioButton> landlinesBtn;
	ArrayList<JRadioButton> emailsBtn;
	ArrayList<JRadioButton> addressesBtn;
	JLabel imageLabel;
	String profilePicPath;
	JCheckBox favBox;
	
	AddContatto(Controller ctrll, Utente utente, LayerContatti caller){
		
		user = utente;
		controller = ctrll;
		chiamante = caller;
		mobilesText = new ArrayList<JTextField>();
		landlinesText = new ArrayList<JTextField>();
		emailsText = new ArrayList<JTextField>();
		addressesText = new ArrayList<ArrayList<JTextField>>();
		mobileGroup = new ButtonGroup();
		landlineGroup = new ButtonGroup();
		emailGroup = new ButtonGroup();
		addressGroup = new ButtonGroup();
		mobilesBtn = new ArrayList<JRadioButton>();
		landlinesBtn = new ArrayList<JRadioButton>();
		emailsBtn = new ArrayList<JRadioButton>();
		addressesBtn = new ArrayList<JRadioButton>();
		profilePicPath = new String("C:\\Users\\Velios\\Desktop\\Uni\\cd\\defaultpic");
		JFrame window = new JFrame();
		JButton btnCrea = new JButton("Crea");
		btnCrea.setBounds(400, 520, 100, 30);
		btnCrea.addActionListener(e -> addContact(window));
		
		ImageIcon image = new ImageIcon(new ImageIcon("defaultpic.jpg").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
		imageLabel = new JLabel();
		imageLabel.setIcon(image);
		imageLabel.setBounds(350, 5, 120, 120);
		JButton btnSelectPic = new JButton("Seleziona...");
		btnSelectPic.setBounds(350, 130, 100, 30);
		btnSelectPic.addActionListener(e -> selectImage());
		
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
		
		favBox = new JCheckBox("Preferiti");
		favBox.setBounds(180, 80, 100, 30);
		
		JPanel mobilePanel = new JPanel();
		JPanel landlinePanel = new JPanel();
		JPanel numberPanel = createNumberPanel(mobilePanel, landlinePanel);
		numberPanel.setPreferredSize(new Dimension(250, 75));
		JScrollPane scrollNumber = createScrollableFields(5, 150, 300, 100, numberPanel);
		JButton addMobile = new JButton("+");
		JButton addLandline = new JButton("+");
		addMobile.setBounds(5, 255, 50, 20);
		addMobile.addActionListener(e -> addNumberField(numberPanel, mobilePanel, 0));
		addLandline.setBounds(130, 255, 50, 20);
		addLandline.addActionListener(e -> addNumberField(numberPanel, landlinePanel, 1));
		JButton removeMobile = new JButton("-");
		JButton removeLandline = new JButton("-");
		removeMobile.setBounds(65, 255, 50, 20);
		removeMobile.addActionListener(e -> removeNumberField(numberPanel, mobilePanel, 0));
		removeLandline.setBounds(190, 255, 50, 20);
		removeLandline.addActionListener(e -> removeNumberField(numberPanel, landlinePanel, 1));
		
		JPanel emailPanel = createEmailPanel();
		emailPanel.setPreferredSize(new Dimension(190, 75));
		JScrollPane scrollEmail = createScrollableFields(300, 150, 190, 100, emailPanel);
		JButton addEmail = new JButton("+");
		addEmail.setBounds(300, 255, 50, 20);
		addEmail.addActionListener(e -> addEmailField(emailPanel));
		JButton removeEmail = new JButton("-");
		removeEmail.setBounds(360, 255, 50, 20);
		removeEmail.addActionListener(e -> removeEmailField(emailPanel));
		
		JPanel addressPanel = createAddressPanel();
		addressPanel.setPreferredSize(new Dimension(275, 175));
		JScrollPane scrollAddress = createScrollableFields(5, 275, 275, 250, addressPanel);
		JButton addAddress = new JButton("+");
		addAddress.setBounds(5, 525, 50, 20);
		addAddress.addActionListener(e -> addAddressField(addressPanel));
		JButton removeAddress = new JButton("-");
		removeAddress.setBounds(65, 525, 50, 20);
		removeAddress.addActionListener(e -> removeAddressField(addressPanel));
		
		window.setVisible(true);
		window.setLayout(null);
		window.setTitle("Nuovo contatto");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setSize(560, 640);
		window.add(scrollNumber);
		window.add(dataPanel);
		window.add(imageLabel);
		window.add(btnSelectPic);
		window.add(favBox);
		window.add(scrollEmail);
		window.add(scrollAddress);
		window.add(addEmail);
		window.add(removeEmail);
		window.add(addMobile);
		window.add(addLandline);
		window.add(removeMobile);
		window.add(removeLandline);
		window.add(addAddress);
		window.add(removeAddress);
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
		
		firstPanel1.setPreferredSize(new Dimension(140, 60));
		firstPanel2.setPreferredSize(new Dimension(140, 60));
		firstPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		firstPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel numberPanel = new JPanel();
		JLabel text1 = new JLabel("Cellulare");
		JLabel text2 = new JLabel("Telefono fisso");
		JRadioButton newMobileBtn = new JRadioButton();
		newMobileBtn.setSelected(true);
		mobileGroup.add(newMobileBtn);
		mobilesBtn.add(newMobileBtn);
		JRadioButton newLandlineBtn = new JRadioButton();
		newLandlineBtn.setSelected(true);
		landlineGroup.add(newLandlineBtn);
		landlinesBtn.add(newLandlineBtn);
		
		firstPanel1.add(text1);
		firstPanel2.add(text2);
		firstPanel1.add(mobileText);
		firstPanel1.add(newMobileBtn);
		firstPanel2.add(landlineText);
		firstPanel2.add(newLandlineBtn);
		
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
		JRadioButton newBtn = new JRadioButton();
		newBtn.setSelected(true);
		emailGroup.add(newBtn);
		emailsBtn.add(newBtn);
		
		emailPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		emailPanel.setBackground(Color.GRAY);
		emailPanel.add(emailLabel);
		emailPanel.add(emailField);
		emailPanel.add(newBtn);
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
		JRadioButton newBtn = new JRadioButton();
		newBtn.setSelected(true);
		addressGroup.add(newBtn);
		addressesBtn.add(newBtn);
		
		addressPanel.add(streetLabel);
		addressPanel.add(addressField.get(0));
		addressPanel.add(nLabel);
		addressPanel.add(addressField.get(1));
		addressPanel.add(capLabel);
		addressPanel.add(addressField.get(2));
		addressPanel.add(citLabel);
		addressPanel.add(addressField.get(3));
		addressPanel.add(addressField.get(4));
		addressPanel.add(newBtn);
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
		JRadioButton newBtn = new JRadioButton();
		emailGroup.add(newBtn);
		emailsBtn.add(newBtn);
		emailsText.add(newField);
		
		panel.add(newField);
		panel.add(newBtn);
		panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()+30));
		panel.revalidate();
		panel.repaint();
	}
	
	private void removeEmailField(JPanel panel) {
		
		int last = emailsText.size()-1;
		if(last > 0) {
			panel.remove(emailsText.get(last));
			panel.remove(emailsBtn.get(last));
			emailGroup.remove(emailsBtn.get(last));
		
			emailsBtn.remove(last);
			emailsText.remove(last);
			panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()-30));
			panel.repaint();
			panel.revalidate();
		}
	}
	
	private void addNumberField(JPanel parentPanel, JPanel panel, int type) {
		
		JTextField newField = createTextField(100, 25);
		JRadioButton newBtn = new JRadioButton();
		if(type == 0) {
			mobilesText.add(newField);
			mobileGroup.add(newBtn);
			mobilesBtn.add(newBtn);
			if(mobilesText.size() > landlinesText.size())
				parentPanel.setPreferredSize(new Dimension(parentPanel.getWidth(), parentPanel.getHeight()+30));
		}
		else {
			landlinesText.add(newField);
			landlineGroup.add(newBtn);
			landlinesBtn.add(newBtn);
			if(landlinesText.size() > mobilesText.size())
				parentPanel.setPreferredSize(new Dimension(parentPanel.getWidth(), parentPanel.getHeight()+30));
		}
			
		panel.add(newField);
		panel.add(newBtn);
		panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()+30));
		panel.revalidate();
		parentPanel.revalidate();
		panel.repaint();
		parentPanel.repaint();
	}
	
	private void removeNumberField(JPanel parentPanel, JPanel panel, int type) {
		
		int lastMobile = mobilesText.size()-1;
		int lastLandline = landlinesText.size()-1;
		if (type == 0) {
			if(lastMobile > 0) {
				panel.remove(mobilesText.get(lastMobile));
				panel.remove(mobilesBtn.get(lastMobile));
				mobileGroup.remove(mobilesBtn.get(lastMobile));
				
				mobilesBtn.remove(lastMobile);
				mobilesText.remove(lastMobile);
				panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()-30));
				if(lastMobile > lastLandline)
					parentPanel.setPreferredSize(new Dimension(parentPanel.getWidth(), parentPanel.getHeight()-30));
			}
		}
		else {
			if(lastLandline > 0) {
				panel.remove(landlinesText.get(lastLandline));
				panel.remove(landlinesBtn.get(lastLandline));
				landlineGroup.remove(landlinesBtn.get(lastLandline));
				
				landlinesBtn.remove(lastLandline);
				landlinesText.remove(lastLandline);
				panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()-30));
				if(lastLandline > lastMobile)
					parentPanel.setPreferredSize(new Dimension(parentPanel.getWidth(), parentPanel.getHeight()-30));
			}
		}
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
		JRadioButton newBtn = new JRadioButton();
		addressGroup.add(newBtn);
		addressesBtn.add(newBtn);
		
		panel.add(streetLabel);
		panel.add(newAddress.get(0));
		panel.add(nLabel);
		panel.add(newAddress.get(1));
		panel.add(capLabel);
		panel.add(newAddress.get(2));
		panel.add(citLabel);
		panel.add(newAddress.get(3));
		panel.add(newAddress.get(4));
		panel.add(newBtn);
		panel.add(nazLabel);
		panel.add(newAddress.get(5));
		JPanel gapPanel = new JPanel();
		gapPanel.setPreferredSize(new Dimension(270, 10));
		panel.add(gapPanel);
		panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()+105));
		panel.revalidate();
		panel.repaint();
	}
	
	private void removeAddressField(JPanel panel) {
		
		int last = addressesText.size()-1;
		if(last > 0) {
			int count = panel.getComponentCount()-1;
			for(int i = 0; i < 13; i++)
				panel.remove(count--);
			addressGroup.remove(addressesBtn.get(last));
			
			addressesBtn.remove(last);
			addressesText.remove(last);
			panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()-105));
			panel.revalidate();
			panel.repaint();
		}
	}
	
	public void addContact(JFrame window) {
		
		Contatto nuovoCont;
		ArrayList<NumeriTel> contNumbers = new ArrayList<NumeriTel>();
		ArrayList<Indirizzo> contAddress = new ArrayList<Indirizzo>();
		ArrayList<Email> contEmail = new ArrayList<Email>();
		//CONTROLLO CONDIZIONE NUMERI//////////////////////////////////////////////////////////
		if(!checkNumbers()) {
			JOptionPane.showMessageDialog(new JFrame(),"Inserire almeno un cellulare ed un telefono fisso" , "Errore", JOptionPane.WARNING_MESSAGE);
			return;
		}
			
		//CONTATTO/////////////////////////////////////////////////////////////////////////////
		if(!(nameText.getText()+surnameText.getText()).isBlank()) {
			try {
				nuovoCont = controller.aggiungiContatto(user.email, nameText.getText(), surnameText.getText(), profilePicPath, favBox.isSelected());
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
					contNumbers.add(controller.getPhoneNumber("MOBILE", text.getText(), mobilesBtn.get(mobilesText.indexOf(text)).isSelected(), nuovoCont.contactID));
			//NUMERI FISSI//////////////////////////////////////////////////////////////////////////////
			for(JTextField text : landlinesText)
				if(!text.getText().isBlank())
					contNumbers.add(controller.getPhoneNumber("LANDLINE", text.getText(), landlinesBtn.get(landlinesText.indexOf(text)).isSelected(), nuovoCont.contactID));
			//INDIRIZZI//////////////////////////////////////////////////////////////////////////////
			for(ArrayList<JTextField> t : addressesText)
				if(!isAddressEmpty(t))
					contAddress.add(controller.getAddress(t.get(0).getText()+", "+t.get(1).getText(), t.get(2).getText(), t.get(3).getText(), t.get(4).getText(), t.get(5).getText(), nuovoCont.contactID, addressesBtn.get(addressesText.indexOf(t)).isSelected()));
			//EMAILS//////////////////////////////////////////////////////////////////////////////
			for(JTextField text : emailsText)
				if(!text.getText().isBlank())
					contEmail.add(controller.aggiungiEmail(text.getText(), emailsBtn.get(emailsText.indexOf(text)).isSelected(), nuovoCont.contactID));
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
	
	public void selectImage() {
		
		JFileChooser fileChooser = new JFileChooser();
		int response = fileChooser.showOpenDialog(null);
		if(response == JFileChooser.APPROVE_OPTION) {
			profilePicPath = fileChooser.getSelectedFile().getAbsolutePath();
			imageLabel.setIcon(new ImageIcon(new ImageIcon(profilePicPath).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
			imageLabel.repaint();
			imageLabel.revalidate();
		}
	}
	
	private boolean checkNumbers() {
		
		boolean cond = false;
		for(JTextField t : mobilesText)
			if(!t.getText().isBlank()) {
				cond = true;
				break;
			}
		if(cond == true)
			for(JTextField t : landlinesText)
				if(!t.getText().isBlank())
					return true;
		return false;
	}
}





