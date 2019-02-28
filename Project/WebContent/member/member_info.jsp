<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.Member" %>
<jsp:include page="../top_menu.jsp"/>
<!DOCTYPE html>
<html>
<head>
<style>
	table{
		margin : auto;
		width : 400px;
		border : 1px solid gray;
		text-align : center;
	}
	.td_titel{
		font-weight: bold;
		font-size : x-large;
	}
</style>
<%
	Member member = (Member)request.getAttribute("member");
%>
<script>
	function modisubmit(){
		modify.submit();
	}
</script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="modify" action="modifyproaction.join" method="post">
<table border=1>
	<tr>
		<td colspan="2" class="td_title">
			<h3>회원 정보</h3>
		</td>
	</tr>
	<tr>
		<td><label for = "id" >아이디 : </label></td>
		<td><input type="text" name="id" id="id" readonly value="<%=member.getMem_id() %>"/></td>
		
	</tr>
	<tr>
		<td><label for ="pass">비밀번호 : </label></td>
		<td><input type="password" name="pass" id="pass" value="<%=member.getMem_pass() %>"/></td>
	</tr>
	<tr>
		<td><label for = "name">이름 : </label></td>
		<td><input type="text" name="name" id="name" value="<%=member.getMem_name() %>"/></td>
	</tr>
	<tr>
		<td><label for="email">이메일주소 : </label></td>
		<td><input type="text" name="email" id="email" value="<%=member.getMem_email() %>"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="javascript:modify.submit()">수정하기</a> &nbsp;&nbsp;
			<a href="javascript:modify.reset()">초기화</a> &nbsp;&nbsp;
			<a href="member/main.jsp">main으로</a>
		</td>
	</tr>
</table>
</form>
</body>
</html>