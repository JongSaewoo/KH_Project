<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="product.model.vo.*, board.amateur.model.vo.*, java.util.ArrayList"%>
<%
	Amateur a = (Amateur)request.getAttribute("amateur");
	FileManagement fm = (FileManagement)request.getAttribute("fileList");
	ArrayList<Reply> rList = (ArrayList<Reply>)request.getAttribute("rList");
	AmateurLike al = (AmateurLike)request.getAttribute("count");
	AmateurLike show = (AmateurLike)request.getAttribute("show");
	
	
	System.out.println("AmateurLike:"+al);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=a.getEvent_title() %></title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/style.css">
 <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
 <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/bootstrap.css">
<script src="<%=request.getContextPath() %>/views/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/views/js/bootstrap.js"></script>    
<style>
	#goAmateurBoard{ float:right; margin-right:100px; }
	#deleteContent{ float:right; margin-right:3px;}
	#updateContent{float:right; margin-right:3px;}

</style>
</head>


<body>
<!-- header -->
<%@include file="../../common/menubar.jsp" %>
 	 <div class="container"> 
         <div class="row" id="detailArea">
            <div class="cols-sm-6">
               <table align="center" class="table">
                  <tr>
                     <td>제목</td>
                     <td colspan="4"><span><%=a.getEvent_title() %></span></td>
                  </tr>
                  <tr>
                     <td>작성자</td>
                     <td><span><%=a.getUser_id() %></span></td>
                     
                     <td>작성일</td>
                     <td><span><%=a.getEvent_date() %></span></td>
                  </tr>
                  <tr>
                     <td colspan="4"><span>내용</span></td>
                  </tr>
                  <tr>
                     <td colspan="4">
                        <p>
                           <%=a.getEvent() %>
                        </p>
                        <p>
                        	<a href="#">
			             		<img src="<%=request.getContextPath() %>/thumbnail_uploadFiles/amateur/<%=fm.getEvent_file() %>" alt="">
			             	</a>
                        </p>
                     </td>
                  </tr>
                  <tr>
                  	<td> 좋아요 </td>
                  	<td> 
                  		<div onclick="emptyheartCheck();" class="emptyheartCheck">
      						<input id="paint_no" type="hidden" value="<%=a.getEvent_no()%>">
      						
      						<div style="float:left">
				      			<div class="likeCount" style="float:right; margin-left:10px;">
					      			<span class="countArea"><%=a.getEvent_like() %></span>
					      		</div>
					      		<div style="float:left">
						      		<%if(a.getUser_id() != null){ %>
						      				<%if(show.getEvent_count()==1){ %>
							      				<img class="heartcheck" src="<%=request.getContextPath()%>/views/img/colorHeart.png">
							      			<%}else{ %>
							      				<img class="heartcheck" src="<%=request.getContextPath()%>/views/img/emptyHeart.png">					      			
							      			<%} %>
						      		<%}else{%>
									      		<img class="heartcheck" src="<%=request.getContextPath()%>/views/img/emptyHeart.png">
					      			<%} %>
					      		</div>
      						</div>
      					</div>
					</td>
                  </tr>
                  <br><br><br>
                  <tr>
					<td>댓글작성</td>
					<td><textArea rows="5" cols="80" id="replyContent"></textArea></td>
					<td>
						<button id="addReply" type="button active" class="btn btn-secondary" style="width:100px;">댓글등록</button>
					</td>
				  </tr>
				  <br><br>
				</table>
				
       <div id="replySelectArea">
			<table id="replySelectTable" class="table" align="center">
				<% if(rList.isEmpty()) { %>
					<tr><td colspan="3">댓글이 없습니다.</td></tr>
					
				<% }else { %>
					<% for(int i=rList.size()-1; i>=0; i--){ %>
						<tr>
							<td width="100px"><%= rList.get(i).getUser_id() %></td>
							<td width="400px"><%= rList.get(i).getReply() %></td>
							<td width="200px"><%= rList.get(i).getReply_date() %></td>
						</tr>
					<% } %>
				<% } %>
			</table>
		</div>
            </div><!-- cols-sm-6 div end -->
         </div><!-- row div end -->
       </div><!-- container div end -->
 
 	<button id="goAmateurBoard" type="button active" class="btn btn-secondary" onclick="pageBack();">목록</button>
 	<%if(loginUser.getUserName().equals(a.getUser_id())){ %>
		<button id="deleteContent" type="button active" class="btn btn-secondary" onclick="goDelete();">삭제</button>
		<button id="updateContent" type="button active" class="btn btn-secondary" onclick="goUpdate();">수정</button>
	<%} %>
	</body>

	<script>
	
	function pageBack(){
		history.go(-1)();
	}
	function goDelete(){
		var deleteNo = document.getElementById("paint_no").value;
		var result = confirm("정말 삭제하시겠습니까?");
		if(result){
			location.href="<%=request.getContextPath()%>/delete.am?deleteNo="+deleteNo;			
		}else{
			
		}
	}
	function goUpdate(){
		var updateNo = document.getElementById("paint_no").value;
		location.href="<%=request.getContextPath()%>/select.am?updateNo="+updateNo;
	}
	
		$(function(){
			$(".heartcheck").click(function(){
		    	  var event_no = "<%=a.getEvent_no()%>";
		    	  var heart = $(".heartcheck").attr('src');
		    	  var heartYN;

		    	  if(heart=='<%=request.getContextPath()%>/views/img/colorHeart.png'){
		    		  $(".heartcheck").attr('src','<%=request.getContextPath()%>/views/img/emptyHeart.png');
		    		  heartYN = 'N';
		    	  }else{
		    		  $(".heartcheck").attr('src','<%=request.getContextPath()%>/views/img/colorHeart.png');
		    		  heartYN = 'Y';
		    	  }
		    	  $.ajax({
		    		url:"like.am",
		    		type:"post",
		    		data:{event_no:event_no,heartYN:heartYN},
		   
		    		success:function(data){
		    			var $likeCount = $(".likeCount");
		    			var $count = $("<span>").text(data);
		    			
		    			$likeCount.html($count);
		    			
		    		},
		    		error:function(request,statur,error){
		    			alert("로그인을 하셔야 합니다.");
		    			 $(".heartcheck").attr('src','<%=request.getContextPath()%>/views/img/emptyHeart.png');
		    		}
		    		  
		    	  })
			})
		
			$("#addReply").click(function(){
				var writer = "<%=loginUser.getUserId()%>";
				var bid = <%=a.getEvent_no()%>;
				var content = $("#replyContent").val();
				
				$.ajax({
					url:"insertReply.am",
					type:"post",
					data:{writer:writer,content:content,bid:bid},
					success:function(data){
						$replyTable = $("#replySelectTable");
						$replyTable.html("");
						for(var key in data){
							var $tr = $("<tr>");
							var $writerTd = $("<td>").text(data[key].user_id).css("width","100px");
							var $contentTd =$("<td>").text(data[key].reply).css("width","400px");
							var $dateTd = $("<td>").text(data[key].reply_date).css("width","200px");
							
							$tr.append($writerTd);
							$tr.append($contentTd);
							$tr.append($dateTd);
							$replyTable.append($tr);
						}
						$("#replyContent").val("");  
					},
					error:function(request,statur,error){
						alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error"+error);
					}
				})
			})
		})
	</script>
<!-- footer -->	
<%@include file="../../common/footer.jsp" %>

</body>
</html>