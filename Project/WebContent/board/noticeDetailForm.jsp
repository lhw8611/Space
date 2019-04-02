<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.NoticeBean"%>
<%@ page import="vo.PageInfo"%>
<%
	NoticeBean article = (NoticeBean) request.getAttribute("article");
	String nowPage = (String) request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#headerImage {
	width: 100%;
	height: 477px;
	background-image: url('headerImage/back01.jpg');
	background-position: 50% 50%;
	background-size: cover;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0;
}

.title {
	color: white;
	font-size: 2em;
	display: inline-block;
	position: absolute;
	margin: auto;
	top: 28%;
	left: 50%;
	transform: translate(-50%, -50%);
}

.title a {
	color: white;
	text-decoration: none;
}

.subject {
	display: inline-block;
	text-align: left;
	font-size: 24px;
	padding-left: 40px;
}

.date {
	text-align: left;
	font-size: 15px;
	float: right;
	display: inline-block;
	margin-top : -50px;
}

.content {
	border-radius: 3px;
	background: white;
	width: 860px;
	margin: 120px auto 0;
	min-height: 450px;
	border: 1px solid silver;
	box-sizing: border-box;
}

.content2 {
	padding: 60px 40px;
	border-top: 1px solid silver;
}
.name{
	height: 39px;
}
.inpb{
	    font-family: "Nanum Gothic";
    font-weight: 700;
    text-transform: uppercase;
    outline: 0;
    background: white;
    width: 100px;
    border: 1px solid #333;
    border-radius : 3px;
    padding: 10px;
    padding-top: 10px;
    padding-right: 10px;
    padding-bottom: 10px;
    padding-left: 10px;
    color: black;
    font-size: 14px;
    cursor: pointer;
    margin-top: 5px;
}
.position{
	width : 860px;
	margin : 0 auto;
}
.btn_position{
	float : right;
}
</style>
</head>

<body>
	<jsp:include page="../top_menu.jsp" />

	<div id="container">
		<div id="main">
			<div id="headerImage">

				<div class="title">
					<h2>
						<a href="/Space/noticeList.bo?page=<%=nowPage%>">Notice</a>
					</h2>
				</div>
			</div>
			<div class="content">
				<div class="name">
					<span class="subject"><%=article.getNo_title()%></span>  
				</div>
				<div class="content2">
				<p
						class="date"><img src="/Space/icon/time.png"
						style="height: 15px;"> <%=article.getNo_date()%></p>
					<%=article.getNo_content()%>
				</div>
			</div>
			<div class="position">
				<div class="btn_position">
				<input type="button" value="목록으로" class="inpb"
					onclick='location.href="/Space/noticeList.bo?page=<%=nowPage%>"' style="margin:30px 0;"/>
					</div>
					</div>
		</div>
	</div>


	<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>