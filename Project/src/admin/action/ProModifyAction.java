package admin.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import admin.svc.ProModifySvc;
import board.svc.ProductWriteSvc;
import vo.ActionForward;
import vo.ProductBean;

public class ProModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ProductBean probean = new ProductBean();
		int updateCheck = 0;
		
		String realFolder = "";
		String saveFolder = "/boardUpload";
		int fileSize = 5 * 1024 * 1024;
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
				new DefaultFileRenamePolicy());
		if (!multi.getParameter("category").equals("x")) {
			probean = new ProductBean();
			probean.setPro_code(Integer.parseInt(multi.getParameter("pro_code")));
			probean.setPro_name(multi.getParameter("subject"));
			probean.setPro_category(multi.getParameter("category"));
			probean.setPro_content(multi.getParameter("content"));
			probean.setPro_price(Integer.parseInt(multi.getParameter("price")));
//		proBean.setPro_image(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
			probean.setPro_image(multi.getFilesystemName("image"));

			ProModifySvc proModifySvc = new ProModifySvc();
			updateCheck = proModifySvc.proModifySvc(probean);

			if (updateCheck==0) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정 실패')");
				out.println("history.back();");
				out.println("</script>");
			} else {
				forward.setPath("qtyManagement.ad");
				forward.setRedirect(true);

			}
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('카테고리를 입력하세요')");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
