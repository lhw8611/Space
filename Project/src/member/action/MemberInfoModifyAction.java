package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import controller.MemberInfoModifySvc;
import vo.ActionForward;
import vo.MemberBean;

public class MemberInfoModifyAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[2]MemberInfoModifyAction");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		ActionForward forward = null;
		String id = request.getParameter("id");
		System.out.println("[2]파라미터 id 값 : " + id);
		System.out.println("[2]파라미터 add 값 : " + request.getParameter("add"));
		MemberInfoModifySvc membermodify = null;
		// 서비스 실행

		if (session.getAttribute("id") != null) {
			membermodify = new MemberInfoModifySvc();
			MemberBean member = new MemberBean();
			member.setMem_id(id);
			member.setMem_pass(request.getParameter("pass"));
			member.setMem_name(request.getParameter("name"));
			member.setMem_add(request.getParameter("mem_add"));
			member.setMem_email(request.getParameter("email"));
			member.setMem_tel(request.getParameter("tel"));
			member.setMem_zip(request.getParameter("mem_zip"));
			member.setMem_add2(request.getParameter("mem_add2"));
			membermodify.modifysvc(member);
			
			forward = new ActionForward();
			forward.setPath("main.jsp");
			System.out.println("[2]action에서 setPath 경로 확인 " + forward.getPath());
		} else {
			out.println("<script>");
			out.println("alert('로그인 하세요!!')");
			out.println("location.href='main.jsp'");
			out.println("</script>");
		}

		return forward;
	}

}
