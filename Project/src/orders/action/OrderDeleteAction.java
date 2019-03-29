package orders.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import orders.svc.OrderDeleteSvc;
import vo.ActionForward;

public class OrderDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[2]OrderDeleteAction");
		ActionForward forward = new ActionForward();
		int or_num = 0;
		if(request.getParameter("or_num") != null) {
			or_num = Integer.parseInt(request.getParameter("or_num"));
		}
		OrderDeleteSvc orderdeletesvc = new OrderDeleteSvc();
		orderdeletesvc.OrderDelete(or_num);
		forward.setPath("/Space/orderList.od");
		forward.setRedirect(true);
		
		return forward;
	}

}
