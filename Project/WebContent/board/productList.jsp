<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="vo.PageInfo"%>
<%@ page import="vo.ProductBean"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ArrayList<ProductBean> articleList = (ArrayList<ProductBean>) request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	String sort = (String)request.getAttribute("sort");
	
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	
	
	System.out.println(sort);
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
	width: 400px;
	height: 400px;
	border: none;
	border-radius: 3px;s
}
#productImage:hover {
	opacity: 0.7;
}
container {
height:100vh;
}
.pro_grid>li {

list-style: none;
display: inline-block;
margin:10px;
border:0.5px solid #D9D9D9;
border-radius: 4px;

} 
#listForm {
	width:1400px;
	margin:0 auto;
}
/* .pro_detail {
	text-align: center;
} */
.pro_img>a {
	text-decoration: none;
} 
#headerImage {
		width:1900px;
	height:477px;
	background-image:
		url('headerImage/back04.jpg');
	background-position: 50% 50%;
	background-size: 	cover;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0; 
}
#title {
	color:white;
	font-size:1.5em;
	display:inline-block;
    position: absolute;
 	margin:auto;
    top: 35%;	
    left: 50%;
    transform: translate(-50%, -50%);
}
#category {
	float:right;
}
#sort {
	float:left;
	margin:10px 10px;
	height:30px;
	border-radius: 3px;
	padding-left: 5px;
}
#category li {
	list-style-type: none;
	display: inline-block;
}
#sort li {
	list-style-type: none;
	display: inline-block;
}
#search {
float:right;
}
</style>
</head>	
<body>

	<div id="container">
		<div id="main">
	<jsp:include page="../top_menu.jsp"></jsp:include>
	<div id="headerImage">
	<div id="title"><h1>Product</h1></div>
	</div>
	
	
	<section id="listForm">
	<div id="category">
	<ul>
	<li><a href="#">가구</a></li>
	<li><a href="#">캔들</a></li>
	<li><a href="#">조명</a></li>
	<li><a href="#">소품</a></li>
	</ul>
	</div>
	<div id="sortDiv">
<!-- 	<ul>
		<li><a href="#">최신순</a></li>
		<li><a href="#">인기순</a></li>
		<li><a href="#">조회수 많은 순</a></li>
		<li><a href="#">가격이 낮은 순</a></li>
		<li><a href="#">판매수가 많은 순</a></li>
		<li><a href="#">구매 후기가 많은 순</a></li>
	</ul> -->
	<form name="sortForm">
		<select id="sort" name="sort" onchange="sortForm.action='/Project/productList.bo';sortForm.submit();">
			<option value="new"<%if(sort.equals("new")) {%>selected<%} %>>최근 등록일 순</option>
			<option value="count" <%if(sort.equals("count")) {%>selected<%} %>>조회수가 많은 순</option>
			<option value="sell" <%if(sort.equals("sell")) {%>selected<%} %>>판매수가 많은 순</option>
			<option value="review" <%if(sort.equals("review")) {%>selected<%} %>>구매 후기가 많은 순</option>
		</select>
	</form>
	<form name="search">
		<input type="text" name="search"><input type="button" value="검색">
	</form>
	</div>
	<div style="clear:both;"></div>
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
			<%=articleList.get(i).getPro_name() %><br> 
			<%=articleList.get(i).getPro_price() %>원<br>
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