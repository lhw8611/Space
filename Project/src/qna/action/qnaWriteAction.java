package qna.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import qna.svc.qnaWriteSvc;
import vo.ActionForward;
import vo.QnaBean;

public class qnaWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[2]qnaWriteAction");
		HttpSession session = request.getSession();
		ActionForward forward = null;
		QnaBean voqna;
		System.out.println(session.getAttribute("id"));
		if (session.getAttribute("id") == null || (!(session.getAttribute("id").equals("admin"))) ) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요');");
			out.println("location.href='/Project/main.jsp';");
			out.println("</script>");
		} else {
			System.out.println("else문 진입체크");
			voqna = new QnaBean();
			voqna.setQna_question(request.getParameter("question"));
			voqna.setQna_answer(request.getParameter("answer"));
			qnaWriteSvc qnawritesvc = new qnaWriteSvc();
			qnawritesvc.UserCheck(voqna);

			forward = new ActionForward();
			forward.setPath("/Project/qnaList.qna");
			forward.setRedirect(true);
		}

		return forward;
	}

}
