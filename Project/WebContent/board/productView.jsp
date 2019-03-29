<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="vo.ReviewBean"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.PageInfo" %>
<%
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	String id = null;
	ArrayList<ReviewBean> reviewList = null;
	float star = 0;
	float totalStar = 0;
	if (session.getAttribute("id") != null) {
		id = (String) session.getAttribute("id");

	}
	if ((ArrayList<ReviewBean>) request.getAttribute("reviewList") != null) {
		reviewList = (ArrayList<ReviewBean>) request.getAttribute("reviewList");
	}
	if((ArrayList<ReviewBean>) request.getAttribute("reviewList") != null) {
		for (int i = 0; i < reviewList.size(); i++) {
			star += reviewList.get(i).getRev_star();
			totalStar = star / reviewList.size();
		}
	}
	totalStar =Math.round(Math.round((totalStar*100)/100));
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#listForm {
	width: 1000px;
	margin: 50px auto;
}

#pro_img img {
	width: 500px;
	height: 500px;
	border: 1px solid #CCCCCC;
}

#content_main {
	
}

#pro_img {
	float: left;
	margin: 0 30px;
}

#container {
	margin: 12vh auto;
}

#review {
	margin: 0 auto;
	width: 900px;
	text-align: center;
}

#review img {
	width: 200px;
	height: 200px;
}

#detailTable td {
	border-bottom: 1px solid #cccccc;
	padding: 7px;
}

#detailTable {
	border-spacing: 0;
}

#pro_detail {
	float: left;
	width: 400px;
	height: 400px;
}

#orderbtn {
	border: 0px;
	background-color: #DD5850;
	color: white;
	width: 100px;
	height: 50px;
	cursor: pointer;
}

#listbtn {
	background-color: white;
	color: black;
	border: 1px solid black;
	width: 100px;
	height: 50px;
	cursor: pointer;
	margin-left: 5px;
}

#cartbtn {
	background-color: white;
	border: 1px solid red;
	width: 120px;
	height: 50px;
	color: #DD5850;
	cursor: pointer;
	margin-left: 4px;
}
#reviewDivision {
	border-top:1px solid gray;
	width:900px;
	min-height: 100px;
	padding-top:5px; 
}
#reviewId {
	font-size: medium;
}
#reviewDate{
	color:gray;
	font-size: small;
	margin-left: 25px;
}
#starImg { 
	width:30px;
	height:30px;
}
#reviewImg {
}
#reviewContent{
}
#pro_content {
width:1000px;
	height: 30vh;
	margin:0 auto;
	
}
#content_title {
	margin:50px;
}
#content {
	margin:50px;
}
#pro_detail>img {
width:20px;
height: 20px; 
margin-top:15px;
}
#qty {
	height: 30px;
	border:0.5px solid #cccccc;
	padding:5px;
	}
#writeReviewBtn {
border-radius:3px;
	border:0px;
	width:120px;
	height:50px;
/* 	 background-image: linear-gradient(to right, #3886FF 0%, #01c9ca 100%);	  */
	 background-color: #01c9ca; 
	color:white; 
	font-weight: 500;
	cursor: pointer;
}
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
	
</style>
</head>
<body>
	<jsp:include page="../top_menu.jsp"></jsp:include>
	<div id="container">
		<div id="main">

			<section id="listForm">

				
				<section id="content_main">
					<div id="pro_img">
						<img src="/Space/boardUpload/${probean.pro_image}" />
					</div>
					<div id="pro_detail">
						<%
						switch ((int)totalStar) {
						case (0):
						%>
						<img src="/Space/images/grayStar.png">
						<img src="/Space/images/grayStar.png">
						<img src="/Space/images/grayStar.png">
						<img src="/Space/images/grayStar.png">
						<img src="/Space/images/grayStar.png">
						<%
						break;
						case (1):
						%>
						<img src="/Space/images/yellowStar.png">
						<img src="/Space/images/grayStar.png">
						<img src="/Space/images/grayStar.png">
						<img src="/Space/images/grayStar.png">
						<img src="/Space/images/grayStar.png">
						<%
							break;
							case (2):
						%>
						<img src="/Space/images/yellowStar.png">
						<img src="/Space/images/yellowStar.png">
						<img src="/Space/images/grayStar.png">
						<img src="/Space/images/grayStar.png">
						<img src="/Space/images/grayStar.png">
						<%
							break;
							case (3):
						%>
						<img src="/Space/images/yellowStar.png">
						<img src="/Space/images/yellowStar.png">
						<img src="/Space/images/yellowStar.png">
						<img src="/Space/images/grayStar.png">
						<img src="/Space/images/grayStar.png">
						<%
							break;
							case (4):
						%>
						<img src="/Space/images/yellowStar.png">
						<img src="/Space/images/yellowStar.png">
						<img src="/Space/images/yellowStar.png">
						<img src="/Space/images/yellowStar.png">
						<img src="/Space/images/grayStar.png">
						<%
							break;
							case (5):
						%>
						<img src="/Space/images/yellowStar.png">
						<img src="/Space/images/yellowStar.png">
						<img src="/Space/images/yellowStar.png">
						<img src="/Space/images/yellowStar.png">
						<img src="/Space/images/yellowStar.png">
						<%
							break;

							}
						%>
						<form action="/Space/orderForm.od" id="orderForm" name="orderForm" method="post">
							<h2 style="margin-top:100px;">${probean.pro_name}</h2>
							<table id="detailTable">
								<tr>
									<td>상품명</td>
									<td>${probean.pro_name}<input type="hidden" id="pro_code"
										name="pro_code" value="${probean.pro_code}" /></td>
								</tr>
								<tr>
									<td>가격</td>
									<td>${probean.pro_price }</td>
								</tr>
								<tr>
									<td>수량</td>
									<td><input type="text" id="qty" name="qty" value="1"></td>
									<td><input type="hidden" id="type" name="type" value="one" /></td>
							</table>
							</div>
						<div id="btn">
						<input type="submit" id="orderbtn" value="주문하기" > 
							<input type="button" id="listbtn" value="목록으로" onclick="location.href='/Space/productList.bo'">
							<input type="button" id="cartbtn" value="장바구니에 담기" onclick="orderForm.action='/Space/cartAdd.od';orderForm.submit();">
						</form>
						
						</div>
					<div style="clear: both"></div>
					<nav id="commandList"></nav>
				</section>
			</section>
			<br><hr style="width:1000px; margin:0 auto;">
			<div id="pro_content">
				<div id="content_title"><h1>제품 상세</h1></div>
				<div id="content">${probean.pro_content}</div>
			</div>
			
			<br><hr style="width:1000px; margin:0 auto;">
			
			<!-- 리뷰 -->
			
			<div id="review">
			<div id="reviewtitle" style="float:left; width:900px; margin:50px auto;">
			 <div style="display:inline; float:left;"><h1>구매후기(<%if (reviewList != null){%><%= reviewList.size()%><% }else { %>0<%} %>)</h1></div>
			 <div style="float:right;"><input type="button" id="writeReviewBtn" value="구매후기 작성" onclick="location.href='reviewRegForm.bo?pro_code=${probean.pro_code}'"> </div>
			</div>
				 <% 	if (reviewList != null) {
						for (int i = 0; i < reviewList.size(); i++) {
				%>
				<div id="reviewDivision" style="clear:both;">
					<div id="reviewers_left" style="float:left; font-size: 0.5em; font-weight: lighter;">
					  	 <div id="reviewId"> <%=reviewList.get(i).getMem_id()%></div>
					  	<div id="reviewDate"><%=reviewList.get(i).getRev_date()%></div></div>
				  	<div id="reviewers_right">
					  	<div id="reviewStar" style="float:right; margin:10px;">
						<%
					  	switch(reviewList.get(i).getRev_star()) {
					  	case(1):
				  		%>
				  		<img src="/Space/images/yellowStar.png" style="width:20px; height:20px;">
				  		<img src="/Space/images/grayStar.png" style="width:20px; height:20px;">
				  		<img src="/Space/images/grayStar.png" style="width:20px; height:20px;">
				  		<img src="/Space/images/grayStar.png" style="width:20px; height:20px;">
				  		<img src="/Space/images/grayStar.png" style="width:20px; height:20px;">
						<%
				  		break;
					  	case(2):
				  		%>
				  		<img src="/Space/images/yellowStar.png" style="width:20px; height:20px;">
				  		<img src="/Space/images/yellowStar.png" style="width:20px; height:20px;">
				  		<img src="/Space/images/grayStar.png" style="width:20px; height:20px;">
				  		<img src="/Space/images/grayStar.png" style="width:20px; height:20px;">
				  		<img src="/Space/images/grayStar.png" style="width:20px; height:20px;">
						<%
					  		break;
					  	case(3):
					  		%>
					  		<img src="/Space/images/yellowStar.png" style="width:20px; height:20px;">
					  		<img src="/Space/images/yellowStar.png" style="width:20px; height:20px;">
					  		<img src="/Space/images/yellowStar.png" style="width:20px; height:20px;">
					  		<img src="/Space/images/grayStar.png" style="width:20px; height:20px;">
					  		<img src="/Space/images/grayStar.png" style="width:20px; height:20px;">
							<%	
					  		break;
					  	case(4):
					  		%>
					  		<img src="/Space/images/yellowStar.png" style="width:20px; height:20px;">
					  		<img src="/Space/images/yellowStar.png" style="width:20px; height:20px;">
					  		<img src="/Space/images/yellowStar.png" style="width:20px; height:20px;">
					  		<img src="/Space/images/yellowStar.png" style="width:20px; height:20px;">
					  		<img src="/Space/images/grayStar.png" style="width:20px; height:20px;">
							<%	
					  		break;
					  	case(5):
					  		%>
					  		<img src="/Space/images/yellowStar.png" style="width:20px; height:20px;">
					  		<img src="/Space/images/yellowStar.png" style="width:20px; height:20px;">
					  		<img src="/Space/images/yellowStar.png" style="width:20px; height:20px;">
					  		<img src="/Space/images/yellowStar.png" style="width:20px; height:20px;">
					  		<img src="/Space/images/yellowStar.png" style="width:20px; height:20px;">
							<%	
					  		break;
					  	}
					  	%>
					  	</div> 
					  	
					</div>
					<div id="reviewDetail" style="clear:both;">
					  <div id="reviewImg" style="float:left;"> 
					 <%if(reviewList.get(i).getRev_img()!=null){ %>
						 <img src="reviewImg/<%=reviewList.get(i).getRev_img()%>">
						 <%} %>
						 
					</div>
					<div id="reviewContent" style="float:left; margin:20px; font-size: 1.2em; font-weight: 500;">
						<%=reviewList.get(i).getContent()%>
					</div>
				</div>
			</div>

			
			<%
				}
				} else {
			%>
			<div>리뷰가 없습니다</div>
			<%
				}
			%>
			</div>
				<section id="page">
					<%
						if (nowPage <= 1) {
					%>
					<div class="pagebox"><</div>
					<!-- &nbsp; -->
					<%
						} else {
					%>
					<a href="/Space/productView.bo?page=<%=nowPage - 1%>&pro_code=${probean.pro_code }#review">
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
					<a href="/Space/productView.bo?page=<%=a%>&pro_code=${probean.pro_code }#review">
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

					<a href="/Space/productView.bo?page=<%=nowPage + 1%>&pro_code=${probean.pro_code }#review">
						<div class="pagebox">></div>
					</a>

					<%
						}
					%>


				</section>
			
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>


