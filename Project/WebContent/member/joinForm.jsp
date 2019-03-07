<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
*{
	margin : 0;
	padding : 0;
}
body {
	color: white;
	background-color: green;
}

.position {
	width: 550px;
	margin: auto;
	border : 1px solid blue;
}

.content{
	padding : 10px 45px 45px 45px;
	border : white solid pink;
	max-width: 360px;
}

.center{
	text-align: center
}

.width100{
	width : 100%;
}

.content button {
	font-family:"Nanum Gothic";
	font-weight: 700;
	text-transform: uppercase;
	outline: 0;
	background: #4CAF50;
	border: 0;
	padding: 13px;
	color: #FFFFFF;
	font-size: 16px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
}

.content input{
	border : 0;
	margin-bottom: 15px;
	padding : 15px;
	box-sizing: border-box;
	width : 100%;
}
</style>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="position">
		<div class="content">
		<form action="<%=request.getContextPath()%>/joinProcess.mem"
			name="joinform" method="post" onsubmit="return passcheck()">
			<div class="center">
			<a href="<%=request.getContextPath()%>/main.jsp">
			<img src="<%=request.getContextPath()%>/images/logo.png" width="150px"></a>
			<hr color="#f00">
			<h1>회원가입</h1>
			</div>
			
			<label for="id">아이디</label>
			<input type="text" name="id" id="id" placeholder="6~20자 영문자 또는 영문자+숫자">
		<button class="width100" name="check" id="check" type="button"
		onclick="window.open('./dulpTest.us?openInit=true','','width=500, height=300')">중복검사</button>
		<br><br>

			<label for="pass">비밀번호 </label>
			<input type="password" name="pass" id="pass" placeholder="4자리 이상" />
			<label for="pass2">비밀번호 확인 </label>
			<input type="password" name="pass2" id="pass2" />
			
			<label for="name">이름</label>
			<input type="text" name="name" id="name" />
			
				<td colspan="2"><span>주소</span></td>
				<td colspan="2"><input type="text" name="mem_zip" id="mem_zip"
					placeholder="우편번호" size="7"> <input type="button"
					onclick="sample4_execDaumPostcode()" value="우편번호 찾기"></td>
				<td colspan="2"><input type="text" name="mem_add" id="mem_add"
					placeholder="도로명주소"></td>
				<td colspan="2"><input type="text" name="mem_add2"
					id="mem_add2" placeholder="상세주소"></td>
				<td><label for="email">이메일 </label></td>
				<td><input type="text" name="email" id="email" /></td>

				<td><input type="hidden" name="grade" id="grade" value="u" /></td>

				<td><label for="tel">전화번호</label></td>
				<td><input type="tel" name="tel" id="tel" /></td>

				<td colspan="2"><hr color="#555">
				<td>
				<td colspan="2"><input type="submit" value="회원가입"></td>
		</form>
		</div>
	</div>
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