<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사이드바</title>
</head>

<style>
	#sidebar {
	top:37%;
	left:5%;
	float: left;
	position: fixed;
	width:250px;
	opacity: 0.8;
	border:1px solid #CCCCCC;
	font-weight: 400; 
	}
	#sidebar li{
	padding:10px;
	list-style-type: none;
	}
	#sidebar a {
	margin:0 7px;
	text-decoration: none;
	color : black;
	
	}
	#side_title {
	font-weight:bold;
	text-align:center;
	color:white;
	background-image: linear-gradient(to right, #01c9ca 0%, #3886FF 100%);
	}
	#sidebar a:hover {	
	font-weight: bold;
	opacity: 0.9;
	}
	
	
	
</style>
<body>

<div id="sidebar">
	<ul>
		<li id="side_title">관리자 페이지</li>
		<li><a href="/Space/salesManagement.ad">매출관리</a></li>
		<li><a href="/Space/admingetlist.mem">회원관리</a></li>
		<li><a href="/Space/productWriteForm.bo">상품등록</a></li>
		<li><a href="/Space/qtyManagement.ad">재고관리</a></li>
		<li><a href="/Space/admin_orderList.ad">전체 주문내역</a></li>
	</ul>
	</div>
</body>
</html>