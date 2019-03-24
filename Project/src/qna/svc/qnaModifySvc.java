package qna.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.qnaDAO;
public class qnaModifySvc {
	public int qnaModify(int qna_num, String qna_question, String qna_answer) {
		Connection con = getConnection();
		qnaDAO qnadao = qnaDAO.getInstance();
		qnadao.setConnection(con);
		int insertCount = qnadao.qnaModify(qna_num, qna_question, qna_answer);
		if(insertCount > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return insertCount;
	}
}
