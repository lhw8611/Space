package board.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.ProductBean;
import vo.ReviewBean;


public class ProductViewSvc {
	public ProductBean getProView(int pro_code) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		//상품 조회수 
		int updateCount = boardDAO.proUpdateReadCount(pro_code);
		if(updateCount>0) {
			commit(con);
			
		}else  {
			rollback(con);
		}
		//상세보기
		ProductBean productBean = boardDAO.productInfo(pro_code);
		close(con);
		return productBean;
	}
	public ArrayList<ReviewBean> reviewList(int pro_code){
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();
		
		reviewList = boardDAO.reviewList(pro_code);
		
		close(con);
		return reviewList;
		
	}
}
