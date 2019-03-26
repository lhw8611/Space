<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.MemberBean"%>
<%@page import="java.util.*"%>
<%
	ArrayList<MemberBean> list = (ArrayList<MemberBean>) request.getAttribute("list");
%>
<jsp:include page="../top_menu.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 관리자 모드(회원 목록 보기)</title>
<style>
table {
	margin: auto;
	width: 400px;
	border: 1px solid gray;
	text-align: center;
}

.td_title {
	font-weight: bold;
	font-size: x-large;
}

td {
	border: 1px solid black;
}

.member_list_title {
	margin: 60px 0 10px;
}

#memlistForm {
	width: 860px;
	margin: 0 auto;
}

* {
	margin: 0;
	padding: 0;
}

#memlistForm ul {
	display: inline-block;
	list-style-type: none;
}

#memlistForm li {
	display: inline-block;
}

div {
	text-decoration: none;
}

#memlistForm ul, #memlistForm li {
	width: 100%;
}

#memlistForm dt {
	float: left;
}

#memlistForm dl {
	width: 100%;
	overflow: hidden;
}

#memlistForm dt {
	background: #ccc;
	box-sizing: border-box;
	text-align: center;
	float: left;
	width: 50%;
	float: left;
	font-size: 16px;
	padding: 10px 20px;
}

#memlistForm dd {
	box-sizing: border-box;
	text-align: center;
	float: left;
	width: 50%;
	background: #f2f2f2;
	padding: 20px 50px;
}

#memlistForm dd p {
	width: 90%;
}

#memlistForm dl a {
	text-decoration: none;
}

#memlistForm a, #memlistForm a:link {
	color: black;
}

#memlistForm a:active {
	text-decoration: underline;
}

member_list_title h2 {
	display: inline-blcok;
}
.vh10{
	width : 100%;
	height : 10vh;
}
#memlistForm{
}
</style>
</head>
<body>
	<jsp:include page="adminSidebar.jsp"></jsp:include>
	<div id="container">
		<div id="main">
			<div class="vh10">
			</div>
			<section id="memlistForm">
				<div class="member_list_title">
					<h2>회원 목록</h2>
					<input type="text" name="search"><input type="button" value="검색">
				</div>
				<br>
				<ul>
					<%
						if (list != null) {
					%>
					<li><dl>
							<dt>아이디</dt>
							<dt>관리</dt>
						</dl></li>
					<%
						for (int i = 0; i < list.size(); i++) {
					%>
					<li><dl>
							<dd><%=list.get(i).getMem_id()%></dd>
							<dd>
								<a href="/Project/memberinfo.mem?id=<%=list.get(i).getMem_id()%>">수정</a>
								&nbsp;&nbsp;&nbsp; <a
									href="javascript:userdelete('<%=list.get(i).getMem_id()%>');">삭제</a>
							</dd>
						</dl></li>
					<%
						}
					%>
				</ul>
				<%
					} else {
				%>
				<h2>등록된 글이 없습니다.</h2>
				<%
					}
				%>

				<%-- 		<section id="pageList">
			<%
				if (nowPage <= 1) {
					out.println("[이전]&nbsp;");
				} else {
					out.println("<a href='qnaList.qna?page=" + (nowPage - 1) + "'>[이전]</a>&nbsp");
				}
			%>
			<%
				for (int a = startPage; a <= endPage; a++) {
					if (a == nowPage) {
						out.println("[" + a + "]");
					} else {
						out.println("<a href='qnaList.qna?page=" + a + "'>[" + a + "]</a>&nbsp;");
					}
				}
			%>
			<%
				if (nowPage >= maxPage) {
					out.println("[다음]");
				} else {
					out.println("<a href='qnaList.qna?page=" + (nowPage + 1) + "'>[다음]</a>");
				}
			%>
		</section> --%>
			</section>
			<script>
				function userdelete(id) {
					var userdel = confirm("회원탈퇴를 진행합니까?");
					if (userdel == true) {
						location.href = "/Project/memberdelete.mem?id=" + id;
					}
				}
			</script>

		</div>
	</div>
			<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
