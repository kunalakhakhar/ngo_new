package view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.DatePickerUtil;
import util.DateUtil;

import net.sourceforge.jdatepicker.AbstractDateModel;
import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePicker;

import controller.DonationViewController;
import dao.Donation;

public class DonationView extends AppView {

	private JPanel panel;
	private GridLayout gLayout;
	private JRadioButton donor_idRadio, donor_nameRadio;
	private ButtonGroup radioBtnGroup;
	private JLabel donor_idLabel, donor_firstnameLabel, donor_lastnameLabel,
			donationAmountLabel, donated_forLabel, dateLabel;
	private JTextField donor_idTxt, donor_firstnameTxt, donor_lastnameTxt,
			donationAmountTxt;
	private JTextArea donated_forTxt;
	private JDatePicker eng_datePicker;
	private JButton saveBtn, cancelBtn;
	private DonationViewController controller;

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
		gLayout = new GridLayout(6, 2);
		donor_idLabel = new JLabel(rb.getString("donateScreen.donor.id"));
		donor_firstnameLabel = new JLabel(
				rb.getString("donateScreen.donor.firstname"));
		donor_lastnameLabel = new JLabel(
				rb.getString("donateScreen.donor.lastname"));
		donationAmountLabel = new JLabel(
				rb.getString("donateScreen.donor.amount"));
		donated_forLabel = new JLabel(rb.getString("donateScreen.donated.for"));
		dateLabel = new JLabel(rb.getString("donateScreen.donation.date"));
		donor_idTxt = new JTextField("");
		donor_firstnameTxt = new JTextField("");
		donor_lastnameTxt = new JTextField("");
		donationAmountTxt = new JTextField("");
		donated_forTxt = new JTextArea("");
		eng_datePicker = DatePickerUtil.GetDatePicker();
		saveBtn = new JButton(rb.getString("screen.saveBtn"));
		cancelBtn = new JButton(rb.getString("screen.cancelBtn"));
		donor_idRadio = new JRadioButton(rb.getString("donateScreen.donor.id"));
		donor_nameRadio = new JRadioButton(
				rb.getString("donateScreen.donor.name"), true);
		radioBtnGroup = new ButtonGroup();
		radioBtnGroup.add(donor_idRadio);
		radioBtnGroup.add(donor_nameRadio);

	}

	private void addEventListeners() {
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Donation d = new Donation();
				if (donor_idRadio.isSelected())
					d.donor_id = Integer.parseInt(donor_idTxt.getText());
				if (donor_nameRadio.isSelected()) {
					d.donor_firstname = donor_firstnameTxt.getText();
					d.donor_lastname = donor_lastnameTxt.getText();
				}
				String dt = eng_datePicker.getModel().getValue().toString();
				if (dt == null || dt == "")
					d.donation_date = new java.sql.Date(new java.util.Date()
							.getTime());
				else
					d.donation_date = new java.sql.Date(DateUtil
							.stringToDate(dt));
				// validate fields,
				// catch for any sql exception
				// construct donation dao
				d.donation_amount = Double.parseDouble(donationAmountTxt.getText());
				d.donated_for = donated_forTxt.getText();
				int donateStatus = controller.saveDonationRecord(d);
			}
		});

		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetAllFields();
			}
		});

		eng_datePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(eng_datePicker.getModel().getValue()
						.toString());
			}
		});

		donor_idRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				donor_idTxt.setEnabled(true);
				donor_firstnameTxt.setEnabled(false);
				donor_lastnameTxt.setEnabled(false);
			}
		});
		donor_nameRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				donor_firstnameTxt.setEnabled(true);
				donor_lastnameTxt.setEnabled(true);
				donor_idTxt.setEnabled(false);
			}
		});
	}

	private void setDisplayProperties() {
		panel.setSize(300, 300);
		panel.setVisible(true);
		donor_idTxt.setEnabled(false);
	}

	private void setLayout() {
		panel.setLayout(gLayout);
		panel.add(donor_idRadio);
		panel.add(donor_idTxt);
		panel.add(donor_nameRadio);
		JPanel namePanel = new JPanel(new GridLayout(2, 2));
		panel.add(namePanel);
		namePanel.add(donor_firstnameLabel);
		namePanel.add(donor_firstnameTxt);
		namePanel.add(donor_lastnameLabel);
		namePanel.add(donor_lastnameTxt);
		panel.add(dateLabel);
		JComponent guiElement = null;
		if (eng_datePicker instanceof JComponent) {
			guiElement = (JComponent) eng_datePicker;
			guiElement.setVisible(true);
		}
		panel.add(guiElement);
		panel.add(donationAmountLabel);
		panel.add(donationAmountTxt);
		panel.add(donated_forLabel);
		panel.add(donated_forTxt);
		panel.add(saveBtn);
		panel.add(cancelBtn);
	}

	private void initializeController() {
		controller = new DonationViewController(this);
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
		donor_firstnameTxt.setText("");
		donor_lastnameTxt.setText("");
		donationAmountTxt.setText("");
		donated_forTxt.setText("");
		eng_datePicker.getModel().setValue(null);
	}
}
