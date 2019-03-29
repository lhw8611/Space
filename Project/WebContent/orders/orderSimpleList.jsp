<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.ArrayList" %>
    <%@page import="vo.OrOdProViewBean" %>
    <%@page import="vo.PageInfo" %>
<%
	ArrayList<OrOdProViewBean> orodproviewbean = (ArrayList<OrOdProViewBean>)request.getAttribute("orodproviewbean");
	PageInfo pageinfo = (PageInfo)request.getAttribute("pageinfo");
    int listCount = pageinfo.getListCount();
    int nowPage = pageinfo.getPage();
    int maxPage = pageinfo.getMaxPage();
    int startPage = pageinfo.getStartPage();
    int endPage = pageinfo.getEndPage();
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
        width: 1000px;
        margin: 0 auto; /*가운데 정렬*/
    }

    #mainWrapper > ul > li:first-child {
        text-align: center;
        font-size:16px;
        height:40px;
        vertical-align:middle;
        line-height:30px;
}

    #ulTable {margin-top:10px;}
    

    #ulTable > li:first-child > ul > li {
        background-color:#EAEAEA;
        font-weight:bold;
        size : 16px;
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
        text-align:center;
      	border-bottom:1px solid silver;
        vertical-align:baseline;
        line-height: 90px;
        height : 90px;
}    

    #ulTable > li > ul > li:first-child               {width:10%;} 
    #ulTable > li > ul > li:first-child +li           {width:45%;} 
    #ulTable > li > ul > li:first-child +li+li        {width:20%;} 
    #ulTable > li > ul > li:first-child +li+li+li     {width:15%;} 
    #ulTable > li > ul > li:first-child +li+li+li+li{width:10%;}
    #pageList {
          clear:both; 
        width:100%; 
        height:50px;
        padding-top : 50px;
        text-align:center;
}

    #liSearchOption {clear:both;}
    #liSearchOption > div {
        margin:0 auto; 
        margin-top: 30px; 
        width:auto; 
        height:100px; 

}

.simple_img{
	width : 90px;
	height: 90px;
}

.simple_title{
  	color:white;
	font-size:2em;
	display:inline-block;
    position: absolute;
 	margin:auto;
    top: 28%;	
    left: 50%;
    transform: translate(-50%, -50%);
}

#headerImage {
		width:100%;
	height:477px;
	background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)),
	url('headerImage/back02.jpg');
	background-position: 50% 50%;
	background-size: 	cover;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0; 
}
.simple_title a{
	color : rgba(255, 255, 255, 0.8);
	text-decoration : none;
}
.simple_title a:hover{
	color : rgba(255, 255, 255, 1);
}
a{
text-decoration : none;
}
#selSearchOption {
    height: 30px;
    border-radius: 3px;
}
#keyWord{
	box-sizing : border-box;
    height: 30px;
    border: 1px solid #CCCCCC;
}
#SearchBtn{
border: 1px solid #CCCCCC;
    height: 32px;
    width: 50px;
    	box-sizing : border-box;
}
</style>
</head>
<body>
<jsp:include page="../top_menu.jsp"/>
 <div id="container">
		<div id="main">
			 <div id="headerImage">
			 <div class="simple_title">
            <h2><a href="/Space/orderList.od">주문/배송 조회</a></h2>
            </div>
            </div>
			 <div id="mainWrapper">
        <ul>
          
            
            <li>
                <%if(orodproviewbean != null){ %>
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
for(int i=0; i<orodproviewbean.size(); i++){
	int totalMoney = orodproviewbean.get(i).getPro_price()*orodproviewbean.get(i).getOd_qty();
	int delivery = 2500;
	if(totalMoney >30000){
		delivery = 0;
	}
	String state = orodproviewbean.get(i).getOr_state();
	if(state.equals("wait")){
	 state = "주문완료";
	}
	totalMoney += delivery;
	out.println("<li><ul><li>" + orodproviewbean.get(i).getOr_date()+"</li>"); //주문일시
	out.println("<li><a href='orderDetailList.od?od_num=" + orodproviewbean.get(i).getOd_num() + "'><img src='/Space/boardUpload/" +orodproviewbean.get(i).getPro_image()  +"'class='simple_img'></a></li>"); //상품 이미지
	out.println("<li><a href='orderDetailList.od?od_num=" + orodproviewbean.get(i).getOd_num() + "'>" + orodproviewbean.get(i).getPro_name() +"</a></li>"); //상품이름
	out.println("<li>" + totalMoney +"</li>"); //결제금액
	out.println("<li>" + state +"</li></ul></li>"); //주문상태
}
%>
                </ul>
                <%
                }else{
                	out.println("<li><h2>구매내역이 없습니다.</h2><br><br></li>");
                }
                %>
            </li>
            
            <!-- 게시판 페이징 영역 -->
            <li>
                <div id="pageList">
                <%if(nowPage <= 1) {%>
                   		 ◀
                <%}else{ %>
                	<a href="/Space/orderList.od?page=<%=(nowPage-1)%>">◀</a>
                <%} %>
                
                <%for(int a=startPage; a <=endPage; a++){
                	if(a == nowPage){%>
                       <b><%=a %></b>
                       <%}else{ %>
                       <b><a href="/Space/orderList.od?page=<%=a %>"><%=a %></a></b>
                       <%}} %>
                    <%if(nowPage >= maxPage){ %>
                 			   ▶
                    <%}else{ %>
                    	<a href="/Space/orderList.od?page=<%=(nowPage+1) %>">▶</a>
                    	<%} %>
                </div>
            </li>
            
            <!-- 검색 폼 영역 -->
            <li id='liSearchOption'>
                <div>
                   <form action="orderList.od">
                    <select id='selSearchOption' >
                        <option>상품이름</option>
                    </select>
                    <input type="text" id="keyWord" name="keyWord" />
                    <input type='submit' id="SearchBtn" value='검색'/>
                    </form>
                </div>
                </li>
        </ul>
    </div>
		</div>
	</div>
	<jsp:include page="../footer.jsp"/>

</body>
</html>