<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
<
style>* {
	margin: 0;
	padding: 0;
	font-size: 15px;
}

h1 {
	font-size: 26px;
}

body {
	margin: 0;
	color: white;
}

.blur:before {
	content: "";
	position: fixed;
	left: 0;
	right: 0;
	z-index: -1;
	display: block;
	background-image: url("/Project/images/join.jpg");
	width: 100%;
	height: 100%;
	-webkit-filter: blur(5px);
	-moz-filter: blur(5px);
	-o-filter: blur(5px);
	-ms-filter: blur(5px);
	filter: blur(5px);
}

.blur{
	z-index: 0;
}
.position {
	width: 550px;
	margin: auto;
	padding-top : 140px;
}

.content {
	background-color: rgba(0, 0, 0, 0.7);
	padding: 10px 45px 45px 45px;
	margin-left: auto;
	margin-right: auto;
	border: white solid pink;
	max-width: 360px;
	box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
		rgba(0, 0, 0, 0.3);
}

.center {
	text-align: center
}

.width100 {
	width: 100%;
}

.width50 {
	width: 49%;
}

.content button, input[type=button], input[type=submit] {
	font-family: "Nanum Gothic";
	font-weight: 700;
	text-transform: uppercase;
	outline: 0;
	background: #abc;
	border: 0;
	padding: 13px;
	color: #FFFFFF;
	font-size: 16px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
}

.content input {
	border: 0;
	margin-bottom: 15px;
	padding: 15px;
	box-sizing: border-box;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="blur">
	<div class="position">
		<div class="content">
			<form name="loginform"
				action="<%=request.getContextPath()%>/loginProcess.mem"
				method="post" onsubmit="return checkForm(this)">

				<div class="center">
					<a href="<%=request.getContextPath()%>/main.jsp"> <img
						src="<%=request.getContextPath()%>/images/logo.png" width="150px"></a>
					<hr color="#abc">
					<h1>회원가입</h1>
				</div>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id" class="width100"/>

				<label for="pass">비밀번호 : </label>
				<input type="password" name="pass" id="pass" class="width100"/>
				
				<button type="submit" class="width50">로그인</button>
				<button onclick="javascript:location.href='joinForm.mem';" class="width50">회원가입</button>
				<a href="<%=request.getContextPath() %>/joinForm.mem">회원가입</a>
			</form>
		</div>
	</div>
	</div>
</body>
</html>