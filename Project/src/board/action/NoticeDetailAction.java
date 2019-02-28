package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.NoticeDetailSvc;
import vo.ActionForward;
import vo.NoticeBean;

public class NoticeDetailAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int no_num = Integer.parseInt(request.getParameter("no_num"));
		String page = request.getParameter("page");
		
		NoticeDetailSvc noticeDetailService = new NoticeDetailSvc();
		NoticeBean article = noticeDetailService.getArticle(no_num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("article", article);
		forward.setPath("/noticeDetailForm.jsp");
		return forward;

	}
}
