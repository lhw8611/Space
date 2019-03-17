package orders.svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.commit;
import static db.jdbcUtil.getConnection;
import static db.jdbcUtil.rollback;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;
import db.jdbcUtil.*;
import sun.security.jca.GetInstance;
import vo.MemberBean;
import vo.OrderBean;
import vo.OrderDetailBean;
import vo.OrderListBean;
import vo.QtyBean;
public class OrderCompleteSvc {
	public void order_qty(ArrayList<OrderListBean> orderlistbean) {//주문할때 재고수 구하는 메소드
		System.out.println("[3]OrderCompleteSvc.productqty");
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		int insertCount = orderDAO.order_qty(orderlistbean);
		
		if(insertCount>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
	}

	public ArrayList<OrderListBean> productInfo(ArrayList<Integer> pro_codes, ArrayList<Integer> pro_qty) {
		System.out.println("[3]OrderCompleteSvc.productInfo");
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		ArrayList<OrderListBean> orderlistbean;
		
		orderlistbean = orderDAO.productsInfoDAO(pro_codes, pro_qty);
		
		return orderlistbean;
	}
	
}
