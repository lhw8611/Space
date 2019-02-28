<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	table{
		margin : auto;
		width : 500px;
		border : 1px solid gray;
		text-align : center;
	}
	.td_title{
		font-weight: bold;
		font-size : x-large;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="joinProcess.mem" name="joinform" method="post">
<table>
	<tr><td colspan="2"> 가입 정보 입력 </td></tr>
	
	<tr><td><label for ="id">아이디</label></td>
		<td><input type="text" name="id" id="id"/></td></tr>
		<!-- id, pass, name, add, email, grade, tel, date -->
	<tr><td><label for = "pass">비밀번호 </label></td>
		<td><input type="password" name="pass" id="pass"/></tr>
		
	<tr><td><label for ="pass2">비밀번호 확인 </label></td>
		<td><input type="password" name="pass2" id="pass2"/></tr>
	
	<tr><td><label for ="name">이름</label></td>
		<td><input type="text" name="name" id="name"/></td></tr>
		
	<tr><td><label for ="add">주소</label></td>
		<td><input type="text" name="add" id="add"/></td></tr>
	
	<tr><td><label for ="email">이메일 </label></td>
		<td><input type="text" name="email" id="email"/></td></tr>
		
	<tr><td><input type="hidden" name="grade" id="grade" value="u"/></td></tr>
	
	<tr><td><label for ="tel">전화번호</label></td>
		<td><input type="tel" name="tel" id="tel"/></td></tr>
	
	<tr><td colspan="2"><input type="submit" value="회원가입"></td></tr>
	
</table>
</form>
</body>
</html>