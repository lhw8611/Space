package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.action.JoinFormAction;
import member.action.LoginFormAction;
import member.action.LogoutAction;
import vo.ActionForward;

@WebServlet("*.mem")
public class MemberController extends HttpServlet {
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		
		ActionForward forward=null;	//경로가 들어있는 클래스
		Action action=null;			//액션에 있는 메소드 이름을 맞춰주려고
		System.out.println("전체경로 : " + RequestURI);
		System.out.println("컨텍스트 : " + contextPath);
		System.out.println("매핑이름 : " + command);
		
		
		if(command.equals("/joinForm.mem")) {
			System.out.println("[1]joinForm.mem 회원가입 폼");
			forward = new ActionForward();
			forward.setPath("member/joinForm.jsp");
		}else if(command.equals("/loginForm.mem")) {
			System.out.println("[1]loginForm.mem 로그인 폼");
			forward = new ActionForward();
			forward.setPath("member/loginForm.jsp");
		}
		
	/*	else if(command.equals("/logoutAction.mem")) {
			System.out.println("[2]logoutAction");
		}*/
			else if(command.equals("/joinProcess.mem")) {
			System.out.println("[1]joinProcess.mem 회원가입 폼액션");
			action = new JoinFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("[1]joinProcess.mem 회원가입 에러");
				e.printStackTrace();
			}
		}else if(command.equals("/loginProcess.mem")) {
			System.out.println("[1]loginProcess.mem 로그인 폼 액션");
			action = new LoginFormAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("[1]loginProcess.mem 로그인 에러");
				e.printStackTrace();
			}
		}else if(command.equals("/logout.mem")) {
			action = new LogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("[1]logout.mem 로그아웃 에러");
				e.printStackTrace();
			}
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

}
