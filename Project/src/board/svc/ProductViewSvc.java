package board.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;
import vo.ProductBean;


public class ProductViewSvc {
	public ProductBean getProView(int pro_code) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		
		int updateCount = boardDAO.updateReadCount(pro_code);
		
		if(updateCount>0) {
			commit(con);
			
		}else  {
			rollback(con);
		}
		
		ProductBean productBean = boardDAO.productInfo(pro_code);
		close(con);
		return productBean;
	}
}
