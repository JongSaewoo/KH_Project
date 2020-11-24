<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import = "artistapply.model.vo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="views/css/style.css">
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
<!-- <link rel="stylesheet" href="../css/bootstrap.css"> -->
<link rel="stylesheet" href="views/css/MainSlide.css">

<script src="views/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="views/js/bootstrap.js"></script>
<style>

</style>
</head>

<body>

	<%@include file="views/common/menubar.jsp" %>


<section> <!-- 메인 슬라이드 -->

		<div id="contents">
			<div class="part1">
		<!-- 		<div class="slide">
					<h1 align="center">REMEMBER ART</h1>
					 <a href="#"><img src="views/img/slide1.jpg" style="height: auto;"> </a>
            <a href="#"><img src=""></a>
            <a href="#"><img src=""></a>    -->

					<div id="slider-wrap" style = "position:relative; margin:-18px auto;">
						<ul id="slider" >
						
							<li>
			
							 <a href="<%=request.getContextPath()%>/view.ap">
							 	<img src="<%=request.getContextPath()%>/views/img/background-1.png">
							 </a>
							
							</li>
							
							
							
							<li>
								<div>
							<!--  	<h3>Slide #1</h3>-->	
									<!-- <span>작가제휴 바로가기</span>  -->
								</div>
								 <button class="btn btn-outline-dark" style=" position:absolute; width:120px; height:50px; margin-top: 10px;"
							onclick= "location.href='<%=request.getContextPath()%>/view.ap">sammy</button>
								<img src="<%=request.getContextPath()%>/views/img/slide4.jpg">
							</li>




							<li>
							
								<div>
								<!-- 	<h3>Slide #3</h3>-->
									<span>Sammy2</span> 
								</div> <a href="<%=request.getContextPath()%>/list.ar">
								<img src="<%=request.getContextPath()%>/views/img/slide5.jpg"></a>
							</li>

				</ul>

						<div class="slider-btns" id="next" >
							<span>▶</span>
						</div>
						<div class="slider-btns" id="previous">
							<span>◀</span>
						</div>

						<div id="slider-pagination-wrap">
							<ul style=" margin-top:38%">
							</ul>
						</div>
					</div>
				</div>
				<!--slide end-->
			</div>
			<!--part1 end-->

			<script>
				//slide-wrap
				var slideWrapper = document.getElementById('slider-wrap');
				//current slideIndexition
				var slideIndex = 0;
				//items
				var slides = document.querySelectorAll('#slider-wrap ul li');
				//number of slides
				var totalSlides = slides.length;
				//get the slide width
				var sliderWidth = slideWrapper.clientWidth;
				//set width of items
				slides.forEach(function(element) {
					element.style.width = sliderWidth + 'px';
				})
				//set width to be 'x' times the number of slides
				var slider = document.querySelector('#slider-wrap ul#slider');
				slider.style.width = sliderWidth * totalSlides + 'px';

				// next, prev
				var nextBtn = document.getElementById('next');
				var prevBtn = document.getElementById('previous');
				nextBtn.addEventListener('click', function() {
					plusSlides(1);
				});
				prevBtn.addEventListener('click', function() {
					plusSlides(-1);
				});

				// hover
				slideWrapper.addEventListener('mouseover', function() {
					this.classList.add('active');
					clearInterval(autoSlider);
				});
				slideWrapper.addEventListener('mouseleave', function() {
					this.classList.remove('active');
					autoSlider = setInterval(function() {
						plusSlides(1);
					}, 300000); 
				});

				function plusSlides(n) {
					showSlides(slideIndex += n);
				}

				function currentSlides(n) {
					showSlides(slideIndex = n);
				}

				function showSlides(n) {
					slideIndex = n;
					if (slideIndex == -1) {
						slideIndex = totalSlides - 1;
					} else if (slideIndex === totalSlides) {
						slideIndex = 0;
					}

					slider.style.left = -(sliderWidth * slideIndex) + 'px';
					pagination();
				}

				//pagination
				slides.forEach(function() {
					var li = document.createElement('li');
					document.querySelector('#slider-pagination-wrap ul')
							.appendChild(li);
				})

				function pagination() {
					var dots = document
							.querySelectorAll('#slider-pagination-wrap ul li');
					dots.forEach(function(element) {
						element.classList.remove('active');
					});
					dots[slideIndex].classList.add('active');
				}

				pagination();
				var autoSlider = setInterval(function() {
					plusSlides(1);
				}, 3000);
			</script> <!-- 슬라이드 스크립트 끝 -->

			<!--part2 end 마스터 피스 부분-->

		
			<!--part3 end 마스터 피스 부분-->
		</div>
		
		<!--content end-->




	<%@include file="views/main/main.jsp" %>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<%@include file="views/common/footer.jsp" %>
</body>
</html>