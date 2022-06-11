package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Controller;

public class Registrazione {

	
	Registrazione(Controller controller, JFrame chiamante){
		
		JFrame reg = new JFrame();
		chiamante.setVisible(false);
		reg.setVisible(true);
		reg.setLayout(null);
		reg.setTitle("Registrazione");
		reg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		reg.setSize(420, 420);
		
		JPanel mainPanel = new JPanel();
		JButton btnIndietro = new JButton("Indietro");
		JButton btnReg = new JButton("Registrati");
		JLabel label1 = new JLabel("Inserisci una e-mail valida:");
		JLabel label2 = new JLabel("Inserisci una password:");
		JLabel label3 = new JLabel("Inserisci di nuovo la password:");
		JLabel label4 = new JLabel("Nome utente:");
		JTextField text1 = new JTextField();
		JTextField text2 = new JTextField();
		JTextField text3 = new JTextField();
		JTextField text4 = new JTextField();
		
		btnIndietro.addActionListener(e -> goBack(chiamante, reg));
		btnReg.addActionListener(e -> goToHome(controller, chiamante, reg));
		btnIndietro.setBounds(105, 300, 95, 30);
		btnReg.setBounds(210, 300, 95, 30);
		
		text1.setPreferredSize(new Dimension(200, 25));
		text2.setPreferredSize(new Dimension(200, 25));
		text3.setPreferredSize(new Dimension(200, 25));
		text4.setPreferredSize(new Dimension(200, 25));
		
		mainPanel.setBounds(60, 40, 250, 300);
		mainPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		mainPanel.add(label1);
		mainPanel.add(text1);
		mainPanel.add(label2);
		mainPanel.add(text2);
		mainPanel.add(label3);
		mainPanel.add(text3);
		mainPanel.add(label4);
		mainPanel.add(text4);
		
		reg.add(btnReg);
		reg.add(btnIndietro);
		reg.add(mainPanel);
	}

	private void goBack(JFrame chiamante, JFrame reg){
		chiamante.setVisible(true);
		reg.dispose();
	}
	private void goToHome(Controller controller, JFrame chiamante, JFrame reg) {
		reg.dispose();
		new Home(controller, chiamante, null);
	}
	
		
	}
