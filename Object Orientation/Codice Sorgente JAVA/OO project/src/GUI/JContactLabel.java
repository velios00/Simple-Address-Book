package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import Controller.Controller;
import Modello.Contatto;

public class JContactLabel extends JLabel implements MouseListener{
	
	Contatto contact;
	Controller controller;
	
	JContactLabel(Controller ctrll, String text, Contatto cont) {
		super(text);
		contact = cont;
		controller = ctrll;
		this.addMouseListener(this);

	}
	
	public Contatto getContact() {
		return contact;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JContactLabel label = (JContactLabel) e.getSource();
		new MostraContatto(controller, label.getContact());
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
		JLabel label = (JLabel) e.getSource();
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.setForeground(Color.GRAY);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
		label.setForeground(Color.BLACK);
		
	}
}
