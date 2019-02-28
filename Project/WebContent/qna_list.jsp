<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.PageInfo"%>
<%@page import="vo.qna"%>
<%@page import="java.util.*"%>
<%-- <%@page import="java.text.SimpleDateFormat"%> --%>


<%
	ArrayList<qna> arryqna = (ArrayList<qna>) request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
%>

<jsp:include page="top_menu.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Q&A</title>
</head>
<body>

	<!-- 게시판 리스트 -->
	<section id="qnalistForm">
		<h2>
			Q&A<a href="qnaWriteForm.qna">게시판글쓰기</a>
		</h2>
		<br>


		<table>
			<%
				if (arryqna != null && listCount > 0) {
			%>
			<tr id="tr_top">
				<td>번호</td>
				<td>질문내용</td>
				<td>답변내용</td>
				<td>날짜</td>
			</tr>
			<%
				for (int i = 0; i < arryqna.size(); i++) {
			%>
			<tr>
				<td>▶ <%=arryqna.get(i).getQna_num()%></td>
				<td>▶ <%=arryqna.get(i).getQna_question()%></td>
				<td>▶ <%=arryqna.get(i).getQna_answer()%></td>
				<td>▶ <%=arryqna.get(i).getQna_date()%></td>

			</tr>
			<%
				}
			%>

		</table>
	</section>
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
</body>
</html>