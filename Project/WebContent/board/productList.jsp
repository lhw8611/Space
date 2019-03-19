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
<title>상품 리스트</title>
<style>
table {
	width: 100%;
}
#productImage {
	width: 300px;
	height: 300px;
	border: none;
}
container {
height:100vh;
}
.pro_grid>li {

list-style: none;
display: inline-block;
margin:20px;
border:0.5px solid #D9D9D9;
border-radius: 4px;

} 
#listForm {
	width:1200px;
	margin:0 auto;
}
.pro_detail {
	text-align: center;
}
.pro_img>a {
	text-decoration: none;
} 
</style>
</head>	
<body>
	<div id="container">
		<div id="main">
	<jsp:include page="../top_menu.jsp"></jsp:include>
	<section id="listForm">
			<c:if test="${articleList!=null }">
			<ul class="pro_grid">
			<%for(int i=0; i<articleList.size(); i++) {
			%>
			<li>
			<div class="pro_img">
			<a href="/Project/productView.bo?pro_code=<%=articleList.get(i).getPro_code()%>">
			<img src="/Project/boardUpload/<%=articleList.get(i).getPro_image() %>" id="productImage"/>
			</a>
			</div>
			<div class ="pro_detail">
			상품명 : <%=articleList.get(i).getPro_name() %><br> 
			가격 : <%=articleList.get(i).getPro_price() %><br>
			</div>
			</li>
			<% 
			}
			%>
			</ul>
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
		<a href="/Project/productList.bo?page=<%=nowPage - 1%>">[이전]</a>&nbsp;
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

		<a href="/Project/productList.bo?page=<%=a%>">[<%=a%>]
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
		<a href="/Project/productList.bo?page=<%=nowPage + 1%>">[다음]</a>
		<%
			}
		%>

	</section>
	</div>
	</div>
 <jsp:include page="../footer.jsp"/> 
</body>
</html>