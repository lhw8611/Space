package qna.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.qnaDAO;
import vo.QnaBean;

public class qnaWriteSvc {
	public void UserCheck(QnaBean voqna){
		System.out.println("[3]UserCheck");
		Connection con = getConnection();
		qnaDAO qnadao = qnaDAO.getInstance();
		qnadao.setConnection(con);
		int insertCount = qnadao.UserCheckDAO(voqna);
		
		
		if(insertCount > 0) {
			commit(con);
		}
		else {
			rollback(con);
		}
		
		close(con);
	}
}
