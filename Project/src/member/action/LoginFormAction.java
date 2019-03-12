package member.action;

import static db.jdbcUtil.*;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.LoginFormSvc;
import vo.ActionForward;
import vo.MemberBean;

import java.sql.Connection;


public class LoginFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("[2]LoginAction");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		ActionForward forward = null;
		HttpSession session = request.getSession();
		MemberBean member = new MemberBean();
		member.setMem_id(request.getParameter("id"));
		member.setMem_pass(request.getParameter("pass"));
		LoginFormSvc loginformsvc = new LoginFormSvc();
		boolean RightUser = loginformsvc.LoginCheck(member);
		
		if(!RightUser) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디나 비밀번호가 일치하지 않습니다.')");
			out.println("location.href='./member/loginForm.jsp'");
			out.println("</script>");
		}else{
			session.setAttribute("id", request.getParameter("id"));
			forward = new ActionForward();
			request.setAttribute("membean", member);
			forward.setRedirect(false);
			forward.setPath("/main.jsp");
			
		}
		return forward;
	}
}
