package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.svc.ProModifyFormSvc;
import vo.ActionForward;

public class ProModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		
		ProModifyFormSvc proModifyFormSvc = new ProModifyFormSvc();
		proModifyFormSvc.getProView(pro_code);
		
		forward.setPath("admin/proModifyForm.jsp");
		return forward;
	}
	
}
