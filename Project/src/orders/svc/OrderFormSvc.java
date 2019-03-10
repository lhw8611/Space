package orders.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;
import dao.OrderDAO;
import vo.MemberBean;
import vo.OrderBean;
import vo.ProductBean;

public class OrderFormSvc {
	//구매자 정보
	public MemberBean purchaserInfo(String id) {
		System.out.println("구매자 정보 서비스 진입");
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		MemberBean membean = orderDAO.purchaserInfo(id);
		
		if(membean!=null) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return membean;
		
	}
	public ProductBean productsInfo(int pro_code) {
		System.out.println("구매 상품 정보 서비스 진입");
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ProductBean probean = boardDAO.productInfo(pro_code);
		
		if(probean!=null) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return probean;
		
	}
}
