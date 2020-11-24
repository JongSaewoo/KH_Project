<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/css/Style-forgetinfo.css">


<script src="../js/jquery-3.4.1.min.js"></script>
<style>
#contents {
	height: 600px;
}

#pwdFind:hover{
	text-decoration:line;
}
</style>
</head>


</head>
<body>
	<%@include file="../../common/menubar.jsp"%>
	<div id="contents" style="background:#cccc;">

<br><br><br><br><br><br><br><br>
		<div
			style="margin: 0 auto; width: 500px; height: 300px; background: #e9ecef ">
			<h1 style="color: black; text-align: center; padding-top: 20px;">비밀번호
				찾기</h1>	<br>
			<hr style="border: 2px solid red">
			
			<div style="width: 250px; height: 200px;  margin: 0 auto;padding-top:20px;">
				 <label style="font-size:15px;">아이디</label>
				 <input type="text" id="id" style="float:right; height:30px; width:250px;"placeholder="회원등록 된 아이디 입력"> 
				 <br><br><br><br>
				 <label style="font-size:15px;">이메일 주소</label>
				 <br>
				 <input type="text" id="email2" style="height:30px;float:right;width:250px;" placeholder="임시 비밀번호 받을 이메일 주소 입력">
				<br><br>
				<button id ="pwdFind" style="float:right" onclick="goPwdFind();">비밀번호 찾기</button>
			</div>
			<button id ="back" style="float:right" onclick="history.back(-1);">뒤로가기</button>
			<button id ="goMain" style="float:right" onclick="goMain();">메인가기</button>
			
		</div>

	</div>
	<%@include file="../../common/footer.jsp"%>
	<script>
	function goPwdFind(){
		alert("새로운 비번 생성해 메일로 보냈습니다.");
		location.href ="<%=request.getContextPath()%>/pwdFind.me?findId="+$('#id').val()+"&findEmail="+$('#email2').val();
	}
	function goMain() {
		location.href = "<%=request.getContextPath()%>/index.jsp";
}
	</script>

</body>
</html>