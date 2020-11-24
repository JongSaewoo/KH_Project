<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="mypage_user.mainOrderRefundWish.model.vo.*,product.model.vo.*, java.util.ArrayList"%>
    
<%
   ArrayList<Morw> list = ((ArrayList<Morw>)request.getAttribute("list"));
   ArrayList<Attachment> plist = (ArrayList<Attachment>) request.getAttribute("plist");
 System.out.println(request.getContextPath());
 int totalPrice = 0;
 NumberFormat nf = NumberFormat.getInstance();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
 <link rel="stylesheet" href="<%= request.getContextPath() %>/views/css/Style-mypagehead.css">
 <link rel="stylesheet" href="<%= request.getContextPath() %>/views/css/Style-refund.css">
  
<script src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>


<script type="text/javascript">

// $("document").ready(function(){
//    $("#input[name=check]:checkbox").click(function(){
//       totalPrice();
//    });
// });

// BASKET_NO 를 일정한 형식으로 담을 변수
var arrayList = '';

// BASKET_NO 값을 읽어오기 위한 메소드 (삭제 버튼 클릭 시 호출 메소드)
function deleteBasket(){
   // 먼저 input checkbox중 name이 check인 객체들을 정의
   var checkboxList = $("input[name=check]:checkbox");
   arrayList = '';
   
   // 위에서 정의한 checkbox 객체 배열 리스트 for문으로 하나씩 확인
   for(var i=0; i<checkboxList.length; i++){
      
      // checkbox가 체크 되어있을 때만 실행
      if($(checkboxList[i]).is(":checked")){
         
         // BASKET_NO 를 담을 변수가 비어있으면 먼저 check된 값을 저장
         if(arrayList == '')
         {
            arrayList = $(checkboxList[i]).val();
         } 
         // 기존에 값이 있으면 앞에 (,) 콤마를 붙이고 값을 이어서 저장
         else{
            arrayList = arrayList + ","+$(checkboxList[i]).val();
         }
         
      }
   }
   
   // 하나도 체크 안했을 시 
   if(arrayList == ''){
      alert("한가지 이상을 선택해주세요.");
   }
   else{
      if(!confirm('삭제하시겠습니까?'))
         return false;
      
      // post ajax 통신 
      var param = "basket_no="+encodeURIComponent(arrayList); 
      $.ajax({
         type: "POST",
         url: "Wishlist",
         data: param,
         success: function(ret){
            // 현재 페이지 새로고침
            location.reload();
         }
      });
   }
}



var totalPrice = 0;

//전체금액
function totalPrice(){
   var checkboxList = $("input[name=check]:checkbox");
   totalPrice = 0;
   
   for(var i=0; i<checkboxList.length; i++){
      
      // checkbox가 체크 되어있을 때만 실행
      if($(checkboxList[i]).is(":checked")){
         
         var parentTr = $(checkboxList[i]).parents('tr');
         var value = $(parentTr).find('input[name=price]').val();
         console.log(value);
         
      }
   }
   
}

function productBuy(paintNo,buyYN){
   
   if(confirm("주문하시겠습니까?")==false)
   return false;   
      
   //문제없이 매개변수받아서 찍음

   var paint_no1 = paintNo;

    $("#buy_paint_no").val(paint_no1);

   var paint_no = $("#buy_paint_no").val();
   
   
   location.href="<%=request.getContextPath()%>/Buy.po?paint_no="+paint_no;
   
   console.log(paint_no);
   
   
   var arrayList='';
   ///basket_no 값을 읽어오기위한 메소드(주문버튼 클릭시 호출 메소드)

   var buyBtnList = $()


   
}


</script>


</head>
<body>
   <%@include file="../common/menubar.jsp" %>
   <%@include file="../common/mypagehead2.jsp" %>
   <div class="title">
        <h3><b>장바구니</b></h3>
        <hr>
    </div>

    <div class="container">
    <div>
        <h4>* 장바구니에 담긴 상품은 30일까지 보관됩니다.</h4>
    </div>

    <br>
    <br>

    <div class = "wishlist-table">
            <div class="table-headline">
                <div><span><b>관심상품</b></span></div>
            </div>
            <table style="width:100%" name="order-list">
                <thead>
                <tr>
                   <th></th>
                   <th>그림</th>
                    <th>상품정보</th>
                    <th>금액</th>
                    <th>주문</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                   <%if(!list.isEmpty()){ %>
                    <%for(Morw m:list){
                       totalPrice+=m.getPaintPrice();
                       %>
                   <tr>
                         <td><input type="checkbox" name="check" value="<%=m.getBasketNo() %>"></td>
                         <td><% for(int j=0; j<plist.size(); j++){ 
                     Attachment a = plist.get(j); %>
                  <% if(m.getPaintNo() == a.getPaint_no()) { %>
                <img src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%=a.getSavefileName() %>" width="150px" height="150px">
                <%} %>
               <%} %></td>
                        <td>작가 : <%=m.getArtistName()%><br>
                        	작품명 : <%=m.getPaintName()%></td>
                        <td><%=nf.format(m.getPaintPrice())%>
                           <input type="hidden" name="price" value="<%=m.getPaintPrice() %>" />
                        </td>
                        <td type="hidden"><a href="javascript: productBuy('<%=m.getPaintNo() %>')" class="btn btn-secondary btn-sm active" role="button" aria-pressed="true">주문하기</a></td>
                      	<td type="hidden"><%=m.getBuyYN()%></td>
                      <form >
                      <input type="hidden" id="buy_paint_no">
                      </form>
                   </tr>
                   	<%} %>
			    	<%} else{%>
			    		
			    	<%} %>
                </tbody>
            </table>
            <div style="float:right">
               <%--전체 금액 : <span id="total_price" ><%=nf.format(totalPrice) %></span> --%>
               <br>
               <b>전체 금액 : <span id="total_price" ><%=nf.format(totalPrice) %></span></b>
            </div>
        </div>
       <br>
      <br>
      <br>
      <button type="submit" class="btn btn-dark" style="width:100px;"onclick="javascript: deleteBasket();">선택상품 삭제</button>
      <button type="submit" class="btn btn-dark" style="width:100px; float:right;"><a href="<%= request.getContextPath() %>/list.po">쇼핑 계속하기</a></button>
      
<!--       <button type="submit" class="btn btn-dark" style="width:100px; float:right;">선택상품 주문</button> -->
<!--       <button type="submit" class="btn btn-dark" style="width:100px; float:right; margin-right: 4px;">전체상품 주문</button> -->
    </div>
   <br>
   <br>
   <br>
   <br>
   <br>
   <%@include file="../common/footer.jsp" %>
</body>
</html>