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
import vo.OrderBean;
import vo.ProductBean;

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
		} else {
			OrderFormSvc odFormSvc = new OrderFormSvc();
			MemberBean membean = odFormSvc.purchaserInfo(id);//구매자 정보
			int MaxPoint = odFormSvc.MaxPoint(id);//사용 가능한 포인트 계산
			
			ArrayList<CartProViewBean> cartList = (ArrayList<CartProViewBean>)session.getAttribute("cartListdb");
			if(cartList == null) {
				cartList = (ArrayList<CartProViewBean>)session.getAttribute("cartList");
			}
			if(cartList == null) {
				cartList = new ArrayList<CartProViewBean>();
			}
			
			for(int i=0; i<cartList.size(); i++) {
				System.out.println("오더폼 상품코드 : " + cartList.get(i).getPro_code());
			}
			
			ArrayList<ProductBean> probeanList = new ArrayList<ProductBean>(); //상품정보 배열저장
			
		
			
			

			
			int codes[] = null;
			if(request.getParameter("type").equals("all")) { //장바구니 전체주문
				ProductBean probean ;
				for(int i=0;i<cartList.size();i++) {
					probean = new ProductBean();
					cartList.get(i).getPro_code();
					cartList.get(i).getPro_name();
					cartList.get(i).getPro_price();
					cartList.get(i).getPro_image();
					probeanList.add(
							
							cartList.get(i).getPro_code());
				}
			}else if(request.getParameter("type").equals("sel")) { //장바구니 선택주문
				codes = Arrays.asList(request.getParameterValues("checklist")).stream().mapToInt(Integer::parseInt).toArray();//string배열을 int배열로 변환
				for(int i=0;i<cartList.size();i++) {
					for(int j=0;j<codes.length;j++) {
						if(cartList.get(i).getPro_code()==(codes[j])) {
							cartList.get(i).getPro_code();
							cartList.get(i).getPro_name();
							cartList.get(i).getPro_price();
							cartList.get(i).getPro_image();
							probeanList.add(probean);
						}
					}
				}
			}else if(request.getParameter("type").equals("one")) {
				int pro_code = Integer.parseInt(request.getParameter("pro_code")); // 상품코드 받아옴
				// 수량 받아옴
				String qty = request.getParameter("qty");
				request.setAttribute("qty", qty);
				ProductBean probean = odFormSvc.productsInfo(pro_code);	//상품정보
				probeanList.add(probean);
			
			}
			
			int totalMoney = 0;
			int delivery = 0;
			if(totalMoney<30000) {
				delivery = 3000;
				totalMoney+= delivery;
			}
		
			

			request.setAttribute("membean", membean);
			request.setAttribute("maxpoint", MaxPoint);
			
			System.out.println("다 실행되나 확인");
			forward.setPath("/orders/orderForm.jsp");
		}
		return forward;
	}

}
