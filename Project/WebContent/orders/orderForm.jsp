<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.MemberBean"%>
<%@ page import="vo.ProductBean" %>
<% MemberBean membean = (MemberBean)request.getAttribute("membean"); 
	ProductBean probean = (ProductBean)request.getAttribute("probean");
	int qty = Integer.parseInt(request.getParameter("qty"));
	System.out.println("qty값 보자 : " + qty);
	session.setAttribute("membean", membean);
	session.setAttribute("probean", probean);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	border:1px solid black;
}
</style>
</head>
<body>

		<form action="<%=request.getContextPath() %>/orderaction.od" method="post">
		<h1>구매자 정보</h1>
			<input type="hidden" id="qty" name="qty" value="<%=qty %>"/>
			<label for="buyer_name">이름</label>			
			<input type="text" id="buyer_name" name="buyer_name" value="<%=membean.getMem_name() %>"/>
			<br>
			<label for="buyer_email">이메일</label>				
			<input type="text" id="buyer_email" name="buyer_email" value="<%=membean.getMem_email() %>"/>
			<br>
			<label for="buyer_tel">전화번호</label>
			<input type="text" id="buyer_tel" name="buyer_tel" value="<%=membean.getMem_tel()%>"/>
			<br>
		<h1>받는사람정보 <button>배송지변경</button></h1>		
			<label for="gain_name">이름</label>
			<input type="text" id="gain_name" name="gain_name" value="<%=membean.getMem_name() %>"/>
			<br>
			<label for="gain_zip">배송주소</label>
			<input type="text" name="gain_zip" id="gain_zip" placeholder="우편번호" size="7" value="<%=membean.getMem_zip()%>"/>
			<button type="button" onclick="sample4_execDaumPostcode()">우편번호 찾기</button>
			<br>
			<input type="text" name="gain_add" id="gain_add" placeholder="도로명주소" value="<%=membean.getMem_add() %>"/>
			<br>
			<input type="text" name="gain_add2" id="gain_add2" placeholder="상세주소" value="<%=membean.getMem_add2() %>"/>
			<br>
			<label for="or_request">배송 요청사항</label>
			<input type="text" id="or_request" name="or_request"/>
		
		
		<h1>결제 금액</h1>
			<label for="item_result">총 상품금액</label>	
			<input type="text" name="item_result" id="item_result"
			value="<%=probean.getPro_price()*qty %>"readonly/>
			<br>
			<label for="point">(-)포인트 사용금액</label>
			<input type="text" name="point" id="point" />
			<button type="submit">사용하기</button>
				<label for="delivery">(+)배송비</label>
			<input type="text" name="delivery" id="delivery" value="2500" readonly/>
			<br>
			<label for="total_result">총 결제금액</label>
			<input type="text" name="total_result" id="total_result" 
			value="<%=probean.getPro_price()*qty+2500 %>"readonly/>
			결제방법
			
			<input type="submit" value="결제하기">
		</form>
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
					document.getElementById('gain_zip').value = data.zonecode;
					document.getElementById("gain_add").value = roadAddr;

					//auto focus
					document.getElementById("gain_add2").focus();
				}
			}).open();
		}
	</script>
</body>
</html>