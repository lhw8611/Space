package board.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.ProductListSvc;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBean;

public class ProSearchAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sW="";
		sW = request.getParameter("sW");
		
		String sort = "new";
		if(request.getParameter("sort")!=null) {
			sort = request.getParameter("sort");
			}
		
		System.out.println("서치프로액션 진입");
		ActionForward forward = new ActionForward();
		
		/* ProSearchSvc SearchSvc = new ProSearchSvc(); */
		ArrayList<ProductBean> articleList = new ArrayList<ProductBean>();
		int page = 1; 	
		int limit = 5; //한페이지에 게시글 10개씩
		int limitPage=5; //10 페이지 노출

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ProductListSvc proListSvc = new ProductListSvc();
		int listCount = proListSvc.getListCount(sW); //  글 전체 개수
		
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

		articleList=proListSvc.getArticleList(page, limitPage, sort, sW);
		/* articleList = SearchSvc.subjectSearchSvc(page,limit, sW); */
			request.setAttribute("articleList", articleList);
			forward.setPath("/board/productList.jsp");
			
		return forward;
	}

}
