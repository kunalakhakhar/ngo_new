package business;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.Member;
import exception.CannotAddMemberException;

public class MemberService extends BaseService {

	public void addNewMember(Member dao) throws CannotAddMemberException {
		String query = "insert into member(firstname,lastname,tel_no,email_id,gender,fax,mobile_no,member_since) values(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);  
			pstmt.setString(1, dao.firstname);
			pstmt.setString(2, dao.lastname);
			pstmt.setString(3, dao.telephone_no);
			pstmt.setString(4, dao.email);
			pstmt.setString(5, dao.gender);
			pstmt.setString(6, dao.fax);
			pstmt.setString(7, dao.mobile_no);
			pstmt.setDate(8, new java.sql.Date(new Date().getTime()));
			pstmt.executeUpdate();  
			ResultSet rs = null;
			rs = pstmt.executeQuery("SELECT LAST_INSERT_ID()");
			int mem_id = -1;
		    if (rs.next()) 
		        mem_id = rs.getInt(1);
			query = "insert into address(member_id,line1,line2,city,pin) values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,mem_id);
			pstmt.setString(2, dao.address.line1);
			pstmt.setString(3, dao.address.line2);
			pstmt.setString(4, dao.address.city);
			pstmt.setString(5, dao.address.pincode);
			pstmt.executeUpdate();
			rs.close();
			rs = null;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CannotAddMemberException();
		} finally {
			try {
				pstmt.close();
				//conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
