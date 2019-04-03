package admin.svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.getConnection;

import java.sql.Connection;

import dao.BoardDAO;
import vo.ProductBean;

public class ProModifyFormSvc {
	public ProductBean getProView(int pro_code) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ProductBean productBean = boardDAO.productInfo(pro_code);
		close(con);
		
		return productBean;
	}
}
