<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.odcomple_title {
	color : white;
	font-size: 1.5em;
	display: inline-block;
	position: absolute;
	margin: auto;
	top: 28%;
	left: 50%;
	transform: translate(-50%, -50%);
}

#headerImage {
	width: 100%;
	height: 477px;
		background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)),
	url('headerImage/back05.jpg');
	background-position: 50% 50%;
	background-size: cover;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0;
}
#headerImage img{
	width : 350px;
	height : auto;
}
.odcomple img{
	margin : 0 auto;
	width : 150px;
	height : auto;
	display: block;
}
.odcomple{
	padding-top : 50px;
	text-align: center;
	font-size : 17px;
}
.btn1{ 
    font-family: "Nanum Gothic";
    font-weight: 700;
    text-transform: uppercase;
    outline: 0;
    background: black;
    border: 1px solid black;
    padding: 10px;
    color: #FFFFFF;
    font-size: 14px;
    cursor: pointer;
    border-radius: 3px;
    width : 110px;
    height: 43px;
}
.btn2{
	background: #fff;
	color : black;
}
</style>
</head>
<body>
	<jsp:include page="../top_menu.jsp" />

	<div id="container">
		<div id="main">

			<div id="headerImage">
				<div class="odcomple_title">
					<h2>
						주문완료
					</h2>
				</div>
			</div>
			<div class=odcomple>
			<img src="<%=request.getContextPath()%>/icon/odcomple.png">
			<br>
			<p>주문이 완료되었습니다. 감사합니다.</p>
			<br><br>
			<button type="button" class="btn1" onClick="location.href='/Space/orderList.od';">주문내역조회</button>
			<button type="button" class="btn1 btn2" onClick="location.href='/Space/main.jsp';">홈으로</button>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>
