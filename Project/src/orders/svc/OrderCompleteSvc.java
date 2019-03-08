package orders.svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.commit;
import static db.jdbcUtil.getConnection;
import static db.jdbcUtil.rollback;

import java.io.PrintWriter;
import java.sql.Connection;

import dao.OrderDAO;
import db.jdbcUtil.*;
import sun.security.jca.GetInstance;
import vo.MemberBean;
import vo.QtyBean;
public class OrderCompleteSvc {
	public QtyBean productqty(int pro_code) {//재고수 구하는 메소드
		QtyBean qtybean = new QtyBean(); 
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		qtybean = orderDAO.productqty(pro_code);
		
		if(qtybean!=null) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return qtybean;
	}
	
}
