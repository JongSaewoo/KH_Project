<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "mypage_artist.management.model.vo.*, artistapply.model.vo.*, board.notice.model.vo.PageInfo, java.util.ArrayList
    , product.model.vo.*"%>
    <%
    	ArrayList<Mypage_artist> PM_list = (ArrayList<Mypage_artist>)request.getAttribute("PM_list");
    	ArrayList<Mypage_artist> search_list_PM = (ArrayList<Mypage_artist>)request.getAttribute("search_list_PM");
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
<title>상품관리</title>
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
        <h3>상품관리</h3>
        <hr>
    </div>

    <div class="container">
        <form action="<%=request.getContextPath()%>/Search.PM" method="post">
            <div class="search-bar">
                <div class="bar1">
                    <select name="category" class="form-control" style="margin-right:150px;">
                                <option value="인물">인물</option>
                                <option value="풍경">풍경</option>
                                <option value="정물">정물</option>
                                <option value="동물">동물</option>
                                <option value="추상">추상</option>
                                <option value="팝아트">팝아트</option>
                                <option value="오브제">오브제</option>
                      </select>
                </div>
              
                <button type="submit" class="btn btn-dark" style="width:70px">조회</button>
            </div>

        </form>
        <div class = "refund-table">
            <div class="table-headline">
                <div><span>상품관리</span></div>
                <div class="button"><button class="btn btn-outline-dark" style = "width:100px" onclick="registration();">상품등록</button></div>
                </div>
                <script>
               function registration(){
            	   location.href="<%=request.getContextPath() %>/views/mypage_artist/art-registration.jsp";
               }
                </script>
                
            <table style="width:100%" name="refund-list">
                <tr>
                    <th>상품 번호</th>
                    <th>이미지</th>
                    <th>상품 정보</th>
                    <th>금액</th>
                    <th>삭제</th>
                </tr>
                <% for(Mypage_artist ma : PM_list){ %>
                <tr>
                    <td><%=ma.getPaint_no() %></td>
                    <td><img src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= ma.getAfile() %>"></td>
                    <td>작품명 : <%=ma.getPaint_name() %><br>
                    	  작가명 : <%=ma.getArtist_name() %></td>
                    <td><%=ma.getPaint_price() %></td>
                    <td>
                    <input class="paint_no" type="hidden" value="<%=ma.getPaint_no() %>">
                    <button class="btn btn-outline-dark Pdelete" id="detail_order" style = "width:100px">삭제</button>
                    </td>
                </tr>
				<%} %>
            </table>
        </div>
        <script>
        	$(function(){
        		$(".Pdelete").click(function(){
        			var paint_no = $(this).parent().children(".paint_no").val();
        			location.href="<%=request.getContextPath()%>/delete.p?paint_no="+paint_no;
        		})
        	})
        </script>
    </div>
 		<br clear="both"><br>
		<!--  페이징 처리 시작! -->
      <div class="pageingArea" align="center">
      <!-- 맨 처음으로 (<<) -->
      <button class="btn btn-outline-dark" onclick="location.href='<%=request.getContextPath() %>/PM.list?currentPage=1'"> << </button>
      
      <!-- 이전 페이지로(<) -->
      <%if(currentPage <= 1) {%>
      <button class="btn btn-secondary" disabled> < </button>
      <%}else{ %>
      <button class="btn btn-outline-dark" onclick="location.href='<%=request.getContextPath() %>/PM.list?currentPage=<%=currentPage-1 %>'"> < </button>
       <%} %>
      <!-- 10개의 페이지 목록 -->
      <%for(int p = startPage ; p<=endPage;p++){ %>
     	 <%if(currentPage == p){ %>
     	 	<button class="btn btn-secondary" disabled><%=p %></button>
     	 <%}else{ %>
     	 	<button class="btn btn-outline-dark" onclick="location.href='<%=request.getContextPath() %>/PM.list?currentPage=<%=p %>'"><%=p %></button>
     	 <%} %>
      <%} %>


      
      <!-- 다음 페이지로(>) -->
        <%if(currentPage == maxPage) {%>
      <button class="btn btn-secondary" disabled> > </button>
      <%}else{ %>
      <button class="btn btn-outline-dark" onclick="location.href='<%=request.getContextPath() %>/PM.list?currentPage=<%=currentPage+1 %>'"> > </button>
      <%} %>
      
      <!-- 맨 끝으로(>>) -->
      <button class="btn btn-outline-dark" onclick="location.href='<%=request.getContextPath() %>/PM.list?currentPage=<%= maxPage%>'"> >> </button>
      
      </div>


<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@include file="../common/footer.jsp" %>
</body>
</html>