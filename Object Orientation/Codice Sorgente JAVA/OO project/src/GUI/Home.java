package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.Controller;
import Modello.Contatto;
import Modello.Gruppo;
import Modello.Utente;

public class Home extends JFrame {
	
	JPanel sidePanel;
	JPanel mainPanel;
	JPanel contattiPanel;
	JPanel gruppiPanel;
	JPanel chiamatePanel;
	Utente utente;
	Controller controller;
	
	Home(Controller ctrll, JFrame chiamante, Utente user){
		
		super();
		utente = user;
		controller = ctrll;
		chiamante.dispose();
		this.setVisible(true);
		contattiPanel = new LayerContatti(utente, controller);
		gruppiPanel = new JPanel();
		chiamatePanel = new JPanel();
		
		JButton btnLayerConts = new JButton("Contatti");
		JButton btnLayerGroups = new JButton("Gruppi");
		JButton btnLayerCalls = new JButton("Chiamate");
		btnLayerConts.addActionListener(e -> changeLayer(contattiPanel));
		btnLayerGroups.addActionListener(e -> changeLayer(gruppiPanel));
		btnLayerCalls.addActionListener(e -> changeLayer(chiamatePanel));
		
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
		this.setSize(1220, 800);
		this.add(sidePanel, BorderLayout.WEST);
		mainPanel = contattiPanel;
		this.add(mainPanel, BorderLayout.CENTER);
		
		System.out.println("Rubrica di "+utente.nickname);
	}
	
	public void changeLayer(JPanel panel) {
		
		this.remove(mainPanel);
		mainPanel = panel;
		this.add(mainPanel, BorderLayout.CENTER);
		this.repaint();
		this.revalidate();
	}
}


