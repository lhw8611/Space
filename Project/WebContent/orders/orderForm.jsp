<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.MemberBean"%>
<%@ page import="vo.ProductBean" %>
<% MemberBean membean = (MemberBean)request.getAttribute("membean"); 
	ProductBean probean = (ProductBean)request.getAttribute("probean");
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

		
		<h1>구매자 정보</h1>
			<label for="buyer_name">이름</label>			
			<input type="text" id="buyer_name" name="buyer_name" value="<%=membean.getMem_name() %>"/>
			<br>
			<label for="buyer_email">이메일</label>				
			<input type="text" id="buyer_email" name="buyer_email" value=""/>
			<br>
			<label for="buyer_tel">전화번호</label>
			<input type="text" id="buyer_tel" name="buyer_tel"/>
			<br>
		<h1>받는사람정보</h1><button>배송지변경</button>		
			<label for="gain_name">이름</label>
			<input type="text" id="gain_name" name="gain_name"/>
			<br>
			<label for="gain_zip">배송주소</label>
			<input type="text" id="gain_zip" name="gain_zip"/>
			<input type="text" id="gain_add" name="gain_add"/>
			<br>
			<input type="text" id="gain_add2" name="gain_add2"/>
			<br>
			<label for="gain_zip">배송 요청사항</label>
			<input type="text" id="gain_add2" name="gain_add2"/>
		
		<h1>결제 상품</h1>
		<table>
			<tr>
				<td>총 상품가격</td>
				<td>포인트</td>
				<td>배송비</td>
				<td>총 결제금액</td>
				결제방법
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