package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import dao.App_User;

import net.jyotish.tithi.calendar.TTCalendarDay;
import net.jyotish.tithi.main.TTModel;

import util.GetCurrentUser;
import view.components.LinkButton;

public class MainAppView extends AppView {

	private JFrame f;
	private JLabel tithiLabel, user_fnameLabel, user_lnameLabel;
	private LinkButton logoutBtn;
	private LoginView loginView;
	private DonationView donationView;
	private SearchView searchView;
	private ReportsView reportsView;
	private LetterView letterView;
	private AdminView adminView;
	private MemberView memberView;
	private JTabbedPane tabbedPane;
	private Component loginViewComp;
	private Box usernameAndLogoutBox;

	public MainAppView() {

	}

	private void intitializeControls() {
		String appHeader = rb.getString("mainScreen.appHeader");
		f = new JFrame(appHeader);
		tithiLabel = new JLabel("Current");
		usernameAndLogoutBox = Box.createHorizontalBox();
		user_fnameLabel = new JLabel("");
		user_lnameLabel = new JLabel("");
	}

	private void setLayout() {
		f.setLayout(new BorderLayout());
		setLogoutView();
	}

	private void setAppUser(App_User appUser) {
		GetCurrentUser.SetUser(appUser);
	}

	public void loginSuccessful(App_User appUser) {
		if (loginView != null) {
			f.remove(loginViewComp);
			loginView = null;
			loginViewComp = null;
		}
		setAppUser(appUser);
		constructMainAppScreen();
		addEventListeners();
	}

	private void setDisplayProperties() {
		f.setSize(600, 500);
		f.setVisible(true);
		f.setResizable(true);
	}

	private void constructMainAppScreen() {

		tabbedPane = new JTabbedPane();

		donationView = new DonationView();
		donationView.setParentView(this);

		searchView = new SearchView();
		searchView.setParentView(this);

		reportsView = new ReportsView();
		reportsView.setParentView(this);

		memberView = new MemberView();
		memberView.setParentView(this);

		letterView = new LetterView();
		letterView.setParentView(this);

		logoutBtn = new LinkButton(rb.getString("screen.logout"));
		logoutBtn.getButtonComponent().setSize(50, 10);
		f.add(usernameAndLogoutBox, BorderLayout.NORTH);
		// set the label alignment here;
		user_fnameLabel.setText(GetCurrentUser.GetUser().user_firstname);
		usernameAndLogoutBox.add(user_fnameLabel);
		usernameAndLogoutBox.add(new JLabel(" "));
		user_lnameLabel.setText(GetCurrentUser.GetUser().user_lastname);
		usernameAndLogoutBox.add(user_lnameLabel);
		usernameAndLogoutBox.add(logoutBtn.getButtonComponent(),
				BorderLayout.NORTH);
		logoutBtn.getButtonComponent().setVisible(true);
		logoutBtn.getButtonComponent().setHorizontalAlignment(
				SwingConstants.RIGHT);
		logoutBtn.getButtonComponent().setVerticalAlignment(SwingConstants.TOP);
		f.add(tabbedPane);

		tabbedPane.addTab(rb.getString("tab.make.donation"),
				donationView.getScreenComponent());
		tabbedPane.addTab(rb.getString("tab.search"),
				searchView.getScreenComponent());
		tabbedPane.addTab(rb.getString("tab.member"),
				memberView.getScreenComponent());
		tabbedPane.addTab(rb.getString("tab.reports"),
				reportsView.getScreenComponent());
		tabbedPane.addTab(rb.getString("tab.send.letter"),
				letterView.getScreenComponent());
		if (GetCurrentUser.GetUser().is_admin == 1)
			addAdminView();
		f.add(tithiLabel, BorderLayout.SOUTH);
		Date dt = null;
		TTModel tm = new TTModel(File.separator + "lib" + File.separator
				+ "0434.tithi");
		try {
			dt = new SimpleDateFormat("yyyy-MM-dd").parse("2013-01-30");
		} catch (ParseException e) {

		}
		TTCalendarDay day = tm.getTithiForDate(dt);
		System.out.println(day.getFirstTithi());
		tithiLabel.setText(day.getFirstTithi());

	}

	private void addEventListeners() {
		if (logoutBtn != null) {
			logoutBtn.getButtonComponent().addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							logoutClicked();
						}
					});
		}
	}

	private void addAdminView() {
		adminView = new AdminView();
		adminView.setParentView(this);
		tabbedPane.addTab(rb.getString("tab.admin"),
				adminView.getScreenComponent());
	}

	private void setLogoutView() {
		if (tabbedPane != null) {
			tabbedPane.setSize(0, 0);
			f.remove(tabbedPane);
			tabbedPane = null;
			f.remove(logoutBtn.getButtonComponent());
		}
		if (usernameAndLogoutBox != null)
			f.remove(usernameAndLogoutBox);
		loginView = new LoginView();
		loginView.setParentView(this);
		loginViewComp = loginView.getScreenComponent();
		f.add(loginViewComp);
		setDisplayProperties();
	}

	private void logoutClicked() {
		setLogoutView();
	}

	public void exitApplication(int status) {
		System.exit(status);
	}

	public void startApp() {
		intitializeControls();
		setLayout();
		addEventListeners();
		setDisplayProperties();
	}

}
