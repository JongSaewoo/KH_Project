<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유 게시판</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/style.css">
 <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
 <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/bootstrap.css">
<script src="<%=request.getContextPath() %>/views/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/views/js/bootstrap.js"></script>    

<style>
	#InsertArea{  margin-bottom:200px;}
	#freeHeader{ background:white; }
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

	<!-- header -->

<%@include file="../../common/menubar.jsp" %>

			<div class="headLine">
				<hr style="display:inline-block;">
				<h3 style="font-size:20px;">자유게시판</h3>
			</div><!-- class headLine end -->
	<br clear="both">
	<div class="container" id="InsertArea">	
	   <div id="freeHeader" class="jumbotron text-center">
	   		<p>올바른 게시글 문화를 지켜주세요.</p>
	   </div>   
       <form action="<%=request.getContextPath()%>/insert.ee" method="post" encType="multipart/form-data">
           <div class="form-group">
            	<label for="free_title">Title</label>
            	<input type="text" class="form-control" name="free_title" id="free_title" placeholder="Enter Title">
       	 	</div>
        	<div class="form-group">
            	<label for="event">Comment</label>
            	<textarea class="form-control" rows="10" name="free_content" id="free_content"></textarea>
        	</div>
        	<div id="fileArea" class="form-group" style="display:block;">
        		<label for="event_file" style="font-size:17px;">작품 제출하기</label>
        		<input type="file" id="event_file" name="event_file" onchange="LoadImg(this)">
        	</div>
        	<div id="ImgArea" class="form-group" style="display:block;">
        		<img id="imgArea" width="150" height="150">
        	</div>
        	<div class="form-group">
            	<input type="submit" class="btn btn-secondary" value="등록하기">&nbsp;
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