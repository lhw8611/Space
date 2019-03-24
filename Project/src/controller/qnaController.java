package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import qna.action.qnaDeleteAction;
import qna.action.qnaListAction;
import qna.action.qnaModifyAction;
import qna.action.qnaWriteAction;
import vo.ActionForward;

@WebServlet("*.qna")
public class qnaController extends HttpServlet {

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());

		ActionForward forward = null;
		Action action = null;
		System.out.println("[1]매핑이름 : " + command);

		if (command.equals("/qnaList.qna")) { //qna 목록보기
			action = new qnaListAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/qnaWriteForm.qna")) { //qna 글쓰기 폼
			System.out.println("qna글쓰기폼 진입");
			forward = new ActionForward();
			forward.setPath("/board/qnaWriteForm.jsp");
		}
		
		else if (command.equals("/qnaForm.qna")) { //qna 글쓰기 액션
			action = new qnaWriteAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}	
		else if (command.equals("/qnaDelete.qna")) { //qna 글삭제 액션
			action = new qnaDeleteAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/qnaModify.qna")) { //qna 글수정 액션
			action = new qnaModifyAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/qnaModifyForm.qna")) { //qna 글수정폼 진입
			forward = new ActionForward();
			forward.setPath("board/qna_modify.jsp");
		}
			
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
