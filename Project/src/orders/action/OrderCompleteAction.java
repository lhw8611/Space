package orders.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberInfoSvc;
import orders.svc.OrderCompleteSvc;
import vo.ActionForward;
import vo.MemberBean;
import vo.OrderBean;
import vo.OrderListBean;
import vo.PointBean;

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
		orderbean.setOr_gettel(request.getParameter("get_tel"));
		orderbean.setOr_request(request.getParameter("or_request"));
		orderbean.setOr_pay(request.getParameter("gyulze"));
		//주문상품 정보
		int size = Integer.parseInt(request.getParameter("size"));
		ArrayList<Integer> pro_codes = new ArrayList<Integer>();
		ArrayList<Integer> pro_qty = new ArrayList<Integer>();
		for(int i=0; i<size; i++) {
			pro_codes.add(Integer.parseInt(request.getParameter("pro_codes"+i)));
			pro_qty.add(Integer.parseInt(request.getParameter("pro_qty"+i)));
		}
		ArrayList<OrderListBean> orderlistbean;
		orderlistbean = ordercompletesvc.productInfo(pro_codes, pro_qty); //주문상품 정보
		session.setAttribute("orderlistbean", orderlistbean);
		
		int insertOrderCnt = ordercompletesvc.order_add(membean, orderbean); //orders 에 insert
//		System.out.println("order table insertCnt  : " + insertOrderCnt);
		int insertQtyCnt = ordercompletesvc.order_qty(orderlistbean);//주문상품 재고수 빼기
//		System.out.println("qty table insertCnt  : " + insertQtyCnt);
		ordercompletesvc.order_detail_add(orderbean, orderlistbean);//order_detail에 insert
		int savePoint = 0;
		for(int i=0; i<orderlistbean.size(); i++) {
			savePoint += orderlistbean.get(i).getPro_price() * orderlistbean.get(i).getOd_qty() / 100;
		}
		PointBean pointbean = new PointBean();
		pointbean.setMem_id(id);
		pointbean.setPo_state("buysave");
		pointbean.setPo_point(savePoint);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("orders/orderComplete.jsp");
		return forward;
	}

}
