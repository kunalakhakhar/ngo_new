package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.LoginViewController;
import dao.App_User;

public class LoginView extends AppView {

	private JPanel panel, container;
	private GridLayout gLayout;
	private JLabel usernameLabel, passwordLabel, loginSuccessLabel, loginFailureLabel;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	private JButton loginBtn, cancelBtn;
	private LoginViewController controller;

	@Override
	public Component getScreenComponent() {
		return constructScreen();
	}
	
	@Override
	public void setParentView(AppView parentView){
		this.parentView = parentView;
	}

	private void initializeControls() {
		container = new JPanel(new BorderLayout());
		panel = new JPanel();
		container.add(panel,BorderLayout.CENTER);
		gLayout = new GridLayout(3, 2);
		usernameLabel = new JLabel(rb.getString("loginScreen.username"));
		passwordLabel = new JLabel(rb.getString("loginScreen.password"));
		loginSuccessLabel = new JLabel("Login successful");
		loginFailureLabel = new JLabel("");
		usernameTxt = new JTextField("");
		passwordTxt = new JPasswordField("");
		loginBtn = new JButton(rb.getString("loginScreen.loginButton"));
		cancelBtn = new JButton(rb.getString("loginScreen.cancelButton"));
	}
	
	private void addEventListeners(){
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				checkLoginDetails();
			}
		});
		usernameTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				checkLoginDetails();
			}
		});
		passwordTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				checkLoginDetails();
			}
		});
	}

	private void setDisplayProperties() {
		panel.setSize(300, 300);
		panel.setVisible(true);
	}

	private void setLayout() {
		panel.setLayout(gLayout);
		panel.add(usernameLabel);
		panel.add(usernameTxt);
		panel.add(passwordLabel);
		panel.add(passwordTxt);
		panel.add(loginBtn);
		panel.add(cancelBtn);
	}
	
	private void initializeController(){
		controller = new LoginViewController(this);
	}

	private JPanel constructScreen() {
		initializeControls();
		initializeController();
		addEventListeners();
		setLayout();
		setDisplayProperties();
		return container;
	}
	
	private void onLoginSuccessful(App_User appUser){
		container.remove(loginFailureLabel);
		((MainAppView)this.parentView).loginSuccessful(appUser);
	}
	
	private void onLoginFailure(){
		
		container.remove(loginFailureLabel);
		container.add(loginFailureLabel, BorderLayout.SOUTH);
		draw(container);
		//((MainAppView)this.parentView).exitApplication(1);
	}
	
	private void checkLoginDetails(){
		String username = usernameTxt.getText().trim();
		String password = passwordTxt.getText().trim();
		System.out.println("password is: "+password);
		if(username.equals("")){
			loginFailureLabel.setText("Please enter a valid username");
			onLoginFailure();
			return;
		}
		if(password.equals("")){
			loginFailureLabel.setText("Please enter a valid password");
			onLoginFailure();
			return;
		}
			
		App_User user = controller.loginClicked(username,password);
		if(user!=null)
			onLoginSuccessful(user);
		else{
			loginFailureLabel.setText("Username and/or password incorrect");
			onLoginFailure();
		}
	}
	
	private void draw(JPanel comp){
		comp.validate();
		comp.repaint();
	}
}
