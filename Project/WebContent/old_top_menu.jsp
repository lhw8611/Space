<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴 페이지</title>
<style>
body {
	position: relative;
	margin: 0;
}

header nav ul {
	border: 1px solid red;
	height: 60px;
	line-height: 60px;
	background: rgba(100, 100, 100, 0.7);
}

nav ul {
	margin: 0;
	padding-left: 0;
	text-align: center;
}

nav ul li:after {
	content: "";
	clear: both;
	color: black;
	text-decoration: none;
}

nav ul li {
	display: inline-block;
	font-size: 15px;
}

nav ul li a {
	color: black;
	text-decoration: none;
}

.left-menu {
	float: left;
	color: red;
	text-decoration: none;
	margin: auto 20px;
}

.right-menu {
	float: right;
	color: red;
	text-decoration: none;
	margin: auto 20px;
}
/* 
.dropdown{
	display : inline-block;
}

header nav ul li ul {
	display: none;
}

header nav ul li ul li{
	display : block;

}

header nav ul li :hover ul {
	display: block;
} */
</style>
</head>
<body>


	<nav>
		<ul>
			<li class="left-menu"><a href="#">마크 </a></li>
			<li><a href="#">about us</a></li>
			<li><a href="#">조명</a></li>
			<li><a href="#">캔들</a></li>
			<li><a href="#">가구</a></li>
			<li><a href="#">소품</a></li>
			<li><a href="#">notice</a></li>
			<li class="right-menu"><a href="#">장바구니</a></li>
			<li class="right-menu"><a href="#">로그인관련</a></li>
			<li class="right-menu"><a href="joinForm.mem">회원가입</a></li>
			<li class="right-menu"><a href="/Project/loginForm.mem">로그인</a></li>
			<li class="right-menu"><a href="logout.mem">로그아웃</a></li>
			<li class="right-menu"><a href="qnaList.qna">Q&A</a></li>
		</ul>
	</nav>




</body>
</html>