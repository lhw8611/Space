package qna.action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import qna.svc.qnaListSvc;
import vo.ActionForward;
import vo.PageInfo;
import vo.QnaBean;

public class qnaListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("[2]qnaListAction");
		ArrayList<QnaBean> articleList = new ArrayList<QnaBean>(); //각 페이지당 출력될 전체 글 목록을 저장할 arrylist
		int page=1; //목록 보기 요청에서 출력될 페이지의 기본값으로 1페이지를 설정하는 부분
		int limit=10; //한 페이지당 출력될 글의 갯수 10개로 설정
		
		//목록 보기에 출력될 페이지가 파라미터로 전송된 경우 page 변수의 값을 변경하는 부분이다. 목록 보기 페이지에서
		//조회할 페이지 번호를 클릭하고 요청한 경우는 페이지 번호가 파라미터로 전송되어 온다.
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		qnaListSvc qnalistsvc = new qnaListSvc();
		int listCount = qnalistsvc.getListCount();//총 리스트 수를 받아옴
		articleList = qnalistsvc.getArticleList(page, limit);//리스트를 받아옴
		//총 페이지 수
		int maxPage = (int)((double)listCount/limit+0.95);
		//0.95를 더해서 올림 처리
		//현재 페이지에 보여줄 시작 페이지 수 (1,11,21등...)
		int startPage = (((int) ((double)page / limit+0.9))-1)*limit+1;
		//현재 페이지에 보여줄 마지막 페이지 수(10,20,30등)
		int endPage = startPage + limit - 1;
		
		if(endPage>maxPage) endPage = maxPage;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_list.jsp");
		forward.setRedirect(false);
		
		System.out.println("[2]경로 확인 : " + forward.getPath());

		return forward;
	}
	
}
