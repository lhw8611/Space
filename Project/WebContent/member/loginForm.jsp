<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="loginform" action="<%=request.getContextPath()%>/loginProcess.mem" method="post" onsubmit="return checkForm(this)">
<table>
	<tr>
		<td colspan="2" class="td_title">
		로그인 페이지
		</td>
	</tr>
	<tr>
		<td><label for = "id">아이디 : </label></td>
		<td><input type="text" name="id" id="id"/></td>
	</tr>
	<tr>
		<td><label for = "pass">비밀번호 : </label></td>
		<td><input type="password" name="pass" id="pass"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="javascript:loginform.submit()">로그인</a>&nbsp;&nbsp;
			<a href="joinForm.mem">회원가입</a>
		</td>
	</tr>
</table>
</form>
</body>
</html>