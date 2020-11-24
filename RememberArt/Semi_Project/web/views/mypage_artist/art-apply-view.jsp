<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "artistapply.model.vo.*, java.util.ArrayList" %>
    
<%
ArrayList<Career> career = (ArrayList<Career>) request.getAttribute("career");
Apply artist = (Apply) request.getAttribute("artist");
	
	String userId = artist.getUser_id();
	String applyDate = artist.getApply_date() != null ? artist.getApply_date() : "";
	String artistInt = artist.getArtist_int() != null ? artist.getArtist_int() : "";
	String aPhoto = artist.getArtist_pro() != null ? artist.getArtist_pro() : "";
	String carEtc = artist.getCar_etc() != null ? artist.getCar_etc() : "";
	String carFile = artist.getCar_file() != null ? artist.getCar_file() : "파일 선택";
	
/* 	for(int i = 0 ; career.size() > i ; i++) {
		Career c = career.get(i);
		
	} */
	

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제휴 신청</title>
 <!-- link rel="stylesheet" href="../css/style.css" -->


 <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/views/css/Style-apply.css">
<script src="<%=request.getContextPath() %>/views/js/jquery-3.4.1.min.js"></script>



</head>
<body>
<%@include file="../common/menubar.jsp" %>
<div class = headline>
        <hr>
        <h3 style="font-size: 20px;"> 작가 제휴 신청 </h3>
    </div>
	
    <div class="container">
        <form action="<%= request.getContextPath() %>/update.ap" method="post" encType="multipart/form-data">
            <h3>프로필</h3>
            <hr>
            <div>
                <div class="introduce-box">
                    <div class="introduce-area">
                        <label class="label-first" for="introduce">자기 소개</label>
                        <textarea class="textarea-introduce" name="introduce" ><%=artistInt%></textarea>
                    </div>
                    <div class="image-box">
                        <a style="font-size: 80%; margin-bottom: 10px; display: inline-block; margin: 10px 0;">사진 업로드</a>
                        <div class = "imgPreview"><img class= "artistImg" width ="135px" src = "<%=request.getContextPath()%>/apply_uploadFiles/<%=aPhoto%>"><span class = "x-box" onclick="deletePhoto();"></span></div>
                        <label for="file1" id ="uploadPhoto1" onchange="uploadPhoto(this);"> 사진 업로드</label>
                        <input type="file" name="file1" id="file1" onchange="uploadPhoto(this);">
                        <!-- <input class="upload-name" value="파일선택"> -->

                    </div>
                </div>
                <br>
                <h3>경력사항 작성</h3>
                <hr>
                <div class="career-box">
                    <div class="career-1">
                        <label class="label-first" style = "margin-bottom:0;">기간</label>
                    </div>
                    <div class="career-2">
                        <label class="label-first" style = "margin-bottom:0;">경력 내용</label>
                    </div>
                </div>
                
                <% for(int i = 0 ; i < career.size(); i++) {
                	Career c = career.get(i);
                %>
                
                <div class="career-box2">
                    <div class="career-1">
                        <input type="text" name="career-t<%=i+1%>" id="carrea-t<%=i+1%>" value="<%=c.getMyCareerTerm()%>">
                    </div>
                    <div class="career-2">
                        <input type="text" name="career<%=i+1%>" id="carrea<%=i+1%>" value="<%=c.getMyCareer()%>">
                    </div>
                </div>
                
                <%}%>
                <%if(career.size()==2){
                %>
                <div class="career-box3">
                    <div class="career-1">
                        <input type="text" name="career-t3" id="carrea-t3">
                    </div>
                    <div class="career-2">
                        <input type="text" name="career3" id="carrea3">
                    </div>
                </div>
                <%} else if(career.size()==1){
                 	for(int i = 2 ; i >= career.size() ; i--) {
                %>
                <div class="career-box3">
                    <div class="career-1">
                        <input type="text" name="career-t<%=3-(i-career.size())%>" id="carrea-t<%=3-(i-career.size())%>">
                    </div>
                    <div class="career-2">
                        <input type="text" name="career<%=3-(i-career.size())%>" id="carrea<%=3-(i-career.size())%>">
                    </div>
                </div>
                <%} %>
                <%} %>
                
                
                <div class="etc-box">
                    <label class="label-first" for="etc" style = "margin-top : 30px;" >기타 이력</label>
                    <div class="etc-area">
                        <textarea class="textarea-etc" name="etc"><%=carEtc%></textarea>
                    </div>
                    <div class="file-box">
                        <label class = "putFile" for="a-file">파일 첨부하기</label>
                        <input class="upload-file" ><%-- value="<%=carFile%>" --%>
                        <input type="file" id="a-file" name="a-file"><br>
                    </div>
                </div>
                <button type="submit" name="goSubmit" style = "margin-left : 5px;" onclick = "updateApply()">수정하기</button>
                <button type="button" id="goMain">취소</button>
            </div>
            
        </form>

    </div>
    
    <script>
    
      $("#uploadPhoto1").hide();
	  $(".imgPreview").attr("style","display:flex");
	  $(".x-box").attr("style","display:flex");
	  $(".upload-file").attr("style", "display:inline-block");
    
    	
    	// 메인으로 가기
    	
    	$("#goMain").on('click', function() {
    		location.href="<%=request.getContextPath()%>/index.jsp";
    	});
    
    
    	// 프로필 이미지 프리뷰
    	
    	function uploadPhoto(value){
    		
			if(value.files && value.files[0]) {
				
				var reader = new FileReader();
			
				reader.onload = function(e) {
					$(".artistImg").attr("src", e.target.result);
				}
			
				reader.readAsDataURL(value.files[0]);
			}
		
			$("#uploadPhoto1").hide();
			$(".imgPreview").attr("style","display:flex");
			$(".x-box").attr("style","display:flex");
		}
    	
    	// 프리뷰 지우기
    	
    	function deletePhoto() {
    		
			$(".imgPreview").attr("style","display:none"); // 사진 감싸는 박스
			
			$(".x-box").attr("style","display:none"); // 취소 버튼
			
			$("#uploadPhoto1").show();	// 사진 업로드 버튼
    		
    	}
    	
    
    	// 파일 이름 불러오기
    	
        $(document).ready(function(){
        	
        	var fileTarget = $('.file-box #a-file');
        	
        	fileTarget.on('change', function(){
        		if(window.FileReader){
        			var filename = $(this)[0].files[0].name;
        		} else { 
        			var filename = $(this).val().split('/').pop().split('\\').pop();
        		}
        		
        		$(this).siblings('.upload-file').val(filename);
        		
        		$(".upload-file").attr("style", "display:inline-block");
        		
        	});
        	
        });
    	
    	<%-- // update
    	
    	function updateApply() {
    		
    		location.href = "<%=request.getContextPath()%>/update.ap";
    	}
 --%>
        </script>
    
<%@include file="../common/footer.jsp" %>
</body>
</html>