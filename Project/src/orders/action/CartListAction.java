package orders.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class CartListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("cartList 액션 진입");
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		//비로그인 상태
		if(session.getAttribute("id")==null) {
			
			
			
		//로그인 상태
		}else {
			
			
		}
		

		return forward;
	}

}
