package board.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import board.svc.ProductWriteSvc;
import vo.ActionForward;
import vo.ProductBean;

public class ProductWriteAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProductWriteAction");
		ActionForward forward = null;
		ProductBean proBean = null;

		String realFolder = "";
		String saveFolder = "/boardUpload";
		int fileSize = 5 * 1024 * 1024;
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
				new DefaultFileRenamePolicy());
		
		
		proBean = new ProductBean();
		proBean.setPro_name(multi.getParameter("name"));
		proBean.setPro_category(multi.getParameter("category"));
		proBean.setPro_content(multi.getParameter("content"));
		proBean.setPro_price(Integer.parseInt(multi.getParameter("price")));
//		proBean.setPro_image(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
		proBean.setPro_image(multi.getFilesystemName("image"));
		
		System.out.println("pro image:"+proBean.getPro_content());
		System.out.println("pro image:"+proBean.getPro_image());
		ProductWriteSvc productWriteSvc = new ProductWriteSvc();
		boolean isWriteSuccess = productWriteSvc.productWriteSvc(proBean);
		
		

		if (!isWriteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("productList.bo");
			
		}
		return forward;

	}
}
