package admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.svc.StateChangeSvc;
import vo.ActionForward;

public class StateChangeAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		String i= request.getParameter("index");
		System.out.println("i:"+i);
		int od_num = Integer.parseInt(request.getParameter("od_num"+i));
		int pro_code = Integer.parseInt(request.getParameter("pro_code"+i));
		String state = request.getParameter("state"+i);
		
		System.out.println(od_num);
		System.out.println(pro_code);
		System.out.println(state);
		
		if(state.equals("none")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('주문 상태를 선택해주세요')");
			out.println("history.back();");
			out.println("</script>");
		
		}else {
			StateChangeSvc refundSvc = new StateChangeSvc();
			refundSvc.stateChange(od_num, pro_code, state);
			forward.setPath("admin_orderList.ad");
			forward.setRedirect(true);
			
		}
		return forward;

	}

}
