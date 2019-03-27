package orders.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;
import vo.OrOdProViewBean;

public class OrderListSvc {
	public ArrayList<OrOdProViewBean> OrderSimpleList(int page, int limit, String id, String keyWord) { //주문조회
		System.out.println("[3]OrderListSvc.OrderSimpleList");
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		ArrayList<OrOdProViewBean> orodproviewbean = orderDAO.OrderSimpleList(page, limit, id, keyWord);
		if(orodproviewbean!=null) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return orodproviewbean;
	}
	
	public int getListCount(String id,String keyWord) { //총 리스트 수를 받아옴
		System.out.println("[3]OrderListSvc.getListCount");
		Connection con = getConnection();
		OrderDAO orderdao = OrderDAO.getInstance();
		orderdao.setConnection(con);
		int listCount = 0;
		listCount = orderdao.getListCount(id, keyWord);
		
		close(con);
		return listCount;
	}

}
