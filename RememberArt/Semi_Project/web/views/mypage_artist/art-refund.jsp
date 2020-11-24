<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="mypage_artist.RefundQnACard.model.vo.*, artistapply.model.vo.*, product.model.vo.*, java.util.ArrayList"%>

<%
    	ArrayList<BuyList_a> list = (ArrayList<BuyList_a>) request.getAttribute("list");
    	ArrayList<Attachment> alist = (ArrayList<Attachment>) request.getAttribute("alist");
    	Apply aphoto = (Apply) request.getAttribute("aphoto");
    	
    	PageInfo pi = (PageInfo)request.getAttribute("pi");
    	
    	int listCount = pi.getListCount();
    	int currentPage = pi.getCurrentPage();
    	int maxPage = pi.getMaxPage();
    	int startPage = pi.getStartPage();
    	int endPage = pi.getEndPage();
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>art refund</title>
<!-- link rel="stylesheet" href="../css/style.css" -->
<link
	href="https://fonts.googleapis.com/css2?family=Roboto&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/views/css/bootstrap.css">
	
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/css/Style-refund.css">
<script
	src="<%=request.getContextPath() %>/views/js/jquery-3.4.1.min.js"></script>

</head>

<body>
	<%@include file="../common/menubar.jsp"%>
	<div class="headline">
		<div class="headline-text">
			<hr>
			<h3 style="font-size: 20px;">작가 마이페이지</h3>
		</div>
		<div class="headline-subbox">
			<div class="artist-img">
				<!--Artist Image-->
				<img src="<%= request.getContextPath() %>/apply_uploadFiles/<%= aphoto.getArtist_pro() %>">
			</div>
			<div class="artist-btn"
				style="display: inline-block; vertical-align: middle;">
				<button class="btn btn-outline-dark"
					style="width: 120px; display: block; margin-bottom: 5px;"
					onclick="updateInfo();">정보 수정</button>
				<button class="btn btn-outline-dark"
					style="width: 120px; display: block; margin-top: 5px;"
					onclick="checkCard();">감동 카드</button>

			</div>
			<script>
                	function checkCard() {
                		location.href = "<%= request.getContextPath() %>/list.ac"
                			
                	}
                	
                	function updateInfo() {
                		location.href = "<%=request.getContextPath()%>/mypage.me?userId=<%=loginUser.getUserId() %>"
                		
                	}
            </script>
		</div>
	</div>
	<%@include file="../common/mypagehead.jsp"%>
	<div class="title">
		<h3>반품 / 환불 관리</h3>
		<hr>
	</div>

	<div class="container">
		<form action="<%=request.getContextPath()%>/alist.search" method="post">
			<div class="search-bar">
				<div class="bar1">
					<input type="text" name="order_status" list="status-list"
						placeholder="주문 상태">
					<datalist id="status-list" name = "order_status">
						<option value="환불 신청"></option>
						<option value="환불 완료"></option>
						<option value="반품 신청"></option>
					</datalist>
				</div>
				<div class="btn-group" data-toggle="buttons"  style = "vertical-align: baseline;" >
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
					<input type="date" name="calendar1"> ~ 
					<input type="date" name="calendar2">
				</div>
				<button type="submit" class="btn btn-outline-dark"
					style="margin: 10px 0; width: 100px">조회</button>
			</div>

		</form>
		<div class="refund-table">
			<div class="table-headline">
				<button class="btn btn-outline-dark"
					style="width: 120px; float: right; margin-bottom : 15px" onclick="refund(this);">환불
					완료</button>
			</div>

		<script type="text/javascript">

			// 전체 선택
           $(function(){
            	// 전체 선택 체크박스를 클릭하면,
               $("#check_all").click(function(){
            	   
                   var chk = $(this).is(":checked");//.attr('checked');
                    // 하위 체크박스의 상태를 checked로 변경
                   if(chk){
                	   $(".check_sub").prop('checked', true);
                   } else{
                	   $(".check_sub").prop('checked', false);
                   }
                });
            });
            
            
		var arrayList = '';


		function refund(){
	
			var checkboxList = $("input[name=check]:checked");
			arrayList = '';
			
			for(var i=0; i<checkboxList.length; i++){
		
				// checkbox가 체크 되어있을 때만 실행
				if($(checkboxList[i]).is(":checked")){
					// 확인
					console.log(i);
					
					if(i != checkboxList.length-1){
						arrayList += $(checkboxList[i]).val() + ",";
					} else{
						arrayList += $(checkboxList[i]).val();
					}
			
				}
			}	
			
			// 확인용
			console.log(arrayList);
	
			// 하나도 체크 안했을 시 
			if(arrayList == ''){
				alert("한가지 이상을 선택해주세요.");
			}else{
					if(!confirm('환불 완료 처리를 하시겠습니까?')){
					return false;}
		
					// post ajax
		
					$.ajax({
						type: "POST",
						url: "list.ar",
						data: {order_no : arrayList},
						success: function(ret){
							// 현재 페이지 새로고침
							location.reload();
						}		
					});
				}
			}


</script>


			<table style="width: 100%" name="refund-list">
				<tr>
					<th style="text-align: center;"><input type="checkbox" id="check_all"></th>
					<th style="text-align: center;">주문 번호</th>
					<th style="text-align: center;">이미지</th>
					<th style="text-align: center;">상품 정보</th>
					<th style="text-align: center;">금액</th>
					<th style="text-align: center;">구매자 아이디</th>
					<th style="text-align: center;">처리 상태</th>
					<th style="text-align: center;">주문 정보 확인</th>
				</tr>
				
				<% for(int i = 0 ; i < list.size(); i++){
                	BuyList_a b = list.get(i);
                %>

				<tr>
					<td><input class="check_sub" type="checkbox" name="check"
						value="<%=b.getOrder_no() %>"></td>
					<td><%=b.getOrder_no() %></td>
					<td>
						<% for(int j=0; j<alist.size(); j++){
							Attachment a = alist.get(j); %>
						<% if(b.getPaint_no() == a.getPaint_no()) { %>
						<img src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= a.getSavefileName() %>" width="150px" height="150px">
						 <%} %>
						<%} %>
					</td>
					<td>작품명 : <%=b.getPaint_name()%><br>작가명 : <%=b.getArtist_name()%>
					</td>
					<td><%=b.getPaint_price()%></td>
					<td><%=b.getUser_id()%></td>
					<td><%=b.getOrder_status()%></td>
					<td><button class="btn btn-outline-dark" id="detail_order" style="width: 150px">주문 상세보기</button></td>
				</tr>
				<%} %>

			</table>
		</div>

	</div>

	<!-- 페이징 처리 시작 -->
	
<div class = "p-parents">
	<div class="pppp">
			<%if (currentPage == 1) { %>
            <a style = "color:#9c9c9c;" disabled>Previous</a>
            <%}else {%>
            <a class = "page-a" href="<%=request.getContextPath() %>/list.ar?currentPage=<%=currentPage - 1 %>" >Previous</a>
            <%} %>
            <ol>
            <%for(int p = startPage ; p<=endPage ; p++){ %>
            <%if(currentPage == p){ %>
              <li class = "page-list1"><button disabled class = "page-cur" ><%=p%></button></li>
            <%} else { %>
              <li class = "page-list2" onclick="location.href='<%=request.getContextPath() %>/list.ar?currentPage=<%=p%>'"><button class = "page-nocur"><%=p%></button></li>
            <%} %>
            <%} %>
            </ol>
            <%if (currentPage == maxPage) { %>
            <a style = "color:#9c9c9c; "  disabled>Next</a>
            <%} else { %>
            <a class = "page-a" href="<%=request.getContextPath()%>/list.ar?currentPage=<%=currentPage + 1%>">Next</a>
            <%} %>
   </div>
</div>


	<%@include file="../common/footer.jsp"%>
</body>
</html>