package controller;

import dao.App_User;
import business.LoginService;
import view.LoginView;

public class LoginViewController extends BaseController {

	private LoginView loginView;
	private LoginService service;

	public LoginViewController(LoginView loginView) {
		this.loginView = loginView;
		service = new LoginService();
	}

	public App_User loginClicked(String username, String password) {

		return service.loginClicked(username, password);
	}

}
