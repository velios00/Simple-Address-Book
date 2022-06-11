package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Controller;
import Modello.Utente;

public class Home extends JFrame{
	
	JPanel sidePanel;
	JPanel centralPanel;
	JPanel bottomPanel;
	
	Home(Controller controller, JFrame chiamante, Utente user){
		
		JFrame home = new JFrame();
		chiamante.dispose();
		home.setVisible(true);
		
		sidePanel = new JPanel();
		centralPanel = new JPanel();
		bottomPanel = new JPanel();
		sidePanel.setBackground(Color.GRAY);
		sidePanel.setPreferredSize(new Dimension(200, 100));
		centralPanel.setBackground(Color.WHITE);
		bottomPanel.setBackground(Color.LIGHT_GRAY);
		bottomPanel.setPreferredSize(new Dimension(100, 50));
		
		home.setLayout(new BorderLayout());
		home.setTitle("Rubrica");
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home.setSize(980, 720);
		home.add(sidePanel, BorderLayout.WEST);
		home.add(centralPanel, BorderLayout.CENTER);
		home.add(bottomPanel, BorderLayout.SOUTH);
		
		System.out.println("Rubrica di "+user.nickname);
	}
}
