package orders.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class orderPayAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("orderPay 액션 진입");
		
		HttpSession session = request.getSession();
		ActionForward forward = null;
		
		session.getAttribute("id");
		String pro_code = (String) request.getAttribute("pro_code");
		
		
		return forward;
	}

}
