<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		<header>
		<div class="h-main">
			<div class="logo">
				<a href="main.jsp"><img src="/Project/images/logo.png"></a>
			</div>
			<ul class="h-right">
				
				<li><a href="<%=request.getContextPath()%>/qnaList.qna"><img src="<%=request.getContextPath()%>/icon/bell.png"></a></li>
				<li><a href="/Project/productWriteForm.bo">상품 글 작성</a></li>
			</ul>
		</div>
	</header>
</body>
</html>