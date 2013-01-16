package controller;

import java.util.ResourceBundle;

public class BaseController {

	protected ResourceBundle rb;

	public BaseController() {
		this.rb = ResourceBundle.getBundle("resources/MessageBundle");
	}
}
