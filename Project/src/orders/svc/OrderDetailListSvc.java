package orders.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;
import vo.OrOdProViewBean;
public class OrderDetailListSvc {
	public ArrayList<OrOdProViewBean> OrderDetailList(int od_num) {
		System.out.println("[3]OrderDetailList");
		Connection con = getConnection();
		OrderDAO orderdao = OrderDAO.getInstance();
		orderdao.setConnection(con);
		
		ArrayList<OrOdProViewBean> orodproviewbean = orderdao.OrderDetailList(od_num);
		if(orodproviewbean != null) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return orodproviewbean; 
	}
}
