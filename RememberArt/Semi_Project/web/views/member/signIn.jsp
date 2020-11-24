<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
#LoginBtn input, #memberJoinBtn, #logoutBtn, #myPage {
	display: inline-block;
	vertical-align: middle;
	text-align: center;
	width: 100px;
	padding: 7px;
	border-style: none;
	
}

#memberJoinBtn {
	width: 86px;
	background: black;
	color: white;
	font-size: 12px;
	height: 17px;
	border: 1px solid white;
}

#loginBtn:hover, #memberJoinBtn:hover, #logoutBtn:hover, #memberJoinBtn:hover,
	#myPage:hover {
	cursor: pointer;
	color:red;
	
}
#LoginBtn input:hover {
	color:red;'
		
}

 a:hover {
color:red;
}

#loginBtn {
	background: red;
	width: 100px;
	
}
</style>

<body style="background-color: #e9ecef; ">
	<script>
		/*로그인 입력 안했을 시 => alter창 또는 팝업창 띄운 후, error 페이지로 연결*/
		function validate() {
			if ($("#userId").val().trim().length == 0) {
				alert("아이디를 입력하세요");
				$("#userId").focus();
				return false;
			}
			//비밀번호를 입력하지 않았을 때
			if ($("#userPwd").val().trim().length == 0) {
				alert("비밀번호를 입력하세요");
				$("#userPwd").focus();

				return false;
			}
			return true;
		}
	</script>


	<%@include file="../common/menubar.jsp"%>
	<!-- section1 -->
	<div class="loginArea" id="login" style="height: 580px;">
		<%
			if (loginUser == null) {
		%>
		<form method="get" action="<%=request.getContextPath()%>/login.me"
			onsubmit="return validate();" >
			<br> <br> <br> <br> <br>
			<h1 align="center" style="color:#cc0000;">로그인</h1>
			<br> <br>
			<table class="inputinfo">
				<tr>
					<td><label></label>
					<td>
					<td><input type="text" name="userId" id="userId"
						placeholder="ID" style="width: 200px; height: 30px;"></td>
				</tr>
				<tr>
					<td><label></label>
					<td>
					<td><input type="password" name="userPwd" id="userPwd"
						placeholder="PASSWORD" style="width: 200px; height: 30px;"></td>
				</tr>
			</table>
			<br>
			<div class="btns" align="center" >
				<div id="memberJoinBtn" onclick="memberJoin();">
					<span style="">회원가입</span>
				</div>
				<div id="LoginBtn" style="display: inline-block;">
					<input type="submit" value="로그인">
				</div>
				
				<div id="infofind" style="font-size:13px; padding:5px; " >
					<a href="<%=request.getContextPath()%>/views/member/Forget/forgetInfo.jsp" style="color:black;margin-left:120px;color:#cc0000;">비밀번호 찾기</a>
				</div>
			</div>

		</form>
		<%
			} else {
		%>
		<%-- 	
		 <div id="userInfo"
			style="background-color: white; margin: 0 auto; float: right;">
			<label><%=loginUser.getUserName()%>님의 방문을 환영합니다.</label>
			<div class="btns" align="center">
				<br> <br>
				<div id="myPage"
					onclick="location.href='myPage.me?userId=<%=loginUser.getUserId()%>';">정보수정
				</div>
				<div id="logoutBtn" onclick="logout();">로그아웃</div>
			</div>
		</div> --%>
		<%
			}
		%>
	</div>
	<!-- loginArea 끝 -->
	<script>
		function memberJoin(){
			location.href="<%=request.getContextPath()%>/views/member/signUp.jsp;"
			}
		function logout(){
			location.href="<%=request.getContextPath()%>/logout.me;"
		}

		$(function userInfo() {

		});
	</script>


	<%@include file="../common/footer.jsp"%>

</body>
</html>