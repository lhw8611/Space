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
	public int order_qty(ArrayList<OrderListBean> orderlistbean) {//주문할때 재고수 구하는 메소드
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
		
		return insertCount;
	}

	public ArrayList<OrderListBean> productInfo(ArrayList<Integer> pro_codes, ArrayList<Integer> pro_qty) { //상품정보 구해서 arraylist에 add
		System.out.println("[3]OrderCompleteSvc.productInfo");
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		ArrayList<OrderListBean> orderlistbean;
		
		orderlistbean = orderDAO.productsInfoDAO(pro_codes, pro_qty);
		
		return orderlistbean;
	}

	public int order_add(MemberBean membean, OrderBean orderbean) { //orders insert
		System.out.println("[3]OrderCompleteSvc.order_add");
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		int insertCount = orderDAO.order_add(membean, orderbean);
		if(insertCount > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return insertCount;
	}
	
	public void order_detail_add(OrderBean orderbean, ArrayList<OrderListBean> orderlistbean){ //order_detail insert
		System.out.println("[3]OrderCompleteSvc.order_detail_add");
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
//		orderbean = orderDAO.order_detail_add(orderbean, orderlistbean);
		ArrayList<Integer> arryInsertCount = orderDAO.order_detail_add(orderbean, orderlistbean);
		boolean insertCheck = false;
		for(int i=0; i<arryInsertCount.size(); i++) {
			System.out.println("order_detail arryInsertCount : " + arryInsertCount.get(i) );
			if(arryInsertCount.get(i)<=0) {
				System.out.println("arryIsnertCount <= 0 ...");
			}else {
				insertCheck = true;
			}
		}
		if(insertCheck) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
	}
}
