<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.MemberBean"%>
<%@ page import="vo.OrderListBean"%>
<%@ page import="java.util.*"%>
<%
	MemberBean membean = (MemberBean) request.getAttribute("membean");
	int totalItem = (int) (request.getAttribute("totalItem"));
	int totalMoney = (int) (request.getAttribute("totalMoney"));
	int delivery = (int) (request.getAttribute("delivery"));
	ArrayList<OrderListBean> orderlistbean = (ArrayList<OrderListBean>) request.getAttribute("orderlistbean");
	request.setAttribute("membean", membean);

	int MaxPoint = (Integer) (request.getAttribute("maxpoint"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
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
	height: 90px;
	border-bottom: 1px solid silver;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 0px 10px;
	overflow: hidden;
	word-break: normal;
	height: 90px;
	background-color: #F6F6F6;
	font-weight: bold;
	size: 16px;
	border-top: 2px solid black;
	border-bottom: 1px solid silver;
}

.tg .tg-s6z2 {
	text-align: center
}

.tg .tg-uys7 {
	text-align: center;
}

.content {
	width: 860px;
	margin: 120px auto 30px;
}

.content h2 {
	margin: 10px 0 10px;
}

table {
	    font-family: '나눔고딕',NanumGothic,'맑은고딕',MalgunGothic,'돋움',Dotum,Helvetica,sans-serif;
	    font-size : 12px;
	box-sizing: border-box;
	width: 100%;
	border: 0;
}

.tg2 {
	width: 430px;
}

.tg2 td {
	height: 40px;
	padding : 5px 15px;
}
.infobutton{
	width: 120px;
    height: 40px;
    border: 1px solid #d0d0d0;
    color: #fff;
    background-color: #6e81a5;
    cursor: pointer;
    font-size : 14px;
        font-weight: 700;
}
.width100 {
    width: 90%;
    height: 40px;
    background: none;
    border: 1px solid silver;
    font-family: tahoma,geneva,sans-serif;
    font-size: 14px;
    margin-bottom: 5px;padding-left: 15px;
}
.zip{
    width: 30%;
    height: 40px;
    background: none;
    border: 1px solid silver;
    line-height: 25px;
    font-family: tahoma,geneva,sans-serif;
    font-size: 14px;margin-bottom: 5px; padding-left: 15px;

}
.point{
  width: 30%;
    height: 40px;
    background: none;
    border: 1px solid silver;
    line-height: 25px;
    font-family: tahoma,geneva,sans-serif;
    font-size: 14px;margin-bottom: 5px; padding-left: 40px;
}
.tg td:first-child {
	background: #EAEAEA;
}
.getter_info .tg .tg-uys7{
	text-align: left;
}
.ordercomplete{
    height: 41px;
    font-size: 14px;
    border: 0;
    cursor: pointer;
    padding: 10px 20px;
        background-color: #DD5850;
    color: #fff;
      font-weight: 700;
}
.complete_position{
	width : 98px;
	margin : 0 auto;
}
.tg img{
	height : 85px;
	width : auto;
}
</style>
</head>
<body>
	<jsp:include page="../top_menu.jsp" />
	<div id="container">
		<div id="main">
			<div class="content">
				<form action="<%=request.getContextPath()%>/orderaction.od"
					method="post">
					<input type="hidden" id="size" name="size"
						value="<%=orderlistbean.size()%>" />
					<div class="orderForm_title">
						<h2>주문/결제</h2>
					</div>
					<div class="orderInfo">
						<table class="tg">
							<tr>
								<th class="tg-s6z2">상품명</th>
								<th class="tg-uys7">상품 이미지</th>
								<th class="tg-uys7">상품가격</th>
								<th class="tg-uys7">수량</th>
								<th class="tg-uys7">총 가격</th>
								<th class="tg-uys7">적립금</th>
							</tr>
							<%
								for (int i = 0; i < orderlistbean.size(); i++) {
							%>



							<tr>
								<td class="tg-s6z2"><input type="hidden"
									id="pro_codes<%=i%>" name="pro_codes<%=i%>"
									value="<%=orderlistbean.get(i).getPro_code()%>" /> <input
									type="hidden" id="pro_qty<%=i%>" name="pro_qty<%=i%>"
									value="<%=orderlistbean.get(i).getOd_qty()%>" /> <%=orderlistbean.get(i).getPro_name()%></td>
								<td class="tg-uys7"><img
									src="/Project/boardUpload/<%=orderlistbean.get(i).getPro_image()%>"></td>
								<td class="tg-uys7"><%=orderlistbean.get(i).getPro_price()%></td>
								<td class="tg-uys7"><%=orderlistbean.get(i).getOd_qty()%></td>
								<td class="tg-uys7"><%=orderlistbean.get(i).getPro_price() * orderlistbean.get(i).getOd_qty()%></td>
								<td class="tg-uys7"><%=orderlistbean.get(i).getPro_price() * orderlistbean.get(i).getOd_qty() / 100%></td>
							</tr>
							<%
								}
							%>
						</table>
					</div>
					<h2>구매자 정보</h2>
					<div class="buyer_info">
						<table class="tg tg2">
							<tr>
								<td class="tg-uys7">이름 <input type="hidden" id="buyer_name"
									name="buyer_name" value="<%=membean.getMem_name()%>" readonly />
								</td>
								<td class="tg-uys7"><%=membean.getMem_name()%></td>
							</tr>
							<tr>
								<td class="tg-uys7">이메일<input type="hidden"
									id="buyer_email" name="buyer_email"
									value="<%=membean.getMem_email()%>" readonly />
								</td>
								<td class="tg-uys7"><%=membean.getMem_email()%></td>
							</tr>
						</table>
					</div>
					<br> <br>
					<h2>
						수취자 정보
					</h2>
					<div class="getter_info">
						<table class="tg tg2">
							<tr>
								<td class="tg-uys7">이름</td>
								<td class="tg-uys7"><input type="text" id="get_name"
									name="get_name" value="<%=membean.getMem_name()%>"  class="width100"/></td>
							</tr>
							<tr>
								<td class="tg-uys7">배송주소</td>
								<td class="tg-uys7"><input type="text" name="get_zip"
									id="get_zip" placeholder="우편번호" size="7"
									value="<%=membean.getMem_zip()%>" readonly class="zip"/>
									<button type="button" onclick="sample4_execDaumPostcode()" class="infobutton">우편번호
										찾기</button> <br> <input type="text" name="get_add" id="get_add"
									placeholder="도로명주소" value="<%=membean.getMem_add()%>" readonly class="width100"/>
									<br> <input type="text" name="get_add2" id="get_add2"
									placeholder="상세주소" value="<%=membean.getMem_add2()%>" class="width100"/></td>
							</tr>
							<tr>
								<td class="tg-uys7">전화번호</td>
								<td class="tg-uys7"><input type="text" id="get_tel"
									name="get_tel" value="<%=membean.getMem_tel()%>" class="width100"/></td>
							</tr>
							<tr>
								<td class="tg-uys7">요청사항</td>
								<td class="tg-uys7"><input type="text" id="or_request"
									name="or_request" value="빠른 배송 부탁드립니다." class="width100"/></td>
							</tr>
						</table>
					</div>
					<br> <br>
					<h2>결제정보</h2>
					<div class="result">
						<table class="tg tg2">
							<tr>
								<td class="tg-uys7"><input type="hidden" name="totalItem"
									id="totalItem" value="<%=totalItem%>" readonly /> 총 상품금액</td>
								<td class="tg-uys7"><%=totalItem%>원</td>
							</tr>
							<tr>
<%-- 								 <td class="tg-uys7">사용 가능 포인트
								</td> 
								<td class="tg-uys7"> <%=MaxPoint%>점 중<br><input type="text" name="or_point"
									id="or_point" value="0" size="5" class="point"/><br>
									<button type="button" class="infobutton">사용하기</button></td>
							</tr> --%>
							<tr>
								<td class="tg-uys7"><input type="hidden" name="delivery"
									id="delivery" value=<%=delivery%> readonly /> (+)배송비</td>
								<td class="tg-uys7"><%=delivery%>원</td>
							</tr>
							<tr>
								<td class="tg-uys7"><input type="hidden"
									name="total_result" id="total_result" value="<%=totalMoney%>"
									readonly /> 총 결제금액 </td>
								<td class="tg-uys7"><%=totalMoney%>원</td>
							</tr>
							<tr>
								<td colspan="2" class="tg-uys7"><label><input type="radio" id="gyulze" name="gyulze"
							value="cash" checked />무통장입금 </label>&nbsp;&nbsp;<label><input type="radio"
							id="gyulze" name="gyulze" value="card" />신용카드</label></td>
							</tr>
						</table>
					</div>
					<br>
					<div class="complete_position">
					<input type="submit" value="결제하기" class="ordercomplete">
					</div>
					<br>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="../footer.jsp" />


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
					document.getElementById('get_zip').value = data.zonecode;
					document.getElementById("get_add").value = roadAddr;

					//auto focus
					document.getElementById("get_add2").focus();
				}
			}).open();
		}
	</script>
</body>
</html>