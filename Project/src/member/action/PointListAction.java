package member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.PointListSvc;
import vo.ActionForward;
import vo.PointBean;

public class PointListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[2]PointListAction");
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		PointListSvc pointlistsvc = new PointListSvc();
		ArrayList<PointBean> arraypointbean = pointlistsvc.pointListSvc(id);
		request.setAttribute("arraypointbean", arraypointbean);
		forward = new ActionForward();
		forward.setPath("member/pointList.jsp");
		
		return forward;
	}

}
