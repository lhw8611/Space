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
		System.out.println("cartListForm Action 진입");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		//로그인 상태
		if (id != null) {
			ArrayList<CartProViewBean> cartList = new ArrayList<CartProViewBean>();
			CartListFormSvc cartListFormSvc = new CartListFormSvc();
			cartList = cartListFormSvc.cartListForm(id);
			session.setAttribute("cartListdb", cartList);
		}

		forward.setPath("orders/cartList.jsp");
		return forward;
	}

}
