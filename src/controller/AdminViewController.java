package controller;

import business.AdminService;
import dao.App_User;
import exception.CannotAddNewAppUserException;
import view.AdminView;

public class AdminViewController extends BaseController {

	private AdminView adminView;
	private AdminService service;
	
	public AdminViewController(AdminView view) {
		this.adminView = view;
		service = new AdminService();
	}
	
	public void addNewAppUser(App_User user) {
		try {
			service.addNewAppUser(user);
		} catch (CannotAddNewAppUserException e) {
			e.printStackTrace();
		}
	}
}
