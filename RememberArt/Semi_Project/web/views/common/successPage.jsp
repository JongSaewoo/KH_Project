<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String message = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error</title>
<style>
@import url("https://fonts.googleapis.com/css?family=Nunito+Sans:400,700&display=swap");

* {
  margin: 0;
}

html {
  font-family: "Nunito Sans", sans-serif;
  font-size: 16px;
  line-height: 1.5;
  color: #1f2d3d;
}

.box-body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100vh;
  margin: 0 auto;
  background-color: #f8f9fc;
}



/* Common styles */
.feature-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  max-width: 420px;
  padding: 40px 32px;
  border-radius: 6px;
  background-color: #fff;
  text-align: center;
}

.feature-box h1 {
  margin-bottom: 1rem;
  font-size: 20px;
  font-weight: 400;
/*   line-height: 1.33333333; */
  color: #253858;
}


.goback-btn {
	width : 100px;
	height : 30px;
	border-radius : 5px;
	font-size : 13px;
	border : 1px solid black;
	
}



</style>

</head>
<body>
<%@include file="../common/menubar.jsp" %>

	<div class = "box-body">
	<div class = "feature-box">
		<h1 align="center" style = "color: black; padding : 0;"><%= message %></h1>
		<button type="button" class = "goback-btn" onclick="location.href='<%=request.getContextPath()%>/amateur.master'" >뒤로 가기</button>
	</div>
	</div>
	
	
	
	
	
	
	
</body>
</html>