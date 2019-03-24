package qna.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import qna.svc.qnaModifySvc;
import vo.ActionForward;

public class qnaModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("qnaModifyAction");
		ActionForward forward = null;
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		String qna_question = request.getParameter("qna_question");
		String qna_answer = request.getParameter("qna_answer");
		qnaModifySvc qnamodifysvc = new qnaModifySvc();
		int insertCount = qnamodifysvc.qnaModify(qna_num, qna_question, qna_answer);
		if(insertCount > 0) {
			forward = new ActionForward();
			forward.setPath("/qnaList.qna");
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정에 실패했습니다.');");
			out.println("location.href='/Project/qnaList.qna';");
			out.println("</script>");
		}
		
		return forward;
	}

}
