package board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.ProductListSvc;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBean;

public class ProductListAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProductListAction 액션 진입");
		String sort = "new";
		if(request.getParameter("sort")!=null) {
		sort = request.getParameter("sort");
		}
		ArrayList<ProductBean> articleList = new ArrayList<ProductBean>();
		
		//페이지 관련
		int page = 1;
		int limit = 10; //한페이지에 게시글 10개씩
		int limitPage=10; //10 페이지 노출

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		ProductListSvc proListService = new ProductListSvc();
		int listCount = proListService.getListCount(); // 글 목록 개수
		articleList = proListService.getArticleList(page, limit, sort);
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
		request.setAttribute("articleList", articleList);
		request.setAttribute("sort", sort);
		ActionForward forward = new ActionForward();
		forward.setPath("/board/productList.jsp");
		return forward;
	}
}
