package orders.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberInfoSvc;
import orders.svc.OrderCompleteSvc;
import vo.ActionForward;
import vo.MemberBean;
import vo.OrderBean;
import vo.OrderDetailBean;
import vo.OrderListBean;
import vo.ProductBean;
import vo.QtyBean;

public class OrderCompleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		OrderCompleteSvc ordercompletesvc = new OrderCompleteSvc();
		String id = (String)session.getAttribute("id");
		//구매자 정보
		MemberInfoSvc memberinfosvc = new MemberInfoSvc();
		MemberBean membean = memberinfosvc.MemberInfo(id); 
		//받는사람 정보
		OrderBean orderbean = new OrderBean();
		orderbean.setOr_getname(request.getParameter("get_name"));
		String get_add = request.getParameter("get_zip") +" "+ request.getParameter("get_add")+" " +request.getParameter("get_add2"); 
		orderbean.setOr_getadd(get_add);
		orderbean.setOr_gettel("get_tel");
		orderbean.setOr_request("or_request");
		//주문상품 정보
		int size = Integer.parseInt(request.getParameter("size"));
		System.out.println("size의 값 : " + size);
		ArrayList<Integer> pro_codes = new ArrayList<Integer>();
		ArrayList<Integer> pro_qty = new ArrayList<Integer>();
		for(int i=0; i<size; i++) {
			pro_codes.add(Integer.parseInt(request.getParameter("pro_codes"+i)));
			pro_qty.add(Integer.parseInt(request.getParameter("pro_qty"+i)));
		}
		
		ArrayList<OrderListBean> orderlistbean;
		orderlistbean = ordercompletesvc.productInfo(pro_codes, pro_qty); //상품정보
		session.setAttribute("orderlistbean", orderlistbean); //주문조회할때도 씀 = session
		for(int i=0; i<orderlistbean.size(); i++) {
			System.out.println("비 속을 걸어도 나 감사하니까~~ : " + orderlistbean.get(i).getPro_name());
		}
		
		ordercompletesvc.order_add() //주문테이블에 값추가
		ordercompletesvc.order_qty(orderlistbean);//주문상품 재고수 빼기
		
//		ProductBean probean = (ProductBean)session.getAttribute("probean"); //상품에 대한 정보
//		ActionForward forward = null;
//		
//		QtyBean qtybean = new QtyBean(); //재고수 저장클래스
//		OrderBean odbean = new OrderBean(); //구매정보 저장 클래스
//		OrderDetailBean oddbean = new OrderDetailBean(); //상세주문 저장
//		
//		oddbean.setPro_code(probean.getPro_code());
//		oddbean.setOd_qty(Integer.parseInt(request.getParameter("qty")));
////		System.out.println("멤버아이디 : " + membean.getMem_id());
////		System.out.println("상품코드 : " + probean.getPro_code());
//		
//		String get_zip = request.getParameter("get_zip");
//		String get_add = request.getParameter("get_add");
//		String get_add2 = request.getParameter("get_add2");
//		String get_addrs =  get_zip + " " +  get_add + " " + get_add2;
//		odbean.setOr_getname(request.getParameter("get_name")); //받는사람 이름
//		odbean.setOr_getadd(get_addrs);	//받는사람 주소
//		odbean.setOr_gettel(request.getParameter("get_tel")); //받는사람 전화
//		odbean.setOr_request(request.getParameter("or_request")); //주문 요청사항
//		odbean.setOr_point(Integer.parseInt(request.getParameter("or_point")));//사용 가능한 포인트
//		
////		 값 제대로 못받아옴 임시보류
//		  odbean.setOr_pay(request.getParameter("gyulze"));
//		  System.out.println("결제 뭘로됬나 확인 : " + request.getParameter("gyulze"));
//		 
//		OrderCompleteSvc ordercompletesvc = new OrderCompleteSvc();
//		qtybean = ordercompletesvc.productqty(probean.getPro_code()); //재고수 확인
//		System.out.println("재고 확인 : " + qtybean.getQty_qty());
//		if((qtybean.getQty_qty()-oddbean.getOd_qty())>0) { //구매가능
//			forward = new ActionForward();
//			ordercompletesvc.orderaction(membean.getMem_id(), odbean, oddbean, qtybean);
//			
//			forward.setPath("orders/orderComplete.jsp");
//		}else {
//			PrintWriter out = response.getWriter();
//			out.println("<script>");
//			out.println("alert('재고수가 부족합니다.');");
//			out.println("location.href='/Project/main.jsp'");
//			out.println("</script>");
//		}
		ActionForward forward = new ActionForward();
		return forward;
	}

}
