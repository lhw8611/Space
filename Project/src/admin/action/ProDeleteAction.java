package admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.svc.ProDeleteSvc;
import vo.ActionForward;

public class ProDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ProDeleteSvc proDeleteSvc = new ProDeleteSvc();
		int check=0;
		
		int pro_code = Integer.parseInt(request.getParameter("pro_code"));
		check=proDeleteSvc.proDel(pro_code);
		
		if(check==0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패')");
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward.setPath("qtyManagement.ad");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
