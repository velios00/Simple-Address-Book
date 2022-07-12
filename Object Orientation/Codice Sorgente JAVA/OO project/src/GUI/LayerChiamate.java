package GUI;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.sql.SQLException;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Controller.Controller;
import Modello.PhoneCall;
import Modello.Utente;

import java.awt.GridLayout;

public class LayerChiamate extends JPanel {
	
	Utente utentex;
	
	public LayerChiamate(Utente utente1, Controller controller) {
		super();
		utentex = utente1;
		try {
			utentex.calls = controller.caricaChiamate(utentex.email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel PannelloLegenda = new JPanel();
		PannelloLegenda.setBackground(SystemColor.activeCaption);
		add(PannelloLegenda);
		PannelloLegenda.setLayout(new GridLayout(1, 5, 20, 3));
		PannelloLegenda.setPreferredSize(new Dimension(800, 50));
		
		JLabel lblName = new JLabel("Nome");
		PannelloLegenda.add(lblName);
		
		JLabel lblNumero = new JLabel("Telefono");
		PannelloLegenda.add(lblNumero);
		
		JLabel lblRicInv = new JLabel("Tipo Chiamata");
		PannelloLegenda.add(lblRicInv);
		
		JLabel lblDate = new JLabel("Data");
		PannelloLegenda.add(lblDate);
		
		JLabel lblDurata = new JLabel("Durata");
		PannelloLegenda.add(lblDurata);
		
		for(PhoneCall phonecall1 : utentex.getCalls()) {
			JPanel pannelloChiamate = new JPanel();
			this.add(pannelloChiamate);
			pannelloChiamate.setLayout(new GridLayout(1, 5, 20, 3));
			pannelloChiamate.setPreferredSize(new Dimension(800, 40));
			pannelloChiamate.add(new JLabel(phonecall1.getNomeContatto()));
			pannelloChiamate.add(new JLabel(phonecall1.getNumber()));
			pannelloChiamate.add(new JLabel(phonecall1.getTipo()));
			pannelloChiamate.add(new JLabel(phonecall1.getDataOra()));
			pannelloChiamate.add(new JLabel(phonecall1.getDurata()));
			
		}
		
	}

}
