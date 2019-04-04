<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.PointBean"%>
<%@page import="java.util.*"%>
<%
	ArrayList<PointBean> arraypointbean = (ArrayList<PointBean>) request.getAttribute("arraypointbean");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.tg {
	border-collapse: collapse;
	border-spacing: 0;
}

.tg td {
	font-size: 14px;
	padding: 0px 10px;
	overflow: hidden;
	word-break: normal;
	height: 50px;
	border-bottom: 1px solid silver;
	box-sizing: border-box;
}

.tg th {
	font-size: 14px;
	font-weight: normal;
	padding: 0px 10px;
	overflow: hidden;
	word-break: normal;
	height: 50px;
	background-color: #EAEAEA;
	font-weight: bold;
	size: 16px;
	border-top: 2px solid black;
	border-bottom: 1px solid silver;
	box-sizing: border-box;
}

.tg .tg-s6z2 {
	text-align: center
}

.tg .tg-uys7 {
	border-color: inherit;
	text-align: center;
	border-bottom: 1px solid silver;
}

.content {
	width: 1000px;
	margin: 0 auto;
}

#headerImage {
	width: 100%;
	height: 477px;
	background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)),
		url('headerImage/back07.jpg');
	background-position: 50% 50%;
	background-size: cover;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0;
}

.point_title {
	color: white;
	font-size: 2em;
	display: inline-block;
	position: absolute;
	margin: auto;
	top: 28%;
	left: 50%;
	transform: translate(-50%, -50%);
}

table {
	box-sizing: border-box;
	width: 100%;
	border: 0;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<jsp:include page="../top_menu.jsp" />
	<%
		if (request.getAttribute("arraypointbean") != null) {
	%>
	<div id="container">
		<div id="main">
			<div id="headerImage">
				<div class="point_title">
					<h2>포인트 내역</h2>
				</div>
			</div>
			<div class="content" style="margin-top: 50px; margin-bottom: 90px;">
				<table class="tg">
					<tr>
						<th class="tg-s6z2">적립내역</th>
						<th class="tg-uys7">구분</th>
						<th class="tg-uys7">적립금</th>
						<th class="tg-uys7">적립잔액</th>
						<th class="tg-uys7">적립일자</th>
					</tr>
					<%
						for (int i = 0; i < arraypointbean.size(); i++) {
								String state = arraypointbean.get(i).getPo_state().trim();
								String division = "";
								if (state.equals("buysave")) {
									state = "결제적립";
									division = "적립";
								} else if (state.equals("join")) {
									state = "가입감사";
									division = "적립";
								} else if (state.equals("usepoint")) {
									state = "결제차감";
									division = "차감";
								}
					%>

					<tr>
						<td class="tg-s6z2"><%=state%></td>
						<td class="tg-uys7"><%=division%></td>
						<td class="tg-uys7">
							<%
								if (division.equals("차감")) {
							%>-<%
								}
							%><%=arraypointbean.get(i).getPo_point()%></td>
						<td class="tg-uys7"><%=arraypointbean.get(i).getPo_total()%></td>
						<td class="tg-uys7"><%=arraypointbean.get(i).getPo_date()%></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
		</div>
	</div>

	<%
		} else {
	%>
	내역이 없습니다.
	<%
		}
	%>
	<jsp:include page="../footer.jsp" />
</body>
</html>