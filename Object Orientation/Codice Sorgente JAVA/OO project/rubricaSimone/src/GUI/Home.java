package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.Controller;
import Modello.Contatto;
import Modello.Gruppo;
import Modello.Utente;

public class Home extends JFrame {
	
	JPanel sidePanel;
	JPanel mainPanel;
	Utente utente;
	Controller controller;
	
	Home(Controller ctrll, JFrame chiamante, Utente user){
		
		utente = user;
		controller = ctrll;
		JFrame home = new JFrame();
		chiamante.dispose();
		home.setVisible(true);
		
		sidePanel = new JPanel();
		sidePanel.setBackground(new Color(104, 153, 252));
		sidePanel.setPreferredSize(new Dimension(100, 100));
		
		mainPanel = layerContatti(user);
		
		home.setLayout(new BorderLayout());
		home.setTitle("Rubrica");
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home.setSize(1220, 800);
		home.add(sidePanel, BorderLayout.WEST);
		home.add(mainPanel, BorderLayout.CENTER);
		
		System.out.println("Rubrica di "+utente.nickname);
	}
	
	public JPanel layerContatti(Utente user) {
		
		JPanel mainPanel = new JPanel();
		JPanel showPanel = new JPanel();
		JPanel topPanel = new JPanel();
		JPanel listPanel = new JPanel();
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
		
		showPanel.setBackground(Color.WHITE);
		showPanel.setLayout(new FlowLayout());
		
		JButton addButton = new JButton("Aggiungi contatto");
		JButton eliminaButton = new JButton("Elimina");
		JButton modificaButton = new JButton("Modifica");
		addButton.addActionListener(e -> aggiungiContatto());
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
		for(Contatto con : user.contatti)
			listPanel.add(new JContactLabel(controller, con, showPanel));
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(listPanel, BorderLayout.WEST);
		mainPanel.add(showPanel, BorderLayout.CENTER);
		
		return mainPanel;
	}
	
	public void aggiungiContatto() {
		new AddContatto(controller, utente);
	}
}
