<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "mypage_artist.management.model.vo.*, artistapply.model.vo.*, board.notice.model.vo.PageInfo, java.util.ArrayList"%>
    <%
    	ArrayList<Mypage_artist> search_list_SM = (ArrayList<Mypage_artist>)request.getAttribute("search_list_SM");
    	/* Apply aphoto = (Apply) request.getAttribute("aphoto"); */
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송관리</title>
 <!-- link rel="stylesheet" href="../css/style.css" -->
  <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/bootstrap.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/Style-refund.css">
<script src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script> 

</head>

<body>
<%@include file="../common/menubar.jsp" %>
<%-- <div class="headline">
        <div class="headline-text">
            <hr>
            <h3 style="font-size: 20px;"> 작가 마이페이지</h3>
        </div>
        <div class="headline-subbox">
            <div class="artist-img">
                <!--Artist Image-->
				<img src="<%= request.getContextPath() %>/apply_uploadFiles/<%= aphoto.getArtist_pro() %>" style= "width:150px; height=150px;">        </div>
            <div class="artist-button">
                <button style="margin-bottom: 3px;">정보수정</button>
                <button>감동카드 확인</button>
            </div>
        </div>
    </div> --%>
<%@include file="../common/mypagehead.jsp" %>
    <div class="title">
        <h3>배송관리</h3>
        <hr>
    </div>

    <div class="container">
        <form action="">
            <div class="search-bar">
                <div class="bar1">
                    <input type="text" name="shipping_status" list="shipping-list" placeholder="배송 상태">
                    <datalist id="shipping-list">
                                <option value="배송준비중"></option>
                                <option value="배송중"></option>
                                <option value="배송완료"></option>
                    </datalist>
                </div>
                <div class="btn-group" data-toggle="buttons">
                                <label class="btn btn-outline-dark">
                                    <input type="radio" name="term" value="today">오늘
                                </label>
                                <label class="btn btn-outline-dark">
                                    <input type="radio" name="term" value="week">1주일
                                </label>
                                <label class="btn btn-outline-dark">
                                    <input type="radio" name="term" value="month" >1개월
                                </label>
                                <label class="btn btn-outline-dark">
                                    <input type="radio" name="term" value="three_months" >3개월
                                </label>
                                <label class="btn btn-outline-dark">
                                    <input type="radio" name="term" value="six_months" >6개월
                                </label>
                            </div> 
                &nbsp;&nbsp;&nbsp;
                <div class="bar3">
                    <input type="date" name="refund-date1"> ~
                    <input type="date" name="refund-date1">
                </div>
                <button type="submit" class="btn btn-dark" style="width:70px">조회</button>
            </div>

        </form>
        <div class = "refund-table">
            <div class="table-headline">
                <div><span>반품 / 환불 목록</span></div>
            </div>
            <table style="width:100%" name="refund-list">
                <tr>
                    <th>주문 번호</th>
                    <th>이미지</th>
                    <th>상품 정보</th>
                    <th>주문날짜</th>
                    <th>배송 완료일</th>
                    <th>주문 정보 확인</th>
                </tr>
                <% for(Mypage_artist ma : search_list_SM){ %>
                <tr>
                    <td><%=ma.getOrder_no() %></td>
                    <td><img src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= ma.getAfile() %>"></td>
                    <td>작품명 : <%=ma.getPaint_name() %><br>
                    	  작가명 : <%=ma.getArtist_name() %></td>
                    <td><%=ma.getOrder_date() %></td>
                    <td><%=ma.getShip_date() %></td>
                    <td>
                     <input class="order_no" type="hidden" value="<%=ma.getOrder_no() %>">
                    <button class="btn btn-outline-dark order" id="detail_order" style = "width:150px">주문 상세보기</button></td>
                </tr>
				<%} %>
            </table>
        </div>
        
    </div>
    <script>
				$(function(){
	                   $(".order").click(function(){
	                      var order_no = $(this).parent().children(".order_no").val();
	                      
	                      location.href="<%=request.getContextPath()%>/DO.view?order_no="+order_no;
	                   });
	                   
	                });
				</script>
  <br clear="both"><br>
		<!--  페이징 처리 시작! -->


<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@include file="../common/footer.jsp" %>
</body>
</html>