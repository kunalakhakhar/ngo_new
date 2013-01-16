package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import view.components.MemberForm;
import view.components.SaveCancel;
import view.components.SearchMemberComponent;

import controller.MemberViewController;
import dao.Address;
import dao.Member;

public class MemberView extends AppView {
	private JPanel panel;
	private JPanel addMemberPanel;
	private JPanel editMemberPanel;
	private JPanel searchContainerPanel;
	private JTabbedPane tabbedPane;
	private SearchView searchView;
	private SaveCancel addSaveCancel, editSaveCancel;
	private MemberViewController controller;
	private SearchMemberComponent searchMemberComponent;
	private MemberForm addMemberForm;

	@Override
	public Component getScreenComponent() {
		return constructScreen();
	}

	@Override
	public void setParentView(AppView parentView) {
		this.parentView = parentView;
	}

	private void initializeControls() {
		panel = new JPanel(new BorderLayout());
		addMemberPanel = new JPanel();
		editMemberPanel = new JPanel();
		tabbedPane = new JTabbedPane();
		addSaveCancel = new SaveCancel(rb);
		editSaveCancel = new SaveCancel(rb);
		searchView = new SearchView();
		searchView.setParentView(this);
		searchContainerPanel = new JPanel(new BorderLayout());
		searchMemberComponent = new SearchMemberComponent(rb);
		addMemberForm = new MemberForm(rb);
	}

	private void addEventListeners() {
		addSaveCancel.saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Member dao = new Member();
				dao.address = new Address();
				dao.firstname = addMemberForm.member_firstnameTxt.getText();
				dao.lastname = addMemberForm.member_lastnameTxt.getText();
				dao.address.line1 = addMemberForm.address_line1Txt.getText();
				dao.address.line2 = addMemberForm.address_line2Txt.getText();
				dao.address.city = addMemberForm.cityTxt.getText();
				dao.address.pincode = addMemberForm.pincodeTxt.getText();
				dao.telephone_no = addMemberForm.tel_noTxt.getText();
				dao.fax = addMemberForm.faxTxt.getText();
				dao.email = addMemberForm.email_idTxt.getText();
				dao.mobile_no = addMemberForm.mobile_noTxt.getText();
				dao.gender = (String)addMemberForm.genderCombo.getSelectedItem();
				if(dao.gender==rb.getString("combo.gender.male"))
					dao.gender = "m";
				else
					dao.gender = "f";
				controller.addNewMember(dao);
			}
		});

		addSaveCancel.cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetAllFields();
			}
		});

		editSaveCancel.saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// member dao
			}
		});

		editSaveCancel.cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetAllFields();
			}
		});
	}

	private void setDisplayProperties() {
		panel.setSize(300, 300);
		panel.setVisible(true);
	}

	private void setLayout() {

		panel.add(tabbedPane);

		tabbedPane.addTab(rb.getString("memberScreen.tab.add.member"),
				addMemberPanel);
//		tabbedPane.addTab(rb.getString("memberScreen.tab.edit.member"),
//				editMemberPanel);
		tabbedPane.addTab(rb.getString("memberScreen.tab.search.member"),
				editMemberPanel);

		searchContainerPanel.add(searchMemberComponent, BorderLayout.NORTH);
		searchContainerPanel.add(searchView.getScreenComponent(),
				BorderLayout.CENTER);

		addMemberPanel.setLayout(new BorderLayout());
		addMemberPanel.add(addMemberForm, BorderLayout.CENTER);
		addMemberPanel.add(addSaveCancel, BorderLayout.SOUTH);

		editMemberPanel.setLayout(new BorderLayout());
		editMemberPanel.add(searchMemberComponent, BorderLayout.NORTH);
		editMemberPanel.add(new MemberForm(rb), BorderLayout.CENTER);
		editMemberPanel.add(editSaveCancel, BorderLayout.SOUTH);
	}

	private void initializeController() {
		controller = new MemberViewController();
		
	}

	private JPanel constructScreen() {
		initializeControls();
		initializeController();
		addEventListeners();
		setLayout();
		setDisplayProperties();

		return panel;
	}

	private void resetAllFields() {
	}

}
