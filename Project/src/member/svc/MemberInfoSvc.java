package member.svc;

import vo.MemberBean;
import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberInfoSvc {
	public MemberBean MemberInfo(String id){
		System.out.println("[3]MemberInfoSvc");
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		MemberBean member = memberDAO.MemberInfoDAO(id);
		
		
		close(con);
		return member;
	}
}
