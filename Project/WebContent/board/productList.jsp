<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="vo.PageInfo"%>
<%@ page import="vo.ProductBean"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ArrayList<ProductBean> articleList = (ArrayList<ProductBean>) request.getAttribute("articleList");
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
<title>Insert title here</title>
<style>
table {
	width: 100%;
}
#productImage {
	width: 150px;
	height: 150px;
	border: none;
}
</style>
</head>	
<body>

	<section id="listForm">
			<c:if test="${articleList!=null }">
			<table>
				<tr>
				<c:forEach var="pro" items="${articleList}" varStatus="status">
					<td>
					<a href="productView.bo?pro_code=${pro.pro_code}">
					<img src="../boardUpload/${pro.pro_image }" id="productImage"/>
					</a> <br>
					상품명 : ${pro.pro_name}<br> 
					가격 : ${pro.pro_price }<br>
					</td>
					<c:if test="${((status.index+1) mod 4)==0 }">
							
				</tr>
				<tr>
				</c:if>
				</c:forEach>
				</tr>
			</table>
		</c:if>
</section>

<!-- 페이지 -->
	<section id="page">
		<%
			if (nowPage <= 1) {
		%>
		[이전]&nbsp;
		<%
			} else {
		%>
		<a href="productList.bo?page=<%=nowPage - 1%>">[이전]</a>&nbsp;
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

		<a href="productList.bo?page=<%=a%>">[<%=a%>]
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
		<a href="productList.bo?page=<%=nowPage + 1%>">[다음]</a>
		<%
			}
		%>

	</section>

</body>
</html>