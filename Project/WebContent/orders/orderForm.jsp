<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.MemberBean"%>
<%@ page import="vo.ProductBean" %>
<% MemberBean membean = (MemberBean)request.getAttribute("membean"); 
	ProductBean probean = (ProductBean)request.getAttribute("probean");
	if(membean == null){
		membean = (MemberBean)session.getAttribute("membean");
	}
	if(probean == null){
		probean = (ProductBean)session.getAttribute("probean");
	}
	int MaxPoint = (Integer)(request.getAttribute("maxpoint"));
	int qty = Integer.parseInt(request.getParameter("qty"));
	System.out.println("orderForm.jsp : " + MaxPoint +" <-포인트, 재고-> " + qty);
	session.setAttribute("membean", membean);
	session.setAttribute("probean", probean);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    ul, li{ 
        list-style:none;
        text-align:center;
        padding:0;
        margin:0;
}

    #mainWrapper{
        width: 800px;
        margin: 0 auto; /*가운데 정렬*/
    }

    #mainWrapper > ul > li:first-child {
        text-align: center;
        font-size:14pt;
        height:40px;
        vertical-align:middle;
        line-height:30px;
}

    #ulTable {margin-top:10px;}
    

    #ulTable > li:first-child > ul > li {
        background-color:#c9c9c9;
        font-weight:bold;
        text-align:center;
}

    #ulTable > li > ul {
        clear:both;
        padding:0px auto;
        position:relative;
        min-width:40px;
}
    #ulTable > li > ul > li { 
        float:left;
        font-size:10pt;
        border-bottom:1px solid silver;
        vertical-align:baseline;
}    

    #ulTable > li > ul > li:first-child               {width:10%;} /*No 열 크기*/
    #ulTable > li > ul > li:first-child +li           {width:45%;} /*제목 열 크기*/
    #ulTable > li > ul > li:first-child +li+li        {width:20%;} /*작성일 열 크기*/
    #ulTable > li > ul > li:first-child +li+li+li     {width:15%;} /*작성자 열 크기*/
    #ulTable > li > ul > li:first-child +li+li+li+li{width:10%;} /*조회수 열 크기*/

    #divPaging {
          clear:both; 
        margin:0 auto; 
        width:220px; 
        height:50px;
}

    #divPaging > div {
        float:left;
        width: 30px;
        margin:0 auto;
        text-align:center;
}

    #liSearchOption {clear:both;}
    #liSearchOption > div {
        margin:0 auto; 
        margin-top: 30px; 
        width:auto; 
        height:100px; 

}

    .left {
        text-align : left;
}

</style>
</head>
<body>
<div id="mainWrapper">
		<form action="<%=request.getContextPath() %>/orderaction.od" method="post">

		<ul><li>결제상품</li>
			<li>
				<ul id ="ulTable">
					<li>
						<ul>
							<li>상품명</li>
							<li>수량</li>
							<li>상품가격</li>
							<li>할인적용금액</li>
						</ul>
					</li>
					<li>
						<ul>
							<li><%=probean.getPro_name() %></li>	
							<li><%=qty%></li>
							<li><%=probean.getPro_price()*qty %>	</li>
							<li>아몰랑</li>
						</ul>
					</li>
				</ul>
			</li>
		</ul>
		
		<br><h1>구매자 정보</h1>
			<input type="hidden" id="qty" name="qty" value="<%=qty %>"/>
			<label for="buyer_name">이름</label>			
			<input type="text" id="buyer_name" name="buyer_name" value="<%=membean.getMem_name() %>" readonly/>
			<br>
			<label for="buyer_email">이메일</label>				
			<input type="text" id="buyer_email" name="buyer_email" value="<%=membean.getMem_email() %>" readonly/>
			<br>
			<br>
		<h1>받는사람정보 <button>배송지변경</button></h1>		
			<label for="get_name">이름</label>
			<input type="text" id="get_name" name="get_name" value="<%=membean.getMem_name() %>"/>
			<br>
			<label for="get_zip">배송주소</label>
			<input type="text" name="get_zip" id="get_zip" placeholder="우편번호" size="7" value="<%=membean.getMem_zip()%>"/>
			<button type="button" onclick="sample4_execDaumPostcode()">우편번호 찾기</button>
			<br>
			<input type="text" name="get_add" id="get_add" placeholder="도로명주소" value="<%=membean.getMem_add() %>"/>
			<br>
			<input type="text" name="get_add2" id="get_add2" placeholder="상세주소" value="<%=membean.getMem_add2() %>"/>
			<br>
			<label for="get_tel">전화번호</label>
			<input type="text" id="get_tel" name="get_tel" value="<%=membean.getMem_tel()%>"/>
			<br>
			<label for="or_request">배송 요청사항</label>
			<input type="text" id="or_request" name="or_request"/>
		
		
		<h1>결제 금액</h1>
			<label for="item_result">총 상품금액</label>	
			<input type="text" name="item_result" id="item_result"
			value="<%=probean.getPro_price()*qty %>"readonly/>
			<br>
			
			<label for="or_point">사용 가능 포인트  <%=MaxPoint %>점 중</label>
			<input type="text" name="or_point" id="or_point" value="0"/>
			<button type="submit">사용하기</button>
			<br>
			<label for="delivery">(+)배송비</label>
			<input type="text" name="delivery" id="delivery" value="2500" readonly/>
			<br>
			<label for="total_result">총 결제금액</label>
			<input type="text" name="total_result" id="total_result" 
			value="<%=probean.getPro_price()*qty+2500 %>"readonly/>
			<br>
			<p>결제방법</p>
			<label><input type="radio" id="gyulze" name="gyulze" value="cash" checked/>무통장입금</label>
			<label><input type="radio" id="gyulze" name="gyulze" value="card" />신용카드</label>
			
			
			<input type="submit" value="결제하기">
		</form>
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