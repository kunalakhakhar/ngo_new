package view.components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.AdminViewController;
import dao.App_User;

public class SysUserForm extends JPanel{

	private ResourceBundle rb;

	public JLabel user_firstnameLabel, user_lastnameLabel, passwordLabel,
			confirm_passwordLabel, usernameLabel, is_adminLabel, messageLabel;
	public JTextField user_firstnameTxt, user_lastnameTxt, usernameTxt;
	
	public JPasswordField passwordTxt,confirm_passwordTxt;
	public JComboBox is_adminCombo;
	public SaveCancel saveCancel;
	private AdminViewController controller;

	public SysUserForm(ResourceBundle rb) {
		this.rb = rb;
		initializeControls();
		setLayout();
		addEventListeners();
		setDisplayProperties();
	}
	
	public void setController(AdminViewController controller){
		this.controller = controller;
	}

	private void initializeControls() {

		user_firstnameLabel = new JLabel(
				rb.getString("adminScreen.user.firstname"));
		user_lastnameLabel = new JLabel(
				rb.getString("adminScreen.user.lastname"));
		usernameLabel = new JLabel(rb.getString("adminScreen.user.username"));
		passwordLabel = new JLabel(rb.getString("adminScreen.user.password"));
		confirm_passwordLabel = new JLabel(rb.getString("adminScreen.user.confirm.password"));
		is_adminLabel = new JLabel(
				rb.getString("adminScreen.user.admin.permission"));
		user_firstnameLabel = new JLabel("");
		
		is_adminCombo = new JComboBox(new String[]{rb.getString("screen.no"),rb.getString("screen.yes")});
		
		user_firstnameTxt = new JTextField();
		user_lastnameTxt = new JTextField();
		passwordTxt = new JPasswordField();
		confirm_passwordTxt = new JPasswordField();
		usernameTxt = new JTextField();
		saveCancel = new SaveCancel(rb);

	}

	private void setLayout() {

		this.setLayout(new GridLayout(7, 2));
		this.add(usernameLabel);
		this.add(usernameTxt);
		this.add(passwordLabel);
		this.add(passwordTxt);
		this.add(confirm_passwordLabel);
		this.add(confirm_passwordTxt);
		this.add(user_firstnameLabel);
		this.add(user_firstnameTxt);
		this.add(user_lastnameLabel);
		this.add(user_lastnameTxt);
		this.add(is_adminLabel);
		this.add(is_adminCombo);
		this.add(saveCancel);
		
	}

	private void setDisplayProperties() {
		this.setVisible(true);
	}
	
	private void addEventListeners(){
		saveCancel.saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.addNewAppUser(makeAppUserDao());
			}
		});
		
		saveCancel.cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetAllFields();
			}
		});
	}
	
	private App_User makeAppUserDao(){
		App_User user = new App_User();
		user.username = usernameTxt.getText().trim();
		user.user_firstname = user_firstnameTxt.getText().trim();
		user.user_lastname = user_lastnameTxt.getText().trim();
		user.password = passwordTxt.getText().trim();
		user.username = usernameTxt.getText().trim();
		user.is_admin = is_adminCombo.getSelectedIndex();
		return user;
	}
	
	private void resetAllFields(){
		
	}
	
}
