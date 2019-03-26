package board.svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.ProductBean;

public class ProductListSvc {
	public int getListCount(String sW) throws Exception {
		int listCount = 0;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		listCount = boardDAO.selectListCount2(sW);
		close(con);
		return listCount;
	}
	
	
	public ArrayList<ProductBean> getArticleList(int page, int limit, String sort, String sW) throws Exception {
		ArrayList<ProductBean> articleList = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		articleList = boardDAO.selectProductList(page, limit, sort, sW);
		
		close(con);
		return articleList;
	}
}
