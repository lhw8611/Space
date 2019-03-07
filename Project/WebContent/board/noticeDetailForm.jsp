<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.NoticeBean"%>
<%@ page import="vo.PageInfo" %>
<%
	NoticeBean article = (NoticeBean)request.getAttribute("article");
	String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.banner-image {
	position: relative;
	width: 100%;
	height: 400px;
	background-image: url(images/1.png);
	background-size: 100% 100%;
}

.position_fixed {
	position: fixed;
	width: 100%;
	margin-top: 0px;
}

#title {
	width: 100%;
	float: left;
}

div>.subject {
	float: left;
	font-size: 50px;
	margin: 15px;
}

div>.date {
	float: right;
	font-size: 30px;
	margin: 20px;
	margin-top: 50px;
	font-size: 30px;
}

#container {
	margin: 50px auto;
	width: 1000px;
	border: 1px solid;
}

#content {
	margin: 50px;
	clear: both;
	height: 300px;
	clear: both;
}
</style>
</head>

<body>
	<header>
		<nav>
			<div class="banner-image">
				<div class="position_fixed">
					<jsp:include page="../top_menu.jsp" />
				</div>
			</div>
		</nav>
	</header>
	<section>

		<article id="container">
			<div id="title">
				<span class="subject"><%=article.getNo_title()%></span> <span class="date"><%= article.getNo_date()%></span>
			</div>
			<hr>
			<div id="content">

				<%=article.getNo_content() %> <br>
			</div>
			<hr>
			<div>
				<input type="button" value="목록" onclick='location.href="/Project/noticeList.bo?page=<%=nowPage %>"' />
			</div>
		</article>
	</section>
	<footer>
		<jsp:include page="../footer.jsp"></jsp:include>
	</footer>
</body>

</html>