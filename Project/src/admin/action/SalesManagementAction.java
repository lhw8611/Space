package admin.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.svc.SalesManagementSvc;
import vo.ActionForward;

public class SalesManagementAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		
		
		
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		SalesManagementSvc salesSvc = new SalesManagementSvc();
		map = salesSvc.SalesManagement();
		
		request.setAttribute("map", map);
		
//		System.out.println(map.get(3));
		forward.setPath("admin/salesManagement.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
