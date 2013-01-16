package view.components;

import java.awt.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;

public class LinkButton {

	private String linkTxt = null;
	private JButton button = null;
	
	public LinkButton(){
	}
	
	public LinkButton(String linkTxt){
		this.linkTxt = linkTxt;
		constructComponent();
	}

	public void setText(String txt) {
		this.linkTxt = txt;
	}

	public JButton getButtonComponent() {
		return button;
	}

	private void constructComponent() {
		button = new JButton();
		button.setText(this.linkTxt);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBorderPainted(false);
		button.setOpaque(false);
		button.setBackground(Color.lightGray);
		button.setSize(75, 10);
	}
}