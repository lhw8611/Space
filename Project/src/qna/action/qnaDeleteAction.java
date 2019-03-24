package qna.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import qna.svc.qnaDeleteSvc;
import vo.ActionForward;

public class qnaDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[2]qnaDeleteAciton");
		ActionForward forward = null;
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		qnaDeleteSvc qnadeletesvc = new qnaDeleteSvc();
		int insertCount = qnadeletesvc.qnaDelete(qna_num);
		if(insertCount > 0) {
			forward = new ActionForward();
			forward.setPath("/qnaList.qna");
				
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제에 실패했습니다.');");
			out.println("location.href='/Project/qnaList.qna';");
			out.println("</script>");
		}
		
		return forward;
	}

}
