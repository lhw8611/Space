<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vo.MemberBean" %>
<%@page import="java.util.*" %>
<%
	ArrayList<MemberBean> list = (ArrayList<MemberBean>)request.getAttribute("list"); 	
%>
<jsp:include page="../top_menu.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 관리자 모드(회원 목록 보기)</title>
<style>
	table{
		margin : auto;
		width : 400px;
		border : 1px solid gray;
		text-align : center;
	}
	.td_title{
		font-weight: bold;
		font-size: x-large;
	}
	td{
		border : 1px solid black;
	}
</style>
</head>
<body>


<table>
	<tr><td colspan=2 class="td_title">회원 목록</td></tr>
		<tr><td>아이디</td><td>관리</td>
	<%
			for(int i=0; i<list.size(); i++){
	%>
	<tr>
		<td><%=list.get(i).getMem_id() %></td>
		<td><a href="/Project/memberinfo.mem?id=<%=list.get(i).getMem_id() %>">수정</a>
		<%System.out.println(i + "  " + list.get(i).getMem_id()); %>
		&nbsp;&nbsp;&nbsp;
		<a href="javascript:userdelete('<%=list.get(i).getMem_id() %>');">삭제</a></td>
	</tr>
		<%}%>
</table>

     <section id="qnalistForm">
           <h2>
                Q&A<a href="qnaWriteForm.qna">게시판글쓰기</a>
           </h2>
           <br>
           <ul>
                <%
                     if (arryqna != null && listCount > 0) {
                %>
                <li><dl>
                           <dd>번호</dd>
                           <dd>질문내용</dd>
                           <dd>답변내용</dd>
                           <dd>날짜</dd>
                     </dl></li>
                <%
                     for (int i = 0; i < arryqna.size(); i++) {
                %>
                     <li><dl>
                          <dd><%=arryqna.get(i).getQna_num()%></dd>
                          <dd><%=arryqna.get(i).getQna_question()%></dd>
                          <dd><%=arryqna.get(i).getQna_answer()%></dd>
                           <dd> <%=arryqna.get(i).getQna_date()%></dd>
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
           <section id="pageList">
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
           </section>
     </section>
	<script>
	function userdelete(id){
		var userdel = confirm("회원탈퇴를 진행합니까?");
		 if(userdel == true){
			 location.href="/Project/memberdelete.mem?id="+id;
		 }
	}
	</script>
</body>
</html>
