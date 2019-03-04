package member.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class LoginFormSvc {
	public boolean LoginCheck(MemberBean member) {
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
