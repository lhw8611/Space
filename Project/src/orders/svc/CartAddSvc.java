package orders.svc;
import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import vo.CartBean;
import vo.ProductBean;

public class CartAddSvc {
	//상품 정보 받아옴
	public ProductBean getCartList(int pro_code) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		ProductBean probean = boardDAO.productInfo(pro_code);
		
		close(con);
		return probean;
		
	}
	public void addCart(HttpServletRequest request, ProductBean probean) {
		HttpSession session = request.getSession();
		ArrayList<CartBean> cartList = (ArrayList<CartBean>)session.getAttribute("cartList");
		
		if(cartList == null) {
			cartList = new ArrayList<CartBean>();
			session.setAttribute("cartList", cartList);
			
		}
		
		boolean isNewCart = true;
		
		//지금 장바구니에 담는 항목이 새로 추가되는 항목인지를 저장할 변수
		for(int i = 0; i<cartList.size(); i++) {
			if(probean.getPro_name().equals(cartList.get(i).getKind())) {
				isNewCart = false;
				cartList.get(i).setQty(cartList.get(i).getQty()+1);
				break;
			}
		}
		if(isNewCart) {
			CartBean cartbean= new CartBean();
			cartbean.setImage(cartDog.getImage());
			cartbean.setKind(cartDog.getKind());
			cartbean.setPrice(cartDog.getPrice());
			cartbean.setQty(1);
			cartList.add(cartbean);
		}
	}
}
