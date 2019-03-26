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
		session.setAttribute("cartList", cartList);
	}
	int cartResult = 0;
	int delivery = 2500;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 리스트</title>
<style>
#cart_form {
	margin: 0 auto;
	width: 1000px;
}

h2 img {
	height: 53px;
	width: auto;
}

h1 {
	line-height: 50px;
	vertical-align: middle;
}

table {
	width: 1000px;
	border-spacing: 0px;
	text-align: center;
}

table tr:first-child td {
	background: #D5D5D5;
	border-top: 1px solid gray;
}

table tr td {
	border-bottom: 1px solid gray;
}

.select {
	height: 41px;
	font-size: 14px;
	font-weight: bold;
	border: 0;
	cursor: pointer;
	padding: 10px 20px;
}

.select1 {
	background-color: #d81818;
	color: #fff;
}

.select2 {
	background-color: #6e81a5;
	color: #fff;
}

#cart_button {
	float: right;
	margin-right: 2px;
	padding-top: 10px;
}

.cart_button_opt {
	cursor: pointer;
	display: inline-block;
	position: relative;
}

.btn1 button[type=button] {
	background-color: #d81818;
	height: 23px;
	color: #fff;
	border: 1px solid black;
	cursor: pointer;
	font-size: 13px;
	width: 100%;
	padding: 3px 6px;
}

.btn2 button[type=button] {
	background-color: #fff;
	height: 23px;
	color: #000;
	border: 1px solid black;
	cursor: pointer;
	font-size: 13px;
	width: 100%;
	padding: 3px 6px;
}

.cart_title {
	color : white;
	font-size: 2em;
	display: inline-block;
	position: absolute;
	margin: auto;
	top: 28%;
	left: 50%;
	transform: translate(-50%, -50%);
}

#headerImage {
	width: 100%;
	height: 477px;
		background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)),
	url('headerImage/back05.jpg');
	background-position: 50% 50%;
	background-size: cover;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0;
}
</style>
</head>
<body>
	<jsp:include page="../top_menu.jsp" />
	<div id="container">
		<div id="main">
			<div id="headerImage">
			<div class="cart_title">
				<h2>
					<img src="<%=request.getContextPath()%>/icon/cart.png"> 장바구니
				</h2>
			</div>
			</div>
			<div id="cart_form">
				<h3 style="text-align: right;">3만원 이상 무료배송</h3>
				<hr style="border: 0; background: #aaa; height: 5px;">
				<br> <br>
				<form name="cartForm" method="post" action="orderForm.od?type=sel">

					<%
						if (cartList == null || cartList.size() == 0) {
					%>
					<table>
						<tr>
							<td>
								<h1>장바구니에 상품이 없습니다.</h1>
							</td>
						</tr>
					</table>
					<%
						} else {
					%>


					<table>
						<tr>
							<td>번호</td>
							<td><input type="checkbox" id="checkAll" name="checkAll"
								onclick="javascript:CheckAll(this.form)" checked /></td>
							<td>상품이미지</td>
							<td>상품명</td>
							<td>가격</td>
							<td>수량</td>
							<td colspan="2">옵션</td>
						</tr>
						<%
							for (int i = 0; i < cartList.size(); i++) {
						%>
						<tr>
							<td><%=i + 1%></td>

							<td><input type="hidden" id="cart_num<%=i%>"
								name="cart_num<%=i%>" value="<%=cartList.get(i).getCart_num()%>" />
								<input type="checkbox" id="checklist" name="checklist"
								value="<%=cartList.get(i).getPro_code()%>" checked /></td>
							<td><img
								src="/Project/boardUpload/<%=cartList.get(i).getPro_image()%>"
								id="cartImage" width="100px" /></td>
							<td><input type="hidden" name="name" id="name"
								value="<%=cartList.get(i).getPro_name()%>" /> <span><%=cartList.get(i).getPro_name()%></span>
							</td>
							<td><input type="hidden" name="price" id="price"
								value="<%=cartList.get(i).getPro_price()%>원" /> <span><%=cartList.get(i).getPro_price()%></span>
							</td>
							<td><input type="text" name="qty<%=i%>" id="qty<%=i%>"
								value="<%=cartList.get(i).getCart_qty()%>" size="3" /></td>
							<td>
								<div class="cart_button_opt">
									<div class="btn btn1" style="margin-bottom: 4px;">
										<button type="button" value="수량변경"
											onclick="cartForm.action='/Project/cartQtyChnage.od?index=<%=i%>';cartForm.submit();">
											수량변경</button>
									</div>
									<div class="btn btn2">
										<button type="button" value="삭제하기"
											onclick="cartForm.action='/Project/cartDelete.od?index=<%=i%>';cartForm.submit();">
											삭제하기</button>
									</div>
								</div>
							</td>
						</tr>




						<%
							//상품 전체 합계
									cartResult += cartList.get(i).getPro_price() * cartList.get(i).getCart_qty();
								}
								//상품 전체 금액이 3만원 이상일 경우 배송비 0원
								if (cartResult >= 30000) {
									delivery = 0;
								}
						%>



					</table>

					<div>
						<h2>
							총 상품가격
							<%=cartResult%>
							+ 배송비
							<%=delivery%>
							= 총 주문금액 :
							<%=cartResult + delivery%>원
						</h2>
					</div>
					<%
						}
					%>



					<input type="hidden" value="<%=cartList%>" />
					<div id="cart_button">
						<button type="button" class="select select1"
							onclick="location.href='/Project/productList.bo';">계속
							쇼핑하기</button>
						<button type="button" class="select select2"
							onclick="location.href='orderForm.od?type=sel', cartForm.submit();">구매하기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
	<script>
		var check = true;
		function CheckAll() {
			var chk = document.getElementsByName("checklist");
			if (check == true) {
				check = false;
				for (var i = 0; i < chk.length; i++) {
					chk[i].checked = false;
				}
			} else {
				check = true;
				for (var i = 0; i < chk.length; i++) {
					chk[i].checked = true;
				}
			}
		}
	</script>
</body>
</html>