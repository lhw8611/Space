package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.action.Admin_orderListAction;
import admin.action.HideAction;
import admin.action.InOutListFormAction;
import admin.action.QtyInOutAction;
import admin.action.QtyInOutDeleteAction;
import admin.action.QtyManagementFormAction;
import admin.action.ShowAction;
import vo.ActionForward;

/**
 * Servlet implementation class adminController
 */
@WebServlet("*.ad")
public class adminController extends HttpServlet {
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

		// 재고관리
		if (command.equals("/qtyManagement.ad")) {
			System.out.println("상품관리 컨트롤러 진입");
			action = new QtyManagementFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 입/출고 리스트 폼
		} else if (command.equals("/inoutListForm.ad")) {
			System.out.println("입/출고 리스트 컨트롤러 진입");
			action = new InOutListFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 입출고 액션
		} else if (command.equals("/QtyInOutAction.ad")) {
			System.out.println("입출고 레지스트 컨트롤러 진입");
			action = new QtyInOutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//입출고 삭제
		}else if (command.equals("/qtyInOutDelete.ad")) {
			System.out.println("입출고 삭제 진입");
			action = new QtyInOutDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		//게시글 show
		}else if (command.equals("/show.ad")) {
			System.out.println("상품 show 컨트롤러 진입");
			action = new ShowAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//게시글 hide
		else if (command.equals("/hide.ad")) {
			System.out.println("상품 hide 컨트롤러 진입");
			action = new HideAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//관리자 - 주문내역
		else if (command.equals("/admin_orderList.ad")) {
			System.out.println("관리자 주문내역");
			action = new Admin_orderListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//환불
		else if (command.equals("/refund.ad")) {
			System.out.println("환불 컨트롤러");
			action = new Admin_orderListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		

		//포워드
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
