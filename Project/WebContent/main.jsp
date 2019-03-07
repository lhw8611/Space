<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<style>
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
					<a href="#">GO TO SHOPPING</a>
				</div>
				<div style="clear: both"></div>
			</div>

			<div class="about">
				<h1 class="about_con about_h1">WHAT WE DO</h1>
				<P class="about_con about_p">홈페이지 내에서 작동하는 모든 기능들을 만들고 이해하는 것에 관심을 가지고
					투자해 왔습니다. 빠른 이해력과 경험을 바탕으로 모두가 불편함 없이 사이트를 이용할 수 있는 것이 목표입니다.</p>
				<img class="about_icon java" src="/Project/images/java.jpg"> <img
					class="about_icon jsp" src="/Project/images/jsp.png"> <img
					class="about_icon css" src="/Project/images/css.png"> <img
					class="about_icon mysql" src="/Project/images/mysql.png">
			</div>
		</div>

	</div>
		<jsp:include page="footer.jsp"/>
</body>
</html>