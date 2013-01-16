package view;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.AdminViewController;

import view.components.AdvancedTableExample;
import view.components.EditSysUserForm;
import view.components.SearchSysUserComponent;
import view.components.SysUserForm;

public class AdminView extends AppView {

	private JPanel panel;
	private JTabbedPane tabbedPane;
	private SysUserForm sysUserForm;
	private EditSysUserForm editUserForm;
	private SearchSysUserComponent searchComponent;
	private AdvancedTableExample table;
	private AdminViewController controller;

	@Override
	public Component getScreenComponent() {
		return constructScreen();
	}

	@Override
	public void setParentView(AppView parentView) {
		this.parentView = parentView;
	}

	private void initializeControls() {
		panel = new JPanel();
		tabbedPane = new JTabbedPane();
		sysUserForm = new SysUserForm(rb);
		editUserForm = new EditSysUserForm();
		searchComponent = new SearchSysUserComponent(rb);
		table = new AdvancedTableExample();
	}

	private void addEventListeners() {

	}

	private void setDisplayProperties() {
		sysUserForm.setSize(300,300);
		panel.setVisible(true);
	}

	private void setLayout() {
		panel.add(tabbedPane);
		tabbedPane
		.addTab(rb.getString("adminScreen.add.user"), sysUserForm);
		sysUserForm.add(table);
		tabbedPane
		.addTab(rb.getString("adminScreen.edit.user"), editUserForm);
		editUserForm.add(searchComponent);
		editUserForm.add(table);
	}

	private void initializeController() {
		controller = new AdminViewController(this);
		sysUserForm.setController(controller);
	}

	private JPanel constructScreen() {
		initializeControls();
		initializeController();
		addEventListeners();
		setLayout();
		setDisplayProperties();
		return panel;
	}
}
