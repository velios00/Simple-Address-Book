package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Controller;
import Modello.Utente;

// TODO: Auto-generated Javadoc
/**
 * Questa è la classe Home, rappresenta il {@link JFrame} principale della rubrica.
 * La principale differenza dalle altre interfacce sta nel fatto che la classe Home è il layer "madre" della rubrica,
 * dove sono presenti collegamenti tramite dei {@link JButton} a tutti i layer, ovvero il {@link LayerContatti}, il {@link LayerGruppi} e il {@link LayerChiamate}.
 * Il layer predefinito è quello dei contatti.
 */
public class Home extends JFrame {
	
	/** The side panel. */
	JPanel sidePanel;
	
	/** The main panel. */
	JPanel mainPanel;
	
	/** The contatti panel. */
	JPanel contattiPanel;
	
	/** The gruppi panel. */
	JPanel gruppiPanel;
	
	/** The chiamate panel. */
	JPanel chiamatePanel;
	
	/** The utente. */
	Utente utente;
	
	/** The controller. */
	Controller controller;
	
	/**
	 * Instantiates a new home.
	 *
	 * @param ctrll the ctrll
	 * @param chiamante the chiamante
	 * @param user the user
	 */
	Home(Controller ctrll, JFrame chiamante, Utente user){
		
		super();
		utente = user;
		controller = ctrll;
		chiamante.dispose();
		this.setVisible(true);
		
		JButton btnLayerConts = new JButton("Contatti");
		JButton btnLayerGroups = new JButton("Gruppi");
		JButton btnLayerCalls = new JButton("Chiamate");
		btnLayerConts.addActionListener(e -> changeLayer(contattiPanel, new LayerContatti(utente, controller)));
		btnLayerGroups.addActionListener(e -> changeLayer(gruppiPanel, new LayerGruppi(utente, controller)));
		btnLayerCalls.addActionListener(e -> changeLayer(chiamatePanel, new LayerChiamate(utente, controller)));
		
		sidePanel = new JPanel();
		sidePanel.setLayout(new FlowLayout());
		sidePanel.setBackground(new Color(104, 153, 252));
		sidePanel.setPreferredSize(new Dimension(100, 100));
		sidePanel.add(btnLayerConts);
		sidePanel.add(btnLayerGroups);
		sidePanel.add(btnLayerCalls);
		
		this.setLayout(new BorderLayout());
		this.setTitle("Rubrica");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1060, 720);
		this.add(sidePanel, BorderLayout.WEST);
		mainPanel = new LayerContatti(utente, controller);
		this.add(mainPanel, BorderLayout.CENTER);
		
		System.out.println("Rubrica di "+utente.nickname);
	}
	
	/**
	 * Change layer.
	 *
	 * @param panel the panel
	 * @param newPanel the new panel
	 */
	public void changeLayer(JPanel panel, JPanel newPanel) {
		
		this.remove(mainPanel);
		mainPanel = newPanel;
		this.add(mainPanel, BorderLayout.CENTER);
		this.repaint();
		this.revalidate();
	}
}


