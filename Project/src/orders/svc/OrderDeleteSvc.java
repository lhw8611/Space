package orders.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.OrderDAO;
public class OrderDeleteSvc {
	public void OrderDelete(int or_num) {
		Connection con = getConnection();
		OrderDAO orderdao = OrderDAO.getInstance();
		orderdao.setConnection(con);
		int[] insertCount = orderdao.OrderDelete(or_num);
		
		if(insertCount[0] > 0 && insertCount[1] >0) {
			commit(con);
		}else {
			rollback(con);
		}
			
		close(con);
	}
}
