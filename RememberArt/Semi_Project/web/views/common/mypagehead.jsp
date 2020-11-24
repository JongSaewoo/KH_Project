<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "artistapply.model.vo.*"%>
    
    <%
/*     Apply aphoto = (Apply)request.getAttribute("aphoto"); */
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage Head</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/Style-mypagehead.css">
</head>
<body>
	<%-- <div class="headline">
        <div class="headline-text">
            <hr>
            <h3 style="font-size: 20px;"> 작가 마이페이지</h3>
        </div>
        <div class="headline-subbox">
            <div class="artist-img">
                <!--Artist Image-->
					<img src="<%= request.getContextPath() %>/apply_uploadFiles/<%= aphoto.getArtist_pro() %>" width="150px" height="150px">
            </div>
            <div class="artist-button">
                <button style="margin-bottom: 3px;">정보수정</button>
                <button>감동카드 확인</button>
            </div>
        </div>
    </div> --%>
    
    <div class="second-menu">
        <ul>
            <li><a href="<%= request.getContextPath() %>/PM.list">상품 관리</a></li><a>|</a>
            <li><a href="<%= request.getContextPath() %>/OM.list">주문 관리</a></li><a>|</a>
            <li><a href="<%= request.getContextPath() %>/SM.list">배송 관리</a></li><a>|</a>
            <li><a href="<%= request.getContextPath() %>/list.ar">반품 / 환불 관리</a></li><a>|</a>
            <li><a href="<%= request.getContextPath() %>/list.qna">문의 내역</a></li>
        </ul>
    </div>
</body>
</html>