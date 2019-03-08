package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.action.AdminMemberList;
import member.action.JoinFormAction;
import member.action.LoginFormAction;
import member.action.LogoutAction;
import member.action.MemberDeleteAction;
import member.action.MemberInfoAction;
import member.action.MemberInfoModifyAction;
import vo.ActionForward;

@WebServlet("*.mem")
public class MemberController extends HttpServlet {
	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());

		ActionForward forward = null; // 경로가 들어있는 클래스
		Action action = null; // 액션에 있는 메소드 이름을 맞춰주려고
//		System.out.println("전체경로 : " + RequestURI);
//		System.out.println("컨텍스트 : " + contextPath);
		System.out.println("[1]매핑이름 : " + command);

		if (command.equals("/joinForm.mem")) {
			System.out.println("[1]joinForm.mem 회원가입 폼");
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("member/joinForm.jsp");
		} else if (command.equals("/loginForm.mem")) {
			System.out.println("[1]loginForm.mem 로그인 폼");
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("member/loginForm.jsp");
		}

		else if (command.equals("/memberinfo.mem")) { // 멤버 정보보기
			System.out.println("[1]memberinfo.mem");
			action = new MemberInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("[1]memberinfo.mem 에러");
				e.printStackTrace();
			}
		}else if (command.equals("/modifyproaction.mem")) { // 회원정보 수정
			action = new MemberInfoModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("[1]logout.mem 로그아웃 에러");
				e.printStackTrace();
			}
		}

		else if (command.equals("/joinProcess.mem")) { // 회원가입
			System.out.println("[1]joinProcess.mem 에러");
			action = new JoinFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("[1]joinProcess.mem 회원가입 에러");
				e.printStackTrace();
			}
		} else if (command.equals("/loginProcess.mem")) { // 로그인
			System.out.println("[1]loginProcess.mem 로그인 폼 액션");
			action = new LoginFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("[1]loginProcess.mem 로그인 에러");
				e.printStackTrace();
			}
		} else if (command.equals("/logout.mem")) { // 로그아웃
			action = new LogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/admingetlist.mem")) { // 관리자 회원목록 보기
			action = new AdminMemberList();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/memberdelete.mem")) { // 회원탈퇴
			action = new MemberDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
