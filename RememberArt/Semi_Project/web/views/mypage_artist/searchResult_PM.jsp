<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "mypage_artist.management.model.vo.*, artistapply.model.vo.*, board.notice.model.vo.PageInfo, java.util.ArrayList
    , product.model.vo.*"%>
    <%
    	ArrayList<Mypage_artist> search_list_PM = (ArrayList<Mypage_artist>)request.getAttribute("search_list_PM");
    	ArrayList<Attachment> alist = (ArrayList<Attachment>)request.getAttribute("alist");
    	/* Apply aphoto = (Apply) request.getAttribute("aphoto"); */
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
                
            <table style="width:100%">
                <tr>
                    <th>상품 번호</th>
                    <th>이미지</th>
                    <th>상품 정보</th>
                    <th>금액</th>
                    <th>수정</th>
                </tr>
                <% for(Mypage_artist ma : search_list_PM){ %>
                <tr>
                    <td><%=ma.getPaint_no() %></td>
                    <td><img src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= ma.getAfile() %>"></td>
                    <td>작품명 : <%=ma.getPaint_name() %><br>
                    	  작가명 : <%=ma.getArtist_name() %></td>
                    <td><%=ma.getPaint_price() %></td>
                    <td><button class="btn btn-outline-dark" id="detail_order" style = "width:100px">내용수정</button>
                    </td>
                </tr>
				<%} %>
            </table>
        </div>
        
    </div>
 		<br clear="both"><br>


<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@include file="../common/footer.jsp" %>
</body>
</html>