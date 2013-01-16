package business;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.App_User;

public class LoginService extends BaseService {

	PreparedStatement pstmt = null;

	public App_User loginClicked(String username, String password) {
		App_User appUser = null;
		String query = null;
		ResultSet rs = null;

		query = "select * from app_user where username=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				appUser = new App_User();
				appUser.user_id = rs.getInt("user_id");
				appUser.username = rs.getString("username");
				appUser.password = rs.getString("user_password");
				appUser.user_firstname = rs.getString("user_firstname");
				appUser.user_lastname = rs.getString("user_lastname");
				appUser.user_since = rs.getDate("user_since");
				appUser.is_admin = rs.getInt("is_admin");
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(appUser.password.equals(password))
		return appUser;
		
		return null;
	}

}
