<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section id="listForm">
		<h2>${probean.pro_name}의상세정보</h2>
		<section id="content_main">
			<section id="content_left">
				<img src="../boardUpload/${probean.pro_image}" />
			</section>

			<form action="../orders/orderForm.od">주문하기
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
					
				</table>
				<input type="submit" value="주문하기">
				<a href="cartList.od">장바구니</a>
			</form>
			
			<div style="clear: both"></div>
			<nav id="commandList">
				<a href="dogList.dog">쇼핑 계속하기</a> <a href="dogCartAdd.dog?id=${dog.id }">장바구니에
					담기</a>
					<a href="orderPay.od?pro_code=${probean.pro_code}">주문하기</a>
			</nav>
		</section>
</body>
</html>