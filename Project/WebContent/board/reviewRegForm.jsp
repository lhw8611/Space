<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
int pro_code = Integer.parseInt(request.getParameter("pro_code"));
String mem_id = (String)session.getAttribute("id");
System.out.println(pro_code);
System.out.println(mem_id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성 폼</title>
</head>
<body>
	<form action="/Project/reviewReg.bo" enctype="multipart/form-data" method=post>
	<input type="hidden" name="mem_id" value="<%=mem_id%>">
	<input type="hidden" name="pro_code" value="<%=pro_code%>">
	<select name="star">
		<option value="1">★☆☆☆☆ </option>
		<option value="2">★★☆☆☆ </option>
		<option value="3">★★★☆☆ </option>
		<option value="4">★★★★☆ </option>
		<option value="5">★★★★★ </option>
	</select>
	
		<div id="content">
			<textarea name="content" id="content" cols="70" rows="7"></textarea>
			<input type="file" name="img" id="img" />
		</div>
		<div>
			<span><input type="reset" value="다시작성" /></span> <span> <input
				type="submit" id="submit" value="작성하기" /></span>

		</div>
	</form>
</body>
</html>