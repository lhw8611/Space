package member.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;
public class JoinFormSvc {
	public int JoinCheck(Member member) {
		System.out.println("[3]JoinFormSvc.JoinCheck");
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		System.out.println("[3]member.getid 값 : " + member.getMem_id());
		System.out.println("[3]member.getpass 값 : " + member.getMem_pass());
		int updateCount;
		updateCount = memberDAO.JoinCheckDAO(member);
		
		if(updateCount > 0) {
			commit(con);
		}
		else {
			rollback(con);
		}
		close(con);
		
		return updateCount;
	}
}
