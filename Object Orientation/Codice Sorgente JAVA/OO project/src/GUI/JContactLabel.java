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
import Modello.Email;
import Modello.Gruppo;
import Modello.Indirizzo;
import Modello.NumeriTel;

public class JContactLabel extends JPanel implements MouseListener{
	
	Contatto con;
	Controller controller;
	LayerContatti home;
	JPanel showPanel;
	public Color defaultColor;
	
	JContactLabel(Controller ctrll, LayerContatti parentPanel, Contatto contact, JPanel mostraPanel) {
		
		con = contact;
		controller = ctrll;
		home = parentPanel;
		showPanel = mostraPanel;
		defaultColor = new Color(235, 239, 247);
		this.addMouseListener(this);
		
		this.setLayout(new GridLayout(1, 5, 2, 0));
		this.setBackground(defaultColor);
		
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
	
	public void setPanel(JPanel panel) {
		
		showPanel = panel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		showPanel.removeAll();
		showPanel.revalidate();
		showPanel.repaint();
		if(home.getSelectedLabel() != null) {
			home.getSelectedLabel().defaultColor = defaultColor;
			home.getSelectedLabel().setBackground(defaultColor);
		}
		home.setSelectedLabel(this);
		defaultColor = Color.WHITE;
		
		JPanel namePanel = new JPanel();
		Image image = new ImageIcon(con.getImagePath()).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		JPanel mobilePanel = new JPanel();
		JPanel landlinePanel = new JPanel();
		JPanel groupPanel = new JPanel();
		JPanel emailPanel = new JPanel();
		JPanel addressPanel = new JPanel();
		
		namePanel.add(new JLabel(con.getName()+" "+ con.getSurname()));
		
		ImageIcon pic = new ImageIcon(image);
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(pic);
		
		for(NumeriTel tel : con.numeri) {
			if(tel.getType().equals("MOBILE"))
				mobilePanel.add(new JLabel(tel.getNumber()));
			else
				landlinePanel.add(new JLabel(tel.getNumber()));
		}
			
		for(Gruppo group : con.getGruppi()) {
			JLabel label = new JLabel(group.getName());
			groupPanel.add(label);
		}
		
		for(Email em : con.emails)
			emailPanel.add(new JLabel(em.getEmail()));
		
		for(Indirizzo i : con.indirizzi)
			addressPanel.add(new JLabel(i.getString()));
		
		showPanel.add(namePanel);
		showPanel.add(imageLabel);
		showPanel.add(mobilePanel);
		showPanel.add(landlinePanel);
		showPanel.add(groupPanel);
		showPanel.add(emailPanel);
		showPanel.add(addressPanel);
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
		panel.setBackground(defaultColor);
		
	}
}
