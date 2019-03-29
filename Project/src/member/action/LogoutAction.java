package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class LogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("로그아웃 액션");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		session.invalidate();
		
		forward.setRedirect(true);
		forward.setPath("/Space/main.jsp");
		
		return forward;
	}

}
