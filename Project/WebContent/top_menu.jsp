<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/top_menu.css" rel="stylesheet" type="text/css">


</head>
<body>
	<header>
		<div class="h-main">

			<div class="logo">
				<a href="main.jsp"><img src="images/logo.png"></a>
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
				<li><a href="qnaList.qna"><img src="icon/bell.png"></a></li>
				<li><a href="#"><img src="icon/cart.png"></a></li>
				<li><a href="loginForm.mem"><img src="icon/logout.png"></a></li>
				<li><a href="logout.mem"><img src="icon/login.png"></a></li>
				<li><a href="qnaList.qna">Q&A</a></li>
				<li><p><%=session.getAttribute("id") %>님 환영합니다</p></li>
			</ul>
		</div>
	</header>

</body>
</html>