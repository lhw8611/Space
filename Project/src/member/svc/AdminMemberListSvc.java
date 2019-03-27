package member.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import vo.MemberBean;

public class AdminMemberListSvc {
	public ArrayList<MemberBean> getMemberList(int page, int limit, String keyWord) { //회원정보
		System.out.println("[3]AdminMemberListSvc");
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		ArrayList<MemberBean> list= memberDAO.getSelectList(page, limit, keyWord);
		
		close(con);
		return list;
	}

	public int listCount(String keyWord) {
		System.out.println("[3]AdminMemberListSvc.listCount");
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int listCount = 0;
		listCount = memberDAO.selectListCount(keyWord);
		
		close(con);
		return listCount;
	}
}
