<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.ArrayList" %>
    <%@page import="vo.OrderListBean" %>
<%
	ArrayList<OrderListBean> ordersimplelist = (ArrayList<OrderListBean>)request.getAttribute("ordersimplelist");

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

.simple_img{
	width : 90px;
	height: 90px;
}
</style>
</head>
<body>
 <div id="mainWrapper">

        <ul>
            <!-- 게시판 제목 -->
            <li>게시판 Title </li>

            <!-- 게시판 목록  -->
            <li>
                Table
                <%if(ordersimplelist != null){ %>
                <ul id ="ulTable">
                    <li>
                        <ul>
                            <li>주문일시</li>
                            <li>주문상품</li>
                            <li>상품이름</li>
                            <li>결제금액</li>
                            <li>주문현황</li>
                        </ul>
                    </li>
                    <!-- 게시물이 출력될 영역 -->


<% 
for(int i=0; i<ordersimplelist.size(); i++){
	int totalMoney = ordersimplelist.get(i).getPro_price()*ordersimplelist.get(i).getOd_qty();
	int delivery = 2500;
	if(totalMoney >30000){
		delivery = 0;
	}
	totalMoney += delivery;
	out.println("<li><ul><li>" + ordersimplelist.get(i).getOr_date()+"</li>"); //주문일시
	out.println("<li>" + "<img src='/Project/boardUpload/" +ordersimplelist.get(i).getPro_image()  +"'class='simple_img'></li>"); //상품 이미지
	out.println("<li>" + ordersimplelist.get(i).getPro_name() +"</li>"); //상품이름
	out.println("<li>" + totalMoney +"</li>"); //결제금액
	out.println("<li>" + ordersimplelist.get(i).getOr_state()+"</li></ul></li>"); //주문상태
}
%>
                </ul>
                <%
                }else{
                	out.println("구매내역이 없습니다.");
                }
                %>
                
                
            </li>

            <!-- 게시판 페이징 영역 -->
            <li>
                <div id="divPaging">
                    <div>◀</div>
                       <div><b>1</b></div>
                    <div>2</div>
                    <div>3</div>
                    <div>4</div>
                    <div>5</div>
                    <div>▶</div>
                </div>
            </li>

            <!-- 검색 폼 영역 -->
            <li id='liSearchOption'>
                <div>
                    <select id='selSearchOption' >
                        <option value='A'>제목+내용</option>
                        <option value='T'>제목</option>
                        <option value='C'>내용</option>
                    </select>
                    <input id='txtKeyWord' />
                    <input type='button' value='검색'/>
                </div>
                </li>

        </ul>
    </div>

</body>
</html>