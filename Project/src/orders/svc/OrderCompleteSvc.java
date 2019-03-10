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
import vo.OrderBean;
import vo.QtyBean;
public class OrderCompleteSvc {
	public QtyBean productqty(int pro_code) {//재고수 구하는 메소드
		System.out.println("[3]OrderCompleteSvc.productqty");
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

	public MemberBean orderaction(String mem_id, OrderBean odbean) { //주문내역에 정보저장
		System.out.println("[3]OrderCompleteSvc.orderaction");
		MemberBean membean = new MemberBean(); 
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		int updateCount = orderDAO.orderinsert(mem_id, odbean);
		System.out.println("insert order 업데이트 카운트 확인 : " + updateCount);
		if(updateCount > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return membean;
		
	}
	
}
