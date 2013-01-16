package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.DatePickerUtil;
import view.components.TablePagination;

import net.sourceforge.jdatepicker.JDatePicker;
import controller.DonationViewController;
import controller.SearchDonationController;
import dao.Donation;
import dao.SearchDonationDao;

public class SearchView extends AppView {

	private JPanel panel, searchBy, searchPanel, searchByIdPanel, searchByNamePanel,
			searchByDatePanel, searchByTithiPanel;
	private JLabel searchDonationBy, donor_idLabel, donor_firstnameLabel, donor_lastnameLabel,
			eng_fromDateLabel, eng_toDateLabel, tithi_dateLabel,
			searchResultMessage;
	private JTextField donor_idTxt, donor_firstnameTxt, donor_lastnameTxt;
	private JDatePicker eng_fromDatePicker, eng_toDatePicker;
	private JButton searchBtn, cancelBtn;
	private TablePagination searchResultTable;
	private JComboBox searchByCombo;

	private SearchDonationController controller;

	@Override
	public Component getScreenComponent() {
		return constructScreen();
	}

	@Override
	public void setParentView(AppView parentView) {
		this.parentView = parentView;
	}

	private void initializeControls() {
		panel = new JPanel(new GridLayout(4, 1));
		searchBy = new JPanel(new GridLayout(1, 2));
		searchPanel = new JPanel(new BorderLayout());
		searchDonationBy = new JLabel("Search Donation by");
		searchByCombo = new JComboBox(new String[] { "Name", "Member ID", "Date", "Tithi" });
		searchBy.add(searchDonationBy);
		searchBy.add(searchByCombo);

		searchByNamePanel = new JPanel(new GridLayout(2, 2));
		searchPanel.add(searchByNamePanel);

		donor_firstnameLabel = new JLabel(
				rb.getString("searchScreen.donor.firstname"));
		donor_lastnameLabel = new JLabel(
				rb.getString("searchScreen.donor.lastname"));
		donor_firstnameTxt = new JTextField("");
		donor_lastnameTxt = new JTextField("");
		searchByNamePanel.add(donor_firstnameLabel);
		searchByNamePanel.add(donor_firstnameTxt);
		searchByNamePanel.add(donor_lastnameLabel);
		searchByNamePanel.add(donor_lastnameTxt);
		
		searchByIdPanel = new JPanel(new GridLayout(1, 2));
		//searchPanel.add(searchByIdPanel);
		donor_idLabel = new JLabel(("Member ID"));
		donor_idTxt = new JTextField("");
		searchByIdPanel.add(donor_idLabel);
		searchByIdPanel.add(donor_idTxt);

		searchByDatePanel = new JPanel(new GridLayout(1, 4));
		eng_fromDateLabel = new JLabel("From");
		eng_fromDatePicker = DatePickerUtil.GetDatePicker();
		eng_toDateLabel = new JLabel("To");
		eng_toDatePicker = DatePickerUtil.GetDatePicker();
		searchByDatePanel.add(eng_fromDateLabel);
		searchByDatePanel.add((JComponent) eng_fromDatePicker);
		searchByDatePanel.add(eng_toDateLabel);
		searchByDatePanel.add((JComponent) eng_toDatePicker);

		searchByTithiPanel = new JPanel(new GridLayout());
		tithi_dateLabel = new JLabel(
				rb.getString("searchScreen.donate.hindu.date"));
		searchByTithiPanel.add(tithi_dateLabel);
		// tithi date picker
		searchBtn = new JButton(rb.getString("searchScreen.donation.searchBtn"));
		cancelBtn = new JButton(rb.getString("screen.cancelBtn"));
		//searchResultTable = new TablePagination();

	}

	private void addEventListeners() {
		eng_fromDatePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(eng_fromDatePicker.getModel().getValue()
						.toString());
			}
		});
		eng_toDatePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(eng_toDatePicker.getModel().getValue()
						.toString());
			}
		});

		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(searchResultTable!=null){
					panel.remove(searchResultTable);
					panel.revalidate();
					panel.repaint();
				}
				int searchStatus = -1;
				SearchDonationDao dao = new SearchDonationDao();
				if (searchByCombo.getSelectedIndex() == 0) {
					dao.donor_firstname = donor_firstnameTxt.getText().trim();
					dao.donor_lastname = donor_lastnameTxt.getText().trim();
					searchStatus = 0;
				}
				if (searchByCombo.getSelectedIndex() == 1) {
					dao.member_id = Integer.parseInt(donor_idTxt.getText().trim());
					searchStatus = 1;
				}
				if (searchByCombo.getSelectedIndex() == 2) {
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date fDate = null;
					java.util.Date tDate = null;
					try {
						fDate = df.parse(eng_fromDatePicker.getModel()
								.getValue().toString());
						tDate = df.parse(eng_toDatePicker.getModel()
								.getValue().toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
					dao.fromDonation_date = fDate.getTime();
					dao.toDonation_date = tDate.getTime();
					searchStatus = 2;
				}
				if (searchByCombo.getSelectedIndex() == 3) {
					searchStatus = 3;
				}

				List<Donation> list = controller.searchDonationRecord(dao,searchStatus);
				Object[] o = null;
				Donation d = null;
				ArrayList<Object[]> dList = new ArrayList<Object[]>();
				String d_id = null;
				for (int i=0;i<list.size();i++){
					d = (Donation)list.get(i);
					d_id = (new Integer(d.donor_id)).toString();
					if(d_id.equals("0"))
						d_id = "Non-member";
					o = new Object[]{d_id, d.donor_firstname,d.donor_lastname,d.donation_amount,d.donated_for,d.donation_date};
					//for (int j=0;j<12;j++)
					dList.add(o);
				}
				searchResultTable = new TablePagination();
				panel.add(searchResultTable);
				searchResultTable.setVisible(true);
				searchResultTable.setTableData(getColumns(), dList);
//				panel.revalidate();
//				panel.repaint();
			}
		});

		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetAllFields();
				panel.remove(searchResultTable);
				panel.revalidate();
				panel.repaint();
			}
		});

		searchByCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchPanel.removeAll();
				if (searchByCombo.getSelectedIndex() == 0) {
					searchPanel.add(searchByNamePanel);
				}
				if (searchByCombo.getSelectedIndex() == 1) {
					searchPanel.add(searchByIdPanel);
				}
				if (searchByCombo.getSelectedIndex() == 2) {
					searchPanel.add(searchByDatePanel);
				}
				if (searchByCombo.getSelectedIndex() == 3) {
					searchPanel.add(searchByTithiPanel);
				}
				panel.validate();
				panel.repaint();
				System.out.println("combo index "
						+ searchByCombo.getSelectedIndex());
			}
		});
	}
	
	private String[] getColumns(){
		String[] cols = new String[]{"Member ID", "First Name","Last Name","Amount","Donated for","Date"};
		return cols;
	}

	private void setDisplayProperties() {
		panel.setSize(300, 300);
		panel.setVisible(true);
		//searchResultTable.setVisible(false);
	}

	private void setLayout() {
		panel.add(searchBy);
		panel.add(searchPanel);
		JPanel searchCancelPanel = new JPanel(new GridLayout(1, 2));
		panel.add(searchCancelPanel);
		searchCancelPanel.add(searchBtn);
		searchCancelPanel.add(cancelBtn);
		//panel.add(searchResultTable);

	}

	private void initializeController() {
		controller = new SearchDonationController(this);
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
		System.out.println("combo index " + searchByCombo.getSelectedIndex());
	}
}
