package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import board.action.NoticeDetailAction;
import board.action.NoticeListAction;
import board.action.NoticeWriteAction;
import board.action.ProSearchAction;
import board.action.ProductListAction;
import board.action.ProductViewAction;
import board.action.ProductWriteAction;
import board.action.ReviewRegAction;
import board.action.ReviewRegFormAction;
import vo.ActionForward;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.bo")
public class BoardFrontController extends javax.servlet.http.HttpServlet {
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
		HttpSession session = request.getSession();

		// 공지사항 목록
		if (command.equals("/noticeList.bo")) {
//			System.out.println("noticeList 컨트롤러 진입");
			action = new NoticeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 공지 글 작성 폼
		} else if (command.equals("/noticeWriteForm.bo")) {
			 if(session.getAttribute("grade").equals("s")) { 
			forward = new ActionForward();
			forward.setPath("/board/noticeWriteForm.jsp");
			forward.setRedirect(false);
			
			  } else { response.setContentType("text/html;charset=UTF-8"); PrintWriter out
			  = response.getWriter(); out.println("<script>");
			  out.println("alert('관리자만 이용가능합니다.');"); out.println("history.back()");
			  out.println("</script>"); out.close(); }
			 
			// 공지 글 작성 액션
		} else if (command.equals("/noticeWritePro.bo")) {
			action = new NoticeWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 공지 글 상세보기
		} else if (command.equals("/noticeDetail.bo")) {
//			System.out.println("noticeDetail 컨트롤러 진입");
			action = new NoticeDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 상품등록 폼
		} else if (command.equals("/productWriteForm.bo")) {
//			System.out.println("상품등록 폼");
			 if(session.getAttribute("grade").equals("s")) { 
			forward = new ActionForward();
			forward.setPath("/board/productWriteForm.jsp");
			
			  } else { response.setContentType("text/html;charset=UTF-8"); 
			  PrintWriter out = response.getWriter(); out.println("<script>");
			  out.println("alert('관리자만 이용가능합니다.');"); out.println("history.back()");
			  out.println("</script>"); out.close(); }
			 

			// 상품등록 액션
		} else if (command.equals("/ProductWritePro.bo")) {
//			System.out.println("productWritePro 컨트롤러 진입");
			action = new ProductWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//상품리스트
		} else if (command.equals("/productList.bo")) {
//			System.out.println("productList 컨트롤러 진입");
			action = new ProductListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//상품 상세보기
		else if (command.equals("/productView.bo")) {
			System.out.println("productView 컨트롤러 진입");
			action = new ProductViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//상품 검색
		else if (command.equals("/proSearch.bo")) {
			System.out.println("상품검색 컨트롤러 진입");
			action = new ProSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//리뷰 등록
		else if (command.equals("/reviewReg.bo")) {
			System.out.println("리뷰 등록컨트롤러 진입");
			action = new ReviewRegAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//리뷰 등록 폼
		else if (command.equals("/reviewRegForm.bo")) {
			System.out.println("리뷰 등록폼 컨트롤러 진입");
			action = new ReviewRegFormAction();
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
