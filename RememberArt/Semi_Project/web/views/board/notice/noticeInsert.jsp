<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice - RememberArt</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/bootstrap.css">
	<script src="<%=request.getContextPath() %>/views/js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/views/js/bootstrap.js"></script>    
<link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/style.css">
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet"> 
<style>
	#insertArea{ margin-bottom:200px}
	#noticeHeader{ background:white; }
	.headLine{ float:left; width:30%; margin-top:90px; margin-left:90px; margin-bottom:50px; display:block; box-sizing:border-box;}
	.headLine hr{ background-color:red; width:25px; border:2px solid red; margin-bottom:10px;}
	
</style>
</head>
<body>
<!-- header -->
<%@include file="../../common/menubar.jsp" %>
			<div class="headLine">
				<hr style="display:inline-block;">
				<h3 style="font-size:20px;">Notice</h3>
			</div><!-- class headLine end -->

	<div id="insertArea" class="container">
	   <div id="noticeHeader" class="jumbotron text-center">
	   		<h1>공지사항 글 등록 페이지(관리자)</h1>
	   </div> 
       <form action="<%=request.getContextPath()%>/insert.no" method="post">
           <div class="form-group">
            	<label for="noti_title">Title</label>
            	<input type="text" class="form-control" name="noti_title" id="noti_title" placeholder="Enter Title">
       	 	</div>
        	<div class="form-group">
            	<label for="notice">Comment</label>
            	<textarea class="form-control" rows="10" name="notice" id="notice"></textarea>
        	</div>
        	<div class="form-group">
            	<input type="submit" onclick="goList();" id="submit" class="btn btn-secondary" value="등록하기" onclick="goNotice();">&nbsp;
            	<input type="reset" class="btn btn-secondary" value="다시쓰기">
        	</div>
       </form>
       <script>
       		
       </script>

   </div>
   <!-- footer -->
   <%@include file="../../common/footer.jsp" %>
   
</body>
</html>