<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 글 쓰기</title>
</head>
<body>
	<header>
		<nav>
			<div class="banner-image">
				<div class="position_fixed">
					<%-- 	<jsp:include page="top_menu.jsp" />  --%>
				</div>
			</div>
		</nav>
	</header>
	<section id="container">

		<h2>새 글 쓰기</h2>
		<hr>
		<article id="wrtieform">
			<form action="ProductWritePro.bo" name="proWriteForm" method="post"
				enctype="multipart/form-data">
				<div id="title">
					제목 : <input type="text" name="name" id="name" autofocus="autofocus"
						size="20" /> 카테고리 : <select name="category" id="category">
						<option value="gagu">가구</option>
						<option value="candle">캔들</option>
						<option value="light">조명</option>
						<option value="props">소품</option>
					</select> 가격 : <input type="text" name="price" id="price" />

				</div>
				<div id="content">

					<textarea name="content" id="content" cols="50" rows="20"></textarea>

				</div>
				<div>
					<span><input type="button" value="취소" /></span> <span>
					<input type="submit" value="작성하기" /></span> 
					<input type="file" name="image" id="image" />
				</div>
			</form>
		</article>
	</section>
	<footer>
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>
</body>
</html>