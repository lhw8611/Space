package orders.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import orders.svc.OrderFormSvc;
import vo.ActionForward;
import vo.CartProViewBean;
import vo.MemberBean;
import vo.OrderListBean;

public class OrderFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[액션]OrderFormAction");
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		String id = (String) session.getAttribute("id"); // 세션에서 ID 받아옴

		if (id == null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 후 이용해주세요.')");
			out.println("location.href='/Project/member/loginForm.jsp'");
			out.println("</script>");
		} else {// 로그인상태
			OrderFormSvc odFormSvc = new OrderFormSvc();
			MemberBean membean = odFormSvc.purchaserInfo(id);// 구매자 정보
			int MaxPoint = odFormSvc.MaxPoint(id);// 사용 가능한 포인트 계산
			
			ArrayList<CartProViewBean> cartList = null;// 카트에서 넘어올 상품정보 배열저장
			ArrayList<OrderListBean> orderlistbean = new ArrayList<OrderListBean>(); //주문할거 배열로 저장
			
			OrderListBean orderbean = null;
			int pro_codes[] = null;
			
			if (request.getParameter("type").equals("sel")) { // 장바구니 주문하기
				System.out.println("구매타입 : sel");
				cartList = (ArrayList<CartProViewBean>)session.getAttribute("cartList");
				pro_codes = Arrays.asList(request.getParameterValues("checklist")).stream().mapToInt(Integer::parseInt)
						.toArray();// string배열을 int배열로 변환
				for (int i = 0; i < cartList.size(); i++) {
					for(int j=0; j < pro_codes.length; j++) {
						if(cartList.get(i).getPro_code() == (pro_codes[j])) {
							orderbean = new OrderListBean();
							orderbean.setPro_code(cartList.get(i).getPro_code()); //주문상품코드
							orderbean.setOd_qty(cartList.get(i).getCart_qty()); //주문갯수
							orderbean.setPro_name(cartList.get(i).getPro_name()); //주문상품이름
							orderbean.setPro_price(cartList.get(i).getPro_price()); //주문상품가격
							orderbean.setPro_image(cartList.get(i).getPro_image()); //주문상품 이미지
							orderlistbean.add(orderbean);
						}
					}
				}
			}
			else if (request.getParameter("type").equals("one")) { // 뷰에서 바로구매
				System.out.println("구매 type : one");
				int pro_code = Integer.parseInt(request.getParameter("pro_code")); // 상품코드 받아옴
				int qty = Integer.parseInt(request.getParameter("qty")); // 수량 받아옴
				orderlistbean = odFormSvc.orderTypeOne(pro_code, qty); // 상품정보

			}


			int totalItem = 0; //총 상품금액
			int totalMoney = 0; //총 결제금액
			int delivery = 2500;
			for (int i = 0; i < orderlistbean.size(); i++) {
				totalMoney += orderlistbean.get(i).getPro_price();
				totalItem += orderlistbean.get(i).getPro_price();
				if (totalMoney > 30000) {
					delivery = 0;
				}
			}
			totalMoney += delivery;
			
			request.setAttribute("totalItem", totalItem);
			request.setAttribute("totalMoney", totalMoney);
			request.setAttribute("delivery", delivery);
			request.setAttribute("orderlistbean", orderlistbean); //주문상품 정보
			request.setAttribute("membean", membean); //구매자정보
			request.setAttribute("maxpoint", MaxPoint); //사용 가능한 최대포인트
			forward.setPath("/orders/orderForm.jsp");
		}
		return forward;
	}

}
