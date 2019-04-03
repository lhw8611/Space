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
			out.println("location.href='/Space/member/loginForm.jsp'");
			out.println("</script>");
		} else {// 로그인상태
			OrderFormSvc odFormSvc = new OrderFormSvc();
			MemberBean membean = odFormSvc.purchaserInfo(id);// 구매자 정보
			int MaxPoint = odFormSvc.MaxPoint(id);// 사용 가능한 포인트 계산

			ArrayList<CartProViewBean> cartList = null;// 카트에서 넘어올 상품정보 배열저장
			ArrayList<OrderListBean> orderlistbean = new ArrayList<OrderListBean>(); // 주문할거 배열로 저장

			OrderListBean orderbean = null;
			int pro_codes[] = null;

			if (request.getParameter("type").equals("sel")) { // 장바구니 주문하기
				System.out.println("구매타입 : sel");
			
				cartList = (ArrayList<CartProViewBean>) session.getAttribute("cartList");
				if(request.getAttribute("point")==null) {
					System.out.println("리퀘스트 어트리뷰트 포인트 널임 ");
				}else if(request.getAttribute("point")!=null) {
					System.out.println("리퀘스트 어트리뷰트 포인트 널아님님님님 ");
				}
				
				if(session.getAttribute("point")==null) {
					System.out.println("세션 리퀘스트 어트리뷰트 포인트 널임");
				}else if(session.getAttribute("point")!=null) {
					System.out.println("세션 리퀘스트 어트리뷰트 포인트 널아님님님");
				}
				
				if(request.getParameterValues("checklist")!= null || session.getAttribute("point")!=null) {
					System.out.println("체크리스트 이프문 진입하는지 체크~");
					boolean point = false;
					if(session.getAttribute("point")!=null) {
						System.out.println("포인트 사용하기해서 참으로됨");
						point = (boolean)session.getAttribute("point");
					}
				if(point == false) {
					if(cartList==null) System.out.println("카트리스트 널");
				pro_codes = Arrays.asList(request.getParameterValues("checklist")).stream().mapToInt(Integer::parseInt)
						.toArray();// string배열을 int배열로 변환
				if(pro_codes==null) System.out.println("프로코드 널");
				for (int i = 0; i < cartList.size(); i++) {
					for (int j = 0; j < pro_codes.length; j++) {
						if (cartList.get(i).getPro_code() == (pro_codes[j])) {
							orderbean = new OrderListBean();
							orderbean.setPro_code(cartList.get(i).getPro_code()); // 주문상품코드
							orderbean.setOd_qty(cartList.get(i).getCart_qty()); // 주문갯수
							orderbean.setPro_name(cartList.get(i).getPro_name()); // 주문상품이름
							orderbean.setPro_price(cartList.get(i).getPro_price()); // 주문상품가격
							orderbean.setPro_image(cartList.get(i).getPro_image()); // 주문상품 이미지
							orderlistbean.add(orderbean);
						}
					}
				}
				
				}else if(point){
					System.out.println("엘즈이프 포인뜨 트루");
					orderlistbean = (ArrayList<OrderListBean>) session.getAttribute("orderlistbean");
				}
				
				if(orderlistbean==null) System.out.println("orderlistbean 널이야 씨발 ...");
				for(int i=0; i<orderlistbean.size(); i++) {
					System.out.println("오더리스트빈 다 찍어본다 : " + orderlistbean.get(i).getPro_name());
				}
				//이미 있는 orderlistbean값을 그대로 사용
				System.out.println("오더리스트빈 사이즈 측정 : " + orderlistbean.size());
				//orderform에서 오면 true되는 point
				request.setAttribute("orderlistbean", orderlistbean);
				request.setAttribute("point", point);
				}

			} else if (request.getParameter("type").equals("one")) { // 뷰에서 바로구매
				System.out.println("구매 type : one");
				int pro_code = Integer.parseInt(request.getParameter("pro_code")); // 상품코드 받아옴
				System.out.println("상품코드 찍어봄 : " + Integer.parseInt(request.getParameter("pro_code")));
				int qty = Integer.parseInt(request.getParameter("qty")); // 수량 받아옴
				System.out.println("수량 찍어봄 : " + request.getParameter("qty"));
				orderlistbean = odFormSvc.orderTypeOne(pro_code, qty); // 상품정보
				// 포인트 사용을 위한 attribute

				request.setAttribute("pro_code", pro_code);
				request.setAttribute("qty", qty);
			}

			if (request.getParameter("usepoint") != null) {
				request.setAttribute("usepoint", request.getParameter("usepoint"));
			}

			int totalItem = 0; // 총 상품금액
			int totalMoney = 0; // 총 결제금액
			int delivery = 2500;
			for (int i = 0; i < orderlistbean.size(); i++) {
				totalMoney += orderlistbean.get(i).getPro_price();
				totalItem += orderlistbean.get(i).getPro_price();
				if (totalMoney > 30000) {
					delivery = 0;
				}
			}
			totalMoney += delivery;

			if (request.getParameter("usepoint") != null && Integer.parseInt(request.getParameter("usepoint").trim()) > 0) {
				totalMoney -= Integer.parseInt(request.getParameter("usepoint").trim());
			}

			request.setAttribute("type", request.getParameter("type")); // 포인트 사용을 위한 attribute

//			//카트리스트에서 session준거 삭제
//			session.removeAttribute("point");
//			session.removeAttribute("orderlistbean");
			// 값 계산
			request.setAttribute("totalItem", totalItem);
			request.setAttribute("totalMoney", totalMoney);
			request.setAttribute("delivery", delivery);
			request.setAttribute("orderlistbean", orderlistbean); // 주문상품 정보
			request.setAttribute("membean", membean); // 구매자정보
			request.setAttribute("maxpoint", MaxPoint); // 사용 가능한 최대포인트
			System.out.println("맨밑에있는 오더리스트 빈 사이즈 측정 : " + orderlistbean.size());
			forward.setPath("/orders/orderForm.jsp");
		}
		return forward;
	}

}
