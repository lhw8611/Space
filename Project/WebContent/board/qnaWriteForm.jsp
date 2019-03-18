<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.all h3{
	text-align: center;
	font-size: 24px;
	margin: 10 auto;
}
.all{
	width : 600px;
	margin : 0 auto;
	padding : 2em;
}


form{
	text-align: center;
}

</style>
</head>
<body>
	<jsp:include page="../top_menu.jsp"/>
	<h3>QNA 글쓰기</h3>
	<hr>
	<div class="form">
	<form action="<%=request.getContextPath()%>/qnaForm.qna" name="qnaForm" method="post">
				<br>
				<input type="text" name="question" id="question" placeholder="Question" size="40" style="height:30px;"/><br><br>
				<textarea rows="10" cols="50" name="answer" id="answer" placeholder="Answer" ></textarea>
				<br>
				<br>
				<br>
				<input type="submit" value="확인">
				<input type="reset" value="다시작성">
	</form>
	</div>
</body>
</html>
