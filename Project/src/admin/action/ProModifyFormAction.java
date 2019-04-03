package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.svc.ProModifyFormSvc;
import vo.ActionForward;
import vo.ProductBean;

public class ProModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		
		ProModifyFormSvc proModifyFormSvc = new ProModifyFormSvc();
		
		
		ProductBean probean = new ProductBean();
		
		probean = proModifyFormSvc.getProView(pro_code);
		
		request.setAttribute("probean", probean);
		
		
		forward.setPath("admin/ProModifyForm.jsp");
		return forward;
	}
	
}
