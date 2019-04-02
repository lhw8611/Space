package orders.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;
import vo.MemberBean;
import vo.OrderListBean;
import vo.ProductBean;

public class OrderFormSvc {
	//구매자 정보
	public MemberBean purchaserInfo(String id) {
		System.out.println("구매자 정보 서비스 진입");
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		MemberBean membean = orderDAO.purchaserInfo(id);
		
		close(con);
		return membean;
	}
	//상품정보
	public ProductBean productsInfo(int pro_code) {
		System.out.println("구매 상품 정보 서비스 진입");
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		ProductBean probean = orderDAO.productInfo(pro_code);
		if(probean!=null) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return probean;
	}
	//뷰에서 넘어온 주문폼
	public ArrayList<OrderListBean> orderTypeOne(int pro_code, int qty) {
		System.out.println("뷰에서 넘어온 주문폼");
		Connection con = getConnection();
		OrderDAO orderDAO= OrderDAO.getInstance();
		orderDAO.setConnection(con);
		ArrayList<OrderListBean> orderlistbean= orderDAO.OrderList(pro_code, qty);
		if(orderlistbean !=null) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return orderlistbean;
	}
	
	public int MaxPoint(String mem_id) { //사용할 수 있는 포인트 계산
		System.out.println("[3]OrderFormSvc.MaxPoint");
		Connection con = getConnection();
		OrderDAO orderDAO= OrderDAO.getInstance();
		orderDAO.setConnection(con);
		int MaxPoint = orderDAO.MaxPointDAO(mem_id);
		
		close(con);
		
		return MaxPoint;
	}
}
