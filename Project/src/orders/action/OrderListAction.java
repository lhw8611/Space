package orders.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import orders.svc.OrderListSvc;
import vo.ActionForward;
import vo.OrOdProViewBean;
import vo.PageInfo;

public class OrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[OrderSimpleListAction]");
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String keyWord = null;

		session.removeAttribute("keyWord");
		if (session.getAttribute("id") != null) {
			forward = new ActionForward();
			OrderListSvc orderlistsvc = new OrderListSvc();
			int page = 1;
			int limit = 5;
			if (request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));

			if (request.getParameter("keyWord") != null)
				keyWord = request.getParameter("keyWord");

			ArrayList<OrOdProViewBean> orodproviewbean = orderlistsvc.OrderSimpleList(page, limit, id, keyWord); // 주문조회
			int listCount = orderlistsvc.getListCount(id, keyWord);
			System.out.println("listCount 가아아아앖 : " + listCount);
			int maxPage = (int) ((double) listCount / limit + 0.95);
			int startPage = ((int) ((double) page / limit + 0.9) - 1) * limit + 1;
			int endPage = startPage + limit - 1;

			if (endPage > maxPage)
				endPage = maxPage;

			PageInfo pageinfo = new PageInfo();
			pageinfo.setListCount(listCount);
			pageinfo.setStartPage(startPage);
			pageinfo.setEndPage(endPage);
			pageinfo.setMaxPage(maxPage);
			pageinfo.setPage(page);

			request.setAttribute("pageinfo", pageinfo);
			request.setAttribute("orodproviewbean", orodproviewbean);

			forward.setRedirect(false);
			forward.setPath("orders/orderSimpleList.jsp");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='/Project/main.jsp';");
			out.println("</script>");
		}
		return forward;
	}

}
