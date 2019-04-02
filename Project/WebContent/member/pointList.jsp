<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vo.PointBean" %>
<%@page import="java.util.*" %>
<%
	ArrayList<PointBean> arraypointbean = (ArrayList<PointBean>)request.getAttribute("arraypointbean");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%for(int i=0; i<arraypointbean.size(); i++){ %>
포인트상태 : <%=arraypointbean.get(i).getPo_state() %>
			포인트 내역 : <%=arraypointbean.get(i).getPo_point() %>
			총 포인트 : <%=arraypointbean.get(i).getPo_total() %>
			날짜 : <%=arraypointbean.get(i).getPo_date() %>
			<br>
<%} %>
</body>
</html>