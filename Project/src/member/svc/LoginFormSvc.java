package member.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;

public class LoginFormSvc {
	public boolean LoginCheck(Member member) {
		System.out.println("[3]LoginFormSvc");
		boolean RightUser = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		RightUser = memberDAO.LoginCheckDAO(member);
		close(con);
		
		return RightUser;
	}
}
