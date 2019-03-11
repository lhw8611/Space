package orders.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import orders.svc.CartQtyChangeSvc;
import vo.ActionForward;

public class CartQtyChangeAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int qtyChangeCheck = 0;
		String id=request.getParameter("id");
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		System.out.println("id : " + id);
		System.out.println("pro_code : " + pro_code);
		System.out.println("qty : " + qty);
		
		CartQtyChangeSvc cartQtyChangeSvc = new CartQtyChangeSvc();
		qtyChangeCheck = cartQtyChangeSvc.qtyChangeSvc(id,pro_code,qty);
		
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

