package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.components.AdvancedTableExample;

public class ReportsView extends AppView {

	private JPanel panel;
	private JLabel generate_reports_by;
	private JComboBox generate_reports_option_combo;
	private AdvancedTableExample table;
	
	@Override
	public Component getScreenComponent() {
		return constructScreen();
	}

	@Override
	public void setParentView(AppView parentView) {
		this.parentView = parentView;
	}
	
	private void initializeControls() {
		generate_reports_by = new JLabel("Generate reports by");
		String[] report_options = {"Member","Year","Month","Tithi"};
		generate_reports_option_combo = new JComboBox(report_options);
		table = new AdvancedTableExample();
		
	}
	
	private void addEventListeners() {
		
	}
	
	private void setDisplayProperties() {
		panel.setVisible(true);
	}
	
	private void setLayout() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel p = new JPanel(new GridLayout(1,2));
		panel.add(p,BorderLayout.NORTH);
		p.add(generate_reports_by);
		p.add(generate_reports_option_combo);
		panel.add(table, BorderLayout.CENTER);
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

