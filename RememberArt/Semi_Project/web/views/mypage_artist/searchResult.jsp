<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "mypage_artist.management.model.vo.*, artistapply.model.vo.*, board.notice.model.vo.PageInfo, java.util.ArrayList"%>
    <%
       ArrayList<Mypage_artist> search_list = (ArrayList<Mypage_artist>)request.getAttribute("search_list");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문관리</title>
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
        <h3>주문관리</h3>
        <hr>
    </div>

    <div class="container">
        <form action="<%=request.getContextPath()%>/Search.MP" method="post">
            <div class="search-bar">
                <div class="bar1">
                    <input type="text" name="order_status" list="order_list" placeholder=" 주문 처리 상태">
                    <datalist id="order_list">
                     <option value="입금전"></option>
                     <option value="배송준비중"></option>
                     <option value="배송완료"></option>
                     <option value="환불신청"></option>
                     <option value="환불완료"></option>
                    </datalist>
                </div>
                <div class="btn-group" data-toggle="buttons" >
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
                <div><span>주문관리</span></div>
                <div class="button"><button class="btn btn-outline-dark" style = "width:120px">입금확인</button></div>
            </div>
            <table style="width:100%" name="refund-list">
                <tr>
                    <th>주문 번호</th>
                    <th>이미지</th>
                    <th>상품 정보</th>
                    <th>금액</th>
                    <th>처리 상태</th>
                    <th>주문 정보 확인</th>
                </tr>
                <% for(Mypage_artist ma : search_list){ %>
                <tr>
               
                    <td class = "orderNo"><%=ma.getOrder_no() %></td>
                    <td><img src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= ma.getAfile() %>"></td>
                    <td>작품명 : <%=ma.getPaint_name() %><br>
                                                     작가명 : <%=ma.getArtist_name() %></td>
                    <td><%=ma.getPaint_price() %></td>
                    <td><%=ma.getOrder_status() %></td>
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

<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@include file="../common/footer.jsp" %>
</body>
</html>