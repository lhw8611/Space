package qna.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;


import dao.qnaDAO;
import vo.QnaBean;

public class qnaListSvc {
	public int getListCount() throws Exception{
		System.out.println("[3]qnaListSvc.getListCount");
		int listCount = 0;
		Connection con = getConnection();
		qnaDAO qnadao = qnaDAO.getInstance();
		qnadao.setConnection(con);
		listCount = qnadao.selectListCount();
		
		close(con);
		return listCount;
	}
	
	public ArrayList<QnaBean> getArticleList(int page, int limit) throws Exception{
		System.out.println("[3]qnaListSvc.getArticleList");
		ArrayList<QnaBean> articleList = null;
		Connection con = getConnection();
		qnaDAO qnadao= qnaDAO.getInstance();
		qnadao.setConnection(con);
		articleList = qnadao.selectArticleList(page, limit);
		
		close(con);
		return articleList;
	}
}
