package board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.NoticeWriteSvc;
import vo.ActionForward;
import vo.NoticeBean;

public class NoticeWriteAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		NoticeBean noticeBean = null;

		noticeBean = new NoticeBean();
		noticeBean.setNo_title(request.getParameter("title"));
		noticeBean.setNo_content(request.getParameter("content"));
		NoticeWriteSvc noticeWriteSvc = new NoticeWriteSvc();
		boolean isWriteSuccess = noticeWriteSvc.noticeWriteSvc(noticeBean);

		if (!isWriteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/Project/noticeList.bo");
			
		}
		return forward;

	}
}
