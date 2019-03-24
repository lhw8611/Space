package qna.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.qnaDAO;
public class qnaDeleteSvc {
	public int qnaDelete(int qna_num){
		System.out.println("[3]qnaDeleteSvc");
		Connection con = getConnection();
		qnaDAO qnadao = qnaDAO.getInstance();
		qnadao.setConnection(con);
		int insertCount = qnadao.qnaDeleteDAO(qna_num);
		
		if(insertCount > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return insertCount;
	}
}
