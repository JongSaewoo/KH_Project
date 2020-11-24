<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/css/Style-signUp.css">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="../js/jquery-3.4.1.min.js"></script>



<style>
#idCheck {
	text-align: center;
}

#idCheck:hover {
	cursor: pointer;
}

#idCheck, #goMain, #joinBtn {
	background: #d3d3d3;
	color: gray;
	border-radius: 5px;
	width: 80px;
	height: 25px;
	text-align: center;
	color: white;
}

#idCheck:hover, #joinBtn:hover, #goMain:hover {
	cursor: pointer;
	color: red;
}

td {
	text-align: right;
}

#joinBtn {
	background: yellowgreen;
}

#joinBtn, #goMain {
	display: inline-block;
}
</style>
</head>


<body>
	<%@include file="../common/menubar.jsp"%>

	<div class="container">
		<form id="joinForm" action="<%=request.getContextPath()%>/insert.me"
			method="post" onsubmit="return insertMember()">
			<!--section1-->
			<div class="section1" style="padding-bottom: 90px;">
				<h1 style="color: black;">JOIN</h1>

				<hr style="border: 1px soild black;">
				<span>회원 가입<a style="color: red; font-size: 15px;">*</a></span>
				<hr style="border: 1px soild black;">

				<div class="artregi-infobox">
					<div class="info-box1">
						<label class="labelfirst" id="name">이름<a
							style="color: red; font-size: 15px;">*</a></label><input
							class="nomal-text" type="text" id="userName" name="userName"
							placeholder="NAME" required>
					</div>
					<br>

					<div class="info-box2">
						<label class="labelfirst">아이디<a
							style="color: red; font-size: 15px;">*</a></label><input
							class="nomal-text" type="text" id="userId" name="userId" required>
					</div>
					<div id="idresult" style="font-size: 13px; float: right;">영어,숫자
						상관없이 4글자이상 입력하세요.</div>
					<br> <br>
					<div id="idCheck"
						style="float: right; width: 100px; height: 20px; background-color: gray; text-align: center; color: white;">중복확인</div>
					<br> <br>

					<div class="info-box3">
						<label class="labelfirst">비밀번호<a
							style="color: red; font-size: 15px;">*</a></label> <input
							class="nomal-text" type="password" id="userPwd" name="userPwd"
							placeholder="PASSWORD" required>
					</div>
					<div id="pwdresult" style="font-size: 13px; float: right;">영어,숫자
						포함해서 6글자 이상 입력하세요.</div>


					<br>

					<div class="info-box4">
						<label class="labelfirst">비밀번호 확인<a
							style="color: red; font-size: 15px;">*</a></label> <input
							class="nomal-text" type="password" id="userPwd2" name="userPwd2"
							placeholder="confirm password" required>
					</div>
					<div id="pwdcheck" style="font-size: 13px; float: right;"></div>

					<br> <br>

					<div class="info-box5">
						<label class="labelfirst">닉네임</label><input class="nomal-text"
							type="text" id="nickName" name="nickName" placeholder="NICKNAME">
					</div>

					<div class="info-box6">
						<label class="labelfirst">휴대폰 번호</label>
						&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input
							type="tel" id="phone" name="phone" maxlength="11"
							autocomplete="off" placeholder="(-)없이 휴대폰 번호 기재"
							style="height: 25px; width: 280px; float: right;">

					</div>

					<div class="info-box7">
						<div>
							<label class="labelfirst">주소<a
								style="color: red; font-size: 15px;">*</a></label>
								
							<div style="float:right;">
							<input type="text" id="sample6_postcode" name="sample6_postcode"  placeholder="우편번호" style="width:188px;height:30px;">
							<input type="button" onclick="sample6_execDaumPostcode()"
								value="우편번호 찾기" style="height:30px;"><br> <input type="text"
								id="sample6_address"  name="sample6_address"  placeholder="주소" style="width:188px;height:30px;"><br> <input
								type="text" id="sample6_detailAddress" name="sample6_detailAddress" placeholder="상세주소" style="width:188px;height:30px;">
							<input type="text" id="sample6_extraAddress" name="sample6_extraAddress" placeholder="참고항목" style="width:85px;height:30px;">

						
</div>
								
						</div>
						
					</div>

<br><br><br><br>
					<div class="info-box7">
						<label class="labelfirst">이메일<a
							style="color: red; font-size: 15px;">*</a></label><input
							class="nomal-text" type="email" id="email" name="email"
							placeholder="@ 포함한 이메일 기재" required>
					</div>
	
					<!-- 
					<div>
						<div class="info-box8">
							<div class="info-box8">
								<button class="PrivacyAgree"
									style="background-color: gray; color: white; text-align: center; width: 400px; height: 20px;">▽</button>
							</div>
							<div class="info-box8">
								<textarea name="paint_int">개인정보수집·이용·제공 동의서 (재)충남테크노파크는 “민간 수출전문가 모집 및 pool 등록”과 관련하여󰡔개인정보보호법󰡕 제15조제1항제1조, 
								제24조제1항제1호에 따라 아래와 같이 개인(신용)정보의 수집·이용에관하여 귀하의 동의를 얻고자 합니다. 1. 개인(신용)정보의 수집·이용에 관한 사항 ① 개인정보의 수집·이용 목적
								ㅇ 귀하의 개인정보는 [(재)충남테크노파크]의 “민간 수출전문가 모집 및 pool 등록”과 관련 하여 전문가pool 등록 및 선정과정, 활용기간 동안의 연락·확인 목적으로 수집·이용됩니다.
								 ② 수집·이용할 개인정보의 항목ㅇ 개인식별 정보(성명, 전자우편주소, (휴대)전화번호) ③ 개인정보의 보유·이용 기간 ㅇ 위 개인정보는 제공된날부터 제공된 목적을 달성할 때까지 보유·
								 이용되며 보유목적 달성시 또는 정보주체가 삭제를 요청할 경우 지체 없이파기합니다.
                    </textarea>
							</div>
						</div>
					</div>
					<div class="info-box9">
						<label class="labelfirst" style="float: right;"><input
							class="" type="checkbox" name="price">확인 후, 동의하겠습니다.</label>
					</div> -->

				</div>
				<!-- infobox 끝 -->
				<br> <br>
				<div class="submit-box" align="center">
					<div id="goMain" onclick="goMain();">메인으로</div>
					<!-- <div id="joinBtn" onclick="insertMember();"></div> -->
					<input type="button" value="가입하기"
						style="width: 280px; height: 30px; color: white; background: #cc0000"
						onclick="checkId()">

				</div>
				<input type="hidden" id="checkTest" value="0">
				<!-- submit-box 끝 -->
			</div>
			<!-- section 끝-->
		</form>
	</div>
	<!-- container 끝-->


	<script>

	//아이디 중복확인 버튼 check 때문에 서브밋 버튼을 버튼으로 만들고, function을 통해 서브밋을  하게됨.
	//그렇게 되면 required 기능이 제공되지 않음
	//required 기능을 하게 하기 위한 if~else문 적용
	function checkId(){
		
		if($("#userName").val()==""){
			alert("이름을 안쓰다니...");
		}else{	// 이름은 썼느데
			if($("#userId").val()==""){
				alert("중요한 아이디를 안쓰다니!");				
			}else{ // 아이디는 썼는데						
					if($("#userPwd").val().length>=6 && $("#userPwd2").val().length>=6){
						if($("#nickName").val()==""){
							alert("닉네임은 써도 되고 안써도 되지만 안쓰면 안넘어가요 ㅎㅎ");
						}else{
							
							if($("#phone").val()==""){
								alert("번호를 입력하세요");
							}else{
								if($("#email").val()==""){
									alert("이메일을 입력하세요.");
								}else{// 이메일을 썼으면
									if(!emailJ.test($("#email").val())){
										alert("이메일 형식으로 써주세요.");
									}else{ //정큐표현식에 맞으면
										 //아이디 중복 확인
										 if($("#checkTest").val() != "0"){
											$("#joinForm").submit();
										}else{
											alert("중복확인 버튼을 체크해 주세요");
										}
									}
								}
								
							}
						}
					}else{
						if($("#userPwd").val()!="" && $("#userPwd2").val()!=""){
								if($("#userPwd").val().length<6||$("#userPwd2").val().length<6){
									alert("비밀번호는 6자 이상을 입력해야 합니다");
									$('#userPwd2').val('');
								}
						}else{
							alert("비밀번호을 다 입력해야 넘어갈 수 있습니다");
						}
					}	
					
					
			}
		}
		
	}

	function goMain() {
			location.href = "<%=request.getContextPath()%>/index.jsp";
	}
	
	function insertMember(){
		/* alert("회원가입이 완료 되었습니다."); */
		
		return true;
	}
	

	
	//ajax

	 	$(function(){
	 		$("#idCheck").click(function(){
	 			$("#idCheck").css("background-color","red");	
	 		});
	 		$("#userId").change(function(){
	 			$("#checkTest").val(0);
	 			$("#idCheck").css("background-color","gray");
	 		});
	 		
	 		$("#idCheck").click(function(){
	 			$("#checkTest").val(1);
	 			var userId = $("#joinForm input[name = 'userId']");
	 			
	 			if(!userId || userId.val().length<4){
	 				alert("아이디는 최소 4자리 이상이어야합니다.");
	 				userId.focus();
	 			}else{
	 				$.ajax({
	 					url: "<%=request.getContextPath()%>/idCheck.me",
						type : "post",
						data : {
							userId : userId.val()
						},
						success : function(data) {
							if (data == 'fail') {
								alert("아이디가 중복 됩니다.");
								userId.focus();
							} else {
								alert("아이디가 사용가능합니다.");
								idCheck = 1;
							}
						},
						error : function(data) {
							console.log("서버 통신 안됨");
						}

					})
				}
			})

		});// ajax 중복

		// jquery

		//아이디 정규식
		var idJ = /^[a-z0-9_]{4,20}$/;
		/* var emailJ = /^[a-zA-Z0-9]+@[a-zA-Z0-9]$/;  */
		
		var emailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		
		$(function() {
			//idresult
			$("#userId").change(
					function() {

						if (idJ.test($(this).val())) {
							$("#idresult").html("정상입력").css('color', 'green');
							$(this).focus().css("background", "white");
						} else {
							$("#userId").val('');
							$("#idresult").html("영어,숫자 사용해서 4글자이상 입력하세요.").css(
									'color', 'red');
							$(this).focus().css("background", "white");
						}
					});

			//비밀번호 정규식 
			var pwdJ = /^[a-z0-9_]{6,20}$/;
			//pwdresult
			$("#userPwd").change(
					function() {
						if (pwdJ.test($(this).val())) {
							$("#pwdresult").html("정상입력").css('color', 'green');
							$(this).focus().css("bakcground", "white");
						} else {
							$("#userId").val('');
							$("#pwdresult").html("영어,숫자 사용해서 6글자 이상 입력하세요.")
									.css('color', 'red');
							$(this).focus().css("background", "white");
						}
					});

			//pwdresult
			/* 	if(!pwdJ.test(name)){
					window.alert("패스워드는 0~9까지 영문자만 가능합니다.");
					
					
					return false;
				} */

			//pwdcheck
			$("#userPwd2").change(function() {

				if ($('#userPwd').val() != $(this).val()) {
					$('#pwdcheck').html('비밀번호 일치 x').css("color", 'red');
					$('#userPwd2').val('');
					$(this).focus();
				} else {
					$("#pwdcheck").html('비밀번호 일치').css("color", 'green');
				}
			})
			$("#userPwd").change(function() {
				if ($('#userPwd2').val() != $(this).val()) {
					/*  					$('#pwdcheck').html('비밀번호 일치 x').css("color", 'red');
					 */$('#userPwd2').val('');
					$('#userPwd2').focus();
				} else {
					$('#pwdcheck').html('비밀번호 일치').css("color", 'green');
				}
			});

		}); // 아이디, 비번 체크

	
	</script>
	
	<script
		/*주소 api*/
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>

	<%@include file="../common/footer.jsp"%>

</body>
</html>