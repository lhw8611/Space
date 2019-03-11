package orders.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.OrderDAO;

public class CartDeleteSvc {
	public int cartDeleteSvc (int cart_num) {
		
	Connection con = getConnection();
	OrderDAO orderDAO = OrderDAO.getInstance();
	orderDAO.setConnection(con);

	int cartDeleteCheck = 0;
	
	cartDeleteCheck = orderDAO.deleteCart(cart_num);
	
	if(cartDeleteCheck>0) {
		commit(con);
		System.out.println("카트 삭제 성공");
	}else {
		rollback(con);
	}
	return cartDeleteCheck;
	}
}
