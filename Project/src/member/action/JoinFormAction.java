package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.svc.JoinFormSvc;
import vo.ActionForward;
import vo.MemberBean;

public class JoinFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("[2]JoinFormAction");
		MemberBean member = new MemberBean();
		member.setMem_id(request.getParameter("id"));
		member.setMem_pass(request.getParameter("pass"));
		member.setMem_name(request.getParameter("name"));
		member.setMem_add(request.getParameter("mem_add"));
		member.setMem_email(request.getParameter("email"));
		member.setMem_grade(request.getParameter("grade"));
		member.setMem_tel(request.getParameter("tel"));
		member.setMem_zip(request.getParameter("mem_zip"));
		member.setMem_add2(request.getParameter("mem_add2"));
		ActionForward forward = null;
		JoinFormSvc joinformsvc = new JoinFormSvc();
		int updateCount = joinformsvc.JoinCheck(member);
		
		if(updateCount > 0) {
			forward = new ActionForward();
			forward.setPath("/Project/loginForm.mem");
			
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입에 실패했습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		System.out.println("[2]updatecount의 값 : " + updateCount);
		return forward;
	}

}
