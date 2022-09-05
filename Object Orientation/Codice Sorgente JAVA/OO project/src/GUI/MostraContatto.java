package GUI;


import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.Controller;
import Modello.Contatto;
import Modello.Gruppo;

public class MostraContatto {
	
	
	public MostraContatto(Controller controller, Contatto contact) {
		
		JFrame showContact = new JFrame();
		for(Gruppo group : contact.getGruppi()) {
			JLabel label = new JLabel(group.getName());
			showContact.add(label);
		}
		showContact.setVisible(true);
		showContact.setLayout(new FlowLayout());
		showContact.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		showContact.setTitle(contact.getName()+contact.getSurname());
		showContact.setSize(640, 720);
	}
}
