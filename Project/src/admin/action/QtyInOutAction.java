package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.svc.QtyInOutSvc;
import vo.ActionForward;

public class QtyInOutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int pro_code = Integer.parseInt((String) request.getAttribute("pro_code"));//이거 인식안됨
		
		int qty = Integer.parseInt(request.getParameter("qty"));
		String note = request.getParameter("note");
		String inout = request.getParameter("inout");
		System.out.println("inout액션에서 프로코드 :"+pro_code);
		
		
		QtyInOutSvc qtyInOutSvc = new QtyInOutSvc();
		
		qtyInOutSvc.QtyInSvc(pro_code,inout,qty,note);
	
		
		forward.setPath("admin/popup_inout");
		return forward;
	}

}
