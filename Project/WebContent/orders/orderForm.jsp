<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/* String  */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	border:1px solid black;
}
</style>
</head>
<body>

		
		<h1>받는 사람 정보</h1>
		<table>
			<tr>
				<td>구매자 이름</td>
				<td><input type="text" id="purchaser" name="purchaser" value="${membean.mem_name }"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" id="name" name="name" ></td>
			</tr>
			<tr>
				<td>연락처</td>
				<td><input type="text" id="tel" name="tel"></td>
			</tr>
			<tr>
				<td>배송 요청사항</td>
				<td><input type="text" id="remarks" name="remarks"></td>
			</tr>
			
		</table>
		
		<h1>결제 상품</h1>
		<table>
			<tr>
				<td>상품명</td>
				<td>가격</td>
				<td>수량</td>
				<td>적립</td>
			</tr>
			<tr>
				<td>${probean.pro_name }</td>
				<td>${probean.pro_price }</td>
				<td>${qty }</td>
				<td></td>
			</tr>
			
		</table>
		
		<h1>결제 정보</h1>
		<table>
			<tr>
				<td>총상품가격</td>
				<td>${probean.pro_price*qty }</td>
			</tr>
			<tr>
				<td>배송비</td>
				<td></td>
			</tr>
			<tr>
				<td>적립금 사용</td>
				<td></td>
			</tr>
			<tr>
				<td>총결제금액</td>
				<td></td>
			</tr>
			<tr>
				<td>결제방법</td>
				<td></td>
			</tr>
		</table>
		
		<a href="#">결제하기</a>
</body>
</html>