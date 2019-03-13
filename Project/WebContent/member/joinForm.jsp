<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
* {
	margin: 0;
	padding: 0;
	font-size : 15px;
}

h1{
	font-size : 26px;
}

body {
	color: white;
}

.position:before {
	content: "";
	position: fixed;
	left: 0;
	right: 0;
	z-index: -1;
	display: block;
	background-image: url("/Project/images/join.jpg");
	width: 100%;
	height: 100%;
	-webkit-filter: blur(5px);
	-moz-filter: blur(5px);
	-o-filter: blur(5px);
	-ms-filter: blur(5px);
	filter: blur(5px);
}

.position {
	z-index: 0;
	width: 550px;
	margin: auto;
}

.content {
	background-color: rgba(0,0,0,0.7);
	padding: 10px 45px 45px 45px;
	margin-left: auto;
	margin-right: auto;
	border: white solid pink;
	max-width: 360px;
	box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
		rgba(0, 0, 0, 0.3);
}

.center {
	text-align: center
}

.width100 {
	width: 100%;
}

.content button, input[type=button], input[type=submit] {
	font-family: "Nanum Gothic";
	font-weight: 700;
	text-transform: uppercase;
	outline: 0;
	background: #abc;
	border: 0;
	padding: 13px;
	color: #FFFFFF;
	font-size: 16px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
}

.content input {
	border: 0;
	margin-bottom: 15px;
	padding: 15px;
	box-sizing: border-box;
}
</style>


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div class="position">
			<div class="content">
				<form action="<%=request.getContextPath() %>/joinProcess.mem"
					name="join" method="post" onsubmit="return validate()">
					<div class="center">
						<a href="<%=request.getContextPath()%>/main.jsp"> <img
							src="<%=request.getContextPath()%>/images/logo.png" width="150px"></a>
						<hr color="#abc">
						<h1>Sing up</h1>
					</div>

					<label for="id">아이디</label> <input type="text" name="id" id="id"
						placeholder="4~20자 영문자 또는 영문자+숫자" class="width100">
						
					<button class="width100" name="check" id="check" type="button"
						onclick="window.open('./dulpTest.us?openInit=true','','width=500, height=300')">중복검사</button>
						
					<br>
					<br> <label for="pass">비밀번호 </label> <input type="password"
						name="pass" id="pass" placeholder="4~20자리 영문자 또는 영문자+숫자" class="width100" /> <label
						for="pass2">비밀번호 확인 </label> <input type="password" name="pass2"
						id="pass2" class="width100" /> <label for="name">이름</label> <input
						type="text" name="name" id="name" class="width100" />

					<label for="mem_zip">주소</label><br>
					<input type="text" name="mem_zip" id="mem_zip" placeholder="우편번호" size="7" />
					<button type="button" onclick="sample4_execDaumPostcode()">우편번호 찾기</button>
						<input type="text" name="mem_add" id="mem_add" placeholder="도로명주소"  class="width100" />
					<input type="text" name="mem_add2" id="mem_add2" placeholder="상세주소"  class="width100" />

					<label for="email">이메일 </label> <input type="text" name="email"
						id="email" class="width100" value=""/> <input type="hidden" name="grade"
						id="grade" value="u" /> <label for="tel">전화번호</label>
						<input type="tel" name="tel" id="tel" class="width100" />
						<button type="submit" class="width100">회원가입</button>
				</form>
			</div>
		</div>
		
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script>
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
 
                if(join.id.value=="") {
                    alert("아이디를 입력해 주세요");
                    join.id.focus();
                    return false;
                }
                if(!check(re,id,"아이디는 4~20자의 영문 대소문자와 숫자로만 입력")) {
                    return false;
                }
 
                if(join.pass.value=="") {
                    alert("비밀번호를 입력해 주세요");
                    join.pass.focus();
                    return false;
                }
                if(!check(re,pass,"패스워드는 4~20자의 영문 대소문자와 숫자로만 입력")) {
                    return false;
                }
 
                if(join.pass.value != join.pass2.value) {
                    alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
                    join.pass2.value = "";
                    join.pass2.focus();
                    return false;
                }
                if(join.name.value=="") {
                    alert("이름을 입력해 주세요");
                    join.name.focus();
                    return false;
                }
                
                if(join.mem_zip.value=="") {
                    alert("우편번호를 입력해 주세요");
                    join.mem_zip.focus();
                    return false;
                }
                if(join.mem_add.value=="") {
                    alert("도로명주소를 입력해 주세요");
                    join.mem_add.focus();
                    return false;
                }
                if(join.mem_add2.value=="") {
                    alert("상세주소를 입력해 주세요");
                    join.mem_add2.focus();
                    return false;
                }
                if(email.value=="") {
                    alert("이메일을 입력해 주세요");
                    email.focus();
                    return false;
                }
 
                if(!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
                    return false;
                }
 
                if(join.tel.value=="") {
                    alert("전화번호를 입력해 주세요");
                    join.tel.focus();
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