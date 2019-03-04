package member.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
public class MemberDeleteSvc {

	public int UserDelete(String id) {
		System.out.println("[3]MemberDeleteSvc");
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		int deleteCount = memberDAO.UserDeleteDAO(id);
		
		if(deleteCount > 0) {
			commit(con);
		}
		else {
			rollback(con);
		}
		
		close(con);
		return deleteCount;
	}

}
