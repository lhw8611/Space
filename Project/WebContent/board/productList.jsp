<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="vo.PageInfo"%>
<%@ page import="vo.ProductBean"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ArrayList<ProductBean> articleList = null;
	if ((ArrayList<ProductBean>) request.getAttribute("articleList") != null) {
		articleList = (ArrayList<ProductBean>) request.getAttribute("articleList");
	}
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	String sort = "new";
	
	if((String)request.getAttribute("sort")!=null) {
	sort = (String) request.getAttribute("sort");
	}
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	System.out.println(listCount);

	System.out.println("jsp에서 아티클 =============================:" + articleList);
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
	border-radius: 3px;
	s
}

#productImage:hover {
	opacity: 0.7;
}

container {
	height: 100vh;
}

.pro_grid>li {
	list-style: none;
	display: inline-block;
	margin: 10px;
	border: 0.5px solid #D9D9D9;
	border-radius: 4px;
}

#listForm {
	width: 1400px;
	margin: 0 auto;
}
/* .pro_detail {
	text-align: center;
} */
.pro_img>a {
	text-decoration: none;
}

#headerImage {
	width: 100%;
	height: 477px;
	background-image: linear-gradient(rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0.3)), url(headerImage/back04.jpg);
	background-position: 50% 50%;
	background-size: cover;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0;
}

#title {
	color: white;
	font-size: 1.5em;
	display: inline-block;
	position: absolute;
	margin: auto;
	top: 28%;
	left: 50%;
	transform: translate(-50%, -50%);
}

#category {
	float: right;
}

#sort {
	float: left;
	margin: 10px 10px;
	height: 30px;
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
	float: right;
	margin: 5px;
	margin-right: 140px;
	line-height: 40px;
}

#search input[type=text] {
	height: 30px;
	border: 1px solid #CCCCCC;
}

#search input[type=submit] {
	border: 1px solid #CCCCCC;
	height: 32px;
	width: 50px;
	line-height:2px;
	background-color: white;
}

#search table {
	border-spacing: 0;
}

/* 페이지 관련 */
#page a {
	text-decoration: none;
	color: black; 
}
#page {
	margin: 50px auto; 
	text-align: center;
	font-size: 1.5rem;
}

.pagebox {
	display: inline-block;
	color: #B2B2B2;
}

.pagebox>a {
	display: inline-block;
	text-align: center;
	font-size: 1.2rem;
	padding: 10px 20px;
	margin: 4px;
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

	<div id="container">
		<div id="main">
			<jsp:include page="../top_menu.jsp"></jsp:include>
			<div id="headerImage">
				<div id="title">
					<h1><a href="/Space/productList.bo">Product</a></h1>
				</div>
			</div>


			<section id="listForm">
				<!-- 				<div id="category">
					<ul>
						<li><a href="#">가구</a></li>
						<li><a href="#">캔들</a></li>
						<li><a href="#">조명</a></li>
						<li><a href="#">소품</a></li>
					</ul>
				</div> -->
				<div id="sortDiv">
					<form name="sortForm">
						<select id="sort" name="sort"
							onchange="sortForm.action='/Space/productList.bo';sortForm.submit();">
							<option value="new" <%if (sort.equals("new")) {%> selected <%}%>>최근
								등록일 순</option>
							<option value="count" <%if (sort.equals("count")) {%> selected <%}%>>조회수가
								많은 순</option>
							<%-- <option value="sell" <%if (sort.equals("sell")) {%> selected <%}%>>판매수가
								많은 순</option>
							<option value="review" <%if (sort.equals("review")) {%> selected <%}%>>구매
								후기가 많은 순</option> --%>
						</select>
					</form>
					<form action="proSearch.bo" id="search">
						<table>
							<tr>
								<td><input type="text" name="sW" required="required"></td>
								<td><input type="submit" value="검색"></td>
							</tr>
						</table>
					</form>
				</div>

				<div style="clear: both;"></div>
				<%
					if (articleList.size() != 0) {
				%>
				<ul class="pro_grid">
					<%
						for (int i = 0; i < articleList.size(); i++) {
					%>
					<li>
						<div class="pro_img">
							<a
								href="/Space/productView.bo?pro_code=<%=articleList.get(i).getPro_code()%>">
								<img src="/Space/boardUpload/<%=articleList.get(i).getPro_image()%>"
								id="productImage" />
							</a>
						</div>
						<div class="pro_detail">
							<%=articleList.get(i).getPro_name()%><br>
							<%=articleList.get(i).getPro_price()%>원<br>
						</div>
					</li>
					<%
						}
						} else {
					%>
					<h1 style="text-align: center; margin: 100px auto;">상품이 없습니다.</h1>

					<%
						}
					%>
				</ul>
			</section>

			<!-- 페이지 -->
			<section id="page">
					<%
						if (nowPage <= 1) {
					%>
					<div class="pagebox"><</div>
					<%
						} else {
					%>
					<!-- [<] 버튼 -->
					<a href="/Space/productList.bo?page=<%=nowPage - 1%>">
						<div class="pagebox"><</div>
					</a>
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
					<a href="/Space/productList.bo?page=<%=a%>">
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

					<a href="/Space/productList.bo?page=<%=nowPage + 1%>">
						<div class="pagebox">></div>
					</a>

					<%
						}
					%>


				</section>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>