<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "mypage_artist.management.model.vo.*, artistapply.model.vo.*, board.notice.model.vo.PageInfo, java.util.ArrayList
    , product.model.vo.*"%>
    <%
    	ArrayList<Mypage_artist> SM_list = (ArrayList<Mypage_artist>)request.getAttribute("SM_list");
    	ArrayList<Mypage_artist> search_list_SM = (ArrayList<Mypage_artist>)request.getAttribute("search_list_SM");
    	PageInfo pi = (PageInfo)request.getAttribute("pi");
    	 Apply aphoto = (Apply) request.getAttribute("aphoto"); 
    	
    	int listCount = pi.getListCount();
    	int currentPage = pi.getCurrentPage();
    	int maxPage = pi.getMaxPage();
    	int startPage = pi.getStartPage();
    	int endPage = pi.getEndPage();
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
<div class="headline">
        <div class="headline-text">
            <hr>
            <h3 style="font-size: 20px;"> 작가 마이페이지</h3>
        </div>
        <div class="headline-subbox">
            <div class="artist-img">
                <!--Artist Image-->
				<img src="<%= request.getContextPath() %>/apply_uploadFiles/<%= aphoto.getArtist_pro() %>">
				<%-- <%= request.getContextPath() %>/detail.po/<%= mlist.getPaint_no() %> --%>
           </div>
            <div class="artist-btn" style = "display: inline-block; vertical-align: middle;
            ">
                <button class="btn btn-outline-dark" style = "width:120px; display:block; margin-bottom:5px;" onclick="updateInfo();">정보 수정</button>
                <button class="btn btn-outline-dark" style = "width:120px; display:block;margin-top:5px;" onclick="checkCard();">감동 카드</button>
                <script>
                	function checkCard() {
                		location.href = "<%= request.getContextPath() %>/list.ac"		
                	}
                	
                	function updateInfo() {
                		location.href = "<%=request.getContextPath()%>/mypage.me?userId=<%=loginUser.getUserId() %>"
                	}
                </script>
            </div>
        </div>
    </div>
<%@include file="../common/mypagehead.jsp" %>
    <div class="title">
        <h3>배송관리</h3>
        <hr>
    </div>

    <div class="container">
        <form action="<%=request.getContextPath()%>/Search.SM" method="post">
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
                                    <input type="radio" name="term" value="0">오늘
                                </label>
                                <label class="btn btn-outline-dark">
                                    <input type="radio" name="term" value="7">1주일
                                </label>
                                <label class="btn btn-outline-dark">
                                    <input type="radio" name="term" value="30" >1개월
                                </label>
                                <label class="btn btn-outline-dark">
                                    <input type="radio" name="term" value="90" >3개월
                                </label>
                                <label class="btn btn-outline-dark">
                                    <input type="radio" name="term" value="180" >6개월
                                </label>
                            </div> 
                &nbsp;&nbsp;&nbsp;
                <div class="bar3">
                    <input type="date" name="calendar1"> ~
                    <input type="date" name="calendar2">
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
                <% for(Mypage_artist ma : SM_list){ %>
                <tr>
                    <td><%=ma.getOrder_no() %></td>
                    <td><img src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= ma.getAfile() %>"></td>
                    <td>작품명 : <%=ma.getPaint_name() %><br>
                    	  작가명 : <%=ma.getArtist_name() %></td>
                    <td><%=ma.getOrder_date() %></td>
                    <td> <%if(ma.getShip_date() == null){%>- -<%}else{ %>
                    <%=ma.getShip_date() %>
                    <%} %>
                    </td>
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
      <div class="pageingArea" align="center">
      <!-- 맨 처음으로 (<<) -->
      <button class="btn btn-outline-dark" onclick="location.href='<%=request.getContextPath() %>/SM.list?currentPage=1'"> << </button>
      
      <!-- 이전 페이지로(<) -->
      <%if(currentPage <= 1) {%>
      <button class="btn btn-secondary" disabled> < </button>
      <%}else{ %>
      <button class="btn btn-outline-dark" onclick="location.href='<%=request.getContextPath() %>/SM.list?currentPage=<%=currentPage-1 %>'"> < </button>
       <%} %>
      <!-- 10개의 페이지 목록 -->
      <%for(int p = startPage ; p<=endPage;p++){ %>
     	 <%if(currentPage == p){ %>
     	 	<button class="btn btn-secondary" disabled><%=p %></button>
     	 <%}else{ %>
     	 	<button class="btn btn-outline-dark" onclick="location.href='<%=request.getContextPath() %>/SM.list?currentPage=<%=p %>'"><%=p %></button>
     	 <%} %>
      <%} %>


      
      <!-- 다음 페이지로(>) -->
        <%if(currentPage == maxPage) {%>
      <button class="btn btn-secondary" disabled> > </button>
      <%}else{ %>
      <button class="btn btn-outline-dark" onclick="location.href='<%=request.getContextPath() %>/SM.list?currentPage=<%=currentPage+1 %>'"> > </button>
      <%} %>
      
      <!-- 맨 끝으로(>>) -->
      <button class="btn btn-outline-dark" onclick="location.href='<%=request.getContextPath() %>/SM.list?currentPage=<%= maxPage%>'"> >> </button>
      
      </div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@include file="../common/footer.jsp" %>
</body>
</html>