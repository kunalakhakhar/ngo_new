package view.components;

import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MemberForm extends JPanel {
	private ResourceBundle rb;

	public JLabel member_firstnameLabel, member_lastnameLabel, tel_noLabel,
			email_idLabel, faxLabel, mobile_noLabel, genderLabel,
			address_line1Label, address_line2Label, cityLabel, pincodeLabel;
	public JTextField member_firstnameTxt, member_lastnameTxt, tel_noTxt,
			email_idTxt, faxTxt, mobile_noTxt, address_line1Txt,
			address_line2Txt, cityTxt, pincodeTxt;
	public JComboBox genderCombo;

	public MemberForm(ResourceBundle rb) {
		this.rb = rb;
		initializeControls();
		setLayout();
		setDisplayProperties();
	}

	private void initializeControls() {

		member_firstnameLabel = new JLabel(
				rb.getString("memberScreen.member.firstname"));
		member_lastnameLabel = new JLabel(
				rb.getString("memberScreen.member.lastname"));
		tel_noLabel = new JLabel(rb.getString("memberScreen.member.telephone"));
		email_idLabel = new JLabel(rb.getString("memberScreen.member.email"));
		faxLabel = new JLabel(rb.getString("memberScreen.member.fax"));
		mobile_noLabel = new JLabel(
				rb.getString("memberScreen.member.mobile_no"));
		genderLabel = new JLabel(rb.getString("memberScreen.member.gender"));
		address_line1Label = new JLabel(
				rb.getString("memberScreen.member.address.line1"));
		address_line2Label = new JLabel(
				rb.getString("memberScreen.member.address.line2"));
		cityLabel = new JLabel(rb.getString("memberScreen.member.address.city"));
		pincodeLabel = new JLabel(
				rb.getString("memberScreen.member.address.pincode"));

		member_firstnameTxt = new JTextField("");
		member_lastnameTxt = new JTextField("");
		address_line1Txt = new JTextField("");
		address_line2Txt = new JTextField("");
		tel_noTxt = new JTextField("");
		email_idTxt = new JTextField("");
		faxTxt = new JTextField("");
		mobile_noTxt = new JTextField("");
		cityTxt = new JTextField("");
		pincodeTxt = new JTextField("");

		String[] genderOptions = { rb.getString("combo.gender.male"),
				rb.getString("combo.gender.female") };
		genderCombo = new JComboBox(genderOptions);

	}

	private void setLayout() {

		this.setLayout(new GridLayout(11, 2));
		this.add(member_firstnameLabel);
		this.add(member_firstnameTxt);
		this.add(member_lastnameLabel);
		this.add(member_lastnameTxt);
		this.add(address_line1Label);
		this.add(address_line1Txt);
		this.add(address_line2Label);
		this.add(address_line2Txt);
		this.add(cityLabel);
		this.add(cityTxt);
		this.add(pincodeLabel);
		this.add(pincodeTxt);
		this.add(genderLabel);
		this.add(genderCombo);
		this.add(email_idLabel);
		this.add(email_idTxt);
		this.add(tel_noLabel);
		this.add(tel_noTxt);
		this.add(mobile_noLabel);
		this.add(mobile_noTxt);
		this.add(faxLabel);
		this.add(faxTxt);
	}

	private void setDisplayProperties() {
		this.setVisible(true);
	}

}
