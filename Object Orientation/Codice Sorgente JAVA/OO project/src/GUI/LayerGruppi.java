package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Controller;
import Modello.Contatto;
import Modello.Gruppo;
import Modello.Utente;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.Font;

// TODO: Auto-generated Javadoc
/**
 * Questa e' la classe LayerGruppi, rappresenta l'interfaccia grafica della sezione dei gruppi della rubrica.
 * contiene diversi {@link JButton} a cui sono associati eventi che fanno interfacciare la classe con il {@link Controller} e l'{@link Utente}
 * per e gestire le modifiche ai gruppi nella rubrica.
 */
public class LayerGruppi extends JPanel {

	/** The utentex. */
	Utente utentex;
	
	/** The gruppox. */
	Gruppo gruppox;
	
	/** The controllerx. */
	Controller controllerx;
	
	/** The add con grp box. */
	JComboBox addConGrpBox;
	
	/** The remove con grp box. */
	JComboBox removeConGrpBox;
	
	/** The selected grp. */
	Gruppo selectedGrp = null;
	
	/** The panel dettagli. */
	JPanel panelDettagli = new JPanel();
	
	/** The panel west. */
	JPanel panelWest = new JPanel();
	
	/** The contact. */
	Contatto contact = null;
	
	/**
	 * Instantiates a new layer gruppi.
	 *
	 * @param utente1 the utente 1
	 * @param controller1 the controller 1
	 */
	public LayerGruppi(Utente utente1, Controller controller1) {
		super();
		utentex = utente1;
		controllerx = controller1;
		addConGrpBox = new JComboBox();
		setLayout(new BorderLayout(0, 0));
		
		panelWest.setBackground(SystemColor.inactiveCaption);
		panelWest.setPreferredSize(new Dimension(200, 50));
		
		panelDettagli = new JPanel();
		panelDettagli.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(panelWest, BorderLayout.WEST);
		add(panelDettagli, BorderLayout.CENTER);
		
		JLabel lblGroupNome = new JLabel("Lista Gruppi");
		lblGroupNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelWest.add(lblGroupNome);
		JButton btnCreateGroup = new JButton("Crea nuovo Gruppo");
		panelWest.add(btnCreateGroup);
		btnCreateGroup.addActionListener(e -> createGrp());
		for(Gruppo gruppo1 : utentex.getGruppi()) {
			JButton jbutton1 = new JButton(gruppo1.getName() + ' ' + gruppo1.getDate());
			panelWest.add(jbutton1);
			jbutton1.addActionListener(e->showGrp(panelDettagli, gruppo1));
			jbutton1.setPreferredSize(new Dimension(180, 40));
		}
		
		
	}

		/**
		 * Show grp.
		 *
		 * @param jpanel2 the jpanel 2
		 * @param gruppo1 the gruppo 1
		 */
		public void showGrp(JPanel jpanel2, Gruppo gruppo1) {
			JLabel jlabelTitolo = new JLabel(gruppo1.getName());
			jlabelTitolo.setFont(new Font("Bebas Neue", Font.BOLD, 50));
			selectedGrp = gruppo1;
			JPanel panelBottoni = new JPanel();
			
			
			jpanel2.removeAll();
			jpanel2.add(jlabelTitolo);
			jpanel2.setBackground(SystemColor.activeCaption);
			jlabelTitolo.setPreferredSize(new Dimension(550, 70));
			
			
			jpanel2.add(panelBottoni);
			panelBottoni.setPreferredSize(new Dimension(150, 130));
			
			
			JButton buttonAddContact = new JButton("Aggiungi al Gruppo");
			panelBottoni.add(buttonAddContact);
			buttonAddContact.addActionListener(e -> addConGrpMet());
			ArrayList<Contatto> GroupList = (ArrayList<Contatto>) selectedGrp.getPartecipanti().clone();
			addConGrpBox.removeAllItems();
			 for(Contatto contatto1 : utentex.contatti) {
				 if(!GroupList.contains(contatto1))
				 addConGrpBox.addItem(contatto1);
			}
			JButton buttonRemoveContact = new JButton("Rimuovi dal Gruppo");
			panelBottoni.add(buttonRemoveContact);
			removeConGrpBox = new JComboBox();
			buttonRemoveContact.addActionListener( e-> remConGrpMet());
			removeConGrpBox.removeAllItems();
			for(Contatto contatto1 : utentex.contatti) {
				 if(GroupList.contains(contatto1))
				 removeConGrpBox.addItem(contatto1);
			}
			
			JButton buttonModifyGroup = new JButton("Modifica Gruppo");
			panelBottoni.add(buttonModifyGroup);
			buttonModifyGroup.addActionListener(e -> modGrp());
			
			JButton buttonRemoveGroup = new JButton("Elimina Gruppo");
			panelBottoni.add(buttonRemoveGroup);
			buttonRemoveGroup.addActionListener(e -> remGrp());
			
			
			
			JPanel panelContatti = new JPanel();
			jpanel2.add(panelContatti);
			
			JPanel panelDescrizione = new JPanel();
			jpanel2.add(panelDescrizione);
			
			JLabel lblName = new JLabel("Partecipante");
			lblName.setFont(new Font("Times new Roman", Font.BOLD, 20));
			panelContatti.add(lblName);
			
			JLabel lblDesc = new JLabel("<html>Descrizione del Gruppo: <br/>" + gruppo1.getDesc() + "</html>");
			lblDesc.setFont(new Font("Arial", Font.PLAIN, 20));
			panelDescrizione.add(lblDesc);
			
			
			
			for(Contatto contatto1 : gruppo1.getPartecipanti()) {
				JLabel jlabelContatto = new JLabel(contatto1.getName() + ' ' + contatto1.getSurname());
				jlabelContatto.setFont(new Font("Times new Roman", Font.PLAIN, 15));
				panelContatti.add(jlabelContatto);
				panelContatti.setPreferredSize(new Dimension(150, 300));
				jlabelContatto.setPreferredSize(new Dimension(100, 30));
			}
			
			jpanel2.repaint();
			jpanel2.revalidate();
		}
		
		/**
		 * Adds the con grp met.
		 */
		public void addConGrpMet() {
			JFrame frameAddConGrp = new JFrame();
			JButton buttonConfirm = new JButton("Conferma");
			frameAddConGrp.setVisible(true);
			frameAddConGrp.setTitle("Aggiungi Contatto al Gruppo");
			frameAddConGrp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frameAddConGrp.setSize(300, 90);
			frameAddConGrp.add(addConGrpBox);
			frameAddConGrp.add(buttonConfirm);
			frameAddConGrp.setLayout(new FlowLayout());
			buttonConfirm.addActionListener(e -> refreshPanel());
			addConGrpBox.addActionListener(e -> contact = (Contatto) addConGrpBox.getSelectedItem());
			buttonConfirm.addActionListener(e -> controllerx.confirmConToGrp(contact, selectedGrp));
			buttonConfirm.addActionListener(e -> frameAddConGrp.dispose());
		}
		
		/**
		 * Rem con grp met.
		 */
		public void remConGrpMet() {
			JFrame frameRemConGrp = new JFrame();
			JButton buttonConfirm = new JButton("Conferma");
			frameRemConGrp.setVisible(true);
			frameRemConGrp.setTitle("Rimuovi Contatto dal Gruppo");
			frameRemConGrp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frameRemConGrp.setSize(300, 90);
			frameRemConGrp.add(removeConGrpBox);
			frameRemConGrp.add(buttonConfirm);
			frameRemConGrp.setLayout(new FlowLayout());
			buttonConfirm.addActionListener(e -> refreshPanel());
			removeConGrpBox.addActionListener(e -> contact = (Contatto) removeConGrpBox.getSelectedItem());
			buttonConfirm.addActionListener(e -> controllerx.removeConToGrp(contact, selectedGrp)); 
			buttonConfirm.addActionListener(e -> frameRemConGrp.dispose());
		}
		
		/**
		 * Creates the grp.
		 */
		public void createGrp() {
			JFrame frameCreateGrp = new JFrame();
			JButton buttonConfirm = new JButton("Crea");
			JTextField nomeGruppoTextField = new JTextField();
			JLabel nomeGruppo = new JLabel("Inserisci qui il nome del gruppo");
			JLabel descrizioneGruppo = new JLabel("Inserisci qui la descrizione del gruppo");
			JTextField descrizioneGruppoTextField = new JTextField();
			nomeGruppoTextField.setPreferredSize(new Dimension (250, 40));
			descrizioneGruppoTextField.setPreferredSize(new Dimension(250, 40));
			buttonConfirm.addActionListener(e -> refreshGrp());
			buttonConfirm.addActionListener(e -> controllerx.creaGruppo(nomeGruppoTextField.getText(), descrizioneGruppoTextField.getText(), utentex));
			buttonConfirm.addActionListener(e -> frameCreateGrp.dispose());

			frameCreateGrp.add(nomeGruppo);
			frameCreateGrp.add(nomeGruppoTextField);
			frameCreateGrp.add(descrizioneGruppo);
			frameCreateGrp.add(descrizioneGruppoTextField);
			frameCreateGrp.setVisible(true);
			frameCreateGrp.setTitle("Crea nuovo Gruppo");
			frameCreateGrp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frameCreateGrp.setSize(300, 220);
			frameCreateGrp.add(buttonConfirm);
			frameCreateGrp.setLayout(new FlowLayout());	
		}
		
		/**
		 * Rem grp.
		 */
		public void remGrp() {
			if(JOptionPane.showConfirmDialog(null, "Sei sicuro di eliminare il gruppo?", "Conferma operazione", JOptionPane.YES_NO_OPTION) == 0) {	
				controllerx.eliminaGruppo(selectedGrp, utentex);
				selectedGrp = null;
				refreshGrp();
			}
			
		}
		
		/**
		 * Mod grp.
		 */
		public void modGrp() {
			JFrame frameModifyGrp = new JFrame();
			JButton buttonConfirm = new JButton("Conferma");
			JTextField nomeGruppoTextField = new JTextField();
			JLabel lblNomeGruppo = new JLabel("Modifica qui il nome del gruppo");
			JTextField descrizioneGruppoTextField = new JTextField();
			JLabel lblDescrizioneGruppo = new JLabel("Modifica qui la descrizione del gruppo");
			nomeGruppoTextField.setPreferredSize(new Dimension(250, 40));
			descrizioneGruppoTextField.setPreferredSize(new Dimension (250, 40));
			
			frameModifyGrp.add(lblNomeGruppo);
			frameModifyGrp.add(nomeGruppoTextField);
			frameModifyGrp.add(lblDescrizioneGruppo);
			frameModifyGrp.add(descrizioneGruppoTextField);
			frameModifyGrp.setVisible(true);
			frameModifyGrp.setTitle("Modifica Gruppo");
			frameModifyGrp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frameModifyGrp.setSize(300, 220);
			frameModifyGrp.add(buttonConfirm);
			frameModifyGrp.setLayout(new FlowLayout());
			buttonConfirm.addActionListener(e -> refreshPanel());
			buttonConfirm.addActionListener(e -> controllerx.modificaGruppo(nomeGruppoTextField.getText(), descrizioneGruppoTextField.getText(), selectedGrp));
			buttonConfirm.addActionListener(e -> frameModifyGrp.dispose());
		}
		
		/**
		 * Refresh panel.
		 */
		private void refreshPanel() {
			
			panelDettagli.removeAll();
			showGrp(panelDettagli, selectedGrp);
		}
		
		/**
		 * Refresh grp.
		 */
		private void refreshGrp() {
			
			panelWest.removeAll();
			JLabel lblGroupNome = new JLabel("Lista Gruppi");
			lblGroupNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
			panelWest.add(lblGroupNome);
			JButton btnCreateGroup = new JButton("Crea nuovo Gruppo");
			panelWest.add(btnCreateGroup);
			btnCreateGroup.addActionListener(e -> createGrp());
			for(Gruppo gruppo1 : utentex.getGruppi()) {
				JButton jbutton1 = new JButton(gruppo1.getName() + ' ' + gruppo1.getDate());
				panelWest.add(jbutton1);
				jbutton1.addActionListener(e->showGrp(panelDettagli, gruppo1));
				jbutton1.setPreferredSize(new Dimension(180, 40));
			panelWest.repaint();
			panelWest.revalidate();
		}
	}
		
}