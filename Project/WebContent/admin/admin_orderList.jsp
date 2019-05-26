<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.OrOdProViewBean"%>
<%@page import="java.util.*"%>
<%@page import="vo.PageInfo"%>
<%
	ArrayList<OrOdProViewBean> OrderList = null;
	OrderList = (ArrayList<OrOdProViewBean>) request.getAttribute("OrderList");
	
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>::Space:: 관리자 페이지 - 주문내역</title>
<style>
a {
	text-decoration: none;
	color: black;
}
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
	#page {
	margin: 10px auto;
	text-align: center;
	font-size: 1.5rem;
}

.pagebox {
	display: inline-block;
	color: #B2B2B2;
	/* border: 1px solid gray;
	text-align: center;
	font-size: 1.2rem;
	padding: 10px 20px;
	margin: 4px; */
}

.pagebox>a {
	display: inline-block;
	text-align: center;
	font-size: 1.2rem;
	padding: 10px 20px;
	margin: 4px;
}
.pagebox a:hover {
	color: black;
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
	<section id="listForm">
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
	</section>
	<section id="page">
					<%
						if (nowPage <= 1) {
					%>
					<div class="pagebox"><</div>
					<!-- &nbsp; -->
					<%
						} else {
					%>
					
					<a href="/Space/admin_orderList.ad?page=<%=nowPage - 1%>">
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
					<a href="/Space/admin_orderList.ad?page=<%=a%>">
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

					<a href="/Space/admin_orderList.ad?page=<%=nowPage + 1%>">
						<div class="pagebox">></div>
					</a>

					<%
						}
					%>


				</section>
	</form>
	</div>
	</div>
	</div>
		<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>