<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="vo.CartProViewBean" %>
<%

ArrayList<CartProViewBean> cartList = (ArrayList<CartProViewBean>)session.getAttribute("cartList");
int cartResult = 0;
int shipping = 3000;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 리스트</title>
<script>
/* 	function checkAll(theForm) {
		if (theForm.remove.length == undefined) {
			theForm.remove.checked = theForm.allCheck.checked;
		} else {
			for (var i = 0; i < theForm.remove.length; i++) {
				theForm.remove[i].checked = theForm.allCheck.checked;
			}
		}
	}
 */

</script>
</head>
<body>
<table>
	3만원 이상 무료배송
	<tr>
		<td><input type="checkbox" id="allCheck" name="allCheck" onclick="checkAll(this.form)" /></td>
		<td>번호</td>
		<td>상품이미지</td>
		<td>상품명</td>
		<td>가격</td>
		<td>수량</td>
		<td>배송비</td>
	</tr>
<%-- 	<c:forEach var="cart" items="${cartList }" varStatus="status">
		<tr>
			<td><input type="checkbox" id="remove" name="remove" value="${cart.pro_code }" /></td>
			<td><img src="boardUpload/${cart.pro_image }" id="cartImage" width="100px"/></td>
			<td><input type="text" name="pro_name" id="pro_name" value="${cart.pro_name }"/></td>
			<td><input type="text" name="pro_price" id="pro_price" value="${cart.pro_price }"/></td>
			<td><input type="text" name="pro_price" id="pro_price" value="${cart.cart_qty}"/></td>
			<td><input type="text" name="delivery" id="delivery" value="<%=shipping%>"/></td>
		</tr>
	</c:forEach> --%>
	<%for(int i=0; i<cartList.size(); i++) {
		%>
		<tr>
			<td><%=i+1 %></td>
			<td><input type="checkbox" id="remove" name="remove" value="<%=cartList.get(i).getPro_code() %>" /></td>
			<td><img src="boardUpload/<%=cartList.get(i).getPro_image() %>" id="cartImage" width="100px"/></td>
			<td><input type="text" name="name" id="name" value="<%=cartList.get(i).getPro_name()%>"/></td>
			<td><input type="text" name="price" id="price" value="<%=cartList.get(i).getPro_price()%>원"/></td>
			<td><input type="text" name="qty" id="qty" value="<%=cartList.get(i).getCart_qty()%>"/></td>
			<td><input type="text" name="delivery" id="delivery" value="<%=shipping%>"/></td>
		</tr>
		<%
		//상품 전체 합계
		cartResult = cartList.get(i).getPro_price()*cartList.get(i).getCart_qty();
		}
	
		//상품 전체 금액이 3만원 이상일 경우 배송비 0원
		if(cartResult>=30000) {
			shipping = 0;
		}
		%>
		
	
	
</table>
	<div>
		<h2>총 상품가격 <%=cartResult %> + 배송비 <%=shipping %> = 총 주문금액 : <%=cartResult+shipping %>원</h2>
	</div>
</body>
</html>