package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.Controller;
import Modello.Contatto;
import Modello.Gruppo;

public class JContactLabel extends JPanel implements MouseListener{
	
	Contatto con;
	Controller controller;
	JPanel showPanel;
	
	JContactLabel(Controller ctrll, Contatto contact, JPanel mostraPanel) {
		
		con = contact;
		controller = ctrll;
		showPanel = mostraPanel;
		this.addMouseListener(this);
		
		this.setLayout(new GridLayout(1, 5, 2, 0));
		this.setBackground(new Color(235, 239, 247));
		
		String email = null, mobile = null, landline = null;
		email = con.getMainEmail();
		mobile = con.getTel("MOBILE");
		landline = con.getTel("LANDLINE");
		
		JLabel label1 = new JLabel(con.getName()+" "+con.getSurname());
		JLabel label2 = new JLabel(mobile);
		JLabel label3 = new JLabel(landline);
		JLabel label4 = new JLabel(email);
		
		this.add(label1);
		this.add(label2);
		this.add(label3);
		this.add(label4);
		this.setPreferredSize(new Dimension(500, 30));
	}
	
	public Contatto getContact() {
		return con;
	}
	
	public JPanel getPanel() {
		return showPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		showPanel.removeAll();
		showPanel.revalidate();
		showPanel.repaint();
		
		Image image = new ImageIcon(con.getImagePath()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon pic = new ImageIcon(image);
		
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(pic);
		
		for(Gruppo group : con.getGruppi()) {
			JLabel label = new JLabel(group.getName());
			showPanel.add(label);
		}
		showPanel.add(imageLabel);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JPanel panel = (JPanel) e.getSource();
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.setBackground(Color.WHITE);;
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JPanel panel = (JPanel) e.getSource();
		panel.setBackground(new Color(235, 239, 247));
		
	}
}
