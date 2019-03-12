package admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.svc.QtyManagementFormSvc;
import vo.ActionForward;
import vo.QtyProViewBean;

public class QtyManagementFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ArrayList<QtyProViewBean> qtyList = new ArrayList<QtyProViewBean>();
		QtyManagementFormSvc qtyManagementFormSvc = new QtyManagementFormSvc();
		
		qtyList = qtyManagementFormSvc.qtyManagementForm();
		request.setAttribute("qtyList", qtyList);
		
		
		
		forward.setPath("admin/qtyManagement.jsp");
		return forward;
	}

}
