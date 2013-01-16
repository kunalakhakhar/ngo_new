package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.DatePickerUtil;
import util.DateUtil;

import net.sourceforge.jdatepicker.JDatePicker;

public class LetterView extends AppView {

	private JPanel panel;
	private JLabel  ref_no, dateLabel, aatmaPriya, shreyasMiti;
	private JButton printBtn;
	private JDatePicker datePicker;
	private JTextField  ref_noTxt, aatmaPriyaTxt, shreyasMitiTxt;
	
	@Override
	public Component getScreenComponent() {
		return constructScreen();
	}
	
	@Override
	public void setParentView(AppView parentView) {
		this.parentView = parentView;
	}
	
	private void initializeControls() {
		panel = new JPanel(new GridLayout(5,2));
		ref_no = new JLabel("Ref. No. PAT/ ");
		
		dateLabel = new JLabel("Letter date ");
		aatmaPriya = new JLabel("Aatma priya ");
		shreyasMiti = new JLabel("Shreyas miti ");
		printBtn = new JButton("Print letter");
		datePicker = DatePickerUtil.GetDatePicker();
		
		ref_noTxt = new JTextField("Ref 111");
		ref_noTxt.setEnabled(false);
		aatmaPriyaTxt  = new JTextField("");
		shreyasMitiTxt  = new JTextField("");
	}
	
	private void addEventListeners() {
		
	}
	
	private void setDisplayProperties() {
		panel.setSize(300, 300);
		panel.setVisible(true);
	}
	
	private void setLayout() {
		panel.add(ref_no);
		panel.add(ref_noTxt);
		panel.add(dateLabel);
		JComponent guiElement = null;
		if (datePicker instanceof JComponent) {
			guiElement = (JComponent) datePicker;
			guiElement.setVisible(true);
		}
		panel.add(guiElement);
		panel.add(aatmaPriya);
		panel.add(aatmaPriyaTxt);
		panel.add(shreyasMiti);
		panel.add(shreyasMitiTxt);
		panel.add(printBtn);
	}
	
	private void initializeController() {
		
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
