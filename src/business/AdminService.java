package business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import dao.App_User;
import exception.CannotAddNewAppUserException;

public class AdminService extends BaseService {

	PreparedStatement pstmt = null;

	public void addNewAppUser(App_User user) throws CannotAddNewAppUserException {
		String query = null;
		ResultSet rs = null;

		query = "insert into app_user(username,user_password,user_firstname,user_lastname,user_since,is_admin) values(?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.username);
			pstmt.setString(2, user.password);
			pstmt.setString(3, user.user_firstname);
			pstmt.setString(4, user.user_lastname);
			pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
			pstmt.setInt(6, user.is_admin);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new CannotAddNewAppUserException();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
