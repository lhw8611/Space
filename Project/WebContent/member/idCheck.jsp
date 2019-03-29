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
<style>
.content{
	width : 300px;
	margin :0 auto;
	text-align: center;
}
a{
	text-decoration: none;
}
.inpt{
	outline: 0;
    background: #f2f2f2;
    width: 100%;
    border: 0;
    padding: 10px;
    box-sizing: border-box;
    font-size: 12px;
    border-radius: 3px;
}
.inps{
    margin-top : 3px;
    font-family: "Nanum Gothic";
    font-weight: 700;
    text-transform: uppercase;
    outline: 0;
    background: #abc;
    border: 0;
    width: 100%;
    padding: 3px;
    color: #FFFFFF;
    font-size: 14px;
    -webkit-transition: all 0.3 ease;
    transition: all 0.3 ease;
    cursor: pointer;
    border-radius: 3px;
    height: 34px;
}
</style>
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
<div class="content">
	<h2>아이디 중복검사</h2>
<form id="idcheck" action="/Space/idCheckProAction.mem" method="post" name=f  onsubmit="return validate()">
	<input type="text" name="id" id="id" value="<%=idcheck %>" class="inpt"><br>
	<input type=submit class="inps" value="중복확인">
</form>

<%if(idcheckrs.equals("true")){ 
	out.println("<hr><p>\""+idcheck+"\"는<br>사용 불가능한 아이디입니다.<br> 다시 검색하세요.</p>");
}else if(idcheckrs.equals("false")){
	out.println("<hr><p>\""+idcheck+"\"는<br>사용 가능한 아이디입니다.<br><a href='#' onclick=\"ok('"+idcheck+"')\">사용하기</a></p>");
}
%>
</div>

<body>
	      <script>
            function validate() {
                var re = /^[a-zA-Z0-9]{4,20}$/
                var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
                var id = document.getElementById("id");
 
                if(idcheck.id.value=="") {
                    alert("아이디를 입력해 주세요");
                    idcheck.id.focus();
                    return false;
                }
                if(!check(re,id,"아이디는 4~20자의 영문 대소문자와 숫자로만 입력")) {
                    return false;
                }
            }
 
            function check(re, what, message) {
                if(re.test(what.value)) {
                    return true;
                }
                alert(message);
                what.value = "";
                what.focus();
                //return false;
            }
      </script>
</body>
</html>