<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.amateur.model.vo.*, board.notice.model.vo.*, java.util.ArrayList"%>

<%
	Amateur a = new Amateur();
	AmateurLike al = new AmateurLike();
	ArrayList<Amateur> list = ((ArrayList<Amateur>)request.getAttribute("amateur"));
	
	ArrayList<FileManagement> fileList = ((ArrayList<FileManagement>)request.getAttribute("fileList"));
	ArrayList<AmateurLike> likeList = ((ArrayList<AmateurLike>)request.getAttribute("likeList"));
	
	System.out.println("fileList:"+fileList);
	System.out.println("list:"+list);
	
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아마추어 게시판 검색결과 입니다.</title>
 <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/bootstrap.css">
	<script src="<%=request.getContextPath() %>/views/js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/views/js/bootstrap.js"></script>    
<link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/style.css">
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet"> 
    
<style>
	#subnav{ float: left; margin-left:20px;}
	#goDetail{z-index:4;}
	.container{ position : relative; margin-top:10px;}
	.headLine{ /*float:left;*/ width:30%; margin-top:90px; margin-left:90px; margin-bottom:50px; display:block; box-sizing:border-box;}
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

<!-- Amateur Board Header -->
	<div class="headLine">
		<hr style="display:inline-block;">
		<h3 style="font-size:20px;">Amateur</h3>
	</div><!-- class headLine end -->
	
<!-- Amateur Board List -->	
	<div class="container">
     	<div class="row">
			<%if(list.isEmpty()){%>
          		<span style="font-size:20px; margin:0 auto; margin-top:140px; margin-bottom:240px;">
          			    게시글이 존재하지 않습니다.
          		</span>
     		<%}else{ %>
				<%for(int i=0;i<list.size();i++){ %>
	        		<% a = list.get(i); %>
	        		<%if(a.getEvent_no()!=0){ %>
	          			<div id="goDetail" class="col-lg-4 col-md-6 mb-4">
			       			<div class="card h-100">
							<%for(int j=0; j<fileList.size();j++){ 
			              		FileManagement fm = fileList.get(j);%>
					              	<%if(a.getEvent_no()==fm.getEvent_no()){ %>
					             		<a href="#">
					             			<img class="card-img-top" src="<%=request.getContextPath() %>/thumbnail_uploadFiles/amateur/<%=fm.getEvent_file() %>" alt="">
					             		</a>
					              	<%} %><!-- if a.getEventNo()==fm.getEvent_no end -->
					        <%} %><!--fileList for loop end --> 			
							<div class="card-body">		              		
			              		<input id="event_no" type="hidden" value="<%=a.getEvent_no() %>">
			              		<h4 class="card-title"><a href="#"><p style="color:black; font-style:bold"><%=a.getEvent_title() %></p></a></h4>
			                	<h5><%=a.getUser_id() %></h5>
			                	<p><%=a.getEvent_date() %></p>
			                	<p><span>조회수</span><%=a.getHit() %>
			                </div><!-- class card-body end -->	


				    	</div><!-- class card end -->
				   	</div><!-- id goDetail end --> 
			<%} %><!-- if a.getEvent_no end -->
          <%}%><!-- list for loop end -->
       <%} %>
       
       
  	  
                
         </div> <!-- /.row -->
		  <form action="<%=request.getContextPath() %>/search.am" method="post" class="form-row" style="margin-left:299px;">
		    <div class="form-group col-md-2">
		      	<select name="Category" class="form-control">
		       	 	<option selected>선택</option>
		        	<option value="title">제목</option>
		        	<option value="content">내용</option>
		        	<option value="writer">작성자</option>
		      	  </select>
		    </div>
		    <div class="form-group col-md-4">
		      <input type="text" class="form-control" id="search_title" name="search_title">
		    </div>
		    <div class="form-group col-md-1">
		    	<button id="goSearch" type="button active" class="btn btn-secondary">검색</button>
		    </div>
  		</form>


	
	</div><!-- contatiner end -->

<!-- insert Btn -->	
	<button id="insertAma" type="button active" class="btn btn-secondary" style="float:right; margin-bottom:100px; margin-right:100px">게시글 작성</button>
	<button id="goback-btn" type="button active" class="btn btn-secondary" style="float:right; margin-bottom:100px; margin-right:100px" onclick="goBack();">목록</button>

	<br clear="both">

<!--footer -->
<%@include file="../../common/footer.jsp" %>
      
</body>
<script>

function goBack(){
	window.history.back();
}

	$(function(){
		
		$(".card-title").click(function(){
			var event_no = $(this).parent().children("input").val();
			location.href="<%=request.getContextPath()%>/detail.am?event_no="+event_no;			
		})	 	
		$("#insertAma").click(function(){
			<% if(loginUser != null){ %>
			    location.href="<%=request.getContextPath()%>/views/board/amateur/amateurInsert.jsp"		
			<% }else{ %>
				alert("로그인 해야만 게시글 상세보기가 가능합니다!");
			<% } %>
		});
	
	})
	
</script>
</html>