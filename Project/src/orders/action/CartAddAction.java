package orders.action;

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
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		CartAddSvc cartAddSvc = new CartAddSvc();
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));

		ProductBean probean = cartAddSvc.getCartList(pro_code);
		// 비로그인 상태
		if (id == null) {
			cartAddSvc.addCart(request, probean);

		// 로그인 상태
		} else {
			cartAddSvc.addCart2(probean, id);
		}

		forward = new ActionForward();
		forward.setPath("/Project/orders/cartList.jsp");
		forward.setRedirect(true);

		return forward;
	}
}
