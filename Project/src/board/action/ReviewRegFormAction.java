package board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import board.svc.ReviewRegSvc;
import vo.ActionForward;
import vo.ReviewBean;

public class ReviewRegFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		String mem_id = (String) session.getAttribute("id");
		ReviewRegSvc reviewReg = new ReviewRegSvc();
		System.out.println(pro_code);
		System.out.println(mem_id);

		if (mem_id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 후 이용해주세요.')");
			out.println("history.back()");
			out.println("</script>");

		} else {
			boolean reviewCheck = false;
			reviewCheck = reviewReg.reviewCheck(mem_id, pro_code);
			System.out.println("reviewCheck:"+reviewCheck);
			
			//회원이 주문한 기록이 있으면
			if(!reviewCheck) {
				System.out.println("기록이 없다.");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('해당 상품 구매한 작성할 수 있습니다.')");
				out.println("history.back()'");
				out.println("</script>");
			}
			forward.setPath("board/reviewRegForm.jsp?pro_code=" + pro_code + "&mem_id=" + mem_id);
		}

		return forward;
	}

}
