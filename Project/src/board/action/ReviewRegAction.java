package board.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import board.svc.ReviewRegSvc;
import vo.ActionForward;
import vo.ReviewBean;

public class ReviewRegAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ReviewRegSvc reviewReg = new ReviewRegSvc();
		ReviewBean reviewBean = new ReviewBean();
		String realFolder = "";
		String saveFolder = "/reviewImg";
		int fileSize = 5 * 1024 * 1024;
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
				new DefaultFileRenamePolicy());
		
		String mem_id = multi.getParameter("mem_id");
		int pro_code = Integer.parseInt(multi.getParameter("pro_code"));
		
		reviewBean.setMem_id(mem_id);
		reviewBean.setRev_star(Integer.parseInt(multi.getParameter("star")));
		reviewBean.setContent(multi.getParameter("content"));
		reviewBean.setRev_img(multi.getFilesystemName("img"));
		reviewBean.setPro_code(pro_code);
		reviewReg.ReviewReg(reviewBean);
		
//		forward.setPath("productView.bo?pro_code="+pro_code);
		forward.setPath("/Space/board/reviewRegCompleted.jsp");
		
		forward.setRedirect(true);
		return forward;
	}

}
