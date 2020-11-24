<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="mypage_user.mainOrderRefundWish.model.vo.*,product.model.vo.*,board.notice.model.vo.PageInfo, java.util.ArrayList"%>
    
<%
	ArrayList<Morw> refund_search_list = ((ArrayList<Morw>)request.getAttribute("refund_search_list"));
	ArrayList<Attachment> plist = (ArrayList<Attachment>) request.getAttribute("plist");
	
 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/views/css/bootstrap.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/views/css/Style-refund.css">
  
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
	
	<div class="title">
        <h3><b>반품 / 환불</b></h3>
        <hr>
    </div>
	<div class="container">
     <form method="post" action="<%=request.getContextPath()%>/SearchRefundServlet">
            <div class="search-bar">
                <div class="bar1">
                    <input type="text"  name="searchStatus" list="order_list" placeholder="주문 처리 상태">
                    <datalist id="order_list">   
                        <option value="환불신청">환불신청</option>
                        <option value="환불완료">환불완료</option>
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
                <div><span><b>취소 / 환불</b></span></div>
            </div>
            <table style="width:100%" name="order-list">
            	<thead>
                <tr>
                    <th>주문 번호</th>
                    <th>그림</th>
                    <th>상품정보</th>
                    <th>금액</th>
                    <th>처리상태</th>
                </tr>
                </thead>
                <tbody>
                 <%if(!refund_search_list.isEmpty()){ %>
			  			<%for(Morw m:refund_search_list){ %>
			    		<tr>
			    			<td><%=m.getOrderNo() %></td>
							<td><% for(int j=0; j<plist.size(); j++){ 
							Attachment a = plist.get(j); %>
						<% if(m.getPaintNo() == a.getPaint_no()) { %>
					 <img src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= a.getSavefileName() %>" width="150px" height="150px">
					 <%} %>
					<%} %></td>
			      			<td>작가 : <%=m.getArtistName()%><br>
			      			작품명 : <%=m.getPaintName()%></td>
			      			<td><%=m.getPaintPrice()%></td>
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