package orders.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;
import vo.OrderBean;
import vo.OrderListBean;

public class OrderListSvc {
	public ArrayList<OrderListBean> OrderSimpleList(String id) { //주문조회
		System.out.println("[3]OrderListSvc.OrderSimpleList");
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		ArrayList<OrderListBean> ordersimplelist = orderDAO.OrderSimpleList(id);
		if(ordersimplelist!=null) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return ordersimplelist;
	}
	
	//	public ArrayList<OrderBean> odlist(String id) { //오더테이블 값 저장ㅇ
//		System.out.println("[OrderListSvc.order]");
//		Connection con = getConnection();
//		OrderDAO orderDAO = OrderDAO.getInstance();
//		orderDAO.setConnection(con);
//		
//		ArrayList<OrderBean> odbeanlist = orderDAO.odlistDAO(id); //사용자 아이디에 대한 주문내역 다 넣음
//	
//		
//		close(con);
//		return odbeanlist;
//	}
//	
//	public ArrayList<OrderDetailBean> oddlist(ArrayList<OrderBean> odbeanlist, String id){
//		System.out.println("[OrderListSvc.order_detail]");
//		Connection con = getConnection();
//		OrderDAO orderDAO = OrderDAO.getInstance();
//		orderDAO.setConnection(con);
//	
//		ArrayList<OrderDetailBean> oddbeanlist = orderDAO.oddlistDAO(odbeanlist, id);
//		
//		return oddbeanlist;
//	}
}
