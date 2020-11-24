<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.amateur.model.vo.*, board.notice.model.vo.*, java.util.ArrayList
    							, product.model.vo.*"%>
<%
	Amateur a = new Amateur();
	ArrayList<Amateur> list = ((ArrayList<Amateur>)request.getAttribute("masterList"));
	ArrayList<FileManagement> fileList = ((ArrayList<FileManagement>)request.getAttribute("masterFileList"));
	
	product p = new product();
	ArrayList<product> proList = ((ArrayList<product>)request.getAttribute("masterProList"));
	ArrayList<Attachment> profileList = ((ArrayList<Attachment>)request.getAttribute("masterProFileList"));
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/bootstrap.css">
	<script src="<%=request.getContextPath() %>/views/js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/views/js/bootstrap.js"></script>    
<%-- <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/style.css"> --%>
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet"> 
<!-- <link rel="stylesheet" href="../css/style.css"> -->
<link
	href="https://fonts.googleapis.com/css2?family=Roboto&display=swap"
	rel="stylesheet">
<!-- <link rel="stylesheet" href="../css/bootstrap.css"> -->
<!-- <link rel="stylesheet" href="../css/MainSlide.css"> -->

<script src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
</head>
<body>

<style>
	.PaintMasterpiece-title{margin:0 auto;margin-top:80px; margin-bottom:80px;}
	.PaintMasterpiece-title span{margin-left:350px;font-size:34px;}
	/* .PaintMasterpiece-title hr{background-color:red; border:2px solid red; margin-bottom:10px;} */
	.AmateurMasterpiece-title{margin:0 auto;margin-top:80px; margin-bottom:80px;}
	.AmateurMasterpiece-title span{margin-left:350px;font-size:34px;}
/* 	.AmateurMasterpiece-title hr{background-color:red; border:2px solid red; margin-bottom:10px;} */
</style>
</head>

<body>
   <section>
       <div id ="contents" >

   <div class="container">
   		<div class="PaintMasterpiece-title">
   	  		<span> Masterpiece of this month - Pro</span>
   	  		<hr>
   	  	</div>
      	<div class="row">
		<%if(proList.isEmpty()){%>
          	<span style="font-size:30px; margin:0 auto">마스터피스 집계 중입니다.</span>
     	<%}else{ %>
			<%for(int i=0;i<proList.size();i++){ %>
	    		<% p = proList.get(i); %>
	       		<%if(p.getPaint_no()!=0){ %>
	          		<div id="goDetail" class="col-lg-4 col-md-6 mb-4">
			       	   <div class="card h-100">
					   <%for(int j=0; j<profileList.size();j++){ 
			            	Attachment at = profileList.get(j);%>
					      	<%if(p.getPaint_no()==at.getPaint_no()){ %>
					       		<a href="#">
				         			<img class="card-img-top" src="<%=request.getContextPath() %>/thumbnail_uploadFiles/<%=at.getSavefileName() %>" alt="">
			             		</a>
			              	<%} %><!-- if a.getEventNo()==fm.getEvent_no end -->
     			        <%} %><!--fileList for loop end --> 			
							<div class="card-body">		              		
				           		<input id="event_no" type="hidden" value="<%=p.getPaint_no()%>">
				           		<h4 class="card-title"><a href="#"><p style="color:black"><%=p.getPaint_name() %></p></a></h4>
				              	<h5><%=p.getArtist_name() %></h5>
				            </div><!-- class card-body end -->	
		         		</div><!-- class card end -->
				   </div><!-- id goDetail end --> 
			<%} %><!-- if a.getEvent_no end -->
          <%}%><!-- list for loop end -->
       <%} %>
                
         </div> <!-- /.row --> 
   
      <br><br><br><br><br><br>
   	  <div class="AmateurMasterpiece-title">
   	  		<span> Masterpiece of this month - Amateur </span>
   	  		<hr>
   	  	</div>
      <div class="row">
			<%if(list.isEmpty()){%>
          		<span style="font-size:30px;">마스터피스 집계 중입니다.</span>
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
			              		<h4 class="card-title"><a href="#"><p style="color:black"><%=a.getEvent_title() %></p></a></h4>
			                	<h5><%=a.getUser_id() %></h5>
			                </div><!-- class card-body end -->	
				    	</div><!-- class card end -->
				   	</div><!-- id goDetail end --> 
			<%} %><!-- if a.getEvent_no end -->
          <%}%><!-- list for loop end -->
       <%} %>
                
         </div> <!-- /.row --> 
       <div class="part1">
      <!--       <div class="slide">
                <h1 align="center">REMEMBER ART</h1>
            <a href="#"><img src="views/img/slide1.jpg" style="height: auto;"> </a>
            <a href="#"><img src=""></a>
            <a href="#"><img src=""></a>    
            </div> slide end       -->
        </div>
        
        <button class="btn btn-outline-dark" style=" width : 120px; height : 50px; margin-top: 10px;"
			onclick= "location.href='<%=request.getContextPath()%>/view.ap'">sammy</button>
		

  </div><!-- container end -->
       
    </div> <!--content end-->
   </section>
			
		</div>
		<!--content end-->
	</section>

</body>
</html>