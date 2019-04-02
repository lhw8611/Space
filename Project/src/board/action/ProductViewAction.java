package board.action;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.NoticeListSvc;
import board.svc.ProductViewSvc;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBean;
import vo.QtyProViewBean;
import vo.ReviewBean;

public class ProductViewAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductViewSvc proViewSvc = new ProductViewSvc();
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		ProductBean probean = proViewSvc.getProView(pro_code);
		ArrayList<ReviewBean> reviewList= new ArrayList<ReviewBean>();
		
		//페이지
		int page = 1;
		int limit = 5; //한페이지에 게시글 10개씩
		int limitPage=10; //10 페이지 노출

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		ProductViewSvc productViewSvc = new ProductViewSvc();
		int listCount = productViewSvc.getListCount(pro_code); // 리뷰 개수 
		reviewList = proViewSvc.reviewList(page,limit,pro_code);
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
		
		
		ArrayList<QtyProViewBean> qtyList = new ArrayList<QtyProViewBean>();
		qtyList = productViewSvc.totalQty(pro_code);
		
		request.setAttribute("qtyList", qtyList);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("probean", probean);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("reviewCount", listCount);
		ActionForward forward = new ActionForward();

		forward.setPath("/board/productView.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
