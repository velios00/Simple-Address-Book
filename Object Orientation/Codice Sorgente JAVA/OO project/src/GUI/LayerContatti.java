package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
		contactList = (ArrayList<Contatto>) utente.contatti.clone();
		selectedLabel = null;
		
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
		listPanel.add(creaSearchPanel());
		listPanel.add(creaLegenda());
		sortContactsBy("Nome");
		
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
		int count = listPanel.getComponentCount()-1;
		while(count > 1)
			listPanel.remove(count--);

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
	
	private JPanel blankPanel(){
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout());
		return panel;
	}
	
	private JPanel creaLegenda() {
		
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
		legendaPanel.setPreferredSize(new Dimension(500, 40));
		legendaPanel.setBackground(new Color(200, 200, 200));
		return legendaPanel;
	}
	
	public void sortContactsBy(String mod) {
		
		if(mod.equals("Nome"))
			contactList.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
		else if(mod.equals("Cognome"))
			contactList.sort((c1, c2) -> c1.getSurname().compareTo(c2.getSurname()));
		else {
			contactList.sort((c1, c2) -> c1.getMainEmail().compareTo(c2.getMainEmail()));
		}
		aggiornaContatti();
	}
	
	private JPanel creaSearchPanel() {
		
		JPanel searchPanel = new JPanel();
		String[] option = {"Nome", "Cognome", "E-mail"};
		JComboBox box = new JComboBox(option);
		box.addActionListener(e -> sortContactsBy(box.getSelectedItem().toString()));
		
		searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		searchPanel.setPreferredSize(new Dimension(500, 40));
		searchPanel.setBackground(new Color(200, 200, 200));
		searchPanel.add(new JLabel("Ordina per: "));
		searchPanel.add(box);
		return searchPanel;
	}
}

