package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.ProductViewSvc;
import vo.ActionForward;
import vo.ProductBean;

public class ProductViewAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductViewSvc proViewSvc = new ProductViewSvc();
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		ProductBean probean = proViewSvc.getDogView(pro_code);
		request.setAttribute("probean", probean);
		ActionForward forward = new ActionForward();

		forward.setPath("productView.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
