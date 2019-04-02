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
<style>
	#main {
	margin:0 auto;
	} 
	input[type=file] {
	float:right;
	}
	
</style>
</head>
<body>
	<div id="main">
	<form action="/Space/reviewReg.bo" enctype="multipart/form-data" method=post>
	<input type="hidden" name="mem_id" value="<%=mem_id%>">
	<input type="hidden" name="pro_code" value="<%=pro_code%>">
	<select name="star" style="height:30px; border-radius: 3px; margin:0 20px;">
		<option value="1">★☆☆☆☆ </option>
		<option value="2">★★☆☆☆ </option>
		<option value="3">★★★☆☆ </option>
		<option value="4">★★★★☆ </option>
		<option value="5">★★★★★ </option>
	</select>
	
		<div id="content">
			<textarea name="content" id="content" cols="70" rows="15" style="margin:10px 20px;"></textarea>
			<input type="file" name="img" id="img" />
		</div>
		<div>
			<input
				type="submit" id="submit" value="작성하기" style="padding:10px 20px;; background-color: #DD5850; color:white; border:0px; margin:0 20px;"/>

		</div>
	</form>
		</div>
</body>
</html>