<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="mypage_user.mainOrderRefundWish.model.vo.*, product.model.vo.*, board.notice.model.vo.PageInfo,java.util.ArrayList"%>
    
<%
	ArrayList<Morw> list = ((ArrayList<Morw>)request.getAttribute("list"));
	Member user = (Member) session.getAttribute("loginUser");
	ArrayList<Attachment> plist = (ArrayList<Attachment>) request.getAttribute("plist");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
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
 <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/bootstrap.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/Style-mypagehead.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/Style-refund.css">
  
<script src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<style>
.p-parents { display: flex; flex-direction: column; justify-content: center; align-items: center; margin: 0 auto; }
.pppp { display: flex; text-align: center; margin : 50px auto; background: rgb(255, 255, 255); height: 36px; border : 1px solid black; border-radius: 5px; justify-content: center; align-items: center; }
.pppp > ol > li:first-child { border-left : 1px solid black; }
.pppp > a { display: inline-flex; justify-content: center; align-items: center; padding: 7px 12px; font-size: 13px; font-weight: 500; color:#9c9c9c; text-decoration: none; }
.pppp > ol { display: inline-flex; list-style: none; justify-content: center; align-items: center; }
.pppp > ol > li { display: inline-flex; list-style: none; justify-content: center; align-items: center; margin-top: 16px; border-right: 1px solid; vertical-align: middle; list-style: none; width: 36px; height: 34px; text-decoration: none; }
.page-list1 { background-color:#c82c1f; }
.page-cur { font-size : 14px; background:none; color: white; padding : 0; border-style : none; }
.page-nocur { font-size: 14px; background:none; color: #c82c1f; padding : 0; border-style : none; }
.page-a:hover { color: black; text-decoration:none; }
</style>
</head>
<body>
	<%@include file="../common/menubar.jsp" %>
	<%@include file="../common/mypagehead2.jsp" %>
    <br>
    <div class="container">
    <ul class="nav nav-pills nav-fill" align="left">
            <li class="nav-item">
            <%=user.getUserName() %>님은 <%=user.getGrade() %>등급입니다
            <a href="#" class="btn btn-secondary btn-sm active" role="button" aria-pressed="true">등급 혜택보기</a>&nbsp;
            <a href="<%=request.getContextPath()%>/views/mypage_user/mypage_point.jsp"  class="btn btn-outline-secondary btn-sm active" role="button" aria-pressed="true">마일리지: <%=user.getPoint() %> point</a>
            </li>
    </ul>
    <br>
    <br>
    
    <div class = "order-table">
            <div class="table-headline">
                <div><span><b>최근 주문내역</b></span></div>
            </div>
            <table style="width:100%" name="order-list">
                <thead>
                <tr>
                    <th>주문 번호</th>
                    <th>그림</th>
                    <th>상품명</th>
                    <th>금액</th>
                    <th>주문일자</th>
                    <th>주문 처리상태</th>
                </tr>
                </thead>
                <tbody>
                <%if(!list.isEmpty()){ %>
			  			<%for(Morw m:list){ %>
			    		<tr>
			    			<td><%=m.getOrderNo() %></td>
							<td><% for(int j=0; j<plist.size(); j++){ 
							Attachment a = plist.get(j); %>
						<% if(m.getPaintNo() == a.getPaint_no()) { %>
					 <img src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= a.getSavefileName() %>" width="150px" height="150px">
					 <%
					 break;
						} %>
					<%} %></td>
			      			<td>작가 : <%=m.getArtistName()%><br>
			      			작품명 : <%=m.getPaintName()%></td>
			      			<td><%=m.getPaintPrice()%></td>
			      			<td><%=m.getOrderDate()%></td>
			      			<td><%=m.getOrderStatus()%></td>
			    		</tr>
			    		<%} %>
			    	<%} else{%>
			    	<%} %>
                </tbody>
            </table>
        </div>
        
        <br>
        <br>
        <br>
 <!-- Pagination -->
	<div class = "p-parents" style="margin:0 auto">
	<div class="pppp">
			<%if (currentPage == 1) { %>
            <a style = "color:#9c9c9c; "  disabled>Previous</a>
            <%}else {%>
            <a class = "page-a" href="<%=request.getContextPath() %>/list.ar?currentPage=<%=currentPage - 1 %>" >Previous</a>
            <%} %>
            <ol>
            <%for(int p = startPage ; p<=endPage ; p++){ %>
            <%if(currentPage == p){ %>
              <li class = "page-list1"><button disabled class = "page-cur" ><%=p%></button></li>
            <%} else { %>
              <li class = "page-list2" onclick="location.href='<%=request.getContextPath() %>/list.ar?currentPage=<%=p%>'"><button class = "page-nocur"><%=p%></button></li>
            <%} %>
            <%} %>
            </ol>
            <%if (currentPage == maxPage) { %>
            <a style = "color:#9c9c9c; "  disabled>Next</a>
            <%} else { %>
            <a class = "page-a" href="<%=request.getContextPath()%>/list.ar?currentPage=<%=currentPage + 1%>">Next</a>
            <%} %>
   </div>
</div>
       
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
<%@include file="../common/footer.jsp" %>
</body>
</html>