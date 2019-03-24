<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	String openInit = "false";
	if(request.getParameter("openInit")!=null){
		openInit = "true";
	}
	String id="";
	id = request.getParameter("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
function init(){
	if(<%=openInit%>){
		document.getElementById("id").value=opener.document.getElementById("id").value;
		
	}
}
function ok(v){
	opener.idcheck=v.trim();
	opener.document.getElementById("id").value=v;
	opener.chkId=true;
	window.close();
}
</script>
<body onload="init()">
<%
	String idcheck = "";
	String idcheckrs = "";
	if((String)request.getAttribute("id") == null){
		idcheck = "";
	}else{
		idcheck = (String)request.getAttribute("id");
	}
	
	if((String)request.getAttribute("idcheckrs") == null){
		idcheckrs = "";
	}else{
		idcheckrs = (String)request.getAttribute("idcheckrs");
	}
	System.out.println("idcheck 최종폼에서 값넘어오나 확인 id값 , idcheckrs값 : " + idcheck + "   " + idcheckrs);
%>
<form action="/Project/idCheckProAction.mem" method="post" name=f>
	<input type=text name=id id=id value="<%=idcheck %>">
	<input type=submit value="중복확인">
</form>
<hr>
<%if(idcheckrs.equals("true")){ 
	out.println("<h3>"+idcheck+"는 사용 불가능한 아이디입니다. 다시 검색하세요.</h3>");
}else if(idcheckrs.equals("false")){
	out.println("<h3>"+idcheck+" 는 사용가능한 아이디입니다. <a href='#' onclick=\"ok('"+idcheck+"')\">사용하기</a></h3>");
}
%>

<body>

</body>
</html>