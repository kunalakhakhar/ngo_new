package view;

import java.awt.Component;
import java.util.ResourceBundle;

import javax.swing.JPanel;

public class AppView {
	
	protected ResourceBundle rb;
	protected AppView parentView;
	
	public AppView(){
		rb = ResourceBundle.getBundle("resources/MessageBundle");
	}
	
	public void setParentView(AppView parentView){
		parentView = this;
	}

	public Component getScreenComponent(){
		return new JPanel();
	}
}
