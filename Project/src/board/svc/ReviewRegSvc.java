package board.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;
import vo.ReviewBean;

public class ReviewRegSvc {
	//해당 유저가 상품을 샀는지 확인
	public boolean reviewCheck(String mem_id, int pro_code) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		boolean reviewCheck = false;
		reviewCheck = boardDAO.reviewCheck(mem_id, pro_code);
		
		close(con);
		return reviewCheck;
	}
	
	public void ReviewReg (ReviewBean reviewBean) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int updateCheck = 0;
		
		updateCheck = boardDAO.reviewReg(reviewBean);
		
		if(updateCheck>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		
		
	}
}
