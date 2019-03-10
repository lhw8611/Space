package orders.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import orders.svc.OrderFormSvc;
import vo.ActionForward;
import vo.MemberBean;
import vo.ProductBean;

public class OrderFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[액션]OrderFormAction");

		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();

		String id = (String) session.getAttribute("id"); // 세션에서 ID 받아옴
		int pro_code = Integer.parseInt(request.getParameter("pro_code")); // 상품코드 받아옴
		String qty = request.getParameter("qty");// 수량 받아옴
		request.setAttribute("qty", qty);

		
		if (id == null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 후 이용해주세요.')");
			out.println("location.href='/Project/member/loginForm.jsp'");
			out.println("</script>");
		} else {
			OrderFormSvc odFormSvc = new OrderFormSvc();
			// 구매자 정보
			MemberBean membean = odFormSvc.purchaserInfo(id);
			request.setAttribute("membean", membean);
			
			//상품정보
			ProductBean probean = odFormSvc.productsInfo(pro_code);
			request.setAttribute("probean", probean);
			
//			int usePoint = probean.getPro_price()
			
			forward.setPath("/orders/orderForm.jsp");
		}
		return forward;
	}

}
