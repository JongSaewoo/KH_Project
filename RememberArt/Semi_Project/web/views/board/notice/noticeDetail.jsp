<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.notice.model.vo.Notice"%>
<%
	Notice n = (Notice) request.getAttribute("notice");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=n.getNoti_title() %></title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/bootstrap.css">
	<script src="<%=request.getContextPath() %>/views/js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/views/js/bootstrap.js"></script>    
<link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/style.css">
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet"> 
<style>
	#goNoticeBoard{ float:right; margin-right:100px; }
	#deleteContent{ float:right; margin-right:3px;}
 	#noticeDetail div{margin: 0 auto; margin-top:30px; margin-bottom:100px;}
	#noticeDetailList:after { display:block; content:""; clear:both; }
	
	.headLine{ float:left; width:30%; margin-top:90px; margin-left:90px; display:block; box-sizing:border-box;}
	.headLine hr{ background-color:red; width:25px; border:2px solid red; margin-bottom:10px;}
	.table{ display:inline-block;}
</style>
</head>
<body>
<%@include file="../../common/menubar.jsp" %>
		
	<!-- Notice 게시판 Logo -->			
	<div class="headLine">
		<hr style="display:inline-block;">
		<h3 style="font-size:20px;">Notice</h3>
	</div><!-- class headLine end -->
<div id="noticeDetail" class="container">


	<div id="noticeDetailList" class="row" style="display:block; margin:center; width:100%">
		<div class="cols-sm-6">
			<table class="table" style="margin-left:150px">
				<tr>
					<td>제목</td>
					<td colspan="6">
						<span><b><%=n.getNoti_title()%></b></span>
						<input id="deleteNo" type="hidden" value="<%=n.getNoti_no()%>">
					</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><span>관리자</span></td>
					<td>작성일</td>
					<td><span><%=n.getNoti_date() %></span>
				</tr>
				<tr>
					<td colspan="6">내용</td>
				</tr>
				<tr>
					<td colspan="6">
						<pre>
							<%=n.getNotice() %>
						</pre>
					</td>
				</tr>
			</table>
			</div>
		</div>
	</div>
	<button id="goNoticeBoard" type="button active" class="btn btn-secondary" onclick="pageBack();">목록</button>
	<button id="deleteContent" type="button active" class="btn btn-secondary" onclick="goDelete();">삭제</button>
<br><br><br>

<%@include file="../../common/footer.jsp" %>
</body>
<script>
	function pageBack(){
		history.go(-1)();
	}
	function goDelete(){
		var deleteNo = document.getElementById("deleteNo").value;
		var result = confirm("정말 삭제하시겠습니까?");
		if(result){
			location.href="<%=request.getContextPath()%>/delete.bo?deleteNo="+deleteNo;			
		}else{
			
		}
	}
</script>
</html>