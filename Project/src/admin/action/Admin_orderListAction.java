package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.svc.Admin_orderListSvc;
import vo.ActionForward;

public class Admin_orderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		Admin_orderListSvc admin_orderListSvc = new Admin_orderListSvc();
		
		
		forward.setPath("admin_orderList.jsp");
		return forward;
	}

}
