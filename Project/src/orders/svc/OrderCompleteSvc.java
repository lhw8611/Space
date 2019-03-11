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
import vo.OrderDetailBean;
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

	public MemberBean orderaction(String mem_id, OrderBean odbean, OrderDetailBean oddbean, QtyBean qtybean) { //주문내역에 정보저장
		System.out.println("[3]OrderCompleteSvc.orderaction");
		MemberBean membean = new MemberBean();
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		int odUpdate = orderDAO.order_insert(mem_id, odbean);//order테이블 값넣기
		oddbean = orderDAO.ordercode(odbean, oddbean);//order테이블 rs반복문 돌려서 마지막행의 주문코드값 가져오기
//		System.out.println("주문코드 들어갔나 확인 : " + oddbean.getOd_num());
//		System.out.println("주문갯수 들어갔나 확인 : " + oddbean.getOd_qty());
//		System.out.println("상품코드 들어갔나 확인 : " + oddbean.getPro_code());
		int oddUpdate = orderDAO.detail_insert(oddbean);//order_detail에 주문한 상품값 넣기 
		int qtyUpdate = orderDAO.qty_insert(oddbean, qtybean); //구매한만큼 재고수테이블에서 상품재고 빼기
		if(odUpdate > 0 && oddUpdate > 0 && qtyUpdate >  0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return membean;
		
	}
	
}
