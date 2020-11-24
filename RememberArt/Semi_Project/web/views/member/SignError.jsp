<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%
    	String message = (String)request.getAttribute("msg");
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/style.css">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto&display=swap"
	rel="stylesheet">


<script src="../js/jquery-3.4.1.min.js"></script>

<style>

</style>
</head>
<body Style ="width:100%; height:600px; background-color:black;">
	<%@include file="../common/menubar.jsp" %>
<h1 style= "color:red;text-align:center;"> 실패 창</h1>
	<button onclick ="goHome();">돌아가기</button>
	<script>
		function goHome(){
			location.href="<%=request.getContextPath()%>/index.jsp";
		}
	</script>


<!-- <a href = "#">비밀번호 찾기</a>

 -->	
</body>
</html>