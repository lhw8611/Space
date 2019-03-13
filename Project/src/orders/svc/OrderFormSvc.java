package orders.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;
import dao.OrderDAO;
import vo.MemberBean;
import vo.OrderBean;
import vo.PointBean;
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
	//상품정보
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
	
	public int MaxPoint(String mem_id) { //사용할 수 있는 포인트 계산
		System.out.println("[3]OrderFormSvc.MaxPoint");
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int MaxPoint = boardDAO.MaxPointDAO(mem_id);
		
		close(con);
		
		return MaxPoint;
	}
}
