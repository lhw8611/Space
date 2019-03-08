package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import orders.action.CartAddAction;
import orders.action.CartListFormAction;
import orders.action.OrderFormAction;
import vo.ActionForward;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("*.od")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String requestURL = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURL.substring(contextPath.length());
//		위 3줄은 경로 구하는 코드
//		System.out.println("requestURL : " + requestURL);
//		System.out.println("contextPath : " + contextPath);
		System.out.println("command : " + command);
//		System.out.println("====================================");

		ActionForward forward = null;
		Action action = null;

		// 주문 페이지
		if (command.equals("/orderForm.od")) {
			System.out.println("orderForm컨트롤러 진입");
			action = new OrderFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/cartAdd.od")) {
			System.out.println("cartAdd 컨트롤러 진입");
			action = new CartAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/cartListForm.od")) {
			System.out.println("cartListForm 컨트롤러 진입");
			action = new CartListFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 포워드
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
