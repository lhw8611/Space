package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberInfoSvc;
import vo.ActionForward;
import vo.MemberBean;

public class MemberInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[2]MemberInfoAction");
		ActionForward forward = new ActionForward();
		String id = request.getParameter("id");
		MemberInfoSvc memberinfosvc = new MemberInfoSvc();
		MemberBean member = memberinfosvc.MemberInfo(id);
		request.setAttribute("member", member);
		
		System.out.println("[2] : " + request.getAttribute(member.getMem_id()));
		forward.setPath("/member/member_info.jsp");
		
		return forward;
	}

}
