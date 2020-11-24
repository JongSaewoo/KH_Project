<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.inquiry.model.vo.*, board.notice.model.vo.*, java.util.ArrayList"%>
<%
ArrayList<Inquiry> list = ((ArrayList<Inquiry>)request.getAttribute("list"));

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
<title>1:1문의</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/style.css">
 <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
 <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/bootstrap.css">
<script src="<%=request.getContextPath() %>/views/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/views/js/bootstrap.js"></script>    
</head>
<style>
	td{
		margin-left : 10px;
		margin-right : 10px;
	}
	#centerDiv{margin-top:70px;}
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
<body>
<%@include file="../../common/menubar.jsp" %>

<!-- 1:1 게시판 Logo -->
<div class="headLine">
		<hr style="display:inline-block;">
		<h3 style="font-size:20px;">1:1문의</h3>
</div><!-- class headLine end -->
<br clear="both">

<div id="centerDiv" class="container" >
<!-- 사용자에게는 보이고 관리자에게 보이지 않는 페이지 div id addQuestionArea -->			
 <div id="addQuestionArea" class="form-group">
  <div class="form-row">
    <div class="form-group col-md-3">
      	<select id="Category" class="form-control">
       	 	<option selected>선택</option>
        	<option value="배송">배송문의</option>
        	<option value="환불">환불문의</option>
        	<option value="사이트오류">사이트 이용문의</option>
        	<option value="기타">기타문의</option>
      	  </select>
    </div>
    <div class="form-group col-md-9">
      <input type="text" class="form-control" id="question_title" name="question_title" placeholder="게시글 제목은 모든 사용자에게 노출됩니다.">
    </div>
  </div>
  <div class="form-row">
  	<div class="form-group col-md-11">
  		<textarea class="form-control" id="question" name="question" rows="5" placeholder="문의 내용을 입력해주세요"></textarea>
    </div>
    <div class="form-group col-md-1">
	    <button id="addQuestion" class="btn btn-danger" style="height:130px">문의하기</button>  		
    </div>
  </div>
  </div><!-- div id addQuestionArea end -->
  
 
 <script>
 	window.onload = function(){
 		//로그인 유저에 따라 입력 창 display none block 여부 확인
 		if("<%=loginUser%>"=null){
 			alert("로그인 한 회원만 이용 가능합니다.");
 		}
 		if("<%=loginUser.getUserId()%>"=="admin"){
	 		document.getElementById("addQuestionArea").style.display="none";		
 		}
 	}
 </script>
 <br><br><br><br>
<table class="table" id="inquiryList">
  <thead>
    <tr>
      <th scope="col" class="text-center">문의구분</th>
      <th scope="col" class="text-center">문의내용</th>
      <th scope="col" class="text-center">작성자</th>
      <%if(loginUser.getUserId().equals("admin")){ %>
      	<th scope="col" class="text-center">답변상태</th>
      <%}else{ %>      
      	<th scope="col" class="text-center">답변 여부</th>
      <%} %>
    </tr>
  </thead>
  <tbody id="showInquiry">
  <% if(list.isEmpty()){%>
  <%} else{%>
  	<%for(Inquiry in :list){%>
    <tr>
      <input type="hidden" value="<%=in.getQ_no() %>">
      <th scope="row" class="text-center"><%=in.getQ_cate() %></th>
      <td class="text-center"><%=in.getQuestion_title() %></td>
      <td class="text-center"><%=in.getUser_id() %></td>
      <td class="text-center"><%=in.getQ_yn() %></td>
      
    </tr>
    <%if(loginUser.getUserId().equals("admin") || loginUser.getUserId().equals(in.getUser_id())){ %>
	<tr style="height:100px;">
		<td class="text-center">문의 내용</td>
		<td class="text-center" colspan="4"><span><%=in.getQuestion() %></span></textarea></td>
		
    </tr>
    <%} %>
    <tr>
    	<td colspan="4"></td>
    </tr>
  <%} %>
 <%} %>
  </tbody>
</table> 
      
<!-- 모달  --> 
        <div class="modal fade" id="intro" role="dialog" aria-labelledby="introHeader" aria-hidden="true" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">답변 입력</h4>
                    </div>
                    <form action="answer.in" method="get">
	                    <div class="modal-body">
	                        <textarea id="answerArea" name="answerArea" cols="55" row="30"></textarea>
	                    </div>
	                    <div class="modal-footer">
	                        <button type="submit" class="btn btn-default">전송</button>
	                        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
	                    </div>
                    </form>
                </div>
            </div>
        </div>

<br clear="both">

		<!-- Pagination -->
	<div class = "p-parents" style="margin:0 auto; margin-top:80px;">
		<div class="pppp">
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
</div>

<%@include file="../../common/footer.jsp" %>

</body>
 <script>
 	$(function(){
 		 		
 		$("#addQuestion").click(function(){
 			var writer = "<%=loginUser.getUserId()%>";
			var q_cate = $("#Category").val();
 			var question_title = $("#question_title").val();
 			var question = $("#question").val();
 			
 			$.ajax({
 				url:"insert.in",
 				type:"post",
 				data:{writer:writer,q_cate:q_cate,question_title:question_title,question:question},
 				success:function(data){
 					$inquiryTable = $("#showInquiry");
 					$inquiryTable.html("");
 					
 					for(var key in data){

 						
 						var $tr = $("<tr>");
 						
 						var $writerTd=$("<td>").text(data[key].user_id).addClass('text-center');
 						var $categoryTd=$("<td>").text(data[key].q_cate).addClass('text-center');
 						var $question_titleTd=$("<td>").text(data[key].question_title).addClass('text-center');
 						var $questionTd=$("<td>").text(data[key].question).addClass('text-center');
 						var $questionYnTd=$("<td>").text(data[key].q_yn).addClass('text-center');
						 						
 						
 						$tr.append($categoryTd);
 						$tr.append($question_titleTd);
 						$tr.append($writerTd);
 						$tr.append($questionYnTd);
						$tr.append($tr);
 						$tr.append($questionTd);

 						
 						$inquiryTable.append($tr);
 					}
 					$("#question_title").val("");
 					$("#question").val("");
 				},
 				error:function(request,statur,error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error"+error);
 				}
 			})
 		})
 	})
 </script>

</html>