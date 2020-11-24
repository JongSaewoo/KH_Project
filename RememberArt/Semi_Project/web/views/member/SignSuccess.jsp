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
	body{
	width:100%;
	height:600px;
	background-color:beige;
	opacity:0.8;
	}
	
	h1 {
	text-align:center;
	font-size:80px;
	color:black;
	}
</style>
</head>
<body>
	<%@include file="../common/menubar.jsp"%>
		<div><%=message %></div>
	<button onclick ="goHome();">돌아가기</button>
	<script>
		function goHome(){
			location.href="<%=request.getContextPath()%>/index.jsp";
		}
	</script>
	<h1>성공</h1>

</body>
</html>