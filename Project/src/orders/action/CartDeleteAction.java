package orders.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import orders.svc.CartDeleteSvc;
import vo.ActionForward;

public class CartDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int cart_num = Integer.parseInt(request.getParameter("cart_num"));
		
		CartDeleteSvc cartDeleteSvc= new CartDeleteSvc();
		cartDeleteSvc.cartDeleteSvc(cart_num);
		
		
		forward.setPath("cartListForm.od");
		forward.setRedirect(true);
		return forward;
	}

}
