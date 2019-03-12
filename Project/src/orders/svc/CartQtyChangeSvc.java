package orders.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.OrderDAO;
import vo.CartProViewBean;

public class CartQtyChangeSvc {
	// 비로그인 상태
	public void qtyChangeSvc(HttpServletRequest request, int index, int qty) {
		HttpSession session = request.getSession();
		ArrayList<CartProViewBean>cartList = (ArrayList<CartProViewBean>) session.getAttribute("cartList");
		cartList.get(index).setCart_qty(qty);
	}

	// 로그인 상태
	public int qtyChangeSvcLogin(int cart_num, int cart_qty) {
		int qtyChangeSvc = 0;
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);

		qtyChangeSvc = orderDAO.qtyChange(cart_num, cart_qty);

		if (qtyChangeSvc > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return qtyChangeSvc;
	}
}
