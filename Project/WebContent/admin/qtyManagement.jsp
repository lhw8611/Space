<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.QtyProViewBean"%>
<%
	ArrayList<QtyProViewBean> qtyList = (ArrayList<QtyProViewBean>)request.getAttribute("qtyList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고관리</title>
<style>
table td {
	border:1px solid black;
	border-spacing: 0;
}

</style>
</head>
<body>
	<table>
		<tr>
			<td>번호</td>
			<td>상품코드</td>
			<td>이미지</td>
			<td>상품명</td>
			<td>남은 수량</td>
			<td>최근 입/출고 날짜</td>
			<td>비고</td>
			<td>상세보기</td>
			<td>입/출고 관리</td>
			<td>삭제</td>
		</tr>
		<%
			if (qtyList == null) {
				out.println("상품이 없습니다.");
			} else {
				for (int i = 0; i < qtyList.size(); i++) {
		%>
		<tr>
			<td><%=qtyList.get(i).getQty_num() %> </td>
			<td><%=qtyList.get(i).getPro_code() %></td>
			<td><img src="/Project/boardUpload/<%=qtyList.get(i).getPro_image() %>" width="100px"/></td>
			<td><%=qtyList.get(i).getPro_name() %></td>
			<td>남은 수량 </td>
			<td><%=qtyList.get(i).getQty_date() %></td>
			<td><%=qtyList.get(i).getQty_note() %></td>
			<td><input type="button" value="상세보기" onclick="window.open('idCheckForm.mem?openInit=true', '', 'width=300, height=200')"/> </td>
			<td><input type="button" value="입/출고 관리" onclick="window.open('inoutListForm.ad?pro_code=<%=qtyList.get(i).getPro_code() %>', '', 'width=300, height=500')"/> </td>
			<td><input type="button" value="삭제"/></td>			
			
		</tr>
		<%
			}
			}
		%>

	</table>
</body>
</html>