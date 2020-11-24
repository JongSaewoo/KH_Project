<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>art-registration</title>
</head>
 <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/Style-ar.css">

<body>
<%@include file="../common/menubar.jsp" %>
<%@include file="../common/mypagehead.jsp" %>


    <div class="title">
        <h3>상품 등록</h3>
        <hr>
    </div>
    

    <div class="container">
        <form action="<%=request.getContextPath()%>/insert.th" method="post" encType="multipart/form-data">
            <!--section1-->
            <div class="section1" style="padding-bottom: 90px;">
                <span>상품 정보<a style="color:red;">*</a></span>
                <div class="artregi-infobox">
                    <div class="info-box1">
                        <label class="labelfirst" for="pname">작품명</label><input class="nomal-text" type="text"
                            name="pname">
                    </div>

                    <div class="info-box2">
                        <label class="labelfirst" for="aname">작가명</label><input class="nomal-text" type="text"
                            name="aname">
                    </div>

                    <div class="info-box3">
                        <label class="labelfirst">테마</label>
                        <select class="select-thema" name="select-thema">
                            <option value="풍경">풍경</option>
                            <option value="인물">인물</option>
                            <option value="정물">정물</option>
                            <option value="동물">동물</option>
                            <option value="추상">추상</option>
                            <option value="팝아트">팝아트</option>
                            <option value="오브제">오브제</option>
                        </select>
                    </div>

                    <div class="info-box4">
                        <label class="labelfirst">제작년도</label><input class="nomal-text" type="text" name="year">
                    </div>

                    <div class="info-box5">
                        <label class="labelfirst">가격</label><input class="nomal-text" type="text" name="price">
                    </div>
						
                    <div class="info-box6" style = "margin-bottom : 10px;">
                        <label class="labelfirst">사이즈</label><input class="nomal-text" type="text" name="size">
                    </div>
                    
                    <script>
                    </script>

                    <div class="info-box7">
                        <label class="labelfirst">상세 설명</label>
                        <textarea name="paint_int">
                    </textarea>
                    </div>
                </div>
            </div>

            <!--section2-->

            <div class="section2" style="padding-bottom: 100px;">
                <span>이미지 등록<a style="color:red;">*</a></span>
                <div class="artregi-imgbox">
                
                    <div class="img-box">
                        <label class="labelsecond">대표 이미지</label>
                        <div class="img-upload1">
                            <label for="thumbnailImg">파일 첨부하기</label>
                            <input class="upload1-name1" value="파일선택">
                            <input type="file" id="thumbnailImg" name="thumbnailImg">
                        </div>
                    </div>
                    
                    <hr style="margin-bottom:30px;">
                    
                    <div class="img-box2">
                        <label class="labelsecond">추가 이미지<br>0/5</label>
                        <div class="img-upload2">
                        <div>
                            <label for="a-img" >파일 첨부하기</label>
                            <input class="upload2-name1" value="파일선택">
                            <input type="file" id="a-img" name="a-img"><br>
                         </div>
                         <div>    
                             <label for="a-img2" >파일 첨부하기</label>
                             <input class="upload2-name2" value="파일선택">
                            <input type="file" id="a-img2" name="a-img2"><br>
                         </div>
                        
                        </div>
                    </div>
                    <script>
					
					</script>
                    
                </div>
                <div class="submit-box">
                <button class = "submit-bt" type=submit>제출하기</button>
                </div>
            </div>
    </form>
    </div>
    <script>
    
    /* 파일 이름 추출해서 넣어주기 label에 넣어주기*/

    $(document).ready(function(){
    	var fileTarget = $('.img-upload1 #thumbnailImg');
    	var fileTarget2 = $('.img-upload2 #a-img');
    	var fileTarget3 = $('.img-upload2 #a-img2');
    	
    	fileTarget.on('change', function(){
    		if(window.FileReader){
    			var filename = $(this)[0].files[0].name;
    			} else { 
    				var filename = $(this).val().split('/').pop().split('\\').pop();
    			}
    				
    		$(this).siblings('.upload1-name1').val(filename);
    		
    	});
    	
    	fileTarget2.on('change', function(){
    		if(window.FileReader){
    			var filename = $(this)[0].files[0].name;
    			} else { 
    				var filename = $(this).val().split('/').pop().split('\\').pop();
    			}
    				
    		$(this).siblings('.upload2-name1').val(filename);
    		
    	});
    	
    	fileTarget3.on('change', function(){
    		if(window.FileReader){
    			var filename = $(this)[0].files[0].name;
    			} else { 
    				var filename = $(this).val().split('/').pop().split('\\').pop();
    			}
    				
    		$(this).siblings('.upload2-name2').val(filename);
    		
    	});
    	
    	
    	});

    </script>
    
<%@include file="../common/footer.jsp" %>
</body>

</html>