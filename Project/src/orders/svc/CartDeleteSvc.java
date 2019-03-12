package orders.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.OrderDAO;
import vo.CartProViewBean;

public class CartDeleteSvc {
	
	//비로그인 상태
	public void caratDeleteSvc(HttpServletRequest request, int index) {
		HttpSession session = request.getSession();
		ArrayList<CartProViewBean>cartList = (ArrayList<CartProViewBean>)session.getAttribute("cartList");
		cartList.remove(index);
	}
	//로그인 상태
	public int cartDeleteSvcLogin (int cart_num) {
		
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
