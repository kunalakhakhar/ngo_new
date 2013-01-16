package controller;

import java.util.List;

import business.DonationService;
import dao.Donation;
import dao.SearchDonationDao;
import view.SearchView;

public class SearchDonationController extends BaseController {

	private static final int BY_NAME = 0;
	private static final int BY_MEMBER_ID = 1;
	private static final int BY_DATE = 2;
	private static final int BY_TITHI = 3;

	private SearchView view;
	private DonationService service;

	public SearchDonationController(SearchView view) {
		this.view = view;
		this.service = new DonationService();
	}

	public List<Donation> searchDonationRecord(SearchDonationDao dao,
			int searchBy) {
		List<Donation> donationList = null;
		if (searchBy == BY_NAME)
			donationList = service.searchDonationByName(dao.donor_firstname,
					dao.donor_lastname);
		if (searchBy == BY_MEMBER_ID) {
			donationList = service.searchDonationByMemberId(dao.member_id);
		}
		if (searchBy == BY_DATE) {
			donationList = service.searchDonationByDate(dao.fromDonation_date,
					dao.toDonation_date);
		}
		if (searchBy == BY_TITHI)
			donationList = service.searchDonationByTithi();
		return donationList;
	}

}
