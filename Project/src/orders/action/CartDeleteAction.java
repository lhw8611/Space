package orders.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import orders.svc.CartDeleteSvc;
import vo.ActionForward;

public class CartDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("딜리트 액션");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		CartDeleteSvc cartDeleteSvc = new CartDeleteSvc();
		// 로그인 상태
		if (session.getAttribute("id") != null) {
			int cart_num = Integer.parseInt(request.getParameter("cart_num"));
			cartDeleteSvc.cartDeleteSvcLogin(cart_num);

		// 비로그인 상태
		}else {
			int index = Integer.parseInt(request.getParameter("index"));
			cartDeleteSvc.caratDeleteSvc(request, index);
			
		}
		forward.setPath("cartListForm.od");
		forward.setRedirect(true);
		return forward;
	}

}
