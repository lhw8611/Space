package orders.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.OrderDAO;

public class CartQtyChangeSvc {
	public int qtyChangeSvc (int cart_num, int cart_qty) {
	int qtyChangeSvc = 0;
	Connection con = getConnection();
	OrderDAO orderDAO = OrderDAO.getInstance();
	orderDAO.setConnection(con);
	
	qtyChangeSvc = orderDAO.qtyChange(cart_num, cart_qty );
	
	if(qtyChangeSvc>0) {
		commit(con);
	}else {
		rollback(con);
	}
	close(con);
	return qtyChangeSvc;
	}
}
