<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <!-- link rel="stylesheet" href="../css/style.css" -->
 <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/style.css">
 <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
 <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/bootstrap.css">
<script src="<%=request.getContextPath() %>/views/js/jquery-3.4.1.min.js"></script>

  <style>
 #search{
    background-color: rgb(224, 224, 224);
}
.rul {
	margin-right: 10px;
	margin-left: 10px;
	height: 400px;
	padding-left: 100px;
	float:left;
}
.gellary {
	display: inline-block;
	background: #fff;
	width: 20%;
	height: 90%;
	margin-right: 20px;
	margin-top: 20px;
	
}
.mname {
	width: 100%;
	height: 50px;
	text-align: center;
}
.list{
	margin-right: 10px;
	margin-left: 10px;
	padding-left: 130px;
	float:left;

}
#buy{
	margin-left:160px;

}
  </style>
  
</head>
<body>
<%@include file="../common/menubar.jsp" %>
       <h1 align="center" style="color: black;">미술품 판매(가제)</h1>
        <div id="search">
            
            <form action="<%=request.getContextPath()%>/worksearch.po" method="post">
            <table class="table table-borderless" >
            <tr>
            <td></td>
            </tr>
                <tr>
                	<td></td>
                    <td><b>작가 명</b><br><input type="text" id="aname" name="aname"></td>
                    <td><b>테마</b><br><select name="category" id="category">
                                <option value="인물">인물</option>
                                <option value="풍경">풍경</option> 
                                <option value="정물">정물</option>
                                <option value="동물">동물</option>
                                <option value="추상">추상</option>
                                <option value="팝아트">팝아트</option>
                                <option value="오브제">오브제</option>
                    </select></td>
                    <td></td>
                    <td></td>
                    <td><b>가격</b><br>
                    <input type="range" id="price" min="0" max="100000000" step="10000" value="0" name="price">
                    <br><div></div></td><!-- 가격이변하는걸 표현해줘야함. -->
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td><button type="submit" id="submit" class="btn btn-dark">검색</button></td>
                    <td></td>
                </tr>
            </table>
        </form>
        
        
        
        </div>
        <p class="h3">작품</p>
       
       <br>
       
       <b>검색 결과가 없습니다.</b>


   <br><br><br><br><br><br><br><br><br>

<%@include file="../common/footer.jsp" %>
</body>
</html>