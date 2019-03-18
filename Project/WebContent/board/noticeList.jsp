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
	System.out.println(articleList.size());
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
	border: 1px solid gray;
	text-align: center;
	font-size: 1.2rem;
	padding: 10px 20px;
	margin: 4px;
}

#button {
	border: 1px solid black;
	text-align: center;
}


#backImage {
	width:1900px;
	height:477px;
	background-image:
		url('https://cdn.imweb.me/thumbnail/20171218/5a37485dd02b9.jpg');
	background-position: 50% 50%;
	background-size: cover;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0; 
}

h1 {
color: rgba(255,255,255,0.9);
font-size: 4em;

   
}
#title {
display:inline-block;
    position: absolute;
 margin:auto;
     top: 35%;
    left: 50%;
    transform: translate(-50%, -50%);
} 
</style>
</head>
<body>
	<jsp:include page="../top_menu.jsp" />

	<div id="container">
		<div id="main">
			<div id="backImage">
			<div id="title"><h1>Notice</h1></div>
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
								href="/Project/noticeDetail.bo?no_num=<%=articleList.get(i).getNo_num()%>&page=<%=nowPage%>">
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
					<div class="pagebox">이전</div>
					<!-- &nbsp; -->
					<%
						} else {
					%>
					<a href="/Project/noticeList.bo?page=<%=nowPage - 1%>">
						<div class="pagebox">이전</div>
					</a>
					<!-- &nbsp; -->
					<%
						}
					%>

					<%
						for (int a = startPage; a <= endPage; a++) {
							if (a == nowPage) {
					%>
					<div class="pagebox" style="background-color: gray">
						<%=a%>
					</div>
					<%
						} else {
					%>
					<a href="/Project/noticeList.bo?page=<%=a%>">
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
					<div class="pagebox">다음</div>
					<%
						} else {
					%>

					<a href="/Project/noticeList.bo?page=<%=nowPage + 1%>">
						<div class="pagebox">다음</div>
					</a>

					<%
						}
					%>


				</section>
				<div id="button">
					<a href="/Project/noticeWriteForm.bo">글쓰기</a>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="../footer.jsp" />
</body>
</html>