<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.CartProViewBean"%>
<%
	ArrayList<CartProViewBean> cartList = null;
	String id = null;

	if (session.getAttribute("id") != null) {
		id = (String) session.getAttribute("id");
	}

	//비로그인 상태
	if (session.getAttribute("id") == null) {
		cartList = (ArrayList<CartProViewBean>) session.getAttribute("cartList");
		//로그인 상태
	} else {
		cartList = (ArrayList<CartProViewBean>) request.getAttribute("cartList");
	}

	int cartResult = 0;
	int shipping = 3000;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 리스트</title>
<script>
	/* 	function checkAll(theForm) {
	 if (theForm.remove.length == undefined) {
	 theForm.remove.checked = theForm.checkAll.checked;
	 } else {
	 for (var i = 0; i < theForm.remove.length; i++) {
	 theForm.remove[i].checked = theForm.checkAll.checked;
	 }
	 }
	 }
	 */
</script>
<script>
	var check = false;
	function CheckAll() {
		var chk = document.getElementsByName("checklist");
		if (check == false) {
			check = true;
			for (var i = 0; i < chk.length; i++) {
				chk[i].checked = true; //모두 체크
			}
		} else {
			check = false;
			for (var i = 0; i < chk.length; i++) {
				chk[i].checked = false; //모두 해제
			}
		}
	}

	function SelectCheck() {
		var chk = document.getElementsByName("checklist"); // 체크박스객체를 담는다
		var len = chk.length; //체크박스의 전체 개수
		var checkRow = ''; //체크된 체크박스의 value를 담기위한 변수
		var checkCnt = 0; //체크된 체크박스의 개수
		var checkLast = ''; //체크된 체크박스 중 마지막 체크박스의 인덱스를 담기위한 변수
		var rowid = ''; //체크된 체크박스의 모든 value 값을 담는다
		var cnt = 0;
		for (var i = 0; i < len; i++) {
			if (chk[i].checked == true) {
				checkCnt++; //체크된 체크박스의 개수
				checkLast = i; //체크된 체크박스의 인덱스
			}
		}
		for (var i = 0; i < len; i++) {
			if (chk[i].checked == true) { //체크가 되어있는 값 구분
				checkRow = chk[i].value;
				if (checkCnt == 1) { //체크된 체크박스의 개수가 한 개 일때,
					rowid += "'" + checkRow + "'"; //'value'의 형태 (뒤에 ,(콤마)가 붙지않게)
				} else { //체크된 체크박스의 개수가 여러 개 일때,
					if (i == checkLast) { //체크된 체크박스 중 마지막 체크박스일 때,
						rowid += "'" + checkRow + "'"; //'value'의 형태 (뒤에 ,(콤마)가 붙지않게)
					} else {
						rowid += "'" + checkRow + "',"; //'value',의 형태 (뒤에 ,(콤마)가 붙게)         			
					}
				}
				cnt++;
				checkRow = ''; //checkRow초기화.
			}
		}
		alert(rowid); //'value1', 'value2', 'value3' 의 형태로 출력된다.
	}
</script>
</head>
<body>
	<form name="cartForm" method="post">

		<%
			if (cartList == null || cartList.size() == 0) {
		%>
		<h1>장바구니에 상품이 없습니다.</h1>
		<%
			} else {
		%>

		<table>
			<h3>3만원 이상 무료배송</h3>
			<tr>
				<td><input type="checkbox" id="checkAll" name="checkAll"
					onclick="javascript:CheckAll(this.form)" /></td>
				<td>번호</td>
				<td>상품이미지</td>
				<td>상품명</td>
				<td>가격</td>
				<td>수량</td>
			</tr>
			<%
				for (int i = 0; i < cartList.size(); i++) {
			%>
			<tr>
				<td><%=i + 1%></td>

				<td><input type="hidden" id="cart_num<%=i%>"
					name="cart_num<%=i%>" value="<%=cartList.get(i).getCart_num()%>" />
					<input type="checkbox" id="checklist" name="checklist"
					value="<%=cartList.get(i).getPro_code()%>" /></td>
				<td><img
					src="../boardUpload/<%=cartList.get(i).getPro_image()%>"
					id="cartImage" width="100px" /></td>
				<td><input type="text" name="name" id="name"
					value="<%=cartList.get(i).getPro_name()%>" /></td>
				<td><input type="text" name="price" id="price"
					value="<%=cartList.get(i).getPro_price()%>원" /></td>
				<td><input type="text" name="qty<%=i%>" id="qty<%=i%>"
					value="<%=cartList.get(i).getCart_qty()%>" /></td>

				<td><input type="button" value="수량 변경"
					onclick="cartForm.action='/Project/cartQtyChnage.od?index=<%=i%>';cartForm.submit();" /></td>
				<td><input type="button" value="삭제"
					onclick="cartForm.action='/Project/cartDelete.od?index=<%=i%>';cartForm.submit();" />
				</td>
			</tr>





			<%
				//상품 전체 합계
						cartResult += cartList.get(i).getPro_price() * cartList.get(i).getCart_qty();
					}

					//상품 전체 금액이 3만원 이상일 경우 배송비 0원
					if (cartResult >= 30000) {
						shipping = 0;
					}
			}
			%>



		</table>
	</form>
	
	<script>
		var check = false;
		function CheckAll() {
			var chk = document.getElementsByName("checklist");
			if (check == false) {
				check = true;
				for (var i = 0; i < chk.length; i++) {
					chk[i].checked = true; //모두 체크
				}
			} else {
				check = false;
				for (var i = 0; i < chk.length; i++) {
					chk[i].checked = false; //모두 해제
				}
			}
		}
		/* path : 전송 URL
		 * params : 전송 데이터 {'q':'a','s':'b','c':'d'...}으로 묶어서 배열 입력
		 * method : 전송 방식(생략가능)		 */
		function post_to_url(path, params, method) {
		    method = method || "post"; // Set method to post by default, if not specified.
		    // The rest of this code assumes you are not using a library.
		    // It can be made less wordy if you use one.
		    var form = document.createElement("form");
		    form.setAttribute("method", method);
		    form.setAttribute("action", path);
		    for(var key in params) {
		        var hiddenField = document.createElement("input");
		        hiddenField.setAttribute("type", "hidden");
		        hiddenField.setAttribute("name", key);
		        hiddenField.setAttribute("value", params[key]);
		        form.appendChild(hiddenField);
		    }
		    document.body.appendChild(form);
		    form.submit();
		}
		</script>
</body>
</html>