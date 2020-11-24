<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

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
        <form action="<%= request.getContextPath() %>/insert.ap" method="post" encType="multipart/form-data">
            <h3>프로필</h3>
            <hr>
            <div>
                <div class="introduce-box">
                    <div class="introduce-area">
                        <label class="label-first" for="introduce">자기 소개</label>
                        <textarea class="textarea-introduce" name="introduce"></textarea>
                    </div>
                    <div class="image-box">
                        <a style="font-size: 80%; margin-bottom: 10px; display: inline-block; margin: 10px 0;">사진 업로드</a>
                        <div class = "imgPreview"><img class= "artistImg" width ="120%"><span class = "x-box" onclick="deletePhoto();"></span></div>
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
                        <label class="label-first" for="career-t1">기간</label>
                        <input type="text" name="career-t1" id="carrea-t1">
                    </div>
                    <div class="career-2">
                        <label class="label-first" for="career1">경력 내용</label>
                        <input type="text" name="career1" id="carrea1">
                    </div>
                </div>
                <div class="career-box2">
                    <div class="career-1">
                        <input type="text" name="career-t2" id="carrea-t2">
                    </div>
                    <div class="career-2">
                        <input type="text" name="career2" id="carrea2">
                    </div>
                </div>
                <div class="career-box3">
                    <div class="career-1">
                        <input type="text" name="career-t3" id="carrea-t3">
                    </div>
                    <div class="career-2">
                        <input type="text" name="career3" id="carrea3">
                    </div>
                </div>
                <div class="etc-box">
                    <label class="label-first" for="etc" style = "margin-top : 30px;" >기타 이력</label>
                    <div class="etc-area">
                        <textarea class="textarea-etc" name="etc"></textarea>
                    </div>
                    <div class="file-box">
                        <label class = "putFile" for="a-file">파일 첨부하기</label>
                        <input class="upload-file" value="파일선택">
                        <input type="file" id="a-file" name="a-file"><br>
                    </div>
                </div>
                <button type="submit" name="goSubmit" style = "margin-left : 5px;">제출하기</button>
                <button type="button" id="goMain">취소</button>
            </div>
            
        </form>

    </div>
    
    <script>
    
    	
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

        </script>
    
<%@include file="../common/footer.jsp" %>
</body>
</html>