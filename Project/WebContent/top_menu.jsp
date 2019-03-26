<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.MemberBean"%>
<%
	String id = null;
	if (session.getAttribute("id") != null) {
		id = (String) session.getAttribute("id");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link href="Project/css/top_menu.css" rel="stylesheet" type="text/css"> -->


<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>

<!-- 서서히 사라지고 나타남(오버시 진해짐) -->
<script>
   $(function() {
      $(window).scroll(function() {
         var scrollTop = $(window).scrollTop();
         if (scrollTop < 500)
            $('header').stop().animate({
               'opacity' : '.6'
            }, 200);
         else
            $('header').stop().animate({
               'opacity' : '1'
            }, 200);
      });
      $('header').hover(function(e) {
         var scrollTop = $(window).scrollTop();
         if (scrollTop < 500) {
            $('header').stop().animate({
               'opacity' : '1'
            }, 200);
         }
      }, function(e) {
         var scrollTop = $(window).scrollTop();
         if (scrollTop < 500) {
            $('header').stop().animate({
               'opacity' : '.6'
            }, 200);
         }
      });
   });
</script>
<style>
* {
	margin: 0;
	padding: 0;
}

body {
	z-index: 1;
	background-color: #f8f8f8;
}

header {
	position:fixed;
	top: 0;
	background-color: black;
	opacity: 0.6;
	width: 100%;
	height: 10vh;
	border-bottom: 2px;
	border-bottom-style: solid;
	z-index: 1;
	border-image: linear-gradient(to right, #01c9ca 0%, #3886FF 100%);
	border-image-slice: 1;
	color: white;
	font-size: 1em;
	box-sizing: border-box;
}

#menu_center ul {
	text-align: center;
	font-family: 'Roboto', 'NanumSquare';
}

#menu_center li {
	padding-right: 40px;
}

header li {
	display: inline-block;
	list-style-type: none;
}

header a {
	text-decoration: none;
	display: block;
}

header a:linked {
	color: white;
}

#menu_main {
	width: 100%;
}

#menu_left {
	width: 20%;
	line-height: 10vh;
	float: left;
	padding-left: 50px;
	box-sizing: border-box;
}

#menu_left a {
	color: #01c9ca;
	font-size: 1.5em;
	font-weight: 200;
}

#menu_center {
	width: 60%;
	line-height: 10vh;
	float: left;
	font-weight: lighter;
	box-sizing: border-box;
}

#menu_center a {
	color: white;
	font-size: 1.2em;
}

#menu_right {
	width: 20%;
	line-height: 10vh;
	float: right;
	box-sizing: border-box;
	padding-right: 30px;
}

#menu_right>ul>li {
	padding-left: 40px;
}

#menu_right>ul>li>a {
	color: white;
	font-size: 1em;
	font-weight: 500;
}

#menu_right>a {
	text-align: right;
}

#menu_right li ul {
	display: none;
	height: auto;
	padding: 0px;
	margin: 0px;
	border: 0px;
	position: absolute;
	width: 200px;
	z-index: 200;
	background: #6e81a5;
}

#menu_right li:hover ul {
	display: block;
}

#menu_right li:hover ul li {
	text-align: left;
	display: block;
	line-height: 10vh;
}

#menu_right>ul {
	text-align: right;
}

#container {
margin-top: 10vh auto;
}
</style>
<body>
	<header>
		<div id="menu_main">

			<div id="menu_left">
				<a href="main.jsp">Space</a>
			</div>

			<div id="menu_center">
				<ul>
					<li><a href="#about">About us</a></li>
					<li><a href="/Project/productList.bo">Shop</a>
					<li><a href="<%=request.getContextPath()%>/qnaList.qna">qna</a></li>
					<li><a href="<%=request.getContextPath()%>/board/boardTest.jsp">Notice</a></li>
					<li><a href="board/boardTest.jsp">보드테스트</a></li>
					<li><a href="/Project/salesManagement.ad">관리자 페이지</a></li>
				</ul>
			</div>

			<div id="menu_right">
				<ul>

					<%
						if (id != null) {
					%>
					<li><a
						href="/Project/memberinfo.mem?id=<%=session.getAttribute("id")%>">My
							page</a>
						<ul>
							<li><a href="/Project/orderList.od">주문배송조회</a></li>
							<li><a href="<%=request.getContextPath()%>/logout.mem">로그아웃</a></li>
						</ul></li>

					<%
						} else {
					%>
					<li><a href="<%=request.getContextPath()%>/loginForm.mem">로그인</a></li>
					<%
						}
					%>
					<li><a href="<%=request.getContextPath()%>/cartListForm.od">장바구니</a></li>
				</ul>
			</div>
		</div>
	</header>
	<div style="clear: both;"></div>
</body>
</html>