package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.Controller;
import Modello.Contatto;
import Modello.Utente;

public class Home extends JFrame {
	
	JPanel sidePanel;
	JPanel centralPanel;
	JPanel bottomPanel;
	Utente utente;
	Controller controller;
	
	Home(Controller ctrll, JFrame chiamante, Utente user){
		
		utente = user;
		controller = ctrll;
		JFrame home = new JFrame();
		chiamante.dispose();
		home.setVisible(true);
		
		sidePanel = new JPanel();
		centralPanel = new JPanel();
		bottomPanel = new JPanel();
		sidePanel.setBackground(Color.GRAY);
		sidePanel.setPreferredSize(new Dimension(200, 100));
		centralPanel.setBackground(Color.WHITE);
		centralPanel.setLayout(new FlowLayout());
		bottomPanel.setBackground(Color.LIGHT_GRAY);
		bottomPanel.setPreferredSize(new Dimension(100, 50));
		
		System.out.println("Rubrica di "+utente.nickname);
		for(Contatto con : user.contatti)
			centralPanel.add(createContactPanel(con));
		
		home.setLayout(new BorderLayout());
		home.setTitle("Rubrica");
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home.setSize(980, 720);
		home.add(sidePanel, BorderLayout.WEST);
		home.add(centralPanel, BorderLayout.CENTER);
		home.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public JPanel createContactPanel(Contatto con){
		
		JPanel contactPanel = new JPanel();
		contactPanel.setLayout(new FlowLayout());
		
		String email = null, mobile = null, landline = null;
		email = con.getMainEmail();
		mobile = controller.getPhoneNumber(con.contactID, "MOBILE");
		landline = controller.getPhoneNumber(con.contactID, "LANDLINE");
		
		JContactLabel contactLabel1 = new JContactLabel(controller, con.getName()+" "+con.getSurname(), con);
		JLabel label2 = new JLabel(mobile);
		JLabel label3 = new JLabel(landline);
		JLabel label4 = new JLabel(email);
		
		contactPanel.add(contactLabel1);
		contactPanel.add(label2);
		contactPanel.add(label3);
		contactPanel.add(label4);

		return contactPanel;
	}
}
