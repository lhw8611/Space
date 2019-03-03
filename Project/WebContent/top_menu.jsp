<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/Project/css/top_menu.css" rel="stylesheet" type="text/css">

<%
	String id = null;
	
	if(session.getAttribute("id")!=null){
		id=(String)session.getAttribute("id");
				
	}
	/* else{
		out.println("<script>");
		out.println("location.href='loginForm.jsp'");
		out.println("</script>");
	} */
	
	System.out.println("top_menu.jsp String id = " + id);
%>
</head>
<body>
	<header>
		<div class="h-main">

			<div class="logo">
				<a href="main.jsp"><img src="/Project/images/logo.png"></a>
			</div>
			<!-- 	<ul>
						<li><a href="#">about us</a></li>
						<li><a href="#">조명</a></li>
						<li><a href="#">캔들</a></li>
						<li><a href="#">가구</a></li>
						<li><a href="#">소품</a></li>
						<li><a href="#">notice</a></li>
					</ul> -->
			<ul class="h-right">
				<!-- 오른쪽정렬 -->
				<li><a href="qnaList.qna"><img src="/Project/icon/bell.png"></a></li>
				<li><a href="#"><img src="/Project/icon/cart.png"></a></li>
				<li><a href="loginForm.mem"><img src="/Project/icon/logout.png"></a></li>
				<li><a href="logout.mem"><img src="/Project/icon/login.png"></a></li>
				<li><a href="qnaList.qna">Q&A</a></li>
				<li><p><%=session.getAttribute("id") %>님 환영합니다</p></li>
				<li><a href="memberinfo.mem?id=<%=id %>">회원정보 수정</a></li>
			</ul>
		</div>
	</header>

</body>
</html>