package orders.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import action.Action;
import orders.svc.CartListFormSvc;
import orders.svc.CartQtyChangeSvc;
import vo.ActionForward;
import vo.CartProViewBean;

public class CartQtyChangeAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int index = Integer.parseInt(request.getParameter("index"));
		int qtyChangeCheck = 0;
		int cart_num = Integer.parseInt(request.getParameter("cart_num"+index));
		int qtyRequest = Integer.parseInt(request.getParameter("qty"+index));
		
		CartQtyChangeSvc cartQtyChangeSvc = new CartQtyChangeSvc();
		qtyChangeCheck = cartQtyChangeSvc.qtyChangeSvc(cart_num,qtyRequest);
		
		if(qtyChangeCheck>0) {
			System.out.println("수량 변경 성공");
			forward.setPath("cartListForm.od");
			forward.setRedirect(true);
		}else {
			System.out.println("수량 변경 실패");
			
		}
		
		
		return forward;
	}

}

