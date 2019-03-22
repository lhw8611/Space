<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.PageInfo"%>
<%@page import="vo.QnaBean"%>
<%@page import="java.util.*"%>
<%-- <%@page import="java.text.SimpleDateFormat"%> --%>


<%
	ArrayList<QnaBean> arryqna = (ArrayList<QnaBean>) request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
* {
	margin: 0;
	padding: 0;
}

#qnalistForm ul {
	display: inline-block;
	list-style-type: none;
}

#qnalistForm li {
	display: inline-block;
}

div {
	text-decoration: none;
}

#qnalistForm {
	width: 860px;
	margin: 0 auto;
}

#qnalistForm ul, li {
	width: 100%;
}

#qnalistForm dt {
	float: left;
}

#qnalistForm dl {
	width: 100%;
	overflow: hidden;
}

#qnalistForm dt {
	text-align: left;
	float : left;
	width: 100%;
	float: left;
	font-size: 16px;
	padding : 10px 20px;
}

#qnalistForm dd {
	text-align: left;
	float: left;
	width: 100%;
	background: #f2f2f2;
	padding : 20px 50px;
}
#qnalistForm dd p{
	width : 90%;
}
#qnalistForm dl a{
	text-decoration: none;
	font-size: 1.5rem;
}
#qnalistForm a, a:link{
	color : black;
}
#qnalistForm a:active{
	text-decoration: underline; 
}

.qna_title{
	margin: 10px 0 10px;
}

#pageList{
	text-align: center;
}
</style>
<title>Q&A</title>
</head>
<body>
	<jsp:include page="../top_menu.jsp" />
	<!-- 게시판 리스트 -->
	<section id="qnalistForm">
		<div class="qna_title">
		<h2>
			Q&A<a href="qnaWriteForm.qna">게시판글쓰기</a>
		</h2>
		</div>
		<br>


		<ul>
			<%
			if (arryqna != null && listCount > 0) {
				for (int i = 0; i < arryqna.size(); i++) {
			%>
				<li><dl>
					<dt><a href="#"><%=arryqna.get(i).getQna_question()%></a></dt>
					<dd><p><%=arryqna.get(i).getQna_answer()%></p></dd>
				</dl></li>
			<%
				}
			%>

		</ul>

		<%
			} else {
		%>
		<h2>등록된 글이 없습니다.</h2>
		<%
			}
		%>








		<section id="pageList">
			<%
				if (nowPage <= 1) {
					out.println("[이전]&nbsp;");
				} else {
					out.println("<a href='qnaList.qna?page=" + (nowPage - 1) + "'>[이전]</a>&nbsp");
				}
			%>

			<%
				for (int a = startPage; a <= endPage; a++) {
					if (a == nowPage) {
						out.println("[" + a + "]");
					} else {
						out.println("<a href='qnaList.qna?page=" + a + "'>[" + a + "]</a>&nbsp;");
					}
				}
			%>

			<%
				if (nowPage >= maxPage) {
					out.println("[다음]");
				} else {
					out.println("<a href='qnaList.qna?page=" + (nowPage + 1) + "'>[다음]</a>");
				}
			%>
		</section>
	</section>
</body>
</html>