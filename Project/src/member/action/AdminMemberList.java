package member.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.AdminMemberListSvc;
import vo.ActionForward;
import vo.MemberBean;
import vo.PageInfo;

public class AdminMemberList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		ActionForward forward = null;
		System.out.println("[2]AdminMemberList");
		if((session.getAttribute("grade")==null) ||
				(!((String)session.getAttribute("grade")).equals("s"))){
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요');");
			out.println("location.href='main.jsp';");
			out.println("</script>");
		}else {
			
//			keyWord
			
			AdminMemberListSvc memberlist = new AdminMemberListSvc();
			int page=1;
			int limit=10;
			String keyWord = null;
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			if(request.getParameter("keyWord") != null) {
				keyWord = request.getParameter("keyWord");
			}
			int listCount = memberlist.listCount(keyWord);
			ArrayList<MemberBean> list = memberlist.getMemberList(page, limit, keyWord);//총 멤버정보 받아옴
			
			int maxPage = (int)((double)listCount/limit+0.95);
			int startPage = (((int) ((double)page / limit+0.9))-1)*limit+1;
			int endPage = startPage + limit - 1;
			
			if(endPage>maxPage) endPage = maxPage;
			
			PageInfo pageInfo = new PageInfo();
			pageInfo.setEndPage(endPage);
			pageInfo.setListCount(listCount);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setPage(page);
			pageInfo.setStartPage(startPage);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			
			forward = new ActionForward();
			forward.setPath("/admin/member_list.jsp");
		}
		
		
		
		return forward;
	}

}
