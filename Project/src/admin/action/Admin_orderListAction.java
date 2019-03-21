package admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.svc.Admin_orderListSvc;
import vo.ActionForward;
import vo.OrOdProViewBean;

public class Admin_orderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		Admin_orderListSvc admin_orderListSvc = new Admin_orderListSvc();
		ArrayList<OrOdProViewBean> OrderList= new ArrayList<OrOdProViewBean>();
		OrderList = admin_orderListSvc.admin_orderList();
		
		request.setAttribute("OrderList", OrderList);
		
		forward.setPath("admin/admin_orderList.jsp");
		return forward;
	}

}
