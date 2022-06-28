package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.Controller;
import Modello.Contatto;
import Modello.Utente;

public class LayerContatti extends JPanel {
	
	Utente utente;
	Controller controller;
	JPanel showPanel;
	JPanel listPanel;
	JPanel topPanel;
	ArrayList<Contatto> contactList;
	JContactLabel selectedLabel;
	
	public LayerContatti(Utente user, Controller ctrll) {
		
		super();
		utente = user;
		controller = ctrll;
		showPanel = blankPanel();
		listPanel = new JPanel();
		topPanel = new JPanel();
		contactList = utente.contatti;
		selectedLabel = null;
		
		JPanel legendaPanel = new JPanel();
		legendaPanel.setLayout(new GridLayout(1,5));
		JLabel label1 = new JLabel("Nome");
		JLabel label2 = new JLabel("Cellulare");
		JLabel label3 = new JLabel("Telefono");
		JLabel label4 = new JLabel("Email");
		legendaPanel.add(label1);
		legendaPanel.add(label2);
		legendaPanel.add(label3);
		legendaPanel.add(label4);
		legendaPanel.setPreferredSize(new Dimension(500, 50));
		legendaPanel.setBackground(new Color(200, 200, 200));
		
		JButton addButton = new JButton("Crea nuovo");
		JButton eliminaButton = new JButton("Elimina");
		JButton modificaButton = new JButton("Modifica");
		addButton.addActionListener(e -> aggiungiContatto());
		eliminaButton.addActionListener(e -> eliminaContatto());
		topPanel.setBackground(new Color(200, 200, 200));
		topPanel.setPreferredSize(new Dimension(100, 50));
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		topPanel.add(addButton);
		topPanel.add(eliminaButton);
		topPanel.add(modificaButton);
		
		listPanel.setBackground(new Color(215, 225, 245));
		listPanel.setPreferredSize(new Dimension(500, 100));
		listPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));
		listPanel.add(legendaPanel);
		aggiornaContatti();
		
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(listPanel, BorderLayout.WEST);
		this.add(showPanel, BorderLayout.CENTER);
	}
	
	public void aggiungiContatto() {
		new AddContatto(controller, utente, this);
	}
	
	public void eliminaContatto() {
		if(selectedLabel != null && JOptionPane.showConfirmDialog(null, "Eliminare il contatto?", "Conferma operazione", JOptionPane.YES_NO_OPTION) == 0) {
			showPanel.removeAll();
			showPanel.repaint();
			showPanel.revalidate();
			controller.eliminaContatto(selectedLabel.con.contactID);
			int index = utente.contatti.indexOf(selectedLabel.con);
			utente.contatti.remove(index);
			aggiornaContatti();
		}
	}
	
	public void aggiornaContatti() {
		listPanel.removeAll();
		for(Contatto con : contactList)
			listPanel.add(new JContactLabel(controller,  this, con, showPanel));
		listPanel.repaint();
		listPanel.revalidate();
	}
	
	public void setSelectedLabel(JContactLabel label) {
		selectedLabel = label;
	}
	
	public JContactLabel getSelectedLabel() {
		return selectedLabel;
	}
	
	public JPanel blankPanel(){
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout());
		return panel;
	}
}