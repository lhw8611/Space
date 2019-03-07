package orders.svc;
import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import vo.CartProViewBean;
import vo.ProductBean;

public class CartAddSvc {
	//상품 정보 받아오는 메소드
	public ProductBean getCartList(int pro_code) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		ProductBean probean = boardDAO.productInfo(pro_code);
		
		close(con);
		return probean;
		
	}
	//카트에 추가하는 메소드
	public void addCart(HttpServletRequest request, ProductBean probean, String id) {
		HttpSession session = request.getSession();
		ArrayList<CartProViewBean> cartList = (ArrayList<CartProViewBean>)session.getAttribute("cartList");
		System.out.println(id);
		//세션에 장바구니가 없을 경우
		if(cartList == null) {
			cartList = new ArrayList<CartProViewBean>();
			session.setAttribute("cartList", cartList);
		}
		
		boolean isNewCart = true;
		
		//이미 장바구니에 상품이 있는 상태
		for(int i = 0; i<cartList.size(); i++) {
			if(probean.getPro_code()==cartList.get(i).getPro_code()) { 
				isNewCart = false;
				cartList.get(i).setCart_qty(cartList.get(i).getCart_qty()+1);
				break;
			}
		}
		//장바구니에 상품이 없는 상태
		if(isNewCart) {
			CartProViewBean cartbean= new CartProViewBean();
			cartbean.setPro_code(probean.getPro_code());
			cartbean.setPro_name(probean.getPro_name());
			cartbean.setPro_price(probean.getPro_price());
			cartbean.setPro_image(probean.getPro_image());
			cartbean.setCart_qty(1);
			cartList.add(cartbean);
			
		}
	}
}
