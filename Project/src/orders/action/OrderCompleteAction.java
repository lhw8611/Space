package orders.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import orders.svc.OrderCompleteSvc;
import vo.ActionForward;
import vo.MemberBean;
import vo.ProductBean;
import vo.QtyBean;

public class OrderCompleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberBean membean = (MemberBean)session.getAttribute("membean"); //구매자정보
		ProductBean probean = (ProductBean)session.getAttribute("probean"); //상품에 대한 정보
		int buyqty = Integer.parseInt(request.getParameter("qty"));
		QtyBean qtybean = new QtyBean();
		
//		System.out.println("멤빈 : " + membean.getMem_name());
//		System.out.println("프로코드 : " + probean.getPro_code());
//		System.out.println("구매수량 직어보자 : " + buyqty);
		OrderCompleteSvc ordercompletesvc = new OrderCompleteSvc();
		
		qtybean = ordercompletesvc.productqty(probean.getPro_code()); //재고수 확인
		System.out.println("재고 확인 : " + qtybean.getQty_qty());
		if((qtybean.getQty_qty()-buyqty)>0) { //구매가능
			ordercompletesvc.orderaction(membean.getMem_id());
			
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('재고수가 부족합니다.');");
			out.println("location.href='/Project/main.jsp'");
			out.println("</script>");
		}
		
		return null;
	}

}
