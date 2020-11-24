<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.free.model.vo.*,
     board.notice.model.vo.*, board.amateur.model.vo.*, java.util.ArrayList" %>

<%
ArrayList<Free> list = ((ArrayList<Free>)request.getAttribute("list"));
PageInfo pi = (PageInfo) request.getAttribute("pi");

System.out.println("[Free게시판]jsp확인-List:"+list);

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
<title>자유 게시판</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/bootstrap.css">
	<script src="<%=request.getContextPath() %>/views/js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/views/js/bootstrap.js"></script>    
<link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/style.css">
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet"> 
<style>
	.headLine{ float:left; width:30%; margin-top:90px; margin-left:90px; margin-bottom:50px; display:block; box-sizing:border-box;}
	.headLine hr{ background-color:red; width:25px; border:2px solid red; margin-bottom:10px;}
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
<!-- Navigation에서 자유게시판 눌렀을 때 나오는 페이지 (계연) -->
	<%@include file="../../common/menubar.jsp" %>
	
			<div class="headLine">
				<hr style="display:inline-block;">
				<h3 style="font-size:20px;">자유게시판</h3>
			</div><!-- class headLine end -->
			
	<div id="freeCorner" class="containter">
		<div id="freeList" class="row" style="display:block">
			
			<!-- 자유 게시판 Logo -->
			
				 <div class="col-sm-6">
					<table class="table table-hover" id="listArea"  style="margin-left:500px;">
				  		<thead>
				    		<tr>
				      			<th>게시글 번호</th>
				      			<th>내용</th>
				      			<th>작성자</th>
				      			<th>작성일</th>
				    		</tr>
				  		</thead>
				  		<tbody>
				  		<%if(list.isEmpty()){ %>
				  			<tr>
				  				<td>게시글이 존재하지 않습니다.</td>
				  			</tr>
				  		<%}else{ %>				   				
					  			<%for(Free f:list){ %>
					    		<tr>
					    			<input type="hidden" value="<%=f.getFree_no() %>">
									<td><%=f.getFree_no() %></td>
					      			<td><%=f.getFree_title() %></td>
					      			<td><%=f.getUser_id() %></td>
					      			<td><%=f.getFree_date() %></td>
					    		</tr>
					    		<%}%>
				    	<%} %>
				  		</tbody>
					</table><!-- class table end -->
				</div><!--class col-sm-6 end -->
			</div><!-- id noticeList end -->
			

		<!-- Pagination -->
	<div class = "p-parents" style="margin-right:0px; margin-top:50px; margin-bottom:80px;">
	<div class="pppp" style="margin-left:900px">
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
	
		
	<!-- 게시글 작성  -->	
	<button id="insertfree" type="button active" class="btn btn-secondary" style="margin-left:1400px; margin-bottom:60px"
			onclick="location.href='<%=request.getContextPath()%>/views/board/free/freeInsert.jsp'">게시글 작성</button>
</div>
<br><br><br>
	<%@include file="../../common/footer.jsp" %>
</body>

<script>
	$(function(){
		$("#listArea td").click(function(){
			var free_no = $(this).parent().children("input").val();
			location.href="<%=request.getContextPath()%>/detail.ee?free_no="+free_no;
			
		})
	})

</script>
</html>