package board.svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.commit;
import static db.jdbcUtil.getConnection;
import static db.jdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import vo.ProductBean;

public class ProductWriteSvc {
	public boolean productWriteSvc(ProductBean noticeBean) throws Exception {
		System.out.println("ProductWriteSvc");
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int insertProduct = boardDAO.productWrite(noticeBean);
		int insertCount = boardDAO.QtyModifyCount();
		
		if (insertProduct > 0 && insertCount>0) {
			commit(con);
			isWriteSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isWriteSuccess;
	}
}
