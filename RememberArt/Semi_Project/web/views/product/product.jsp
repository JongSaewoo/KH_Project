<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "product.model.vo.*, java.util.ArrayList, board.notice.model.vo.*"%>
    <%
    	ArrayList<product> plist = (ArrayList<product>)request.getAttribute("list");
    	ArrayList<Attachment> alist = (ArrayList<Attachment>)request.getAttribute("alist");

   		 PageInfo pi = (PageInfo) request.getAttribute("pi");
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
<title>Insert title here</title>
  <!-- link rel="stylesheet" href="../css/style.css" -->
 <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/style.css">
 <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
 <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/bootstrap.css">
<script src="<%=request.getContextPath() %>/views/js/jquery-3.4.1.min.js"></script>

  <style>
 #search{
    background-color: rgb(224, 224, 224);
}
.rul {
	margin-right: 10px;
	margin-left: 10px;
	height: 400px;
	padding-left: 100px;
	float:left;
}
.gellary {
	display: inline-block;
	background: #fff;
	width: 20%;
	height: 90%;
	margin-right: 20px;
	margin-top: 20px;
	
}
.mname {
	width: 100%;
	height: 20px;
	text-align: center;
}
.list{
	margin-right: 10px;
	margin-left: 50px;
	padding-left: 10px;
	float:left;

}
.buy{
	margin-left:160px;

}

            input[type=range]::-webkit-slider-runnable-track { 
                width: 100%; 
                height: 8.4px; 
                cursor: pointer; 
                box-shadow: 1px 1px 1px #000000, 0px 0px 1px #0d0d0d;
                 background: red; 
                 border-radius: 1.3px; 
                 border: 0.2px solid #010101;
            }
                  
            input[type=range]:focus::-webkit-slider-runnable-track {
                    background: red;
            }

	table{
		table-layout:fixed;
	}
	b{
		margin-left:150px;
	}
  </style>
  
</head>
<body>
<%@include file="../common/menubar.jsp" %>
       <h1 align="center" style="color: black;">미술품 판매(가제)</h1>
        <div id="search">
            
            <form action="<%=request.getContextPath()%>/worksearch.po" method="post">
            
            
            <table class="table table-borderless" >
               
	
				<tr>          
                    <td><b>작가 명</b><br><input type="text" class="form-control" id="aname" name="aname" style="width:200px; margin-left:150px;"></td>
                    <td><b>테마</b><br><select name="category" id="category" class="form-control" style="width:200px; margin-left:150px;">
                    			<option value="테마를 선택하세요">테마를 선택하세요</option>
                                <option value="인물">인물</option>
                                <option value="풍경">풍경</option> 
                                <option value="정물">정물</option>
                                <option value="동물">동물</option>
                                <option value="추상">추상</option>
                                <option value="팝아트">팝아트</option>
                                <option value="오브제">오브제</option>
                    </select></td>
                    <td>
                    <div>
                    <label > <b>가격: </b></label><br>
                    <input type="range" name="price" min="0" max="100000000" step="5000000" value="0"
                     oninput="document.getElementById('value1').innerHTML=this.value;" style="margin-left:150px;">
                    <span id="value1"></span>
                </div>
                    </td>
                </tr>
                
                
                <tr>
                    <td></td>
                    <td><button type="submit" id="submit" class="btn btn-dark"  style="width:150px; margin-left:150px;">검색</button></td>
                    <td></td>
                </tr>
            </table>
        </form>
        </div>
        
        
        <p class="h3">작품</p>
        <br clear="both">
           <% for(int i=0; i<plist.size(); i++){ 
        	   
             product p = plist.get(i); %>			
        	<div class="list">
						<input id="paint_no" type="hidden" value="<%=p.getPaint_no()%>"><br>
						<%  for(int j=0; j<alist.size(); j++){
                 			Attachment a = alist.get(j); %>
				<% if(p.getPaint_no() == a.getPaint_no() && p.getAuc_yn().equals("N")) { %>
				<img class="rimage" src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= a.getSavefileName() %>" style="width: 300px; height: 300px;">		      
					
					<div class="mname" align="center">
						<!-- 이름 -->
						<p><%=p.getPaint_name() %></p>
					</div>
					<div class="mname" align="center">
						<!-- 작가명-->
						<p>작가명 :<%=p.getArtist_name() %></p>
					</div>
					<br>
						<!-- 상세정보보기로 이동 -->
						<button type="button" class="btn btn-outline-dark detail_view" style="width:303px; heighht:20px;">상세보기</button><br><br>
						
						<!-- 구매페이지로이동-->					
						<button class="btn btn-outline-secondary buy_product" style="width:303px; heighht:20px;">구매하기</button>
					
			</div>
			<% }}} %>
 
                  
                  <script>
                  $(function(){
				 		$(".detail_view").click(function(){
				 			var paint_no = $(this).parent().children("input").val();
				 			
				 			location.href="<%=request.getContextPath()%>/detail.po?paint_no="+paint_no;
				 		});
				 		
				 	});
                  
                  $(function(){
                	  $(".buy_product").click(function(){
                		  var paint_no = $(this).parent().children("input").val();
                		  
                		  location.href="<%=request.getContextPath()%>/Buy.po?paint_no="+paint_no;
                	  })
                	  
                  })
				 	
					</script>
					
		<br clear="both"><br>
		<!--  페이징 처리 시작! -->
      <div class="pageingArea" align="center">
      <!-- 맨 처음으로 (<<) -->
      <button class="btn btn-outline-dark" onclick="location.href='<%=request.getContextPath() %>/list.po?currentPage=1'"> << </button>
      
      <!-- 이전 페이지로(<) -->
      <%if(currentPage <= 1) {%>
      <button class="btn btn-outline-dark"  disabled> < </button>
      <%}else{ %>
      <button class="btn btn-outline-dark"  onclick="location.href='<%=request.getContextPath() %>/list.po?currentPage=<%=currentPage-1 %>'"> < </button>
       <%} %>
      <!-- 10개의 페이지 목록 -->
      <%for(int p = startPage ; p<=endPage;p++){ %>
     	 <%if(currentPage == p){ %>
     	 	<button class="btn btn-outline-dark"  disabled><%=p %></button>
     	 <%}else{ %>
     	 	<button class="btn btn-outline-dark"  onclick="location.href='<%=request.getContextPath() %>/list.po?currentPage=<%=p %>'"><%=p %></button>
     	 <%} %>
      <%} %>

      
      <!-- 다음 페이지로(>) -->
        <%if(currentPage == maxPage) {%>
      <button class="btn btn-outline-dark"  disabled> > </button>
      <%}else{ %>
      <button class="btn btn-outline-dark"  onclick="location.href='<%=request.getContextPath() %>/list.po?currentPage=<%=currentPage+1 %>'"> > </button>
      <%} %>
      
      <!-- 맨 끝으로(>>) -->
      <button class="btn btn-outline-dark"  onclick="location.href='<%=request.getContextPath() %>/list.po?currentPage=<%= maxPage%>'"> >> </button>
      
      </div>


   <br ><br>
<div>
<%@include file="../common/footer.jsp" %>
</div>
</body>
</html>