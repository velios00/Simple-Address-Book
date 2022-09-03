package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Controller;
import Modello.Contatto;
import Modello.Gruppo;
import Modello.Utente;

// TODO: Auto-generated Javadoc
/**
 * Questa è la classe LayerContatti, rappresenta l'interfaccia grafica della sezione dei contatti della rubrica.
 * Contiene i {@link JContactLabel} e diversi {@link JButton} a cui sono associati eventi che fanno 
 * interfacciare LayerContatti con il {@link Controller} e l'{@link Utente} per gestire le modifiche ai contatti della rubrica.
 */
public class LayerContatti extends JPanel {
	
	/** The utente. */
	Utente utente;
	
	/** The controller. */
	Controller controller;
	
	/** The show panel. */
	JPanel showPanel;
	
	/** The list panel. */
	JPanel listPanel;
	
	/** The top panel. */
	JPanel topPanel;
	
	/** The contact list. */
	ArrayList<Contatto> contactList;
	
	/** The selected label. */
	JContactLabel selectedLabel;
	
	/** The elimina button. */
	public JButton eliminaButton;
	
	/** The modifica button. */
	public JButton modificaButton;
	
	/** The filter box. */
	JComboBox filterBox;
	
	/** The sort box. */
	JComboBox sortBox;
	
	/**
	 * Instantiates a new layer contatti.
	 *
	 * @param user the user
	 * @param ctrll the ctrll
	 */
	public LayerContatti(Utente user, Controller ctrll) {
		
		super();
		utente = user;
		controller = ctrll;
		showPanel = blankPanel();
		listPanel = new JPanel();
		topPanel = new JPanel();
		selectedLabel = null;
		
		JButton addButton = new JButton("Crea nuovo");
		addButton.addActionListener(e -> aggiungiContatto());
		eliminaButton = new JButton("Elimina");
		modificaButton = new JButton("Modifica");
		eliminaButton.addActionListener(e -> eliminaContatto());
		eliminaButton.setEnabled(false);
		modificaButton.setEnabled(false);
		
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
		resetContactList();
		aggiornaContatti();
		
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(listPanel, BorderLayout.WEST);
		this.add(showPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Aggiungi contatto.
	 */
	public void aggiungiContatto() {
		new AddContatto(controller, utente, this);
	}
	
	/**
	 * Elimina contatto.
	 */
	public void eliminaContatto() {
		if(selectedLabel != null && JOptionPane.showConfirmDialog(null, "Eliminare il contatto?", "Conferma operazione", JOptionPane.YES_NO_OPTION) == 0) {
			showPanel.removeAll();
			showPanel.repaint();
			showPanel.revalidate();
			controller.eliminaContatto(selectedLabel.con.getID());
			int index = utente.contatti.indexOf(selectedLabel.con);
			for(Gruppo grp : selectedLabel.con.getGruppi())
				grp.getPartecipanti().remove(index);
			utente.contatti.remove(index);
			eliminaButton.setEnabled(false);
			modificaButton.setEnabled(false);
			showFiltered();
		}
	}
	
	/**
	 * Refresh list panel.
	 */
	public void refreshListPanel() {
		int count = listPanel.getComponentCount()-1;
		while(count > 1)
			listPanel.remove(count--);

		for(Contatto con : contactList)
			listPanel.add(new JContactLabel(controller,  this, con, showPanel));
		
		listPanel.repaint();
		listPanel.revalidate();
	}
	
	/**
	 * Aggiorna contatti.
	 */
	public void aggiornaContatti() {
		sortContactsBy(sortBox.getSelectedItem().toString());
		refreshListPanel();
	}
	
	/**
	 * Sets the selected label.
	 *
	 * @param label the new selected label
	 */
	public void setSelectedLabel(JContactLabel label) {
		selectedLabel = label;
	}
	
	/**
	 * Gets the selected label.
	 *
	 * @return the selected label
	 */
	public JContactLabel getSelectedLabel() {
		return selectedLabel;
	}
	
	/**
	 * Blank panel.
	 *
	 * @return the j panel
	 */
	private JPanel blankPanel(){
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout());
		return panel;
	}
	
	/**
	 * Crea legenda.
	 *
	 * @return the j panel
	 */
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
	
	/**
	 * Sort contacts by.
	 *
	 * @param mod the mod
	 */
	public void sortContactsBy(String mod) {
		
		if(mod.equals("Nome"))
			contactList.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
		else if(mod.equals("Cognome"))
			contactList.sort((c1, c2) -> c1.getSurname().compareTo(c2.getSurname()));
		else {
			contactList.sort((c1, c2) -> c1.getMainEmail().compareTo(c2.getMainEmail()));
		}
	}
	
	/**
	 * Crea search panel.
	 *
	 * @return the j panel
	 */
	private JPanel creaSearchPanel() {
		
		JPanel searchPanel = new JPanel();
		String[] option = {"Nome", "Cognome", "E-mail"};
		sortBox = new JComboBox(option);
		filterBox = creaFilterBox();
		JTextField search = new JTextField();
		search.setPreferredSize(new Dimension(100, 25));
		JButton btnSearch = new JButton("Cerca");
		btnSearch.addActionListener(e -> cercaCont(search, searchPanel));
		sortBox.addActionListener(e -> aggiornaContatti());
		filterBox.addActionListener(e -> showFiltered());
		
		searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		searchPanel.setPreferredSize(new Dimension(500, 40));
		searchPanel.setBackground(new Color(200, 200, 200));
		searchPanel.add(new JLabel("Mostra: "));
		searchPanel.add(filterBox);
		searchPanel.add(new JLabel("Ordina per: "));
		searchPanel.add(sortBox);
		searchPanel.add(search);
		searchPanel.add(btnSearch);
		return searchPanel;
	}
	
	/**
	 * Cerca cont.
	 *
	 * @param field the field
	 * @param panel the panel
	 */
	private void cercaCont(JTextField field, JPanel panel) {
		
		String str = field.getText();
		if(str.isBlank()) {
			resetContactList();
			aggiornaContatti();
			return;
		}

		resetContactList();
		ArrayList<Contatto> tmpList = controller.searchContactList(contactList, str);
		field.setText("");
		contactList = tmpList;
		refreshListPanel();
	}
	
	/**
	 * Reset contact list.
	 */
	private void resetContactList() {
		contactList = (ArrayList<Contatto>) filterContact().clone();
	}
	
	/**
	 * Crea filter box.
	 *
	 * @return the j combo box
	 */
	private JComboBox creaFilterBox() {
		
		JComboBox box = new JComboBox();
		box.addItem(new String("Tutti"));
		box.addItem(new String("Preferiti"));
		for(Gruppo g : utente.getGruppi())
			box.addItem(g.getName());
		box.setSelectedIndex(0);
		return box;
	}
	
	/**
	 * Filter contact.
	 *
	 * @return the array list
	 */
	private ArrayList<Contatto> filterContact(){
		
		String item = filterBox.getSelectedItem().toString();
		if(item.equals("Tutti"))
			return utente.contatti;
		if(item.equals("Preferiti")) {
			ArrayList<Contatto> list = new ArrayList<Contatto>();
			for(Contatto con : utente.contatti)
				if(con.isFavorite())
					list.add(con);
			return list;
		}
		for(Gruppo g : utente.getGruppi())
			if(item.equals(g.getName()))
				return g.getPartecipanti();
		return null;	
	}
	
	/**
	 * Show filtered.
	 */
	public void showFiltered() {
		resetContactList();
		refreshListPanel();
	}
}

