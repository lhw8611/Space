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


<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>

<!-- 서서히 사라지고 나타남(오버시 진해짐) -->
<script>
$(function() {
	$(window).scroll(function(){
	                    var scrollTop = $(window).scrollTop();
	                    if(scrollTop <500 )
	                        $('header').stop().animate({'opacity':'.8'},200);
	                    else    
	                        $('header').stop().animate({'opacity':'1'},200);
	                });
	                
	                $('header').hover(
	                    function (e) {
	                        var scrollTop = $(window).scrollTop();
	                        if(scrollTop <500){
	                            $('header').stop().animate({'opacity':'1'},200);
	                        }
	                    },
	                    function (e) {
	                        var scrollTop = $(window).scrollTop();
	                        if(scrollTop <500){
	                            $('header').stop().animate({'opacity':'.8'},200);
	                        }
	                    }
	                );
	});
</script>
<style>
* {
	margin: 0;
	padding: 0;
}

body {
	background-color: #f8f8f8;
}

header {
	top: 0;
	background-color: black;
	opacity: 0.6;
	width: 100%;
	height: 10vh;
	position: fixed;
	border-bottom: 2px;
	border-bottom-style: solid;
	z-index: 100;
	border-image: linear-gradient(to right, #01c9ca 0%, #3886FF 100%);
	border-image-slice: 1;
	overflow: hidden;
	
}
input[type=text] {
border-radius: 3px;
}

#menu_center ul {
	text-align: center;
	height: 100%;
	font-family: 'Roboto', 'NanumSquare';
}

#menu_right ul {
	text-align: right;
}

header li {
	display: inline-block;
	list-style-type: none;
}

header a {
	text-decoration: none;
	display: block;
}

#menu_main {
	width: 100%;
	height: 75px;
}

#menu_left {
	width: 20%;
	height: 75px;
	float: left;
}

#menu_left a {
	color: #01c9ca;
	font-size: 1.5em;
	margin: 17px 20px;
	font-weight: 200;
}

#menu_center {
	width: 60%;
	height: 75px;
	float: left;
	font-weight: lighter;
}

#menu_center a {
	color: white;
	font-size: 1.2em;
	margin: 17px 20px;
}

#menu_right {
	width: 20%;
	height: 75px;
	float: right;
}

#menu_right a {
	color: white;
	font-size: 1em;
	margin: 17px 20px;
	font-weight: 500;
}

.size {
	height: 10vh;
	background-color: black;
}
</style>
<body>
	<div class="size">
		<header>
			<div id="menu_main">

				<div id="menu_left">
					<a href="main.jsp">Space</a>
				</div>

				<div id="menu_center">
					<ul>
						<li><a href="#about">About us</a></li>
						<li><a href="productList.bo">Shop</a></li>
						<li><a href="#">Notice</a></li>
						<li><a href="<%=request.getContextPath()%>/qnaList.qna">qna</a></li>
						<%
							if (id != null) {
						%>
						<li><a href="<%=request.getContextPath()%>/logout.mem">로그아웃</a></li>
						<%
							} else {
						%>
						<li><a href="<%=request.getContextPath()%>/loginForm.mem">로긴</a></li>
						<%
							}
						%>
						<li><a
							href="<%=request.getContextPath()%>/board/boardTest.jsp">Notice</a></li>
						
					</ul>
				</div>

				<div id="menu_right">
					<ul>
						<li><a href="<%=request.getContextPath()%>/cartListForm.od">장바구니</a></li>
						<li><a href="/Project/memberinfo.mem?id=<%=session.getAttribute("id")%>">My page</a></li>
						<li><a href="board/boardTest.jsp">보드테스트</a>
						<li><a href="login"></a></li>
						<li><a href='admin/salesManagement.jsp'>관리자 페이지</a>
						</li>
						
					</ul>
				</div>
			</div>
		</header>
		<div style="clear: both;"></div>
	</div>
</body>
</html>
