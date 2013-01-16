package view.components;

import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SaveCancel extends JPanel {

	private ResourceBundle rb;
	public JButton saveBtn, cancelBtn;

	private void initializeControls() {
		saveBtn = new JButton(rb.getString("screen.saveBtn"));
		cancelBtn = new JButton(rb.getString("screen.cancelBtn"));
	}

	private void setLayout() {
		this.setLayout(new GridLayout(1, 2));
		this.add(saveBtn);
		this.add(cancelBtn);
	}

	private void setDisplayProperties() {
		this.setVisible(true);
	}

	public SaveCancel(ResourceBundle rb) {
		this.rb = rb;
		initializeControls();
		setLayout();
		setDisplayProperties();
	}

}
