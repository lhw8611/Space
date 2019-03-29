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
<title>::Space:: 관리자 페이지 - 주문내역</title>
<style>
	#orderlistTable img {
		width:100px;
		height:100px;
	}
	#orderlistTable td {
		border-bottom:1px solid #CCCCCC;
		text-align: center;
		padding:10px 0;
	}
	select {
		height:30px;
		border-radius: 3px;
	}
	#table {
	width:1000px;
	margin:100px auto;
	
	}
	#container { 
	}
	#main {
	border:.5px solid #CCCCCC;
	background-color: white;
	width:1000px;
	margin:100px auto;
	}
	#orderlistTable {
	width: 900px;
	border-spacing: 0;
	}
	button {
	height:30px;
	width:55px;
	}
	
</style>
</head>
<body>
<jsp:include page="../top_menu.jsp"></jsp:include>
	<div id="container">
	<jsp:include page="adminSidebar.jsp"></jsp:include>
		<div id="main">
<div id="table">
	<form name="orderList" method="post">
	<h2 style="margin-left: 50px;">주문내역</h2>
	<table id="orderlistTable" style="margin:20px auto;">
		<tr>
			<td>주문날짜</td>
			<td>구매자 ID</td>
			<td>주문상품</td>
			<td>상품이름</td>
			<td>상품가격</td>
			<td>수량</td>
			<td>결제금액</td>
			<td>주문상태</td>
			<td>상태 변경</td>
		</tr>
		
		<%
		
			for (int i = 0; i < OrderList.size(); i++) {
		%>
		<tr>
			<td><%=OrderList.get(i).getOr_date()%></td>
			<td><%=OrderList.get(i).getMem_id()%></td>
			<td><img src="boardUpload/<%=OrderList.get(i).getPro_image()%>"></td>
			<td><%=OrderList.get(i).getPro_name()%></td>
			<td><%=OrderList.get(i).getPro_price()%>원</td>
			<td><%=OrderList.get(i).getOd_qty()%></td>
			<td><%=OrderList.get(i).getPro_price()*OrderList.get(i).getOd_qty()%>원</td>
			<td><%=OrderList.get(i).getOd_state()%></td>
			<td>
				<select name="state<%=i%>" style="color:gray">
					<option value="none" style="color:gray">선택</option>
					<option value="wait" style="color:black">배송 준비중</option>
					<option value="ing" style="color:black">배송 중</option>
						<option value="deleCom" style="color:black"> 배송 완료</option>
					<option value="exchangeWait" style="color:black">교환 대기</option>
					<option value="exchange" style="color:black">교환 완료</option>
					<option value="refundWait" style="color:black">환불 대기</option>
					<option value="refund" style="color:black">환불 완료</option>
					<option value="cancel" style="color:black"> 취소 대기</option>
				
				</select>
				
				<input type="hidden" name="od_num<%=i%>" value="<%=OrderList.get(i).getOd_num() %>">
				<input type="hidden" name="pro_code<%=i%>" value="<%=OrderList.get(i).getPro_code() %>">
				<button onclick="orderList.action='/Space/changeState.ad?index=<%=i %>';orderList.submit();">변경</button>
			</td>
			
		</tr>
		<%
			}
		%>
	</table>
	</form>
	</div>
	</div>
	</div>
		<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>