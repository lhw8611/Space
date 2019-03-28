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

		// 상품 조회수
		int updateCount = boardDAO.proUpdateReadCount(pro_code);
		if (updateCount > 0) {
			commit(con);

		} else {
			rollback(con);
		}
		// 상세보기
		ProductBean productBean = boardDAO.productInfo(pro_code);
		close(con);
		return productBean;
	}
	//리뷰 개수 조회
	public int getListCount(int pro_code) throws Exception {
		int listCount = 0;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		listCount = boardDAO.selectListCountReview(pro_code);
		close(con);
		return listCount;
	}
	
	//리뷰 출력
	public ArrayList<ReviewBean> reviewList(int page, int limit, int pro_code) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();

		reviewList = boardDAO.reviewList(page,limit,pro_code);

		close(con);
		return reviewList;

	}
	
	
}
