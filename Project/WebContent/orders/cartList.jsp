<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.CartProViewBean"%>
<%
	ArrayList<CartProViewBean> cartList = null;
	String id = null;

	if (session.getAttribute("id") != null) {
		id = (String) session.getAttribute("id");
	}

	//비로그인 상태
	if (session.getAttribute("id") == null) {
		cartList = (ArrayList<CartProViewBean>) session.getAttribute("cartList");
		//로그인 상태
	} else {
		cartList = (ArrayList<CartProViewBean>) session.getAttribute("cartListdb");
	}

	int cartResult = 0;
	int shipping = 3000;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 리스트</title>
</head>
<body>
	<form name="cartForm" method="post">
		<%
			if (cartList == null || cartList.size() == 0) {
		%>
		<h1>장바구니에 상품이 없습니다.</h1>
		<%
			} else {
		%>
		<table>
			<h3>3만원 이상 무료배송</h3>
			<tr>
				<td><input type="checkbox" id="checkAll" name="checkAll"
					onclick="javascript:CheckAll(this.form)" /></td>
				<td>번호</td>
				<td>상품이미지</td>
				<td>상품명</td>
				<td>가격</td>
				<td>수량</td>
			</tr>
			<%
				for (int i = 0; i < cartList.size(); i++) {
			%>
			<tr>
				<td><%=i + 1%></td>
				<td><input type="hidden" id="cart_num<%=i%>" name="cart_num<%=i%>" value="<%=cartList.get(i).getCart_num()%>" />
					<input type="checkbox" id="checklist" name="checklist" value="<%=cartList.get(i).getPro_code()%>" /></td>
				<td><img src="../boardUpload/<%=cartList.get(i).getPro_image()%>" id="cartImage" width="100px" /></td>
				<td><input type="text" name="name" id="name" value="<%=cartList.get(i).getPro_name()%>" /></td>
				<td><input type="text" name="price" id="price" value="<%=cartList.get(i).getPro_price()%>원" /></td>
				<td><input type="text" name="qty<%=i%>" id="qty<%=i%>" value="<%=cartList.get(i).getCart_qty()%>" /></td>
				<td><input type="button" value="수량 변경" onclick="cartForm.action='/Project/cartQtyChnage.od?index=<%=i%>';cartForm.submit();" /></td>
				<td><input type="button" value="삭제" onclick="cartForm.action='/Project/cartDelete.od?index=<%=i%>';cartForm.submit();" /></td>
			</tr>
			<%   //상품 전체 합계
						cartResult += cartList.get(i).getPro_price() * cartList.get(i).getCart_qty();
					}

				//상품 전체 금액이 3만원 이상일 경우 배송비 0원
					if (cartResult >= 30000) {
						shipping = 0;
					}
			%>
		</table>
		<div>
			<h2>
				총 상품가격
				<%=cartResult%>
				+ 배송비
				<%=shipping%>
				= 총 주문금액 :
				<%=cartResult + shipping%>원
			</h2>
		</div>
		<%
			}
		%>
		<button type="button"
			onClick="location.href='/Project/orderForm.od?type=sel'">선택
			결제하기</button>
		<button type="button"
			onClick="location.href='/Project/orderForm.od?type=all'">전체
			결제하기</button>
	</form>
	
	<script>
		var check = false;
		function CheckAll() {
			var chk = document.getElementsByName("checklist");
			if (check == false) {
				check = true;
				for (var i = 0; i < chk.length; i++) {
					chk[i].checked = true; //모두 체크
				}
			} else {
				check = false;
				for (var i = 0; i < chk.length; i++) {
					chk[i].checked = false; //모두 해제
				}
			}
		}
	</script>
</body>
</html>