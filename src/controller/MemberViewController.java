package controller;

import business.MemberService;
import dao.Member;
import exception.CannotAddMemberException;

public class MemberViewController extends BaseController{

	private MemberService service;
	private String sqlMessage = null;
	
	public MemberViewController(){
		this.service = new MemberService();
	}
	public String addNewMember(Member dao) {
		try {
			service.addNewMember(dao);
		} catch (CannotAddMemberException e) {
			e.printStackTrace();
			return sqlMessage = e.getMessage();
		}
		System.out.println(rb.getString("memberScreen.message.member.add.success"));
		return rb.getString("memberScreen.message.member.add.success");
	}
}
