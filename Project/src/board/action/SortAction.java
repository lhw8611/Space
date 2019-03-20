package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.ProductSortSvc;
import vo.ActionForward;

public class SortAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		String sort = request.getParameter("sort");
		
		
		ProductSortSvc productSortSvc = new ProductSortSvc();
		productSortSvc.productSortSvc(sort);
		
		return forward;
	}

}
