<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background: #212121;">
	<%@include file="../common/menubar.jsp"%>

	<div id="part1" style="width:100%;height: 700px;">

		<div style="display: block; float: left; width: 50%;">
			<img src="<%=request.getContextPath()%>/views/img/slide3.jpg"
				style="height: 600px; ">
		</div>
		<div
			style="width: 30%; display: inline-block; float: right; margin-right: 110px; margin-top: 230px;text-align:left;">
			<h1 style="font-size: 26px; color:white;letter-spacing: 1px;  font-family:'바탕체'; line-height:40px;">아름다움을 넘어서 미술품이 좋은 재테크가 되는 환경을
				구축합니다.</h1>
				 <br>
				<hr style="border:2px solid red;width:85px;">
			<br> <br>
			<p style="color:#cccccc; white; letter-spacing: 1px; line-height:30px;">아름다운 미술품은 생활의 여유와 내면의 평화를 줍니다. 그러나 많은
				사람들은 경제적인 이유로 미술품을 쉽게 구입하는 것을 망설입니다. 리멤버아트에서 수준 높은 작품을 안심하고 합리적인
				가격으로 구매하여, 아름다운 작품으로부터 느끼는 행복과 함께 다양한 경제적 수익을 얻을 수 있습니다.</p>
		</div>

	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	

	


	<div id="part2" style="height: 600px;  ">

		<div
			style="width: 31%;display: block;  float: left; margin-right:40px;margin-left: 120px; margin-top:-80px ; text-align:right;">
			<h1 style="font-size: 26px; color:white; font-family:'바탕체'; line-height:40px;">역량있는 작가들이 창작활동에만 집중 할 수 있도록
			 전시 홍보,판매에 이르기까지 모든지원을 아끼지 않습니다.</h1>
				 <br>
				 <hr style= "border:2px solid red; width:210px;float:right;">
			<br> <br> 
				 <br>
			<p style="color: #cccccc; letter-spacing: 1px; text-align:right; line-height:30px;">현재의 국내 미술 시장은 대형 갤러리에서
			 소수의 유명 작가들의 작품들만 유통이 되고 있는 시장입니다.리멤버아트는 역량 있는 작가들을 발굴하여 경제적인 걱정을 하지 않고 창작활동에만 집중할 수
				있도록 전시 홍보 판매에 필요한 모든 서비스를 지원합니다.</p>
		</div>

		<div style="flaot:right; display:inline-block; width:40%;">
			<img src="<%=request.getContextPath()%>/views/img/slide1.jpg"
				style="height: 600px;">
		</div>

	</div>

	<br>
	<br>
	<br>
	<br>
	<%@include file="../common/footer.jsp"%>

</body>
</html>