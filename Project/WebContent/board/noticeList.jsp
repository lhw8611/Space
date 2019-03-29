<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="vo.PageInfo"%>
<%@ page import="vo.NoticeBean"%>
<%@ page import="java.util.*"%>
<%
	ArrayList<NoticeBean> articleList = (ArrayList<NoticeBean>) request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	
	String id =	"";
	if((String)session.getAttribute("id")!=null) {
		id=(String)session.getAttribute("id");
	}
	//수정해보자
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a {
	text-decoration: none;
	color: black;
}

#board {
	width: 1000px;
	margin: 120px auto;
}

table {
	width: 1000px;
	margin: 0 auto;
	text-align: center;
	border-spacing: 0;
}

table td {
	/* border-top: 1px solid black; */
	border-bottom: 1px solid gray;
	margin: 0;
	height: 50px;
}

table tr:first-child td {
	border-top: 2px solid gray;
	border-bottom: 2px solid gray;
	margin: 0;
	height: 50px;
}

#title-left {
	text-align: left;
	padding-left: 50px;
}

#page {
	margin: 10px auto;
	text-align: center;
	font-size: 1.5rem;
}

.pagebox {
	display: inline-block;
	color: #B2B2B2;
	/* border: 1px solid gray;
	text-align: center;
	font-size: 1.2rem;
	padding: 10px 20px;
	margin: 4px; */
}

.pagebox>a {
	display: inline-block;
	text-align: center;
	font-size: 1.2rem;
	padding: 10px 20px;
	margin: 4px;
}

#writeBtn {
	width:100px;
	height:35px;
	background-color:#0082FC;
	border-radius:3px;
	text-align: center;
	color:white;
	padding-top: 7px;
	margin:30px 0;
}

.pagebox a:hover {
	color: black;
}

#backImage {
	width: 100%;
	height: 477px;
	background-image:
		url('https://cdn.imweb.me/thumbnail/20171218/5a37485dd02b9.jpg');
	background-position: 50% 50%;
	background-size: cover;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0;
}

#title h2 {
	color: rgba(255, 255, 255, 0.9);
	font-size: 48px;
}

#title {
	display: inline-block;
	position: absolute;
	margin: auto;
	top: 28%;
	left: 50%;
	transform: translate(-50%, -50%);
}
#title a{
	color : rgba(255, 255, 255, 0.8);
	text-decoration: none;
}
#title a:hover{
	color : white;
}
</style>
</head>
<body>
	<jsp:include page="../top_menu.jsp" />

	<div id="container">
		<div id="main">
			<div id="backImage">
			<div id="title"><h2><a href="/Space/noticeList.bo">Notice</a></h2></div>
			</div>
			<div id="board">
			
	 			
				<section id="listForm">
					<table>
						<tr class="tableTitle" height="50px">
							<td width="70px">No</td>
							<td width="500px">제목</td>
							<td width="50px">조회수</td>
							<td width="120px">작성시간</td>

						</tr>
						<%
							for (int i = 0; i < articleList.size(); i++) {
						%>
						<tr>
							<td><%=articleList.get(i).getNo_num()%></td>
							<td id="title-left"><a
								href="/Space/noticeDetail.bo?no_num=<%=articleList.get(i).getNo_num()%>&page=<%=nowPage%>">
									<%=articleList.get(i).getNo_title()%></a></td>

							<td><%=articleList.get(i).getNo_count()%></td>
							<td><%=articleList.get(i).getNo_date()%></td>
						</tr>
						<%
							}
						%>

					</table>

				</section>

				<section id="page">
					<%
						if (nowPage <= 1) {
					%>
					<div class="pagebox"><</div>
					<!-- &nbsp; -->
					<%
						} else {
					%>
					<a href="/Space/noticeList.bo?page=<%=nowPage - 1%>">
						<div class="pagebox"><</div>
					</a>
					<!-- &nbsp; -->
					<%
						}
					%>

					<%
						for (int a = startPage; a <= endPage; a++) {
							if (a == nowPage) {
					%>
					<div class="pagebox" style="color:black;">
						<%=a%>
					</div>
					<%
						} else {
					%>
					<a href="/Space/noticeList.bo?page=<%=a%>">
						<div class="pagebox">
							<%=a%>
						</div>
					</a>
					<!-- &nbsp; -->
					<%
						}
					%>
					<%
						}
					%>
					<%
						if (nowPage >= maxPage) {
					%>
					<div class="pagebox">></div>
					<%
						} else {
					%>

					<a href="/Space/noticeList.bo?page=<%=nowPage + 1%>">
						<div class="pagebox">></div>
					</a>

					<%
						}
					%>


				</section>



				<%
					if (id.equals("admin")) {
				%>
				<a href="/Space/noticeWriteForm.bo"><div id="writeBtn"
						style="float: right;">글쓰기</div> </a>


				<%
					}
				%>


			</div>
		</div>
	</div>


	<jsp:include page="../footer.jsp" />
</body>
</html>