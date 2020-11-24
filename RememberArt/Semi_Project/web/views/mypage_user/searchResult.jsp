<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="mypage_user.mainOrderRefundWish.model.vo.*,product.model.vo.*,java.util.ArrayList"%>
    
<%
	
	ArrayList<Morw> search_list = ((ArrayList<Morw>)request.getAttribute("search_list"));
	ArrayList<Attachment> plist = (ArrayList<Attachment>) request.getAttribute("plist");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/bootstrap.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/Style-mypagehead.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/Style-ar.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/Style-refund.css">

<style>

.refund > label{
margin : 18px;
}

.refund > input {
float : right;
}


</style>  
  
<script src="<%=request.getContextPath() %>/views/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript">

// var searchStatus='all';
// var term = 'all';


$("document").ready(function(){
	$("#searchStatus").change(function(){
		var searchStatus = this.value;
		 $("#searchStatus").val(searchStatus);
// 		console.log(searchStatus);
	});
	
	$("input[name=term]:radio").click(function(){
		term = this.value;
		console.log(term);
	});
	
});

function setStatus(obj){
	term = obj.value;
}




//수령확인 버튼
function confirm2(gOrderNo, gPaintName, gArtistName, gPaintNo){
		   var status = $("#order_status_"+gOrderNo).val();
		   
		   var order_no = gOrderNo;
		   var paint_name = gPaintName;
		   var artist_name = gArtistName;
		   var paint_no = gPaintNo;
		
		   
		   // input hidden태그에 보조 정보들 넣기
		   $("#modal_order_no").val(order_no);
		   $("#modal_paint_name").val(paint_name );
		   $("#modal_artist_name").val(artist_name);
		   $("#modal_paint_no").val(paint_no);
		   
		   $("#modal_img").css('background-image', 'url(\''+$("#img_"+order_no).val()+'\')');
		   $("#modal_title").html(paint_name+" 작가님에게<br/>메시지를 남겨보세요.");
		   
		   if(status == '배송완료'){
	            if(confirm('수령하시겠습니까?')==true){
	               var param = "order_no="+encodeURIComponent(gOrderNo); //ajax 뒷단 통신
	               $.ajax({
	                  type: "POST",
	                  url: "Mo.li",
	                  data: param,
	                  success: function(ret){ //통신이 완료되었을때 안에거 실행
	                     $("order_status_"+gOrderNo).val("수령완료");
	                     $("#td_order_status_"+gOrderNo).html("수령완료");
	                      alert("수령확인 완료되었습니다!");
	                        $("#modal_order_no").val(gOrderNo);
	                        $("#confirm2button").css("background","white").css("color","black");
	                        $("#modal").fadeIn(500);
	                  }
	               });
	            }
	         }else if(status=='수령완료'){            
	               alert("이미 수령된 상품입니다!");
	         }else{
	            alert("수령은 배달완료 이후 가능합니다!");
	         }
	      }

function insertMessage(){
	
	
	   if(confirm('메세지를 보내시겠습니까?')==false)
	    return false;
	    var message = $("#message").val();
	    var orderNo = $("#modal_order_no").val();
	    var paintNo = $("#modal_paint_no").val();
	    var paintName = $("#modal_paint_name").val();
	    var artistName =  $("#modal_artist_name").val();
	    var data = {
	    		'message' : message,
	    		'order_no' : orderNo,
	    		'paint_no' : paintNo,
	    		'paint_name' : paintName,
	    		'artist_name' : artistName
	    	 };
	    	 
	    
	    //위에 벨류 문제없이 잘 받음 확인!!
	
	console.log("Ajax 시작전 ");
	$.ajax({
	  type: "POST",
	  url: "Mo.litwo",
	  data: data,
	  success: function(ret){
	   	location.reload();
	}
	});
	}
	

function refundApply(orderNo,paintNo){
	var order_no = orderNo;
	var paint_no = paintNo;
	$("#refund_order_no").val(order_no);
	$("#refund_paint_no").val(paint_no);
	$("#modal_refund").fadeIn(500);

}


function submit(){
	 var orderNo = $("#refund_order_no").val(); //괄호안에 아무것도 없으면 불러오는 것!
	 var paintNo = $("#refund_paint_no").val();
	 var selectBank = $("#selectBank").val();
	 var refundName = $("#refundName").val();
	 var account = $("#account").val();
	 var refundReason = $("#refundReason").val();
	 
	 var status = $("#order_status_"+orderNo).val();
	 console.log('test : '+status);
		
	 var data = {
		//문자열로 넣어주면 키가됨!!!!!!!
		'order_no' : orderNo,
		'paint_no' : paintNo,
		'select_bank' : selectBank,
		'refund_name' : refundName,
		'account' : account,
		'refund_reason' : refundReason
	 };
	 
	 console.log(data);
	 
	 $.ajax({
		  type: "POST",
		  url: "Mo.lithree",
		  data: data,
		  success: function(){
				location.reload();
		},
		  error:function(e){
			  console.log(e);
		  }
		
		});
	
}


</script>


</head>
<body>
	<%@include file="../common/menubar.jsp" %>
	<%@include file="../common/mypagehead2.jsp" %>
    
    <div class="title">
        <h3><b>주문 내역</b></h3>
        <hr>
    </div>
    	
    
      
      <div class="container">
      <form method="post" action="<%=request.getContextPath()%>/SearchServlet">
             <div class="search-bar">
                <div class="bar1">
                    <input type="text"  name="searchStatus" list="order_list" placeholder="주문 처리 상태">
                    <datalist id="order_list">   
                        <option value="입금 전">입금전</option>
                        <option value="배송준비중">배송준비중</option>
                        <option value="배송중">배송중</option>
                        <option value="배송완료">배송완료</option>
                        <option value="수령완료">수령완료</option>
                        <option value="환불신청">환불신청</option>
                        <option value="환불완료">환불완료</option>
                    </datalist>
                </div>
                <div class="btn-group" data-toggle="buttons">
                                <label class="btn btn-outline-dark">
                                    <input type="radio" name="term" value="0">오늘
                                </label>
                                <label class="btn btn-outline-dark">
                                    <input type="radio" name="term" value="7">1주일
                                </label>
                                <label class="btn btn-outline-dark">
                                    <input type="radio" name="term" value="30" >1개월
                                </label>
                                <label class="btn btn-outline-dark">
                                    <input type="radio" name="term" value="90" >3개월
                                </label>
                                <label class="btn btn-outline-dark">
                                    <input type="radio" name="term" value="180" >6개월
                                </label>
                            </div>
                &nbsp;&nbsp;&nbsp;
                <div class="bar3">
                    <input id="calendar1" type="date" name="calendar1"> ~
                    <input id="calendar2" type="date" name="calendar2">
                </div>
                <button type="submit" class="btn btn-dark" style="width:70px">조회</button>
            </div>
        </form>
     
    
   
    <br>
    <br>
    <div>
        <h6>* 기본적으로 최근 3개월간의 자료가 조회되며, 기간 검색시 지난 내역을 조회할 수 있습니다.<br>
        * 취소/환불 신청은 수령확인 버튼을 누르면 진행됩니다.
        </h6>
    </div>
    <br>
    <br>

      <div class = "order-table">
            <div class="table-headline">
                <div><span><b>주문 상품정보</b></span></div>
            </div>
            <table style="width:100%" name="order-list">
                <thead>
                <tr>
                    <th>주문 번호</th>
                    <th>그림</th>
                    <th>상품정보</th>
                    <th>금액</th>
                    <th>주문일자</th>
                    <th>주문 처리상태</th>
                    <th>취소 /환불</th>
                </tr>
                </thead>
                <tbody>
                <%if(!search_list.isEmpty()){ %>
			  			<%for(int i =0 ; i <search_list.size();i++){ 
			  			Morw m = search_list.get(i);
			  			String gOrderStatus = m.getOrderStatus();
			  			String gOrderNo = m.getOrderNo();
			  			String gPaintName = m.getPaintName();
			  			String gArtistName = m.getArtistName();
			  			int gPaintNo = m.getPaintNo();%>
			    		<tr>
			    			<td><%=m.getOrderNo() %><input type = "hidden" id="order_status_<%=m.getOrderNo()%>" value="<%=m.getOrderStatus()%>"></td>
							<td><% for(int j=0; j<plist.size(); j++){ 
							Attachment a = plist.get(j); %>
						<% if(m.getPaintNo() == a.getPaint_no()) { %>
					 <img src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= a.getSavefileName() %>" width="150px" height="150px">
					 <input type="hidden" id="img_<%=m.getOrderNo()%>" value="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= a.getSavefileName() %>" />
					 
					 
					 <%} %>
					<%} %></td>
			      			<td id="artistname"><label>작가 : </label><%=m.getArtistName()%><br>
			      			<label>작품명 : </label><%=m.getPaintName()%>
			      				<input type="hidden" id="paint_name_<%=m.getOrderNo()%>" value="<%= m.getPaintName() %>" />
			      			</td>
			      			<td><%=m.getPaintPrice()%></td>
			      			<td><%=m.getOrderDate()%></td>
			      			<td id="td_order_status_<%=m.getOrderNo()%>"><%=m.getOrderStatus()%></td>
			      			<td>
			      			<%if("환불신청".equals(m.getOrderStatus())){ %>
			      				<a href="javascript: alert('이미 신청 완료되었습니다.');" id="refundApplyButton_<%=gOrderNo %>" class="btn btn-secondary btn-sm active" role="button" aria-pressed="true" style="background-color: #fff; color: black;">반품/취소</a>	
			      			<%}else{ %>
			      				<a href="javascript: refundApply('<%=m.getOrderNo()%>',<%=gPaintNo %>)" id="refundApplyButton_<%=gOrderNo %>" class="btn btn-secondary btn-sm active" role="button" aria-pressed="true">반품/취소</a>
			      			<%} %>
			      			<a href="javascript: confirm2('<%=gOrderNo %>','<%=gPaintName %>','<%=gArtistName %>',<%=gPaintNo %>)" id="confirm2button" class="btn btn-secondary btn-sm active" role="button" value="confirm2Status" >수령확인</a>
			      			</td>
			      			<td style="display:none"><%=m.getPaintNo() %></td>
			    		</tr>
			    		<%} %>
			    	<%} else{%>
			    	<%} %>
                </tbody>
            </table>
        </div>
   
      <br>
      <br>
   
    
    
	
	<%@include file="../common/footer.jsp" %>
	<div id="modal" style="position: fixed; display:none; width: 100%; height: 100%; top: 0; left: 0; background-color: rgba(0, 0, 0, 0.7); z-index: 9999;">
      <div style="width: 400px; height: 400px; background-color: #fff; border-radius: 20px; position: absolute; left: 50%; top: 50%; transform: translate(-50%, -50%);">
      	<div style="position: absolute; top : 30px; left:40px;"><h5><b id="modal_title">작가님에게 메세지를 남겨 보세요!</b></h5></div>
          <a href="javascript: $('#modal').fadeOut(500);" style="width: 15px; height: 15px; position: absolute; top: 15px; right: 20px; display: block;">
                   <img src="thumbnail_uploadFiles/modal.png" style="width: 100%;" /></a>
          <div id="modal_img" style="width: 310px; margin: 0 auto; height: 160px; margin-top: 97px; background-size: cover; background-repeat: no-repeat; background-position: center center;">
          	
          </div>
          <textarea id="message" style="position:absolute; bottom :60px; width:310px; height:60px; left: 50%; transform: translateX(-50%);"></textarea>
          <input type="hidden" id="modal_order_no"><button type="button" onclick="insertMessage()" style="position:absolute; left:170px; bottom:20px; border-radius:10px;"><b>전송</b></button>
		  <input type="hidden" id="modal_paint_no">
		  <input type="hidden" id="modal_paint_name">
		  <input type="hidden" id="modal_artist_name">
      </div>
   </div>
   
   <div id="modal_refund" style="position: fixed; display:none; width: 100%; height: 100%; top: 0; left: 0; background-color: rgba(0, 0, 0, 0.7); z-index: 9999;">
      <div style="width: 400px; height: 380px; background-color: #fff; border-radius: 20px; position: absolute; left: 50%; top: 50%; transform: translate(-50%, -50%);">
      	<div style="position: absolute; top : 20px; left:20px;"><h5><b>환불계좌등록</b></h5></div>
          <a href="javascript: $('#modal_refund').fadeOut(500);" style="width: 15px; height: 15px; position: absolute; top: 15px; right: 20px; display: block;">
                   <img src="thumbnail_uploadFiles/modal.png" style="width: 100%;" /></a>
          <div style="border-radius:5px; width:360px; height:250px; background-color:lightgray; position:absolute; top:60px; left:20px; border:1px black;">
          	<ul class= "refund" style="position:absolute; top:20px; left:20px;">
          	<li><label style="margin:14px; width:100px;">예금주</label><input type="text" id="refundName" style="width:180px">
          	<div style="font-size:10px; position:absolute; left:126px; margin-top: -6px;">예금주명은 주문자명과 동일해야합니다.</div></li>
		    <li><label style="margin:14px; width:100px;">은행선택</label><select id="selectBank" style="width:180px">
		    <option value="국민">국민</option>
		    <option value="신한">신한</option>
		    <option value="NH">NH</option>
		    <option value="우리">우리</option>
		    </select></li>
		    <li><label style="margin:14px; width:100px;">계좌번호</label><input type="text" id="account" style="width:180px">
		    <div style="font-size:10px; position:absolute; left:126px; margin-top: -9px;">'-'없이 숫자만 입력해주세요.</div></li>
		    <li><label style="margin:14px; width:100px;">환불사유</label><input type="text" id="refundReason" style="width:180px"></li>
          	</ul>
          </div>
          <button type="button" onclick="submit()" style="position:absolute; width:80px; left:115px; bottom:15px; border-radius:10px;"><b>등록</b></button>
       	  <button type="button" id="" style="position:absolute; width:80px; right:115px; bottom:15px; border-radius:10px;"><b>취소</b></button>
       	  <input type="hidden" id="refund_order_no">
       	  <input type="hidden" id="refund_paint_no">
      </div>
   </div>
</body>
</html>