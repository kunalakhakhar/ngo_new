package business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.Donation;
import dao.SearchDonationDao;
import exception.CannotAddDonationException;

public class DonationService extends BaseService {

	PreparedStatement pstmt = null;

	public void addNewDonation(Donation d) throws CannotAddDonationException {
		String query = null;
		String fname = null;
		String lname = null;
		ResultSet rs = null;

		// check if the donation is by using the member id.
		// if yes than fetch the first and last name of the donor from the
		// member table;
		if (d.donor_id != -1) {
			query = "select firstname, lastname from member where member_id=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, d.donor_id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					fname = rs.getString("firstname");
					lname = rs.getString("lastname");
				}
				query = "insert into donation(member_id,donor_firstname,donor_lastname,donation_amount,donated_for,donation_accepted_by,donation_date) values(?,?,?,?,?,?,?)";
				pstmt = null;
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, d.donor_id);
				pstmt.setString(2, fname);
				pstmt.setString(3, lname);
				pstmt.setDouble(4, d.donation_amount);
				pstmt.setString(5, d.donated_for);
				pstmt.setInt(6, 1);
				pstmt.setDate(7, d.donation_date);
				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
					// conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			query = "insert into donation(donor_firstname,donor_lastname,donation_amount,donated_for,donation_accepted_by,donation_date) values(?,?,?,?,?,?)";
			pstmt = null;
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, d.donor_firstname);
				pstmt.setString(2, d.donor_lastname);
				pstmt.setDouble(3, d.donation_amount);
				pstmt.setString(4, d.donated_for);
				pstmt.setInt(5, 1);
				pstmt.setDate(6, d.donation_date);
				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
				throw new CannotAddDonationException();
			} finally {
				try {
					pstmt.close();
					// conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<Donation> searchDonationByName(String fname, String lname) {
		ResultSet rs = null;
		List<Donation> dList = new ArrayList<Donation>();
		Donation d = null;

		String query = "select * from donation where donor_firstname=? and donor_lastname=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			rs = pstmt.executeQuery();
			dList = returnDonationListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dList;
	}
	
	public List<Donation> searchDonationByMemberId(int member_id) {
		List<Donation> dList = new ArrayList<Donation>();
		Donation d = null;
		ResultSet rs = null;
		String query = "select * from donation where member_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();
			dList = returnDonationListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dList;
	}
	public List<Donation> searchDonationByDate(long fromDate, long toDate) {
		java.sql.Date fDate = new java.sql.Date(fromDate);
		java.sql.Date tDate = new java.sql.Date(toDate);

		ResultSet rs = null;
		List<Donation> dList = new ArrayList<Donation>();
		Donation d = null;

		String query = "select * from donation where donation_date>=? and donation_date<=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setDate(1, fDate);
			pstmt.setDate(2, tDate);
			rs = pstmt.executeQuery();
			dList = returnDonationListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dList;
	}

	public List<Donation> searchDonationByTithi() {

		return new ArrayList<Donation>();
	}

	private List<Donation> returnDonationListFromResultSet(ResultSet rs)
			throws SQLException {
		List<Donation> dList = new ArrayList<Donation>();
		Donation d = null;
		while (rs.next()) {
			d = new Donation();
			d.donor_id = rs.getInt("member_id");
			d.donor_firstname = rs.getString("donor_firstname");
			d.donor_lastname = rs.getString("donor_lastname");
			d.donation_amount = rs.getDouble("donation_amount");
			d.donated_for = rs.getString("donated_for");
			d.donation_date = rs.getDate("donation_date");
			dList.add(d);
		}
		return dList;
	}
}
