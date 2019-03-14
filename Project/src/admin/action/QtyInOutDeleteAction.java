package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.svc.QtyInOutDeleteSvc;
import vo.ActionForward;

public class QtyInOutDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		int qty_num = Integer.parseInt(request.getParameter("qty_num"));
		System.out.println("세션 코드 : "+session.getAttribute("pro_code"));
		int pro_code = (int)session.getAttribute("pro_code");
		
		QtyInOutDeleteSvc qtyInOutDeleteSvc = new QtyInOutDeleteSvc();
		qtyInOutDeleteSvc.qtyInOutDelete(qty_num);
		
		forward.setPath("inoutListForm.ad?pro_code="+pro_code);
		return forward;
	}

}
