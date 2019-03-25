<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.MemberBean"%>
<jsp:include page="../top_menu.jsp" />
<!DOCTYPE html>
<html>
<head>
<style>
.memlist_title {
	margin: 10px 0 10px;
}

.tg {
	border-collapse: collapse;
	border-spacing: 0;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 0px 10px;
	overflow: hidden;
	word-break: normal;
	border-bottom: 1px solid silver;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 0px 10px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: black;
	height: 40px;
	background-color: #c9c9c9;
	font-weight: bold;
	size: 16px;
}

.tg .tg-s6z2 {
	background: #EAEAEA;
	width: 25%;
	padding: 5px 5px 5px 10px;
}

.tg .tg-uys7 {
	text-align: left;
	width: 75%;
	padding: 5px 5px 5px 10px;
}

.content {
	width: 600px;
	margin: 0 auto;
}

table {
	width: 100%;
}



label {
	font-size: 16px;
}
.button1{
	    font-family: "Nanum Gothic";
    font-weight: 700;
    text-transform: uppercase;
    outline: 0;
    background: white;
    width: 100px;
    border: 1px solid #333;
    padding: 10px;
    padding-top: 10px;
    padding-right: 10px;
    padding-bottom: 10px;
    padding-left: 10px;
    color: black;
    font-size: 14px;
    cursor: pointer;
}
.button2{
    font-family: "Nanum Gothic";
    font-weight: 700;
    text-transform: uppercase;
    outline: 0;
    background: #d81818;
    width: 100px;
    border: 1px solid #333;
    padding: 10px;
    padding-top: 10px;
    padding-right: 10px;
    padding-bottom: 10px;
    padding-left: 10px;
    color: white;
    font-size: 14px;
    cursor: pointer;
}
.button_group{
	text-align: center;
}
.input_style{
	width: 90%;
    height: 40px;
    background: none;
    border: 1px solid silver;
    font-family: tahoma,geneva,sans-serif;
    font-size: 14px;
    margin-bottom: 5px;
    padding-left: 15px;
}
.zip{
    width: 30%;
    height: 40px;
    background: none;
    border: 1px solid silver;
    line-height: 25px;
    font-family: tahoma,geneva,sans-serif;
    font-size: 14px;
    margin-bottom: 5px;
    padding-left: 15px;
}
.infobutton {
    width: 120px;
    height: 40px;
    border: 1px solid #d0d0d0;
    color: #fff;
    background-color: #6e81a5;
    cursor: pointer;
    font-size: 14px;
    font-weight: 700;
}
</style>
<%
	MemberBean member = (MemberBean)request.getAttribute("member");
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
	<div id="container">
		<div id="main">
			<div class="content">

				<div class="memlist_title">
					<h2>회원정보 수정</h2>
				</div>
				<form name="modify"
					action="<%=request.getContextPath()%>/modifyproaction.mem"
					method="post" onsubmit="return validate();">
					<table class="tg">
						<tr>
							<td class="tg-s6z2"><label for="id">아이디 : </label></td>
							<td class="tg-uys7"><input type="text" name="id" id="id"
								readonly value="<%=member.getMem_id()%>" class="input_style"/></td>
						</tr>
						<tr>
							<td class="tg-s6z2"><label for="id">비밀번호 : </label></td>
							<td class="tg-uys7"><input type="password" name="pass"
								id="pass" value="<%=member.getMem_pass()%>" class="input_style"/></td>
						</tr>
						<tr>
							<td class="tg-s6z2"><label for="id">비밀번호 확인</label></td>
							<td class="tg-uys7"><input type="password" name="pass2"
								id="pass2" value="<%=member.getMem_pass()%>" class="input_style"/></td>
						</tr>
						<tr>
							<td class="tg-s6z2"><label for="id">이름 : </label></td>
							<td class="tg-uys7"><input type="text" name="name" id="name"
								value="<%=member.getMem_name()%>" class="input_style"/></td>
						</tr>
						<tr>
							<td class="tg-s6z2"><label for="add">주소</label></td>
							<td class="tg-uys7"><input type="text" name="mem_zip" class="zip"
								id="mem_zip" placeholder="우편번호" size="7"
								value="<%=member.getMem_zip()%>"> <input type="button" class="infobutton"
								onclick="sample4_execDaumPostcode()" value="우편번호 찾기"> <br>
								<input type="text" class="input_style" name="mem_add" id="mem_add"
								placeholder="도로명주소" value="<%=member.getMem_add()%> "> <br>
								<input type="text" class="input_style" name="mem_add2" id="mem_add2"
								placeholder="상세주소" value="<%=member.getMem_add2()%>"></td>
						</tr>
						<tr>
							<td class="tg-s6z2"><label for="email">이메일 </label></td>
							<td class="tg-uys7"><input type="text" class="input_style" name="email"
								id="email" value="<%=member.getMem_email()%>" /></td>
						</tr>
						<tr>
							<td class="tg-s6z2"><label for="grade">등급 </label></td>
							<td class="tg-uys7"><input type="text" name="grade" class="input_style"
								id="grade"
								value="<%if (member.getMem_grade().equals("s")) {%><%="관리자등급"%>
					<%} else if (member.getMem_grade().equals("u")) {%><%="일반등급"%>	
					<%}%>
					"
								readonly /></td>
						</tr>
						<tr>
							<td class="tg-s6z2"><label for="date">가입일 </label></td>
							<td class="tg-uys7"><input type="text" class="input_style" name="date" id="date"
								value="<%=member.getMem_date()%>" readonly /></td>
						</tr>
						<tr>
							<td class="tg-s6z2"><label for="tel">전화번호</label></td>
							<td class="tg-uys7"><input type="tel" class="input_style" name="tel" id="tel"
								value="<%=member.getMem_tel()%>" /></td>
						</tr>

					</table>
					<br><br>
					<div class="button_group">
					<button type="reset" class="button1">초기화</button>
					<button type="submit" class="button2">수정하기</button>
					<button type="button" class="button1" onClick="javascript:userdelete('<%=session.getAttribute("id") %>');">회원탈퇴</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
	function userdelete(id){
		var userdel = confirm("회원탈퇴를 진행합니까?");
		 if(userdel == true){
			 location.href="<%=request.getContextPath()%>/memberdelete.mem?id="
						+ id;
			}
		}
	</script>


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
		function validate() {
			var re = /^[a-zA-Z0-9]{4,20}$/ // 아이디와 패스워드가 적합한지 검사할 정규식
			var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			// 이메일이 적합한지 검사할 정규식

			var id = document.getElementById("id");
			var pw = document.getElementById("pass");
			var email = document.getElementById("email");

			// ------------ 이메일 까지 -----------

			if (modify.id.value == "") {
				alert("아이디를 입력해 주세요");
				modify.id.focus();
				return false;
			}
			if (!check(re, id, "아이디는 4~20자의 영문 대소문자와 숫자로만 입력")) {
				return false;
			}

			if (modify.pass.value == "") {
				alert("비밀번호를 입력해 주세요");
				modify.pass.focus();
				return false;
			}
			if (!check(re, pass, "패스워드는 4~20자의 영문 대소문자와 숫자로만 입력")) {
				return false;
			}

			if (modify.pass.value != modify.pass2.value) {
				alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
				modify.pass2.value = "";
				modify.pass2.focus();
				return false;
			}
			if (modify.name.value == "") {
				alert("이름을 입력해 주세요");
				modify.name.focus();
				return false;
			}

			if (modify.mem_zip.value == "") {
				alert("우편번호를 입력해 주세요");
				modify.mem_zip.focus();
				return false;
			}
			if (modify.mem_add.value == "") {
				alert("도로명주소를 입력해 주세요");
				modify.mem_add.focus();
				return false;
			}
			if (modify.mem_add2.value == "") {
				alert("상세주소를 입력해 주세요");
				modify.mem_add2.focus();
				return false;
			}
			if (email.value == "") {
				alert("이메일을 입력해 주세요");
				email.focus();
				return false;
			}

			if (!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
				return false;
			}

			if (modify.tel.value == "") {
				alert("전화번호를 입력해 주세요");
				modify.tel.focus();
				return false;
			}

		}

		function check(re, what, message) {
			if (re.test(what.value)) {
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