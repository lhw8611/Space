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
table {
	width: 100%;
}
</style>
</head>
<body>

	<section id="listForm">
		<table>
			<tr class="tableTitle">
				<td>No</td>
				<td>제목</td>
				<td>작성시간</td>
				<td>조회수</td>
			</tr>
			<%
				for (int i = 0; i < articleList.size(); i++) {
			%>
			<tr>
				<td><%=articleList.get(i).getNo_num()%></td>
				<td><a href="noticeDetail.bo?no_num=<%=articleList.get(i).getNo_num()%>&page=<%=nowPage%>"> <%=articleList.get(i).getNo_title()%></a></td>
				<td><%=articleList.get(i).getNo_date()%></td>
				<td><%=articleList.get(i).getNo_count()%></td>
			</tr>
			<%
				}
			%>

		</table>
		<a href="noticeWriteForm.bo">글쓰기</a>
	</section>

	<section id="page">
		<%
			if (nowPage <= 1) {
		%>
		[이전]&nbsp;
		<%
			} else {
		%>
		<a href="noticeList.bo?page=<%=nowPage - 1%>">[이전]</a>&nbsp;
		<%
			}
		%>

		<%
			for (int a = startPage; a <= endPage; a++) {
				if (a == nowPage) {
		%>
		[<%=a%>]
		<%
			} else {
		%>

		<a href="noticeList.bo?page=<%=a%>">[<%=a%>]
		</a>&nbsp;
		<%
			}
		%>
		<%
			}
		%>
		<%
			if (nowPage >= maxPage) {
		%>
		[다음]
		<%
			} else {
		%>
		<a href="noticeList.bo?page=<%=nowPage + 1%>">[다음]</a>
		<%
			}
		%>

	</section>

</body>
</html>