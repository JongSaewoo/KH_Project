<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.notice.model.vo.*, java.util.ArrayList"%>
<%
	ArrayList<Notice> list = ((ArrayList<Notice>)request.getAttribute("list"));
	
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
<title>공지사항</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/bootstrap.css">
	<script src="<%=request.getContextPath() %>/views/js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/views/js/bootstrap.js"></script>    
<link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/style.css">
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet"> 

 <style>
 	#insertNotice{ float:right; margin-right:100px; }
 	#noticeList div{margin: 0 auto;}
 	#noticeCorner {margin-top:40px;} 
	#subnav{ float: left; margin-left:20px;}
	.headLine{ float:left; width:30%; /*margin-top:90px; margin-left:90px; margin-bottom:50px;*/ display:block; box-sizing:border-box;}
	.headLine hr{ background-color:red; width:25px; border:2px solid red; margin-bottom:10px;}
 	table{ margin-top:30px; }
 	#noticeCorner .col-sm-6 { box-sizing:border-box; float:left; width:70%; margin:0; padding:0; }
 	#noticeList:after { display:block; content:""; clear:both; }
 	#noticeList { width:1800px; margin: 20px auto 0; }
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
<!-- Navigation에서 Notice>공지사항 눌렀을 때 나오는 페이지 (계연) -->
	<%@include file="../../common/menubar.jsp" %>
	
	<div id="noticeCorner" class="containter">
		<div id="noticeList" class="row" style="display:block">
			
			<!-- Notice 게시판 Logo -->
			
			<div class="headLine">
				<hr style="display:inline-block;">
				<h3 style="font-size:20px;">Notice</h3>
			</div><!-- class headLine end -->
			
				 <div class="col-sm-6" style="text-align:center !important; display:block; margin-top:140px; margin-bottom:200px;">
					<table class="table table-hover" id="listArea" style="text-align:center;">
				  		<thead>
				    		<tr>
				      			<th class="text-center">게시글 번호</th>
				      			<th class="text-center">내용</th>
				      			<th class="text-center">작성자</th>
				      			<th class="text-center">작성일</th>
				    		</tr>
				  		</thead>
				  		<tbody>
				  		<%if(list.isEmpty()){ %>
				  			<tr>
								<td class="text-center" colspan="4" style=" margin:0 auto; margin-top:140px; margin-bottom:240px; width:100%">
          			    						게시글이 존재하지 않습니다.
          						</td>
   				  			</tr>
				  		<%}else{ %>				   				
					  			<%for(Notice n:list){ %>
					    			<%if(n.getNoti_no()!=0){ %>
							    		<tr>
							    			<input type="hidden" value="<%=n.getNoti_no() %>">
											<td class="text-center"><%=n.getNoti_no() %></td>
							      			<td class="text-center"><%=n.getNoti_title() %></td>
							      			<td class="text-center">관리자</td>
							      			<td class="text-center"><%=n.getNoti_date() %></td>
							    		</tr>
					    		<%}%>
					    	<%} %>
				    	<%} %>
				  		</tbody>
					</table><!-- class table end -->
				</div><!--class col-sm-6 end -->
			</div><!-- id noticeList end -->
			
		<!-- Pagination -->
	<div class = "p-parents" style="margin-right:0px;">
	<div class="pppp" style="margin-left:1000px">
			<%if (currentPage == 1) { %>
            <a style = "color:#9c9c9c; "  disabled>Previous</a>
            <%}else {%>
            <a class = "page-a" href="<%=request.getContextPath() %>/list.no?currentPage=<%=currentPage - 1 %>" >Previous</a>
            <%} %>
            <ol>
            <%for(int p = startPage ; p<=endPage ; p++){ %>
            <%if(currentPage == p){ %>
              <li class = "page-list1"><button disabled class = "page-cur" ><%=p%></button></li>
            <%} else { %>
              <li class = "page-list2" onclick="location.href='<%=request.getContextPath() %>/list.no?currentPage=<%=p%>'"><button class = "page-nocur"><%=p%></button></li>
            <%} %>
            <%} %>
            </ol>
            <%if (currentPage == maxPage) { %>
            <a style = "color:#9c9c9c; "  disabled>Next</a>
            <%} else { %>
            <a class = "page-a" href="<%=request.getContextPath()%>/list.no?currentPage=<%=currentPage + 1%>">Next</a>
            <%} %>
   </div>
</div>
	
		
	<!-- 게시글 작성  -->	
	<%-- <button id="insertNotice" type="button active" class="btn btn-secondary" 
			onclick="location.href='<%=request.getContextPath()%>/views/board/notice/noticeInsert.jsp'">게시글 작성</button> --%>
</div>
<br><br><br>
	<%@include file="../../common/footer.jsp" %>
</body>

<script>
	$(function(){
		$("#listArea td").click(function(){
			var noti_no = $(this).parent().children("input").val();
			location.href="<%=request.getContextPath()%>/detail.no?noti_no="+noti_no;
			
		})
	})

</script>
</html>