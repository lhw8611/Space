package admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.svc.Admin_orderListSvc;
import board.svc.NoticeListSvc;
import vo.ActionForward;
import vo.OrOdProViewBean;
import vo.PageInfo;

public class Admin_orderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		Admin_orderListSvc admin_orderListSvc = new Admin_orderListSvc();
		ArrayList<OrOdProViewBean> OrderList= new ArrayList<OrOdProViewBean>();
		
		
		
		int page = 1;
		int limit = 10; //한페이지에 게시글 10개씩
		int limitPage=10; //10 페이지 노출

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listCount = admin_orderListSvc.admin_orderList_count(); // 글 목록 개수
		OrderList = admin_orderListSvc.admin_orderList(page,limit);
		int maxPage = (int) ((double) listCount / limit + 0.95); //글 목록 개수가 1.2면 2페이지가 필요하니깐 올림을 해주기위해 0.95를 더함
		int startPage = (((int) ((double) page / limitPage + 0.9)) - 1) * limitPage + 1;
		int endPage = startPage + limitPage - 1;
		if (endPage > maxPage)
			endPage = maxPage;

		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		
		request.setAttribute("pageInfo", pageInfo);
		
		request.setAttribute("OrderList", OrderList);
		
		forward.setPath("admin/admin_orderList.jsp");
		return forward;
	}

}
