package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberDeleteSvc;
import vo.ActionForward;

public class MemberDeleteAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[2]MemberDeleteAction");
		ActionForward forward = null;
		MemberDeleteSvc memberdeletesvc = new MemberDeleteSvc();
		int delcount;
		HttpSession session = request.getSession();
		 if(session.getAttribute("id") != null) {
			delcount = memberdeletesvc.UserDelete(request.getParameter("id"));
				if(delcount > 0) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('탈퇴되었습니다.');");
					if(session.getAttribute("id").equals("admin")) {
					forward = new ActionForward();
					forward.setPath("/Space/admingetlist.mem");
					forward.setRedirect(true);
					}else {
					out.println("location.href='logout.mem';");
					}
					out.println("</script>");
				}
		}
		return forward;
	}

}
