<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    int qna_num = Integer.parseInt(request.getParameter("qna_num"));
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="qnaModify.qna" method="post">
		
		<input type="hidden" name="qna_num" id="qna_num" value="<%=qna_num %>"/>
		<input type="text" name="qna_question" id="qna_question" />
	<input type="text" name="qna_answer" id="qna_answer" />
	
	<input type="submit" value="확인">
			<input type="reset" value="다시작성">
	</form>
</body>
</html>