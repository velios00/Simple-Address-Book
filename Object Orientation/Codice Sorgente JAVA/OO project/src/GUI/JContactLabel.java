package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Controller;
import Modello.Contatto;
import Modello.Email;
import Modello.Gruppo;
import Modello.Indirizzo;
import Modello.MessagingAccount;
import Modello.NumeriTel;

// TODO: Auto-generated Javadoc
/**
 * Questa è la classe JContactLabel, rappresenta un'anteprima delle informazioni del {@link Contatto}. Implementa un metodo {@link mouseClicked} attraverso il quale è
 * possibile visualizzare tutti i dettagli del contatto. Contiene diversi metodi che fanno interfacciare la classe con il {@link LayerContatti}
 * per aggiornare l'interfaccia.
 */
public class JContactLabel extends JPanel implements MouseListener{
	
	/** The con. */
	Contatto con;
	
	/** The controller. */
	Controller controller;
	
	/** The home. */
	LayerContatti home;
	
	/** The show panel. */
	JPanel showPanel;
	
	/** The default color. */
	public Color defaultColor;
	
	/**
	 * Instantiates a new j contact label.
	 *
	 * @param ctrll the ctrll
	 * @param parentPanel the parent panel
	 * @param contact the contact
	 * @param mostraPanel the mostra panel
	 */
	JContactLabel(Controller ctrll, LayerContatti parentPanel, Contatto contact, JPanel mostraPanel) {
		
		con = contact;
		controller = ctrll;
		home = parentPanel;
		showPanel = mostraPanel;
		defaultColor = new Color(235, 239, 247);
		this.addMouseListener(this);
		
		this.setLayout(new GridLayout(1, 5, 2, 0));
		this.setBackground(defaultColor);
		
		String email = null, mobile = null, landline = null;
		email = con.getMainEmail();
		mobile = con.getTel("MOBILE");
		landline = con.getTel("LANDLINE");
		
		JLabel label1 = new JLabel(con.getName()+" "+con.getSurname());
		JLabel label2 = new JLabel(mobile);
		JLabel label3 = new JLabel(landline);
		JLabel label4 = new JLabel(email);
		
		this.add(label1);
		this.add(label2);
		this.add(label3);
		this.add(label4);
		this.setPreferredSize(new Dimension(500, 30));
	}
	
	/**
	 * Gets the contact.
	 *
	 * @return the contact
	 */
	public Contatto getContact() {
		return con;
	}
	
	/**
	 * Gets the panel.
	 *
	 * @return the panel
	 */
	public JPanel getPanel() {
		return showPanel;
	}
	
	/**
	 * Sets the panel.
	 *
	 * @param panel the new panel
	 */
	public void setPanel(JPanel panel) {
		
		showPanel = panel;
	}
	
	/**
	 * Mouse clicked.
	 *
	 * @param e the e
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
		showPanel.removeAll();
		showPanel.revalidate();
		showPanel.repaint();
		showPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		if(home.getSelectedLabel() != null) {
			home.getSelectedLabel().defaultColor = defaultColor;
			home.getSelectedLabel().setBackground(defaultColor);
		}
		home.setSelectedLabel(this);
		home.eliminaButton.setEnabled(true);
		home.modificaButton.setEnabled(true);
		defaultColor = Color.WHITE;
		
		Image image = new ImageIcon(con.getImagePath()).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		JLabel mobileLabel = new JLabel("<html><font size=\"+1\">Cellulare</font><br/>");
		JLabel landlineLabel = new JLabel("<html><font size=\"+1\">Telefono</font><br/>");
		JLabel groupLabel = new JLabel("<html><font size=\"+1\">Gruppi</font><br/>");
		JLabel emailLabel = new JLabel("<html><font size=\"+1\">Email</font><br/>");
		JLabel messagingLabel = new JLabel("<html><font size=\"+1\">Account di messaging</font><br/>");
		JPanel addressPanel = new JPanel();
		
		JLabel nameLabel = new JLabel(con.getName()+" "+ con.getSurname());
		nameLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
		nameLabel.setPreferredSize(new Dimension(300, 40));
		
		ImageIcon pic = new ImageIcon(image);
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(pic);
		
		mobileLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		mobileLabel.setVerticalAlignment(JLabel.TOP);
		landlineLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		landlineLabel.setVerticalAlignment(JLabel.TOP);
		for(NumeriTel tel : con.numeri) {
			boolean linkedM = false, linkedL = false;
			if(tel.getType().equals("MOBILE"))
			{
				if(tel.ifLinked())
				{
					mobileLabel.setText(mobileLabel.getText()+tel.getNumber()+"*<br/>");
					linkedM = true;
				}
				else
					mobileLabel.setText(mobileLabel.getText()+tel.getNumber()+"<br/>");
				if(linkedM)
					mobileLabel.setText(mobileLabel.getText()+"*numero di reindirizzamento");
			}
			else
			{
				if(tel.ifLinked())
				{
					landlineLabel.setText(landlineLabel.getText()+tel.getNumber()+"*<br/>");
					linkedL = true;
				}
					
				else
					landlineLabel.setText(landlineLabel.getText()+tel.getNumber()+"<br/>");
				if(linkedL)
					landlineLabel.setText(landlineLabel.getText()+"*numero di reindirizzamento");
			}
		}
		mobileLabel.setText(mobileLabel.getText()+"</html>");
		mobileLabel.setPreferredSize(new Dimension(130, 100));
		landlineLabel.setText(landlineLabel.getText()+"</html>");
		landlineLabel.setPreferredSize(new Dimension(130, 100));
		mobileLabel.setOpaque(true);
		landlineLabel.setOpaque(true);
		
		groupLabel.setVerticalAlignment(JLabel.TOP);
		groupLabel.setHorizontalAlignment(JLabel.RIGHT);
		groupLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		for(Gruppo group : con.getGruppi())
			groupLabel.setText(groupLabel.getText()+group.getName()+"<br/>");
		groupLabel.setText(groupLabel.getText()+"</html>");
		groupLabel.setPreferredSize(new Dimension(150, 100));
		groupLabel.setOpaque(true);
		
		emailLabel.setVerticalAlignment(JLabel.TOP);
		emailLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		for(Email em : con.emails)
		{
			if(em.main())
				emailLabel.setText(emailLabel.getText()+"<b>"+em.getString()+"</b><br/>");
			else
				emailLabel.setText(emailLabel.getText()+em.getString()+"<br/>");
			for(MessagingAccount ma : em.getMessaging())
				messagingLabel.setText(messagingLabel.getText()+" "+ma);
		}
		emailLabel.setText(emailLabel.getText()+"</html>");
		emailLabel.setPreferredSize(new Dimension(220, 140));
		emailLabel.setOpaque(true);
		
		addressPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
		addressPanel.setPreferredSize(new Dimension(430, 220));
		JLabel addressTitleLabel = new JLabel("<html><font size=\"+1\">Indirizzo</font></html>");
		addressTitleLabel.setVerticalAlignment(JLabel.TOP);
		addressTitleLabel.setPreferredSize(new Dimension(430, 20));
		addressTitleLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		addressPanel.add(addressTitleLabel);
		//addressLabel.setVerticalAlignment(JLabel.TOP);
		for(Indirizzo i : con.indirizzi) {
			JLabel currLabel = new JLabel();
			currLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
			if(i.main)
				currLabel.setText("<html><b>"+i.getString()+"</b></html>\"");
			else
				currLabel.setText(i.getString());
			addressPanel.add(currLabel);
			JLabel separator = new JLabel();
			separator.setPreferredSize(new Dimension(20, 1));
			addressPanel.add(separator);
		}
		
		showPanel.add(nameLabel);
		showPanel.add(imageLabel);
		showPanel.add(mobileLabel);
		showPanel.add(landlineLabel);
		showPanel.add(groupLabel);
		showPanel.add(emailLabel);
		showPanel.add(messagingLabel);
		showPanel.add(addressPanel);
	}

	/**
	 * Mouse pressed.
	 *
	 * @param e the e
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Mouse released.
	 *
	 * @param e the e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Mouse entered.
	 *
	 * @param e the e
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		JPanel panel = (JPanel) e.getSource();
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.setBackground(Color.WHITE);;
		
	}

	/**
	 * Mouse exited.
	 *
	 * @param e the e
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		JPanel panel = (JPanel) e.getSource();
		panel.setBackground(defaultColor);
		
	}
}
