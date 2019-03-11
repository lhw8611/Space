package orders.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import orders.svc.OrderListSvc;
import vo.ActionForward;
import vo.OrderBean;

public class OrderListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[OrderListAction]");
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		if(session.getAttribute("id") != null) {
		forward = new ActionForward();
		OrderListSvc odlistsvc = new OrderListSvc();
		ArrayList<OrderBean> odbeanlist = odlistsvc.odlist(id);
		session.setAttribute("odbeanlist", odbeanlist);
		forward.setRedirect(false);
		forward.setPath("orders/orderList.jsp");
		}else {
					
		}
		return forward;
	}

}
