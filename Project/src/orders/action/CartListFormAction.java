package orders.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import orders.svc.CartListFormSvc;
import vo.ActionForward;
import vo.CartProViewBean;

public class CartListFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<CartProViewBean> cartList = new ArrayList<CartProViewBean>();
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		CartListFormSvc cartListFormSvc = new CartListFormSvc();
		cartList = cartListFormSvc.cartListForm(id);

		request.setAttribute("cartList", cartList);
		for(int i=0; i<cartList.size(); i++) {
			System.out.println(cartList.get(i).getPro_name());
		}
		// 비로그인 상태
		if (id != null) {
			
			
		// 로그인 상태
		} else {
			
		}
		System.out.println("cartListForm Action 진입");
		forward.setPath("orders/cartList.jsp");
		return forward;
	}

}
