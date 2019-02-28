package board.svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.commit;
import static db.jdbcUtil.getConnection;
import static db.jdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import vo.NoticeBean;

public class NoticeDetailSvc {
	public NoticeBean getArticle(int no_num) throws Exception {
		NoticeBean article = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		//누르면 조회수 올라가야하니깐
		int updateCount = boardDAO.updateReadCount(no_num);
		if(updateCount > 0 ) {
			commit(con);
		}else {
			rollback(con);
		}
		article = boardDAO.selectArticle(no_num);
		close(con);
		return article;
	}
}
