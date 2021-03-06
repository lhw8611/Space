<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.ProductBean" %>
	
	<%
 	ProductBean probean = null;
	if((ProductBean)request.getAttribute("probean")!=null) {
		 probean = (ProductBean)request.getAttribute("probean");
	 } 
	System.out.println(probean.getPro_code());
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>::Space:: 관리자 - 상품수정</title>
<style>
#page {
	width:100%;
	height:100vh;
	background-color: #F8F8F8;
}


#container {
	width: 1000px;
	margin: 0 auto;
	
}
article {
	width: 800px;
	margin:100px auto;
	border:0.5px solid #CCCCCC;
	border-radius:2px;
	background-color: #fff;
	padding:80px;
}

/* 제목 */
#subject {
	width: 95%;
	margin: 5px auto;
	height: 30px;
	background: #fff;
	border-radius: 4px;
	padding: 0 10px;
}

#subject:hover {
	opacity: 0.8;
}
#category {
height:33px;
border-radius: 4px;
}
#price {
height:30px;
border-radius: 4px;
}

/* textarea */
#content textarea {
width:95%;
	padding: 5px 10px;
	margin : 5px auto;
	border-radius: 4px;
	margin: 5px 0;
}

#content:hover {
	opacity: 0.8;
}

/* 다시작성 */
input[type="reset"] {
	width: 100px;
	height: 40px;
	background: #494948;
	border: 1px solid #fff;
	cursor: pointer;
	border-radius: 5px;
	color: #fff;
	font-size: 16px;
	font-weight: 400;
	padding: 6px;
	margin-top: 10px;
}

input[type=reset]:hover {
	opacity: 0.8;
}

input[type=reset]:active {
	opacity: 0.6;
}

input[type=reset]:focus {
	outline: none;
}

/* 작성하기 */
input[type=submit] {
	
	width: 100px;
	height: 40px;
	background: #0082FC;
	border: 1px solid #fff;
	cursor: pointer;
	border-radius: 5px;
	color: #fff;
	font-size: 16px;
	font-weight: 400;
	padding: 6px;
	margin-top: 10px;
}

input[type=submit]:hover {
	opacity: 0.8;
}

input[type=submit]:active {
	opacity: 0.6;
}

input[type=submit]:focus {
	outline: none;
}

#submit {
	float: right;
}
/* 
삭제하기 */
#delete {
	
	width: 100px;
	height: 40px;
	background: Gray;
	border: 1px solid #fff;
	cursor: pointer;
	border-radius: 5px;
	color: #fff;
	font-size: 16px;
	font-weight: 400;
	padding: 6px;
	margin-top: 10px;
}

#delete:hover {
	opacity: 0.8;
}

#delete:active {
	opacity: 0.6;
}

#delete:focus {
	outline: none;
}

#delete {
	float: right;
}

form {
	margin: 70px auto;
	
}
#image {
float:right;
}
#file {
margin:5px;
}

</style>
</head>
<script>
	function clickon(v) {
		if (v.value == '가구') {
			alert(v.value + ' 선택하셨습니다.');
		}
	}
</script>
<body id="page">

		<jsp:include page="../top_menu.jsp" />  
		<jsp:include page="../admin/adminSidebar.jsp"></jsp:include>
	<section id="container">
		
		
		<article id="wrtieform">
		<h2>관리자 - 상품 수정</h2>
		<hr>
			<form action="/Space/ProModifyAction.ad" name="proWriteForm" method="post"
				enctype="multipart/form-data">
				<div id="title">
					<input type="text" name="subject" id="subject" autofocus="autofocus"
						size="20" placeholder="상품명" value="<%=probean.getPro_name()%>"/> 
						<input type="hidden" value="<%=probean.getPro_code() %>" name="pro_code">
						</div>
						<div id="option">
						<select name="category" id="category">
						<option value="x" style="color:gray;" >카테고리 선택</option> 
						<option value="gagu">가구</option>
						<option value="candle">캔들</option>
						<option value="light">조명</option>
						<option value="props">소품</option>
					</select> <input type="text" name="price" id="price" placeholder="가격" value="<%=probean.getPro_price()%>" style="text-align: right; padding-right: 10px;"/>원
	<input type="file" name="image" id="image" value="<%=probean.getPro_image()%>"/>
				</div>
				<div id="content" >

					<textarea name="content" id="content" cols="50" rows="20"><%=probean.getPro_content() %></textarea>

				</div>
				
				<div>
					
					<span><input type="reset" value="다시작성" /></span> 
					<span><input type="button" id="delete" value="삭제하기" onclick="location.href='/Space/ProDeleteAction.ad?pro_code=<%=probean.getPro_code()%>'"/></span> <span>
					<input type="submit" id="submit" value="수정하기" /></span> 
					
				</div>
			</form>
		</article>
	</section>
	
		 <jsp:include page="../footer.jsp"/>
</body>
</html>