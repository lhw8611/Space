<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style>
.banner-image {
	position: relative;
	width: 100%;
	height: 400px;
	background-image: url(images/1.png);
	background-size: 100% 100%;
}

.position_fixed {
	position: fixed;
	width: 100%;
	margin-top: 0px;
}

.clearf {
	clear: both;
}
</style>
</head>
<body>
	<header>
		<nav>
			<div class="banner-image">
				<div class="position_fixed">
					<jsp:include page="top_menu.jsp" />
				</div>
			</div>
		</nav>
	</header>
	<h2><%=session.getAttribute("id") %>님 환영합니다.</h2>
	<article>
		<section>
			<img src="images/2.png" width="50%" height="700" alt="이미지2">
		</section>
		<section>
			<img src="images/2.png" width="50%" height="233" alt="이미지2">
		</section>
	</article>
	<footer>
	<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>