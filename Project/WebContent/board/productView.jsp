<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 <%
	String id = null;
	
	if(session.getAttribute("id")!=null){
		id=(String)session.getAttribute("id");
				
	}
 %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#listForm {
width:1000px;
margin:0 auto;
}

#pro_img img{
	width:400px;
	height:400px;
	
}
#content_main {
}
#pro_img {
float:left;
margin:0 30px;
}
</style>
</head>
<body>
<jsp:include page="../top_menu.jsp"></jsp:include>
	<div id="container">
		<div id="main">

	<section id="listForm">
	
		<h2>${probean.pro_name}의상세정보</h2>
		<section id="content_main">
		<div id="pro_img">
				<img src="/Project/boardUpload/${probean.pro_image}" />
		</div>
			<div id="pro_detail">
			<form action="/Project/orderForm.od" name="orderForm" method="post">주문하기
				<table>
					<tr>
						<td>상품명 </td>
						<td>${probean.pro_name}<input type="hidden" id="pro_code" name="pro_code" value="${probean.pro_code}"/></td>
					</tr>
					<tr>
						<td>상품 가격</td><td>${probean.pro_price }</td>
					</tr>
					<tr>
						<td> 수량 </td><td><input type="text" id="qty" name="qty" value="1"></td>
					<tr>
						<td>카테고리</td><td>${probean.pro_category }</td>
					</tr>
					<tr><td>
					<input type="hidden" id="type" name="type" value="one"/></td></tr>
				</table>
				<input type="submit" value="주문하기">
			</form>
			</div>
			<div style="clear: both"></div>
			<nav id="commandList">
				<a href="/Project/dogList.dog">쇼핑 계속하기</a> 
				<a href="#" onclick="orderForm.action='/Project/cartAdd.od';orderForm.submit();"> 장바구니에 담기</a>
			</nav>
		</section>
		</section>
		<jsp:include page="../footer.jsp"></jsp:include>
			</div>
	</div>
</body>
</html>


