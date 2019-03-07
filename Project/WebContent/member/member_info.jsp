<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.MemberBean"%>
<jsp:include page="../top_menu.jsp" />
<!DOCTYPE html>
<html>
<head>
<style>
table {
	margin: auto;
	width: 400px;
	border: 1px solid gray;
	text-align: center;
}

.td_titel {
	font-weight: bold;
	font-size: x-large;
}
</style>
<%
	MemberBean member = (MemberBean) request.getAttribute("member");
%>
<script>
	function modisubmit() {
		modify.submit();
	}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="modify" action="<%=request.getContextPath()%>/modifyproaction.mem" method="post"
		onsubmit="return passcheck()">
		<table border=1>
			<tr>
				<td colspan="2" class="td_title">
					<h3>회원 정보</h3>
				</td>
			</tr>
			<tr>
				<td><label for="id">아이디 : </label></td>
				<td><input type="text" name="id" id="id" readonly
					value="<%=member.getMem_id()%>" /></td>

			</tr>
			<tr>
				<td><label for="pass">비밀번호 : </label></td>
				<td><input type="password" name="pass" id="pass"
					value="<%=member.getMem_pass()%>" /></td>
			</tr>
			<tr>
				<td><label for="pass2">비밀번호 확인 </label></td>
				<td><input type="password" name="pass2" id="pass2"
					value="<%=member.getMem_pass()%>" /></td>
			</tr>
			<tr>
				<td><label for="name">이름 : </label></td>
				<td><input type="text" name="name" id="name"
					value="<%=member.getMem_name()%>" /></td>
			</tr>

			<tr>
				<td colspan="2"><label for="add">주소</label></td>
			</tr>
			<tr>
				<td colspan="2"><input type="text" name="mem_zip" id="mem_zip"
					placeholder="우편번호" size="7" value="<%=member.getMem_zip()%>">
					<input type="button" onclick="sample4_execDaumPostcode()"
					value="우편번호 찾기"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="text" name="mem_add" id="mem_add"
					placeholder="도로명주소" value="<%=member.getMem_add()%>"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="text" name="mem_add2"
					id="mem_add2" placeholder="상세주소" value="<%=member.getMem_add2()%>"></td>
			</tr>
			<tr>
				<td><label for="email">이메일 </label></td>
				<td><input type="text" name="email" id="email"
					value="<%=member.getMem_email()%>" /></td>
			</tr>

			<tr>
				<td><label for="grade">등급 </label></td>
				<td><input type="text" name="grade" id="grade"
					value="<%
					if(member.getMem_grade().equals("s")){
					%><%="관리자등급" %>
					<%}else if(member.getMem_grade().equals("u")){
					%><%="일반등급" %>	
					<%} %>
					" readonly /></td>
			</tr>
			<script>
			function gradecheck{
				var grade = document.getElementById("grade").value;
				if(grade.equals('s')){
					document.getElementById("grade").value = "관리자 등급";
				}else if(grade.equals('u')){
					document.getElementById("grade").value = "일반 등급";
				}else{
					document.getElementById("grade").value = "ㅋㅋ뭔가안됨";
				}
			}
			</script>
			<tr>
				<td><label for="date">가입일 </label></td>
				<td><input type="text" name="date" id="date"
					value="<%=member.getMem_date() %>" readonly /></td>
			</tr>
			<tr>
				<td><label for="tel">전화번호</label></td>
				<td><input type="tel" name="tel" id="tel"
					value="<%=member.getMem_tel()%>" /></td>
			</tr>
			<tr>
			<tr>
				<td colspan="2"><a href="javascript:modify.submit()">수정하기</a>
					&nbsp;&nbsp; <a href="javascript:modify.reset()">초기화</a>
			</tr>
		</table>
	</form>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script>
		//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
		function sample4_execDaumPostcode() {
			new daum.Postcode({
				oncomplete : function(data) {
					// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

					// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
					// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
					var roadAddr = data.roadAddress; // 도로명 주소 변수
					var extraRoadAddr = ''; // 참고 항목 변수

					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraRoadAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraRoadAddr += (extraRoadAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}

					// 우편번호와 주소 정보를 해당 필드에 넣는다.
					document.getElementById('mem_zip').value = data.zonecode;
					document.getElementById("mem_add").value = roadAddr;

					//auto focus
					document.getElementById("mem_add2").focus();
				}
			}).open();
		}
	</script>
	<script>
		function passcheck() {
			var pass = document.getElementById("pass").value;
			var pass2 = document.getElementById("pass2").value;

			if (pass != pass2) {
				alert('비밀번호가 틀렸습니다. 다시 입력해주세요');
				return false;
			}
		}
	</script>
</body>
</html>