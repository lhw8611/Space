package orders.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;
import vo.OrderBean;
import vo.OrderDetailBean;

public class OrderListSvc {
	public ArrayList<OrderBean> odlist(String id) {
		System.out.println("[OrderListSvc]");
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		ArrayList<OrderBean> odbeanlist = orderDAO.odlistDAO(id); //사용자 아이디에 대한 주문내역 다 넣음
		ArrayList<OrderDetailBean> oddbeanlist = orderDAO.oddlistDAO(odbeanlist);
		close(con);
		return odbeanlist;
	}
}
