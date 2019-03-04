<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vo.MemberBean" %>
<%@page import="java.util.*" %>
<%
	ArrayList<MemberBean> list = (ArrayList<MemberBean>)request.getAttribute("list"); 	
%>
<jsp:include page="../top_menu.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 관리자 모드(회원 목록 보기)</title>
<style>
	table{
		margin : auto;
		width : 400px;
		border : 1px solid gray;
		text-align : center;
	}
	.td_title{
		font-weight: bold;
		font-size: x-large;
	}
</style>
</head>
<body>
<table>
	<tr><td colspan=2 class="td_title">회원 목록</td></tr>
	<%-- <%while(rs.next()) { %> --%>
	<%
			for(int i=0; i<list.size(); i++){
	%>
	<tr>
		<td><%=list.get(i).getMem_id() %></td>
		<td><a href="memberinfo.mem?id=<%=list.get(i).getMem_id() %>">수정</a>
		<%System.out.println(i + "  " + list.get(i).getMem_id()); %>
		&nbsp;&nbsp;&nbsp;
		<a href="merber_delete.member?id=<%=list.get(i).getMem_id() %>">삭제</a></td>
	</tr>
		<%}%>
		
<a href="../main.jsp">돌아가기</a>
</table>
</body>
</html>
