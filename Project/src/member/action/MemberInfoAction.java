package member.action;

import java.io.PrintWriter;

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
		ActionForward forward = null;
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		if(session.getAttribute("id") != null) {
		forward = new ActionForward();
		MemberInfoSvc memberinfosvc = new MemberInfoSvc();
		MemberBean member = memberinfosvc.MemberInfo(id);
		request.setAttribute("member", member);
		forward.setPath("/member/member_info.jsp");
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='/Space/main.jsp';");
			out.println("</script>");
		}
		
		return forward;
	}

}
