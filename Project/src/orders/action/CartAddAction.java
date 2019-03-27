package orders.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import orders.svc.CartAddSvc;
import vo.ActionForward;
import vo.ProductBean;

public class CartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		int qty=1;
		if(request.getParameter("qty")!=null) {
			qty = Integer.parseInt(request.getParameter("qty"));
		}
		
		CartAddSvc cartAddSvc = new CartAddSvc();
		ProductBean probean = cartAddSvc.getCartList(pro_code); //상품코드로 정보 probean에 넣기
//		ArrayList<ProductBean> probeanlist = (ArrayList<Product 
		
		// 비로그인 상태
		if (id == null) {
			cartAddSvc.addCart(request, probean, qty);
			forward.setPath("/Project/orders/cartList.jsp");
			forward.setRedirect(true);
		// 로그인 상태
		} else {
			cartAddSvc.addCart2(probean, id);
			forward.setPath("/Project/cartListForm.od");
			forward.setRedirect(true);
		}

		return forward;
	}
}
