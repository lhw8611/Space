<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
<style>
#page {
	width:100%;
	height:100vh;
	background-color: #F8F8F8;
}


#container {
	width: 1000px;
	margin: 0 auto;
	
}

article {
	width: 800px;
	margin:100px auto;
	border:0.5px solid #CCCCCC;
	border-radius:2px;
	background-color: #fff;
	padding:80px;
}

/* 제목 */
#title input[type=text] {
	width: 95%;
	margin: 5px auto;
	height: 30px;
	background: #fff;
	border-radius: 4px;
	padding: 0 10px;
}

#title input[type=text]:hover {
	opacity: 0.8;
}

/* textarea */
#content textarea {
width:95%;
	padding: 5px 10px;
	margin : 5px auto;
	
	border-radius: 4px;
	margin: 5px 0;
}

#content:hover {
	opacity: 0.8;
}

/* 다시작성 */
input[type="reset"] {
	width: 100px;
	height: 40px;
	background: #494948;
	border: 1px solid #fff;
	cursor: pointer;
	border-radius: 5px;
	color: #fff;
	font-size: 16px;
	font-weight: 400;
	padding: 6px;
	margin-top: 10px;
}

input[type=reset]:hover {
	opacity: 0.8;
}

input[type=reset]:active {
	opacity: 0.6;
}

input[type=reset]:focus {
	outline: none;
}

/* 작성하기 */
input[type=submit] {
	
	width: 100px;
	height: 40px;
	background: #0082FC;
	border: 1px solid #fff;
	cursor: pointer;
	border-radius: 5px;
	color: #fff;
	font-size: 16px;
	font-weight: 400;
	padding: 6px;
	margin-top: 10px;
}

input[type=submit]:hover {
	opacity: 0.8;
}

input[type=submit]:active {
	opacity: 0.6;
}

input[type=submit]:focus {
	outline: none;
}

#submit {
	float: right;
}

form {
	margin: 70px auto;
	
}
</style>
</head>
<body id="page">
	<jsp:include page="../top_menu.jsp"></jsp:include>
	<section id="container">

		

		<article id="wrtieform">
		<h2>공지사항 작성</h2>
			<hr>
			<form action="/Space/noticeWritePro.bo" method="post">
				<div id="title">
					<input type="text" name="title" id="title" autofocus="autofocus" size="20"
						required="required" placeholder="제목" />
				</div>
				<div id="content">

					<textarea name="content" id="content" cols="50" rows="20" placeholder="내용"></textarea>
				</div>
				<div>
					<span><input type="reset" value="다시작성" id="reset" /></span> <span
						id="submit"><input type="submit" value="작성하기" id="write" /></span>
				</div>
			</form>
		</article>
		<div style="clear: both;"></div>
	</section>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>