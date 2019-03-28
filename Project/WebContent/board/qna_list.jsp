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
	
	String id =	"";
	if((String)session.getAttribute("id")!=null) {
		id=(String)session.getAttribute("id");
	}
	
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
	margin: 120px auto;
}

#qnalistForm ul, #qnalistForm li {
	width: 100%;
	margin-bottom: 40px;
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
	float: left;
	width: 100%;
	float: left;
	font-size: 16px;
	padding: 10px;
}

#qnalistForm dd {
	text-align: left;
	float: left;
	width: 100%;
	background: #f2f2f2;
	padding: 20px;
}

#qnalistForm dd p {
	width: 90%;
}

#qnalistForm dl a {
	text-decoration: none;
	font-size: 1.5rem;
}

#qnalistForm a, #qnalistForm a:link {
	color: black;
}

#qnalistForm a:active {
	text-decoration: underline;
}

.qna_title {
	font-size: 2em;
	display: inline-block;
	position: absolute;
	margin: auto;
	top: 28%;
	left: 50%;
	transform: translate(-50%, -50%);
}

#pageList {
	text-align: center;
}

#headerImage {
	width: 100%;
	height: 477px;
	background-image: linear-gradient(rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0.3)),
	url('headerImage/back08.jpg'); 
	background-position: 50% 50%;
	background-size: cover;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0;
}

#writeBtn {
	width: 100px;
	height: 35px;
	background-color: #0082FC;
	border-radius: 3px;
	border : 0px;
	text-align: center;
	color: white;
	padding-top: 7px;
	margin-bottom: 40px;
	font-size : 17px;
	cursor: pointer;
}
.qna_title a{
	color : rgba(255, 255, 255, 0.8);
	text-decoration: none;
}
.qna_title a:hover{
	color : white;
}

</style>
<title>QNA</title>
</head>
<body>
	<jsp:include page="../top_menu.jsp" />
	<!-- 게시판 리스트 -->
	<div id="container">
		<div id="main">

			<div id="headerImage">
				<div class="qna_title">
					<h2>
						<a href="/Project/qnaList.qna">QNA</a>
						<%
						if (session.getAttribute("id") != null && session.getAttribute("id").equals("admin")) {
					%>
						<%
							}
						%>
					</h2>
				</div>
			</div>
			<section id="qnalistForm">
				<br>


				<ul>
					<%
						if (arryqna != null && listCount > 0) {
							for (int i = 0; i < arryqna.size(); i++) {
					%>
					<li><dl>
							<dt>
								<a href="#"><%=arryqna.get(i).getQna_question()%></a>
								<%
									if (session.getAttribute("id") != null && session.getAttribute("id").equals("admin")) {
								%>
								<a href="qnaDelete.qna?qna_num=<%=arryqna.get(i).getQna_num()%>"
									style="font-size: 10px;">삭제하기</a> <a
									href="qnaModifyForm.qna?qna_num=<%=arryqna.get(i).getQna_num()%>"
									style="font-size: 10px;">수정하기</a>
								<%
									}
								%>
							</dt>
							<dd>
								<p><%=arryqna.get(i).getQna_answer()%></p>
							</dd>
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
				<%
					if (id.equals("admin")) {
				%>
				<div>
					<button type="button" id="writeBtn" onClick="javascript:location.href='qnaWriteForm.qna';">글쓰기</button>
				</div>
				<%
					}
				%>
			</section>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>