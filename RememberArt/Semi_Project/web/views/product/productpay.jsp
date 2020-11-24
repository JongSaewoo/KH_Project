<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import = "product.model.vo.*,artistapply.model.vo.*, java.util.ArrayList"%>
    <%
    	product po = (product)request.getAttribute("po");
    ArrayList<Attachment> at = (ArrayList<Attachment>)request.getAttribute("at");
    %>
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
 <!— Swiper JS —>
  <script src="<%=request.getContextPath() %>/views/js/swiper.min.js"></script>
    <!-- Link Swiper's CSS -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/swiper.min.css">

 <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


</head>
<style>
#order {
	display: inline-block;
	width: 760px;
	height: 100%;
	float: left;
	margin-left: 20px;
	margin-right: 0px;
	margin-top: 20px;
}

#what {
	display: inline-block;
	width: 320px;
	height: 896px;
	float: left;
	margin-left: 150px;
	margin-right: 0px;
	margin-top: 0px;
	background-color: rgb(224, 224, 224);
}

#add1, #add4{
	width:100px;	
}
#add2, #add3, #add5, #add6{
	width:400px;
}
</style>
<body>
<%@include file="../common/menubar.jsp" %>
<table class="table">
  <thead>
    <tr>
      <th scope="col"></th>
      <th scope="col">상품명</th>
      <th scope="col">수량</th>
      <th scope="col">판매가</th>
      <th scope="col">배송비</th>
    </tr>
  </thead>
   
  <tbody>
      <tr>
      <th scope="row">
       <%for(int i =0; i< at.size();i++){
	  Attachment a = at.get(i);
	  if(a.getFileLevel() == 0){%>
      <img class="rimage" src="<%=request.getContextPath()%>/thumbnail_uploadFiles/<%=a.getSavefileName()%>" style="width: 126px; height: 115px;">
      <%}} %>
      </th>
      <td ><a><%=po.getPaint_name()%></a></td>
      <td><a>1</a></td>
      <td><a><%=po.getPatint_price()%></a></td>
      <td><a>무료배송</a></td>
    </tr>
  </tbody>
  
</table>
<br clear="both"><br>
<script>
function execDaumPostcode() {
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
                   } else {
                     
                   }

                   // 우편번호와 주소 정보를 해당 필드에 넣는다.
                   document.getElementById('add1').value = data.zonecode;
                   document.getElementById("add2").value = addr;
                   // 커서를 상세주소 필드로 이동한다.
                   document.getElementById("add3").focus();
               }
           }).open();
       }
</script>

<script>
function execDaumPostcode2() {
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
            } else {
              
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('add4').value = data.zonecode;
            document.getElementById("add5").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("add6").focus();
        }
    }).open();
}
</script>
<div id="order">

<form action="<%=request.getContextPath()%>/insert.po" method="post"><!-- form 태그 시작 -->
<h3 align="left">주문하시는 분</h3>
<input type="hidden" id="paint_no" value="<%=po.getPaint_no()%>" name="paint_no">
<table class="table table-borderless">
    <tr>
      <th scope="col">이름* &nbsp; &nbsp;
      <input type="text" id="name" placeholder="이름" size="10px" style="margin-left: 50px;" name="ordername"></th>
    </tr>
    <tr>
      <th scope="row">핸드폰 &nbsp;
       <input type="text" id="name" placeholder="010-" size="10px" style="margin-left: 50px;" name="orderphone"></th>


    </tr>
    <tr>
      <th scope="row">E-mail

       <input type="email" id="email" placeholder="abc@abc.com" size="20px" style="margin-left: 62px;" name="orderEmail"></th>


    </tr>
    <tr>
      <th>
       <input type="text" id="add1" class="form-control" placeholder="우편번호" size="10px" style="margin-left: 109px; float:left;" name="orderadress1" readonly>	
       &nbsp;&nbsp;<button type="button" onclick="execDaumPostcode();" class="btn btn-outline-dark ">주소 검색</button>
       </th>
    </tr>
    <tr>
      <th scope="row">주소
       <input type="text" id="add2" class="form-control" placeholder="기본주소" size="20px" style="margin-left: 109px; " name="orderadress2" readonly></th>
    </tr>
     <tr>
      <th>
       <input type="text" id="add3" class="form-control" placeholder="상세 주소" size="20px" style="margin-left: 109px;" name="orderadress3">
       </th>
    </tr>
</table>
<h3 align="left">받으시는 분</h3>
<script>
 
</script>
<table class="table table-borderless">
    <tr>
      <th scope="row">이름 &nbsp;&nbsp;
       <input type="text" id="name" placeholder="이름" size="10px" style="margin-left: 62px;" name="receivename"></th>

    </tr>
     <tr>
      <th scope="row">핸드폰 &nbsp;
       <input type="text" id="name" placeholder="010-" size="10px" style="margin-left: 50px;" name="receivephone"></th>


    </tr>
    <tr>
      <th>
       <input type="text" id="add4" class="form-control" placeholder="우편번호" size="10px" style="margin-left: 109px; float:left;" name="receiveaddress1" readonly>
       &nbsp;&nbsp;<button type="button" onclick="execDaumPostcode2();"  class="btn btn-outline-dark">주소 검색</button>
       </th>
    </tr>
    <tr>
      <th scope="row">주소
       <input type="text" id="add5" class="form-control" placeholder="기본주소" size="20px" style="margin-left: 109px;" name="receiveaddress2" readonly></th>
    </tr>
     <tr>
      <th>
       <input type="text" id="add6" class="form-control" placeholder="상세 주소" size="20px" style="margin-left: 109px;" name="receiveaddress3">
       </th>
    </tr>
    <tr>
     <th scope="row">전하실 말씀
      <textarea style="margin-left: 20px; resize : none;" class="form-control col-sm-8" rows="6" name="receivecontent"></textarea>
</table>

<br celar="both">
</div>
<div id="what">
<h3 align="left" style="margin-top: 30px; margin-left: 10px">결제 정보</h3>
<br>
<div style="background: white; margin-left:10px; width:300px">
<a>총 주문 금액 : <%=po.getPatint_price()%>원</a>
</div>

<h3 align="left" style="margin-top: 30px; margin-left: 10px">결제 수단</h3>

<div class="btn-group" data-toggle="buttons" style="margin-left:10px; width:300px;">
<label class="btn btn-outline-dark">
<input type="radio" name="orderrule" value="무통장입금" style="display:none;">무통장입금
</label>
<label class="btn btn-outline-dark">
<input type="radio" name="orderrule" value="신용 카드" style="display:none;">신용 카드
</label>
</div>

<h3 align="left" style="margin-top: 30px; margin-left: 10px">이용 약관 안내</h3>
<br>

<div style="background: white; margin-left:10px; width:300px">
<a>아름답고 소담스러운 열매를 맺어 우리 인생을 풍부하게 하는 것이다 보라 청춘을 ! 그들의 몸이 얼마나 튼튼하며

별과 같이 산야에 피어나는 군영과 같이 이상은 실로 인간의 부패를 방지하는 소금이라 할지니 인생에 가치를 주는 원질이 되는 것이다 그들은 앞이 긴지라 착목한는 곳이 원대하고 그들은 피가 더운지라 실현에 대한 자신과 용기가 있다 그러므로 그들은 이상의 보배를 능히 품으며 그들의 이상은 아름답고

열매를 맺어 우리 인생을 풍부하게 하는 것이다 보라 청춘을 ! 그들의 몸이 얼마나 튼튼하며 그들</a>
</div>

<br>

<div style="margin-left:10px; width:300px">
<button type="submit" class="btn btn-dark btn-sm" style=" width:300px;"> 주문하기</button> <br><br>
<button type="button" class="btn btn-dark btn-sm buycancel" style=" width:300px;"> 취소</button> 
</div>
</form>
<script>
$(function(){
		$(".buycancel").click(function(){
			
			location.href="<%=request.getContextPath()%>/index.jsp";
				
		});
		
	});
	
	
</script>
</div>
<br clear="both"><br>
<%@include file="../common/footer.jsp" %>

</body>
</html>