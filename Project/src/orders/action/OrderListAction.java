package orders.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import orders.svc.OrderListSvc;
import vo.ActionForward;
import vo.OrOdProViewBean;

public class OrderListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[OrderSimpleListAction]");
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		if(session.getAttribute("id") != null) {
		forward = new ActionForward();
		OrderListSvc orderlistsvc = new OrderListSvc();
		ArrayList<OrOdProViewBean> orodproviewbean = orderlistsvc.OrderSimpleList(id); //주문조회
		
		request.setAttribute("orodproviewbean", orodproviewbean);
		
		forward.setRedirect(false);
		forward.setPath("orders/orderSimpleList.jsp");
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='/Project/main.jsp';");
			out.println("</script>");
		}
		return forward;
	}

}
