<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.free.model.vo.*, java.util.ArrayList"%>
<%
	Free f = (Free)request.getAttribute("free");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=f.getFree_title() %></title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/style.css">
 <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
 <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/bootstrap.css">
<script src="<%=request.getContextPath() %>/views/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/views/js/bootstrap.js"></script>    

</head>
<style>
	#InsertArea{  margin-bottom:200px;}
	#freeHeader{ background:white; }
	.headLine{ float:left; width:30%; margin-top:90px; margin-left:90px; margin-bottom:50px; display:block; box-sizing:border-box;}
	.headLine hr{ background-color:red; width:25px; border:2px solid red; margin-bottom:10px;}
</style>
<body>

	<!-- header -->

<%@include file="../../common/menubar.jsp" %>

			<div class="headLine">
				<hr style="display:inline-block;">
				<h3 style="font-size:20px;">자유게시판</h3>
			</div><!-- class headLine end -->
	<br clear="both">
	<div class="container" id="InsertArea">	
	   <div id="freeHeader" class="jumbotron text-center">
	   		<p>게시글 수정 페이지 입니다.</p>
	   </div>   
       <form action="<%=request.getContextPath()%>/update.ee?updateNo=<%=f.getFree_no() %>" method="post" encType="multipart/form-data">
           <div class="form-group">
            	<label for="free_title"></label>
            	<input type="text" class="form-control" name="free_title" id="free_title" placeholder="Enter Title"  value="<%=f.getFree_title()%>">
       	 	</div>
        	<div class="form-group">
            	<label for="event">Comment</label>
            	<textarea class="form-control" rows="10" name="free_content" id="free_content"><%=f.getFree_content()%></textarea>
        	</div>
        	<div id="fileArea" class="form-group" style="display:block;">
        		<label for="event_file" style="font-size:17px;">작품 제출하기</label>
        		<input type="file" id="event_file" name="event_file" onchange="LoadImg(this)">
        	</div>
        	<div id="ImgArea" class="form-group" style="display:block;">
        		<img id="imgArea" width="150" height="150" src="<%=request.getContextPath() %>/thumbnail_uploadFiles/free/<%=f.getFree_picture()%>">
        	</div>
        	<div class="form-group">
            	<input type="submit" class="btn btn-secondary" value="수정하기" >&nbsp;
            	<input type="reset" class="btn btn-secondary" value="다시쓰기">
        	</div>
		</form>
   </div>
    <!-- footer -->
	<%@include file="../../common/footer.jsp" %>

   
</body>
<script>
	function LoadImg(value){
		if(value.files && value.files[0]){
			var reader = new FileReader();

			reader.onload = function(e){
				$("#imgArea").attr("src",e.target.result);
			}
		}
		reader.readAsDataURL(value.files[0]);
	}
</script>
</html>