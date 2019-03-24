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
<style>
.content {
	width: 800px;
	margin: 100px auto;
	border: 0.5px solid #CCCCCC;
	border-radius: 2px;
	background-color: #fff;
	padding: 80px;
}

form {
	margin: 70px auto;
}

#qna_question {
	width: 95%;
	margin: 5px auto;
	height: 30px;
	background: #fff;
	border-radius: 4px;
	padding: 0 10px;
}

#qna_answer {
	width: 95%;
	padding: 5px 10px;
	margin: 5px auto;
	border-radius: 4px;
	margin: 5px 0;
}

#btn1 {
	width: 100px;
	height: 40px;
	background: #494948;
	border: 1px solid #fff;
	cursor: pointer;
	border-radius: 5px;
	color: #fff;
	font-size: 16px;
	font-weight: 400;
	padding: 6px;
	margin-top: 10px;
}

#btn2 {
	width: 100px;
	height: 40px;
	background: #0082FC;
	border: 1px solid #fff;
	cursor: pointer;
	border-radius: 5px;
	color: #fff;
	font-size: 16px;
	font-weight: 400;
	padding: 6px;
	margin-top: 10px;
	float: right;
}
</style>
</head>
<body>
	
		
		
		
	
	
		<jsp:include page="../top_menu.jsp" />

	<div id="container">
		<div id="main">
			<div class="content">
				<h2>QNA 글 작성</h2>
				<hr>
				<form action="<%=request.getContextPath()%>/qnaModify.qna" method="post">
				<input type="hidden" name="qna_num" id="qna_num" value="<%=qna_num %>"/>
					<div id="title">
					<input type="text" name="qna_question" id="qna_question" placeholder="Question"/>
					</div>
					<div id="content">
					<textarea rows="20" cols="50" name="qna_answer" id="qna_answer" placeholder="Answer"/></textarea>
					</div>
					<div id="button">
						<span> <input type="reset" value="다시작성" id="btn1">
						</span> <span> <input type="submit" value="작성하기" id="btn2">
						</span>
					</div>
				</form>
			</div>
		</div>
	</div>
		<jsp:include page="../footer.jsp" />
</body>
</html>