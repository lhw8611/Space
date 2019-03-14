package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.svc.ShowHideSvc;
import vo.ActionForward;

public class ShowAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ShowHideSvc showHideSvc = new ShowHideSvc();
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		showHideSvc.proShow(pro_code);
		
		forward.setPath("qtyManagement.ad");
		return forward;
	}

}
