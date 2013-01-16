package view.components;

import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchSysUserComponent extends JPanel {

	private ResourceBundle rb;

	public JLabel username_Label, user_firstnameLabel, user_lastnameLabel;
	public JTextField username_Txt, user_firstnameTxt, user_lastnameTxt;
	public JButton searchBtn;

	public SearchSysUserComponent(ResourceBundle rb) {
		this.rb = rb;
		initializeControls();
		setLayout();
		setDisplayProperties();
	}

	private void initializeControls() {
		username_Label = new JLabel(rb.getString("adminScreen.user.username"));
		user_firstnameLabel = new JLabel(
				rb.getString("adminScreen.user.firstname"));
		user_lastnameLabel = new JLabel(
				rb.getString("adminScreen.user.lastname"));

		username_Txt = new JTextField("");
		user_firstnameTxt = new JTextField("");
		user_lastnameTxt = new JTextField("");
		searchBtn = new JButton(rb.getString("screen.searchBtn"));
	}

	private void setLayout() {
		this.setLayout(new GridLayout(4, 2));
		this.add(username_Label);
		this.add(username_Txt);
		this.add(user_firstnameLabel);
		this.add(user_firstnameTxt);
		this.add(user_lastnameLabel);
		this.add(user_lastnameTxt);
		this.add(searchBtn);
	}

	private void setDisplayProperties() {
		this.setBorder(BorderFactory.createTitledBorder(rb
				.getString("memberScreen.searchComp.search.by")));
		this.setSize(600, 50);
		this.setVisible(true);
	}
}
