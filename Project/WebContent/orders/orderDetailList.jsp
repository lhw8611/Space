<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="vo.OrOdProViewBean"%>
<%
	ArrayList<OrOdProViewBean> orderdetaillist = (ArrayList<OrOdProViewBean>) request
			.getAttribute("orderdetaillist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.page_title {
	position: relative;
	z-index: 100;
	margin: 0 0 11px;
	zoom: 1;
}

page_title:after {
	display: block;
	clear: both;
	content: '';
}

body {
	font-family: '나눔고딕', NanumGothic, '맑은고딕', MalgunGothic, '돋움', Dotum,
		Helvetica, sans-serif;
}

ul {
	display: blcok;
	list-style-type: none;
}

li {
	display: list-item;
	text-align: -webkit-match-parent;
}

dd {
	display: block;
	margin-inline-start: 40px;
}

a {
	text-decoration: none;
}

.fl {
	float: left;
}

.vsb:after {
	display: block;
	visibility: hidden;
	clear: both;
	content: '';
}

.order_info1 {
	margin-top: -1px;
	zoom: 1;
}

.order_info1 li:first-child {
	margin-left: 0;
	border: none;
}

.order_info1 li {
	float: left;
	margin-left: 25px;
	padding-left: 29px;
	border-left: 1px solid #eff0f1;
}

.order_info1:after {
	display: block;
	visibility: hidden;
	content: "";
	clear: both;
}

.order_info1 li dl:after {
	display: block;
	visibility: hidden;
	content: "";
	clear: both;
}

.order_info1 li dt {
	float: left;
	margin: 0 25px 0 0;
	padding-top: 4px;
	font-size: 14px;
	font-weight: bold;
}

.order_info1 li dd {
	float: left;
	font-size: 17px;
	font-weight: normal;
	line-height: 20px;
}

.order_info1 li dl {
	color: #20232c;
	line-height: 13px;
}

.order_info1 li dd a {
	display: inline-block;
	width: 88px;
	height: 20px;
	margin: 1px 0 0 3px;
	background:
		url(../../../../img/service/front/order/spr_btn.gif?20190214)
		no-repeat -410px -720px;
	vertical-align: top;
}

a:-webkit-any-link {
	color: -webkit-link;
	cursor: pointer;
	text-decoration: underline;
}

.content {
	width: 860px;
	margin: 0 auto;
}

.tg {
	width: 860px;
	border-collapse: collapse;
	border-spacing: 0;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 2px 20px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: black;
}

.tg td img{
	wdith : 80px;
	height: 80px;
}
.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 2px 20px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: black;
}

.tg .tg-uys7 {
	border-color: inherit;
	text-align: center;
}

.tg .tg-0lax {
	text-align: center;
}

.tg .tg-xldj {
	border-color: inherit;
	text-align: center;
}
</style>
</head>
<body>
	<jsp:include page="../top_menu.jsp" />

	<div id="container">
		<div id="main">
			<div class="content">
				<div class="page_title">
					<h3>주문상세정보</h3>
				</div>
				<div class="order_header vsb">
					<ul class="fl order_info1">
						<li>
							<dl>
								<dt>주문일자</dt>
								<dd>
									<strong> <%=orderdetaillist.get(0).getOr_date()%>
									</strong>
								</dd>
							</dl>
						<li>
							<dl>
								<dt>주문번호</dt>
								<dd class="ordnum"><%=orderdetaillist.get(0).getOr_num()%>
									<a href="#" class="order_del_click">내역삭제</a>
								</dd>
							</dl>

						</li>
					</ul>
				</div>


				<table class="tg">
					<tr>
						<th class="tg-0lax">상품주문번호</th>
						<th class="tg-xldj">상품정보</th>
						<th class="tg-uys7">상품금액(수량)</th>
						<th class="tg-xldj">진행상태</th>
					</tr>
<%
						for (int i = 0; i < orderdetaillist.size(); i++) {
					%>
					<tr>
						<td class="tg-0lax"><%=orderdetaillist.get(i).getPro_code()%></td>
						<td class="tg-xldj"><a href="/Project/productView.bo?<%=orderdetaillist.get(i).getPro_code()%>"
							target="_blank"><img src="/Project/boardUpload/<%=orderdetaillist.get(i).getPro_image()%>"
								alt="<%=orderdetaillist.get(i).getPro_name()%>"></a></td>
						<td class="tg-uys7">aaaa</td>
						<td class="tg-xldj">aa</td>
					</tr>
					<tr>
						<td class="tg-0lax"></td>
						<td class="tg-xldj"></td>
						<td class="tg-uys7">ddd</td>
						<td class="tg-xldj"></td>
					</tr>
				</table>
				상품
				<%=orderdetaillist.get(i).getPro_name()%>
				가격
				<%=orderdetaillist.get(i).getPro_price()%>
				수량
				<%=orderdetaillist.get(i).getOd_qty()%>

				<%
					}
				%>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>