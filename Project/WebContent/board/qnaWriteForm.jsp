<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="qnaForm.qna" name="qnaForm" method="post">
		<table>
			<tr>
				<td>question</td>
				<td><textarea rows="10" cols="20" name="question" id="question"></textarea></td>
			</tr>
			<tr>
				<td>answer</td>
				<td><textarea rows="10" cols="20" name="answer" id="answer"></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="확인"></td>
				<td><input type="reset" value="다시작성"></td>				
		</table>
	</form>
</body>
</html>
