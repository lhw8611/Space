package member.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import vo.MemberBean;

public class AdminMemberListSvc {
	public ArrayList<MemberBean> getMemberList() {
		System.out.println("[3]AdminMemberListSvc");
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		ArrayList<MemberBean> list= memberDAO.getSelectList();
		
		close(con);
		return list;
	}

}
