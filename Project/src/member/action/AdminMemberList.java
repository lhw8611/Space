package member.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.AdminMemberListSvc;
import vo.ActionForward;
import vo.MemberBean;

public class AdminMemberList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		ActionForward forward = null;
		System.out.println("[2]AdminMemberList");
		
		if((session.getAttribute("id")==null) ||
				(!((String)session.getAttribute("id")).equals("admin"))){
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요');");
			out.println("location.href='main.jsp';");
			out.println("</script>");
		}else {
			forward = new ActionForward();
			forward.setPath("admin/member_list.jsp");
			System.out.println("[2]action에서 setPath 경로 확인 " + forward.getPath());
		}
		
		AdminMemberListSvc memberlist = new AdminMemberListSvc();
		ArrayList<MemberBean> list = memberlist.getMemberList();
		request.setAttribute("list", list);
		
		return forward;
	}

}
