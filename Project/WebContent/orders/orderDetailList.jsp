<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="vo.OrOdProViewBean" %>
<%
	ArrayList<OrOdProViewBean> orderdetaillist = (ArrayList<OrOdProViewBean>)request.getAttribute("orderdetaillist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	주문일자 <%=orderdetaillist.get(0).getOr_date() %> 
	주문번호<%=orderdetaillist.get(0).getOr_num() %>
	<%for(int i=0; i<orderdetaillist.size(); i++){ %>
	
상품 <%=orderdetaillist.get(i).getPro_name() %> 
가격 <%=orderdetaillist.get(i).getPro_price() %>
수량 <%=orderdetaillist.get(i).getOd_qty() %>

	<%} %>
</body>
</html>