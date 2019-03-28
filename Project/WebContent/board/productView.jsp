<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="vo.ReviewBean"%>
<%@ page import="java.util.*"%>
<%
	String id = null;
	ArrayList<ReviewBean> reviewList = null;
	if (session.getAttribute("id") != null) {
		id = (String) session.getAttribute("id");

	}
	if ((ArrayList<ReviewBean>) request.getAttribute("reviewList") != null) {
		reviewList = (ArrayList<ReviewBean>) request.getAttribute("reviewList");
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#listForm {
	width: 1000px;
	margin: 0 auto;
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
	background-color: red;
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
	color: red;
	cursor: pointer;
	margin-left: 4px;
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
						<img src="/Project/boardUpload/${probean.pro_image}" />
					</div>
					<div id="pro_detail">
						<form action="/Project/orderForm.od" id="orderForm" name="orderForm" method="post">
							<h2 style="margin-top:100px;">${probean.pro_name}</h2>
							<table id="detailTable">
								<tr>
									<td>상품명</td>
									<td>${probean.pro_name}<input type="hidden" id="pro_code"
										name="pro_code" value="${probean.pro_code}" /></td>
								</tr>
								<tr>
									<td>상품 가격</td>
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
							<input type="button" id="listbtn" value="목록으로" onclick="location.href='/Project/productList.bo'">
							<input type="button" id="cartbtn" value="장바구니에 담기" onclick="orderForm.action='/Project/cartAdd.od';orderForm.submit();">
						</form>
						
						</div>
					<div style="clear: both"></div>
					<nav id="commandList"></nav>
				</section>
			</section>
			<br><hr style="width:1000px; margin:0 auto; color:#CCCCCC;">
			<div id="review">
			<div id="reviewtitle" style="float:left; width:900px; margin:50px auto;">
			 <div style="display:inline; float:left;"><h1>구매후기(<%if (reviewList != null){%><%= reviewList.size()%><% }else { %>0<%} %>)</h1></div>
			 <div style="float:right;"><input type="button" id="writeReviewBtn" value="구매후기 작성" onclick="location.href='reviewRegForm.bo?pro_code=${probean.pro_code}'"> </div>
			</div>
				 <% 	if (reviewList != null) {%>
				<%
				
						for (int i = 0; i < reviewList.size(); i++) {
				%>
				<div>
					<span><%=reviewList.get(i).getMem_id()%> <%=reviewList.get(i).getRev_date()%>
					</span> <span> <img src="reviewImg/<%=reviewList.get(i).getRev_img()%>">
					</span>
				</div>
				<div>
					<span><%=reviewList.get(i).getRev_star()%></span> <span><%=reviewList.get(i).getContent()%></span>
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
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>


