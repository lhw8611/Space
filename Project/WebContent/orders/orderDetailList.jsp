<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="vo.OrOdProViewBean"%>
<%@page import="vo.MemberBean" %>
<%
	ArrayList<OrOdProViewBean> orderdetaillist = (ArrayList<OrOdProViewBean>) request
			.getAttribute("orderdetaillist");
	MemberBean membean = (MemberBean)request.getAttribute("membean");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.tg, .tg2 {
	border-collapse: collapse;
	border-spacing: 0;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 0px 10px;
	overflow: hidden;
	word-break: normal;
	height: 90px;
	border-bottom: 1px solid silver;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 0px 10px;
	overflow: hidden;
	word-break: normal;
	height: 90px;
	background-color: #F6F6F6;
	font-weight: bold;
	size: 16px;
	border-top: 2px solid black;
	border-bottom: 1px solid silver;
}

.tg2 td{
	height: 40px;
}


.input_section1{
box-sizing : border-box;
	width: 100%;
}
.input_section1 .tg2 td:first-child {
	background-color: #EAEAEA;
}
.input_section1 .tg3 td:first-child {
	background-color: #EAEAEA;
}
.get_info{
box-sizing : border-box;
	display : inline-block;
	margin : 0;
	padding : 0;
	width : 70%;
}
.order_info{
box-sizing : border-box;
display : inline-block;
margin : 0;
	padding : 0;
	width : 29%;
}
.tg .tg-s6z2 {
	text-align: center;
}

.tg .tg-uys7 {
	text-align: center;
}

.content {
	width: 1000px;
	margin: 0 auto;
}

.detail_title {
	  	color:white;
	font-size: 2em;
	display:inline-block;
    position: absolute;
 	margin:auto;
    top: 28%;	
    left: 50%;
    transform: translate(-50%, -50%);
}
.detail_title2{
	margin: 50px 0 10px;
}
table {
box-sizing : border-box;
	width: 100%;
	border: 0;
}

tr td img {
	width: 90px;
	height: 90px;
}

dt, dd {
	display: inline-block;
	margin-left: 20px;
}

dd {
	font-weight: bold;
}

li {
	display: inline-block;
}

.vertical_line {
	border-left: 2px solid #EAEAEA;
}

.detail_border {
	border: 5px solid #eff0f2;
	padding: 17px 0;
	margin-bottom: 15px;
}

.detail_delete {
	font-size: 12px;
	padding: 2px 8px;
	border: 1px solid silver;
	background: #EAEAEA;
	border-radius: 15px;
}

a {
	text-decoration: none;
	color: black;
}

.result {
	float: right;
	margin-top: 40px;
	margin-bottom: 15px;
	padding : 40px;
	border : 1px solid silver;
}

.result li {
	display: block;
	border-bottom: 1px solid black;
}
#headerImage {
		width:100%;
	height:477px;
	background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)),
	url('headerImage/back03.jpg');
	background-position: 50% 50%;
	background-size: 	cover;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0; 
}
</style>
</head>
<body>
	<%
		int cartResult = 0;
		int delivery = 2500;

		for (int i = 0; i < orderdetaillist.size(); i++) {
			cartResult += orderdetaillist.get(i).getPro_price() * orderdetaillist.get(i).getOd_qty();

		}
		if (cartResult >= 30000) {
			delivery = 0;
		}
	%>
	<jsp:include page="../top_menu.jsp" />

	<div id="container">
		<div id="main">
			<div id="headerImage">
				<div class="detail_title">
					<h2>주문상세정보</h2>
				</div>
				</div>
			<div class="content">
				<div class="detail_border">
					<ul>
						<li>
							<dl>
								<dt>주문일자</dt>
								<dd style="margin-right: 20px;">
									<%=orderdetaillist.get(0).getOr_date()%>
								</dd>
							</dl>
						</li>
						<li class="vertical_line">
							<dl>
								<dt>주문번호</dt>
								<dd class="ordnum"><%=orderdetaillist.get(0).getOr_num()%>
								</dd>
								<dd>
									<div class="detail_delete">
										<a href="#">내역삭제</a>
									</div>
								</dd>
							</dl>
						</li>
					</ul>
				</div>
				<table class="tg">
					<tr>
						<th class="tg-s6z2">상품정보</th>
						<th class="tg-uys7">상품이름</th>
						<th class="tg-uys7">상품금액(수량)</th>
						<th class="tg-uys7">상품 총 금액</th>
						<th class="tg-uys7">진행상태</th>
					</tr>
					<%
						for (int i = 0; i < orderdetaillist.size(); i++) {
					%>
					<tr>
						<td class="tg-s6z2"><a
							href="/Project/productView.bo?<%=orderdetaillist.get(i).getPro_code()%>"
							target="_blank"><img
								src="/Project/boardUpload/<%=orderdetaillist.get(i).getPro_image()%>"
								alt="<%=orderdetaillist.get(i).getPro_name()%>"></a></td>
						<td class="tg-uys7"><a
							href="/Project/productView.bo?<%=orderdetaillist.get(i).getPro_code()%>"
							target="_blank"><%=orderdetaillist.get(i).getPro_name()%></a></td>
						<td class="tg-uys7"><%=orderdetaillist.get(i).getPro_price()%>원, ( <%=orderdetaillist.get(i).getOd_qty() %> )</td>
						<td class="tg-uys7"><%=orderdetaillist.get(i).getPro_price()*orderdetaillist.get(i).getOd_qty()%></td>
						<td class="tg-uys7"><%if(orderdetaillist.get(i).getOd_state().equals("refund")){%>
								구매취소
						<%}else if(orderdetaillist.get(i).getOd_state().equals("wait")){%>		
						주문완료
						<%} %>
						</td>
					</tr>
					<%
						}
					%>



				</table>
			
				<div class="detail_title2">
					<h2>배송지 정보</h2>			
				</div>
				
				<div class="input_section1">
					<div class="get_info">
					<table class="tg tg2">
						<tr>
							<td class="tg-uys7">수령인</td>
							<td class="tg-uys7"><%=orderdetaillist.get(0).getOr_getname() %></td>
						</tr>
						<tr>
							<td class="tg-uys7">연락처</td>
							<td class="tg-uys7"><%=orderdetaillist.get(0).getOr_gettel()%></td>
						</tr>
						<tr>
							<td class="tg-uys7">배송지</td>
							<td class="tg-uys7"><%=orderdetaillist.get(0).getOr_getadd()%></td>
						</tr>
						<tr>
							<td class="tg-uys7">요청사항</td>
							<td class="tg-uys7"><%=orderdetaillist.get(0).getOr_request()%></td>
						</tr>
					</table>
					</div>
					
					<div class="order_info">
					<table class="tg tg2 tg3">
						<tr>
							<td class="tg-uys7"><strong>주문자정보</strong></td>
						</tr>
						<tr>
						<td class="tg-uys7">구매자 이름<br><%=membean.getMem_name() %></td>
						</tr>
						<tr>
						<td class="tg-uys7">구매자 전화번호<br><%=membean.getMem_tel() %> </td>
						</tr>
						<tr>
						<td class="tg-uys7">구매자 이메일<br><%=membean.getMem_email() %></td>
						</tr>
					</table>
					</div>
				</div>
				
				<div class="result">
					<ul>
						<li>상품금액 : <%=cartResult%>원
						</li>
						<li>포인트 : 0원</li>
						<li>배송비 : <%=delivery%>원
						</li>
						<li style="border : 0;">&nbsp;</li>
						<li><h3>
								결제금액 :
								<%=cartResult + delivery%>원
							</h3></li>
					</ul>
				</div>

			</div>

		</div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>