package controller;

import business.DonationService;
import dao.Donation;
import exception.CannotAddDonationException;
import view.DonationView;

public class DonationViewController {

	private DonationView donationView;
	private DonationService donationService;
	
	public DonationViewController(DonationView donationView){
		this.donationView = donationView;
		donationService = new DonationService();
	}
	
	public int saveDonationRecord(Donation d){
		try {
			donationService.addNewDonation(d);
		} catch (CannotAddDonationException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
