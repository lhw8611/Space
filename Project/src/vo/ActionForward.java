package vo;

public class ActionForward {
	
	private String path; //서블릿에서 요청 처리 후 포워딩 될 최종 뷰 페이지 url이 저장되는 변수
	private boolean redirect = false; //포워드 방식이 저장되는 변수 - false면 디스패치방식, true면 리다이렉트방식

	public String getPath() {
		return path;	
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

//	public ActionForward(String path, boolean redirect) {
//		this.path = path;
//		this.redirect = redirect;
//	}

}
