<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.MemberBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/top_menu.css" rel="stylesheet" type="text/css">

<%
	String id = null;
	if(session.getAttribute("id")!=null){
		id=(String)session.getAttribute("id");
	}
	
%>
</head>
<body>
	<header>
		<div class="h-main">

			<div class="logo">
				<a href="main.jsp"><img src="/Project/images/logo.png"></a>
			</div>
			<!-- 	<ul>
						<li><a href="#">about us</a></li>
						<li><a href="#">조명</a></li>
						<li><a href="#">캔들</a></li>
						<li><a href="#">가구</a></li>
						<li><a href="#">소품</a></li>
						<li><a href="#">notice</a></li>
					</ul> -->
			<ul class="h-right">
				<!-- 오른쪽정렬 -->
					<ii><a href='admin/admin_page.jsp'>관리자 페이지</a></ii>
				<li><a href="<%=request.getContextPath()%>/qnaList.qna"><img src="<%=request.getContextPath()%>/icon/bell.png"></a></li>
				<li><a href="<%=request.getContextPath()%>/cartListForm.od"><img src="<%=request.getContextPath()%>/icon/cart.png"></a></li>
				<% if(id!=null) {%>
				<li><a href="<%=request.getContextPath()%>/logout.mem"><img src="<%=request.getContextPath()%>/icon/login.png"></a></li>
				<% }else {%>
				<li><a href="<%=request.getContextPath()%>/loginForm.mem"><img src="<%=request.getContextPath()%>/icon/logout.png"></a></li>
				<% }%>
				<li><a href="<%=request.getContextPath()%>/board/boardTest.jsp">Notice</a></li>
				
				<% if(id!=null) {%>
				<li><p><%=session.getAttribute("id") %>님 환영합니다</p></li>
				<li><a href="<%=request.getContextPath()%>/memberinfo.mem?id=<%=id %>">회원정보 수정</a></li>
				<% }%>
				<a href="javascript:userdelete('<%=id %>');">회원탈퇴</a>
				
				
			</ul>
		</div>
	</header>
	<script>
	function userdelete(id){
		var userdel = confirm("회원탈퇴를 진행합니까?");
		 if(userdel == true){
			 location.href="<%=request.getContextPath()%>/memberdelete.mem?id="+id;
		 }
	}
	</script>
</body>
</html>