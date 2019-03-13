<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.QtyProViewBean"%>
<%@ page import="java.util.*"%>
<%
	ArrayList<QtyProViewBean> qtyInOutList = (ArrayList<QtyProViewBean>)request.getAttribute("qtyInOutList");
System.out.println((ArrayList<QtyProViewBean>)request.getAttribute("qtyInOutList"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입/출고 관리</title>
</head>
<body>

<form action="QtyInOutAction.ad">
입고<input type="radio" name="inout" id="inout" value="in" checked="checked"/>
출고<input type="radio" name="inout" id="inout" value="out"/><br>

수량 : <input type="text" name="qty" id="qty"/><br>
비고 : <input type="text" name="note" id="note"/>
<input type="submit" value="등록"/>

</form>
	<table>
		<tr>
			<td>번호</td>
			<td>상품명</td>
			<td>입/출고</td>
			<td>비고</td>
			<td>날짜</td>
		</tr>
		<%
			if (qtyInOutList == null) {
				out.println("<h2>입/출고 내역이 없습니다.</h2>");
			} else {
				for (int i = 0; i < qtyInOutList.size(); i++) {
		%>
		<tr>
			<td><%=i+1 %></td>
			<td><%=qtyInOutList.get(i).getPro_name() %></td>
			<td><%=qtyInOutList.get(i).getQty_inout() %></td>
			<td><%=qtyInOutList.get(i).getQty_note() %></td>
			<td><%=qtyInOutList.get(i).getQty_date() %></td>
		
		</tr>
		<%
			}
			}
		%>

	</table>
	
</body>
</html>