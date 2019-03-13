package orders.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import dao.OrderDAO;
import vo.CartProViewBean;
import vo.ProductBean;

public class CartAddSvc {
	// 상품 정보 받아오는 메소드
	public ProductBean getCartList(int pro_code) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		ProductBean probean = boardDAO.productInfo(pro_code);

		close(con);
		return probean;

	}

	// 비로그인 상태 cartAdd
	public void addCart(HttpServletRequest request, ProductBean probean, int qty) {
		HttpSession session = request.getSession();
		ArrayList<CartProViewBean> cartList = (ArrayList<CartProViewBean>) session.getAttribute("cartList");
		
		// 세션에 장바구니가 없을 경우
		if (cartList == null) {
			cartList = new ArrayList<CartProViewBean>();
			session.setAttribute("cartList", cartList);
		}

		boolean isNewCart = true;

		// 이미 장바구니에 상품이 있는 상태
		for (int i = 0; i < cartList.size(); i++) {
			if (probean.getPro_code() == cartList.get(i).getPro_code()) {
				isNewCart = false;
				cartList.get(i).setCart_qty(cartList.get(i).getCart_qty() + qty);
				break;
			}
		}
		// 장바구니에 상품이 없는 상태
		if (isNewCart) {
			CartProViewBean cartbean = new CartProViewBean();
			cartbean.setPro_code(probean.getPro_code());
			cartbean.setPro_name(probean.getPro_name());
			cartbean.setPro_price(probean.getPro_price());
			cartbean.setPro_image(probean.getPro_image());
			cartbean.setCart_qty(qty);
			cartList.add(cartbean);

		}
	}

	// 로그인 상태 addCart
	public int addCart2(ProductBean probean, String id) {
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);

		boolean isNewCart = true;
		isNewCart = orderDAO.checkCart(probean, id);

		System.out.println("서비스에서 isNewCart = " + isNewCart);
		System.out.println("서비스에서 probean:" + probean.getPro_name());
		System.out.println("서비스에서 ID : " + id);

		// 장바구니에 해당 상품이 없는 경우
		int checkCartInsert = orderDAO.addCart(isNewCart, probean, id);

		// 커밋 성공 여부
		if (checkCartInsert >= 0) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return checkCartInsert;



	}
}
