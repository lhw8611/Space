<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>notice</title>
</head>
<body>
	<section id="container">

		<h2>새 글 쓰기</h2>
		<hr>
		<article id="wrtieform">
			<form action="/Project/noticeWritePro.bo" method="post">
				<div id="title">
					제목 : <input type="text" name="title" id="title" autofocus="autofocus"
						size="20" />
				</div>
				<div id="content">
					
					<textarea name="content" id="content" cols="50" rows="20"></textarea>
				</div>
				<div>
					<span><input type="button" value="취소" /></span> <span><input
						type="submit" value="작성하기" /></span>
				</div>
			</form>
		</article>
	</section>
	<footer>
		<jsp:include page="../footer.jsp"></jsp:include>
	</footer>
</body>
</html>