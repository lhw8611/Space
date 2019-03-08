package orders.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;
import vo.CartProViewBean;

public class CartListFormSvc {
	public ArrayList<CartProViewBean> cartListForm(String id) {
		ArrayList<CartProViewBean> cartList = new ArrayList<CartProViewBean>();
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		cartList = orderDAO.cartListForm(id);

		close(con);
		return cartList;
	}
}
