package view.components;

import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchMemberComponent extends JPanel {

	private ResourceBundle rb;

	public JLabel member_idLabel, member_firstnameLabel, member_lastnameLabel;
	public JTextField member_idTxt, member_firstnameTxt, member_lastnameTxt;
	public JButton searchBtn;

	public SearchMemberComponent(ResourceBundle rb) {
		this.rb = rb;
		initializeControls();
		setLayout();
		setDisplayProperties();
	}

	private void initializeControls() {
		member_idLabel = new JLabel(rb.getString("memberScreen.member.id"));
		member_firstnameLabel = new JLabel(
				rb.getString("memberScreen.member.firstname"));
		member_lastnameLabel = new JLabel(
				rb.getString("memberScreen.member.lastname"));

		member_firstnameTxt = new JTextField("");
		member_lastnameTxt = new JTextField("");
		member_idTxt = new JTextField("");
		searchBtn = new JButton(rb.getString("screen.searchBtn"));
	}

	private void setLayout() {
		this.setLayout(new GridLayout(4, 2,0,0));
		this.add(member_idLabel);
		this.add(member_idTxt);
		this.add(member_firstnameLabel);
		this.add(member_firstnameTxt);
		this.add(member_lastnameLabel);
		this.add(member_lastnameTxt);
		this.add(searchBtn);
	}

	private void setDisplayProperties() {
		this.setBorder(BorderFactory.createTitledBorder(rb
				.getString("memberScreen.searchComp.search.by")));
		this.setSize(600, 50);
		this.setVisible(true);
	}

}
