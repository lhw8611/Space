package orders.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;
import vo.OrOdProViewBean;
import vo.OrderBean;
import vo.OrderListBean;

public class OrderListSvc {
	public ArrayList<OrOdProViewBean> OrderSimpleList(String id) { //주문조회
		System.out.println("[3]OrderListSvc.OrderSimpleList");
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		ArrayList<OrOdProViewBean> orodproviewbean = orderDAO.OrderSimpleList(id);
		if(orodproviewbean!=null) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return orodproviewbean;
	}
	

}
