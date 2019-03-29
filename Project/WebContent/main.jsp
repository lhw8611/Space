<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<%-- <link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css"> --%>
<style>

* {
	margin: 0;
	padding: 0;
}

.main_content{ /*메인 배너*/
	background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)),
	url(images/banner1.jpg);
	height: 100vh;
	background-size: cover;
	background-position: top;
	background-repeat: no-repeat;
}

.h-title {
	text-align: center;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	color: rgba(255, 255, 255, 0.8);
}

.h-title2 {
	position: absolute;
	top: 60%;
	left: 50%;
	transform: translate(-50%, -50%);
	border: 1px solid #fff;
	color: rgba(255, 255, 255, 0.8);
	font-size: 2rem;
	padding: 20px;
}

.h-title2 a {
	text-decoration: none;
	color: rgba(255, 255, 255, 0.7);
}

.about {
	background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)),
		url(images/about_us.jpg);
	height: 100vh;
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
	background-color: rgb(234, 234, 234);
}

.about_con { /* about us content option */
	color: rgba(255, 255, 255, 0.8);
	left: 50%;
	transform: translate(-50%, -50%);
	text-align: center;
	position: absolute;
}

.about_h1 {
	top: 130%;
}

.about_p {
	color: rgba(255, 255, 255, 0.8);
	top: 135%;
}

.about_icon {
	top: 165%;
	transform: translate(-50%, -50%);
	position: absolute;
	width: 10rem;
}

.java {
	left: 35%;
}

.jsp {
	left: 45%;
}

.css {
	left: 55%;
}

.mysql {
	left: 65%;
}
</style>


</head>
<body>
	<div id="container">
		<div id="main">
			<jsp:include page="top_menu.jsp" />

			<div class="main_content">
				<div class="h-title">
					<h1>ARE YOU READY?</h1>
					<p>do you wanna the best interior accessories?</p>
				</div>
				<div class="h-title2">
					<a href="/Space/productList.bo">GO TO SHOPPING</a>
				</div>
				<div style="clear: both"></div>
			</div>

			<div class="about" id="about">
				<h1 class="about_con about_h1">WHAT WE DO</h1>
				<P class="about_con about_p">홈페이지 내에서 작동하는 모든 기능들을 만들고 이해하는 것에 관심을 가지고
					투자해 왔습니다. 빠른 이해력과 경험을 바탕으로 모두가 불편함 없이 사이트를 이용할 수 있는 것이 목표입니다.</p>
				<img class="about_icon java" src="<%=request.getContextPath()%>/images/java.jpg"> <img
					class="about_icon jsp" src="<%=request.getContextPath()%>/images/jsp.png"> <img
					class="about_icon css" src="<%=request.getContextPath()%>/images/css.png"> <img
					class="about_icon mysql" src="<%=request.getContextPath()%>/images/mysql.png">
			</div>
		</div>

	</div>
		<jsp:include page="footer.jsp"/>
</body>
</html>