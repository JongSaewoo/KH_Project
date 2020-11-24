<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import = "product.model.vo.*,artistapply.model.vo.*, java.util.ArrayList"%>
<%

	product plist = (product)request.getAttribute("plist");
	ArrayList<Attachment> alist = (ArrayList<Attachment>)request.getAttribute("alist");
	ArrayList<product> sizelist = (ArrayList<product>)request.getAttribute("sizelist");
	Apply apply = (Apply)request.getAttribute("aplly");
	masterpiece mp = (masterpiece)request.getAttribute("mp");
	MasterpieceCount mc = (MasterpieceCount)request.getAttribute("mc");
	ArrayList<Paint_QnA> qna = (ArrayList<Paint_QnA>)request.getAttribute("qna");
	ArrayList<Paint_QnA> qna2 = (ArrayList<Paint_QnA>)request.getAttribute("qna2");


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

 <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
 <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/bootstrap.css">
<!-- link rel="stylesheet" href="../css/style.css" -->
 <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/style.css">
<script src="<%=request.getContextPath() %>/views/js/jquery-3.4.1.min.js"></script>
 <!— Swiper JS —>
  <script src="<%=request.getContextPath() %>/views/js/swiper.min.js"></script>
    <!-- Link Swiper's CSS -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/views/css/swiper.min.css">


<style>

h3 {
font-size : 19px;
margin-bottom : 50px;
}

.bodyhr{
width : 90%;
color : black;
margin : 50px auto;
text-align : center;
border: 0.5px solid black;

}

.headline{
  margin-top : 90px;
  margin-left: 90px;
  margin-bottom: 50px;
}

.headline hr {
  background-color:#c82c1f;
  width:25px;
  border:2px solid red;
  margin-bottom: 10px;
}

#sumnailimage {
   border: solid 1px;
   display: inline-block;
   width: 528px;
   height: 507px;
   margin-left: 191px;
   margin-right: 20px;
   margin-top: 20px;
   float: left;
}

#data {
   display: inline-block;
   width: 760px;
   height: 400px;
   float: left;
   margin-left: 20px;
   margin-right: 0px;
   margin-top: 20px;
}

#simulationMain {
   position : relative;<!--안에 이미지에 대한 기준-->
   border: solid 1px;
   display: inline-block;
   width: 369px;
   height: 358px;
   margin-left: 191px;
   margin-right: 20px;
   margin-top: 20px;
   float: left;
}
#simulation1{
border: solid 1px;
   display: inline-block;
   width: 121px;
   height: 125px;
   margin-left: 50px;
   margin-right: 20px;
   margin-top: 100px;
   float: left;
}
#simulation2{
border: solid 1px;
   display: inline-block;
   width: 121px;
   height: 125px;
   margin-left: 10px;
   margin-right: 20px;
   margin-top: 100px;
   float: left;
}
#simulation3{
border: solid 1px;
   display: inline-block;
   width: 121px;
   height: 125px;
   margin-left: 10px;
   margin-right: 20px;
   margin-top: 100px;
   float: left;
}

#writer {
   border: solid 1px;
   display: inline-block;
   width: 200px;
   height: 200px;
   float: left;
}
#data2 {
   display: inline-block;
   width: 400px;
   height: 200px;
   float: left;
   margin-left: 30px;
   margin-right: 0px;
   padding : 20px
}



 ul,li{
      list-style:none;
      }
  #slide{
      height:400px;
      width: 400px;
      position:relative;
      overflow:hidden;
     
      }
  #slide ul{
      width:400%;
      height:200%;
      transition:1s;
      }
      
  #slide img{
  	width : 100%;
  }
  #slide li{
  	  width : 400px;
      float: left;
      }
  #slide input{
      display:none;
      }
  #slide label{
      display:inline-block;
      vertical-align:middle;
      margin-left:10px;
      margin-right:10px;
      margin-bottom:0px;
      width:15px;
      height:15px;
      border:3px solid rgb(0, 0, 0);
      transition:0.3s;
      border-radius:50%;
      cursor:pointer;
      }
  #slide .pos{
      text-align:center;
      position:absolute;
      bottom: 0;
      left:0;
      width:100%;
      }
    #pos1:checked~ul{
      margin-left:0%;
      }
    #pos2:checked~ul{
      margin-left:-400px;
      }
    #pos3:checked~ul{
      margin-left:-802px;
      }
    #pos1:checked~.pos>label:nth-child(1){
      background:rgba(255, 255, 255, 0.400);
      }
    #pos2:checked~.pos>label:nth-child(2){
      background:rgba(255, 255, 255, 0.400);
      }
    #pos3:checked~.pos>label:nth-child(3){
      background:rgba(255, 255, 255, 0.400);
      }

      #imgInfo { width:100%; }
      #imgInfo td { text-align: left; width:50%; font-size:14px; line-height:2; padding-left : 10px; }
      .btnArea { width:100%; margin:10px 0; }
	  .btnArea:after { display:block; content:""; clear:both; }
	 .btn{
	 	display : block;
	 	margin-bottom : 10px;
	 	width:330px;
	 	height:40px;
	 }
	 
	  <!-- Demo styles -->

    html, body {
      position: relative;
      height: 100%;
    }
    body {
      background: white;
      font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
      font-size: 14px;
      color:#000;
      margin: 0;
      padding: 0;
    }
    .swiper-container {
      width: 75%;
      height: 500px;
    }
    .swiper-slide {
      text-align: center;
      font-size: 18px;
      background: #fff;
      }
      
      .swiper-fiximg {
       	 position : absolute;
       	 top : 20%;
       	 left : 40%;
       	 z-index:100;
       	 
      }
      
    div.swiper-button-prev,
	div.swiper-button-next {
	color: #c82c1f; }
	
	/* div.swiper-pagination {
	background: #c82c1f;
	} */
	
	span.swiper-pagination-progressbar-fill {
	background:#c82c1f;
	}


	/* 작가 소개 박스 */
	.writerint-box {
	justify-content: center;
	display : flex;
	margin: 0 auto;
	}
	
	/* 댓글 창 */
	
	.QnAContent {
	padding : 15px;
	border-style: none;
    border-radius: 5px;
    background: rgb(241, 241, 241);
    overflow: auto;
    resize: none;
	}
	
	.Acontents {
	display : flex;
	align-items: center;
	}
	
	.AContent {
	
	justify-content: center;
	width: 400px;
	padding : 15px;
	border-style: none;
    border-radius: 5px;
    background: rgb(241, 241, 241);
    overflow: auto;
    resize: none;
	}
	
	.addQNA {
	height:135px;
	padding : 10px;
    border-radius: 5px;
    border : 1px solid black;
    background: none;
    font-size : 15px;
	}
	
	.answerlist-box > td {
	vertical-align : middle;
	text-align: center;
	}
	
	.answerlist-box > td > button {
	padding : 10px;
    border-radius: 5px;
    border : 1px solid black;
    background: none;
    font-size : 15px;
	}
	
	
	#replySelectArea {
		width : 80%;
		margin : 0 auto;
	}
	
	.Acontent_btn {
	display : inline-block;
	padding: 20px;
	}
	
	.hideA, .insertA {
	margin : 0 auto;
	display : block;
	padding : 10px;
    border-radius: 5px;
    border : 1px solid black;
    background: none;
    font-size : 15px;
    margin-right : 10px;
	}
	
	.question-box {
    width: 100%;
    vertical-align: middle;
    display: flex;
    justify-content: center;
    background-color: rgb(241, 241, 241);
    border-radius: 5px;
    padding: 9px;
    align-items: center;

	}
	
	/* 페이징 처리 */
      
   /*    .p-parents {
  	display: flex;
  	flex-direction: column;
  	justify-content: center;
  	align-items: center;
 	 margin: 0 auto;
 	 }
      
       .pppp {
        display: flex;
        text-align: center;
        margin : 50px auto;
        background: rgb(255, 255, 255);
        height: 36px;
        border : 1px solid black;
        border-radius: 5px;
        justify-content: center;
        align-items: center;
        
      }
      
      
      .pppp > ol > li:first-child {
       border-left : 1px solid black;
      }

      .pppp > a {
        display: inline-flex;
        justify-content: center;
        align-items: center;
        padding: 7px 12px;
        font-size: 13px;
        font-weight: 500;
        color:#9c9c9c;
        text-decoration: none;
      }

      .pppp > ol {
        display: inline-flex;
        list-style: none;
        justify-content: center;
        align-items: center;
        
      }

      .pppp > ol > li {
        display: inline-flex;
        list-style: none;
        justify-content: center;
        align-items: center;
        margin-top: 16px;
        border-right: 1px solid;
        vertical-align: middle;
        list-style: none;
        width: 36px;
        height: 34px;
        text-decoration: none;
      }
      
  
     
     .page-list1 {
     background-color:#c82c1f;
     }
     
     .page-cur {
     font-size : 14px;
     background:none;
   	 color: white;
     padding : 0;
     border-style : none;
   
     }
     
     .page-nocur {
     font-size: 14px;
     background:none;
   	 color: #c82c1f;
     padding : 0;
     border-style : none;
     
     }
     
     .page-a:hover {
     color: black;
     text-decoration:none;
     }
	 */
	
	
	 
</style>
</head>
<body>
   <%@include file="../common/menubar.jsp"%>
   
   <div class = headline>
        <hr>
        <h3 style="font-size: 20px;"> 작품 상세보기 </h3>
    </div>
<br>
<table style = "margin : 0 auto;">
<tr>
<td>
   <div id="slide">
  <input type="radio" name="pos" id="pos1" checked>
  <input type="radio" name="pos" id="pos2">
  <input type="radio" name="pos" id="pos3">

  <ul>
  <%for(int i =0; i< alist.size();i++){
	  Attachment a = alist.get(i);
	  if(a.getFileLevel() == 0){%>
    <li><img src="<%=request.getContextPath()%>/thumbnail_uploadFiles/<%=a.getSavefileName()%>" style = "width:102%; height: 400px; overflow:hidden;"></li>
    <%}}%>
     <%for(int i =0; i< alist.size();i++){
	  Attachment a = alist.get(i);
	  if(a.getFileLevel() == 1){%>
    <li><img src="<%=request.getContextPath()%>/thumbnail_uploadFiles/<%=a.getSavefileName()%>" style = "width:102%; height: 400px; overflow:hidden;"></li>
        <%}}%>
  </ul>
  <p class="pos">
    <label for="pos1"></label>
    <label for="pos2"></label>
    <label for="pos3"></label>

  </p>
</div>
   
   </td>
  <td style="box-sizing:border-box; padding: 0 0 0 50px;">
      <div>
         <h3 align="left" style = "margin-top : 5px; margin-bottom:0;"><%=plist.getPaint_name() %></h3>
      </div>
      <hr style="margin: 1 auto;" color="black" width="330px">
      
      <table id="imgInfo">
      	<tr>
      		<td>작가명 : <%= plist.getArtist_name()%></td>
      	</tr>
      	<tr>
      		<td>카테고리 : <%=plist.getCategory() %></td>
      	</tr>
      	<tr>
      		<td>제작년도 : <%=plist.getPaint_mdate()%></td>
      		
      	<tr>
      		<td>사이즈 :<% for(int i =0;i < sizelist.size();i++) {
				product p = sizelist.get(i);
				if(plist.getSize_no() == p.getSize_no() ){%>
			
					<%=p.getWidth()%> *<%=+p.getHeight()%>
					
				<% }}%></td>
      	</tr>
      </table>  	 																																																																																	
      <hr style="margin: 1 auto;" color="black" width="330px">
      <table id="imgInfo">
      <tr>
      <td>
       <span style="text-align: center; width: 100px; font-size: 20px;"><%=plist.getPatint_price() %>원</span>
      </td>
      <td>
  
      		
      		<div style="float:right;">
      		<div class="likeCount" style="float:left; margin-right:10px;">
      			<span class="countArea"><%=mc.getCount() %></span>
      		</div>
      		
      		<%if(mp.getUser_id() != null){ %>
      		<img class="heartcheck" src="<%=request.getContextPath()%>/views/img/colorHeart.png">
      		<%}else{%>
      		<img class="heartcheck" src="<%=request.getContextPath()%>/views/img/emptyHeart.png">
      		<%} %>
      		
      		
      		</div>
      </td>
      </tr>
      </table>
      <script>
      $(function(){
      $(".heartcheck").click(function(){
    	  var paint_no = "<%=plist.getPaint_no()%>";
    	  var heart = $(".heartcheck").attr('src');
    	  var heartYN;

    	  if(heart=='<%=request.getContextPath()%>/views/img/colorHeart.png'){
    		  $(".heartcheck").attr('src','<%=request.getContextPath()%>/views/img/emptyHeart.png');
    		  heartYN = 'N';
    	  }else{
    		  $(".heartcheck").attr('src','<%=request.getContextPath()%>/views/img/colorHeart.png');
    		  heartYN = 'Y';
    	  }
    	  $.ajax({
    		url:"masterpiece.po",
    		type:"post",
    		data:{paint_no:paint_no,heartYN:heartYN},
   
    		success:function(data){
    			var $likeCount = $(".likeCount");
    			var $count = $("<span>").text(data);
    			
    			$likeCount.html($count);
    			
    		},
    		error:function(request,statur,error){
    			alert("로그인을 하셔야 합니다.");
    			 $(".heartcheck").attr('src','<%=request.getContextPath()%>/views/img/emptyHeart.png');
    		}
    		  
    	  })
    	  
    	  
      });
      });
      
     
      </script>
      
     

      <div class="btnArea">
      <input id="paint_no" type="hidden" value="<%=plist.getPaint_no()%>">
	      <input type="button" class = "btn btn-outline-dark buypaint" value="바로 구매하기">
	       <input type="button" class = "btn btn-outline-dark basket"  value="장바구니 담기">
	      <input type="button" class = "btn btn-outline-danger moveqna"  value="Q & A"  style="margin-bottom:0;"><input type="hidden" value="" id="mid">
	      <!-- <input type="hidden" value="${ movieDetail.movie_id }" id="movie_id"> -->
	      <input type="hidden" value="" id="movie_id">
  
      </div>
       <script>
       $(function(){
       		$(".moveqna").click(function (){
          		 var offset = $("#qna").offset();
     		      $('html, body').animate({scrollTop : offset.top}, 400);
       	});
       	});
       
       
                  $(function(){
				 		$(".buypaint").click(function(){
				 			var paint_no = $(this).parent().children("#paint_no").val();
				 			
				 			location.href="<%=request.getContextPath()%>/Buy.po?paint_no="+paint_no;
				 		});
				 		
				 	});
				 	
                  $(function(){
				 		$(".basket").click(function(){
				 			var paint_no = $(this).parent().children("#paint_no").val();
				 			
				 			location.href="<%=request.getContextPath()%>/basket.po?paint_no="+paint_no;
				 		});
				 		
				 	});
			</script>
      
  </td>
	</tr>
	</table>	
   <br clear="both">
   <br>
   <hr class = "bodyhr">
   <h3 align="center">인테리어 시뮬레이션</h3>
     
  <!-- Swiper -->
  <div class="swiper-container"> 
  <%for(int i =0; i< alist.size();i++){
	  Attachment a = alist.get(i);
	  if(a.getFileLevel() == 0){%>
     	<img class = "swiper-fiximg" src="<%=request.getContextPath()%>/thumbnail_uploadFiles/<%=a.getSavefileName()%>" width="20%">
    <%}}%>
    
 
    <div class="swiper-wrapper">
      <div class="swiper-slide"><img src="<%=request.getContextPath() %>/views/interior/인테리어1.jpg" width="100%"></div>
      <div class="swiper-slide"><img src="<%=request.getContextPath() %>/views/interior/인테리어2.jpg"  width="100%"></div>
      <div class="swiper-slide"><img src="<%=request.getContextPath() %>/views/interior/인테리어3.jpg"  width="100%"></div>
      <div class="swiper-slide">Slide 4</div>
    </div>
    <!— Add Pagination —>
    <div class="swiper-pagination"></div>
    <!— Add Arrows —>
    <div class="swiper-button-next"></div>
    <div class="swiper-button-prev"></div>
  </div>
  
  <!— Initialize Swiper —>
  <script>
    var swiper = new Swiper('.swiper-container', {
      pagination: {
        el: '.swiper-pagination',
        type: 'progressbar',
      },
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
    });
  </script>
   
   <br clear="both">
   <hr class = "bodyhr">
   <h3 align="center">작품 소개</h3>
   <a style="display : block; text-align: center; margin : 0 auto; width: 70% ;font-size: 15px; text-decoration: none !important; border-bottom: dotted 0px !important; color: black !important;"><%=plist.getPaint_int() %></a>
   <br>
   <hr class = "bodyhr">
   <h3 align="center">작가 소개</h3>
   <div class = "writerint-box">
   <div id="writer">
      <img src="<%=request.getContextPath()%>/apply_uploadFiles/<%=apply.getArtist_pro() %>" style="width: 200px; height: 200px;">
   </div>
   
   <div id="data2">
   <a class = "writerint"><%=apply.getArtist_int() %></a>
   </div>
   </div>
   
   
   <hr class = "bodyhr">
  
   <h3 align="center">Q & A</h3>
   <div class="qna" id="qna">
   <table style = "margin : 0 auto; margin-bottom : 20px;">
	<tr>
		<td>
		<textArea rows="5" cols="100" class="QnAContent" placeholder="질문을 작성하세요."></textArea>
		</td>
		<td style= "padding: 15px;">
			<button class="addQNA" type="button active" style="width:100px; ">질문 등록</button>
		</td>
		</tr>
   </table>
   
   <div id="replySelectArea">
			<table id="replySelectTable" class="table" align="center">
				<% if(qna.isEmpty()) { %>
					<tr><td colspan="3">Q & A가 없습니다.</td></tr>				
				<% }else { %>
					<% for(int i= qna.size()-1; i>=0; i--){ %>
						<tr class = "answerlist-box">
							<td><%= qna.get(i).getUser_id() %></td>
							<td><span  class = "question-box"><%= qna.get(i).getPqusetion() %></span></td>
							<td><%= qna.get(i).getPq_date() %></td>
							<%if(qna.get(i).getPq_yn().equals("N")) {%>
							<td>
								<input class="qna_no" type="hidden" value="<%=qna.get(i).getPq_no()%>">
								<button class="addA" type="button active" style="width:100px; display: inline-block; margin: 0; font-size : 14px;">답변하기</button>
								<div class="Acontents" style="display:none;">
									<textArea rows="5" cols="100" class="AContent"></textArea>
									<div class="Acontent_btn">
									
										<input id="paint_no" type="hidden" value="<%=plist.getPaint_no()%>"><br>
										<button class="hideA"  type="button active"  onclick="test(this)" style="width:100px;  margin-bottom : 10px; font-size : 14px;">답변접기</button>
										<button class="insertA" type="button active" style="width:100px;  margin: 0; font-size : 14px;">작성완료</button>
									</div>
							</div>
							</td>
							<tr class="answertable<%=qna.get(i).getPq_no() %>" style="display:none; vertical-align : middle; text-align: center;">
							</tr>
							<%}else if(qna.get(i).getPq_yn().equals("Y")){%>
							<td rowspan='2'>
							<b>답변완료</b>
							</td>
							<%} %>
						</tr>
							<%for(int j=0; j <= qna2.size()-1; j++){ %>
							<%if(qna.get(i).getPq_no() == qna2.get(j).getPq_no()){%>
							<tr style="vertical-align : middle; text-align: center;">
								<td style="vertical-align: middle;" >답변:</td>
								<td><span  class = "question-box" ><%= qna2.get(j).getPanswer()%></span></td>
								<td><%= qna2.get(j).getPa_date() %></td>
							</tr>
							<%}} %>
					<% } %>
				<% } %>
			</table>
		</div>
</div>
		<script>
		$(function(){
			$(".hideA").click(function(){
				$(this).parents(".Acontents").hide();
				$(this).parent().parent().parent().children(".addA").show();
				
			})
		})
		function test(a){
			
		}
		$(function(){
			$(".addA").click(function(){
				$(this).parent().children(".Acontents").show();
				$(this).hide();
				
			})
		})
		//대댓글
		$(function(){
			$(".insertA").click(function(){ 
				var qna_no = $(this).parent().parent().parent().children(".qna_no").val();
				var content = $(this).parent().parent().children(".AContent").val();
				var paint_no = $(this).parent().children("input").val();
				$(this).parent().parent(".Acontents").hide();
				$(this).parent().parent().parent().children(".addA").hide();
				$(this).parent().parent().parent("td").attr("rowspan","2");
				$(this).parent().parent().parent("td").css('font-weight','700').text("답변완료");
				
				$.ajax({
					url:"insertA.po",
					type:"post",
					data:{qna_no:qna_no,content:content,paint_no:paint_no},
					
					success:function(data){
						$replyTable = $(".answertable"+qna_no);
						$replyTable.html("");
					
						//	var $tr = $("<tr>");
							
							var $test1 = $("<td>").text("답변 :").css("vertical-align","middle");
							var $contentTd =$("<td>");
							var $contentTd2 =$("<span>").text(data.panswer).addClass("question-box");
							var $dateTd = $("<td>").text(data.pa_date);
							var $td =$("<td>").text(" ");
							var $td2 =$("<td>").text(" ");
						
	
							$replyTable.append($test1);
							$replyTable.append($contentTd);
							$contentTd.append($contentTd2);
							$replyTable.append($dateTd);
							//$replyTable.append($td);
							//$replyTable.append($tr);
							$replyTable.css("display","table-row");
					},
					error:function(request,statur,error){
						alert("판매자만 작성할 수 있습니다..");
					
					}
				})
			})
		})
		
		//댓글
		$(function(){
			$(".addQNA").click(function(){
				var paint_no = "<%=plist.getPaint_no()%>";
				var content = $(".QnAContent").val();
				
				$.ajax({
					url:"insertQnA.po",
					type:"post",
					data:{content:content,paint_no:paint_no},
					
					success:function(data){
						$replyTable = $("#replySelectTable");
						$replyTable.html("");
						for(var key in data){
							var $tr = $("<tr>");
							var $writerTd = $("<td>").text(data[key].user_id);
							var $contentTd =$('<td width = "350px" class = "question-box">').text(data[key].pqusetion);
							var $dateTd = $("<td>").text(data[key].pq_date);
						
							
							$replyTable.append($tr);
							$tr.append($writerTd);
							$tr.append($contentTd);
							$tr.append($dateTd);
							
						}
						$(".QnAContent").val("");
					},
					error:function(request,statur,error){
						alert("로그인을해야합니다.");
					}
				})
			})
		})
		</script>
   <br clear="both">
   
   	<!-- 페이징 처리 시작 -->
	
<%-- <div class = "p-parents">
	<div class="pppp">
			<%if (currentPage == 1) { %>
            <a style = "color:#9c9c9c; "  disabled>Previous</a>
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
	</div> --%>
   
   
   <%@include file="../common/footer.jsp"%>



</body>
</html>