<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.OrOdProViewBean"%>
<%@page import="java.util.*"%>
<%
	ArrayList<OrOdProViewBean> OrderList = null;
	OrderList = (ArrayList<OrOdProViewBean>) request.getAttribute("OrderList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	img {
		width:100px;
		height:100px;
	}
	td {
		border:1px solid black;
	}
</style>
</head>
<body>
	<table>
		<tr>
			<td>주문번호</td>
			<td>주문날짜</td>
			<td>주문상품</td>
			<td>상품이름</td>
			<td>수량</td>
			<td>결제금액</td>
			<td>주문상태</td>
			<td>구매자 ID</td>
			<td>취소/환불</td>
		</tr>
		<%
			for (int i = 0; i < OrderList.size(); i++) {
		%>
		<tr>
			<td><%=OrderList.get(i).getOd_num()%></td>
			<td><%=OrderList.get(i).getOr_date()%></td>
			<td><img src="boardUpload/<%=OrderList.get(i).getPro_image()%>"></td>
			<td><%=OrderList.get(i).getPro_name()%></td>
			<td><%=OrderList.get(i).getOd_qty()%></td>
			<td><%=OrderList.get(i).getPro_price()%></td>
			<td><%=OrderList.get(i).getOr_state()%> <a href="#">변경</a></td>
			<td><%=OrderList.get(i).getMem_id()%></td>
			<td><a href="#" onclick="window.open('refund.ad?', '', 'width=300, height=200')">관리</a></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>