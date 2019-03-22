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
<link href="Project/css/top_menu.css" rel="stylesheet" type="text/css">


<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>

<!-- 서서히 사라지고 나타남(오버시 진해짐) -->
<script>
$(function() {
	$(window).scroll(function(){
	                    var scrollTop = $(window).scrollTop();
	                    if(scrollTop <500 )
	                        $('header').stop().animate({'opacity':'.6'},200);
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
	                            $('header').stop().animate({'opacity':'.6'},200);
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

header {
	top:0;
	background-color: black;
	opacity:0.6;
	width: 100%;
	height: 75px;
	position: fixed;
	border-bottom:2px;
	border-bottom-style:solid;
	z-index: 100;
	border-image: linear-gradient(to right, #01c9ca 0%, #3886FF 100%);
	border-image-slice: 1;
}

#menu_center ul {
	text-align: center;
	height:100%;
    font-family: 'Roboto','NanumSquare';
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
</style>

<body>
	<header>
		<div id="menu_main">


			<div id="menu_left">
				<a href="main.jsp">Space</a>
			</div>


			<div id="menu_center">
				<ul>
					<li><a href="#">About us</a></li>
					<li><a href="#">조명</a></li>
					<li><a href="#">캔들</a></li>
					<li><a href="#">가구</a></li>
					<li><a href="#">소품</a></li>
					<li><a href="#">Notice</a></li>
				</ul>
			</div>

			<div id="menu_right">
				<ul>
					<li><a href="#">장바구니</a></li>
					<li><a href="#">My page</a></li>
				</ul>
			</div>


		</div>
	</header>
	<script>
	function userdelete(id){
		var userdel = confirm("회원탈퇴를 진행합니까?");
		 if(userdel == true){
			 location.href="<%=request.getContextPath()%>
		/memberdelete.mem?id="
						+ id;
			}
		}
	</script>
</body>
</html>
