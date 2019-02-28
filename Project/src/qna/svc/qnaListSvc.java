package qna.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;


import dao.qnaDAO;
import vo.qna;

public class qnaListSvc {
	public int getListCount() throws Exception{
		int listCount = 0;
		Connection con = getConnection();
		qnaDAO qnadao = qnaDAO.getInstance();
		qnadao.setConnection(con);
		listCount = qnadao.selectListCount();
		
		close(con);
		return listCount;
	}
	
	public ArrayList<qna> getArticleList(int page, int limit) throws Exception{
		System.out.println("[3]boardlist 서비스진입");
		ArrayList<qna> articleList = null;
		Connection con = getConnection();
		qnaDAO qnadao= qnaDAO.getInstance();
		qnadao.setConnection(con);
		articleList = qnadao.selectArticleList(page, limit);
		
		close(con);
		return articleList;
	}
}
