package controller;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class MemberInfoModifySvc {
	public void modifysvc(MemberBean member){
		System.out.println("[3]MemberInfoModifySvc");
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		int count = memberDAO.modifyDAO(member);
		
		if(count >0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
	}
}
