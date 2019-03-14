package admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.svc.InOutListFormSvc;
import vo.ActionForward;
import vo.QtyProViewBean;

public class InOutListFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		ArrayList<QtyProViewBean> qtyInOutList = new ArrayList<QtyProViewBean>();
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		InOutListFormSvc inOutListFormSvc = new InOutListFormSvc();
		
		qtyInOutList = inOutListFormSvc.InOutManagement(pro_code);
		System.out.println("성공적");
		request.setAttribute("qtyInOutList", qtyInOutList);
		session.setAttribute("pro_code", pro_code);
		
		
		forward.setPath("admin/popup_inout.jsp");
		return forward;
	}

}
