package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.svc.MemberInfoSvc;
import vo.ActionForward;

public class IdCheckProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		String id = request.getParameter("id");
		boolean IdCheckResult = false;
		MemberInfoSvc memberinfosvc = new MemberInfoSvc();
		IdCheckResult = memberinfosvc.IdCheck(id);
		
		request.setAttribute("id", id);
		request.setAttribute("idcheckrs", String.valueOf(IdCheckResult));
		System.out.println("[2]"+request.getAttribute("id"));
		System.out.println("[2]"+request.getAttribute("idcheckrs"));
		forward.setPath("member/idCheck.jsp");
		
		return forward;
	}

}
