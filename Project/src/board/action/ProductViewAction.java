package board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import board.svc.ProductViewSvc;
import vo.ActionForward;
import vo.ProductBean;

public class ProductViewAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductViewSvc proViewSvc = new ProductViewSvc();
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		ProductBean probean = proViewSvc.getProView(pro_code);
		
//		ArrayList<ProductBean> probeanlist = new ArrayList<ProductBean>();
//		probeanlist.add(probean);
		
		request.setAttribute("probean", probean);
		ActionForward forward = new ActionForward();

		forward.setPath("/board/productView.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
