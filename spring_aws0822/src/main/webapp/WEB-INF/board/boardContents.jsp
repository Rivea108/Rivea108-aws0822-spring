<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.myaws.myapp.domain.BoardVo" %>   
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %> 
 <% 
/*  BoardVo bv = (BoardVo)request.getAttribute("bv");   //강제형변환  양쪽형을 맞춰준다 
 
 String memberName = "";
 if (session.getAttribute("memberName") !=null){
	 memberName = (String)session.getAttribute("memberName");
 }
 int midx=0;
 if (session.getAttribute("midx") !=null){ //강제형변환이 안먹음
	 midx = Integer.parseInt(session.getAttribute("midx").toString());
 } 		 */			
 %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>글내용</title>
<link href="<%=request.getContextPath() %>/resources/css/style2.css" rel="stylesheet">
<!--jquery CDN주소 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script> 
<script>
function checkimageType(fileName){
	var pattern = /jpg$|gif$|png$|jpeg$/i; //자바스크립트의 정규표현식 
	return fileName.match(pattern);
}
function getOriginalFileName(fileName){ //원본파일 이름 추출
	var idx = fileName.lastIndexOf("_")+1;
	return fileName.substr(idx);
}
function getImageLink(fileName){//자바스크립트는 형태를 지정하지않는다.
	var front = fileName.substr(0, 12);
	var end = fileName.substr(14);
	return front+end;
}
function download(){
	//주소 사이에s-는 빼고
	var downloadImage = getImageLink("${bv.filename}");
	var	downLink = "${pageContext.request.contextPath}/board/displayFile.aws?fileName="+downloadImage+"&down=1";	
	return downLink;
}





function commentDel(cidx){
	let ans= confirm("삭제하시겠습니까?");	
	if (ans== true){
		
		$.ajax({
			type :  "get",    //전송방식
			url : "${pageContext.request.contextPath}/comment/"+cidx+"/commentDeleteAction.aws",
			dataType : "json",       // json타입은 문서에서  {"키값" : "value값","키값2":"value값2"}
			success : function(result){   //결과가 넘어와서 성공했을 받는 영역
			//	alert("전송성공 테스트");	
			//	alert(result.value);
			$.boardCommentList();			
							
			},
			error : function(){  //결과가 실패했을때 받는 영역						
				alert("전송실패");
			}			
		});			
	}	
	return;
}

//jquery로 만드는 함수  ready밖에 생성
$.boardCommentList = function(){
	
	let block = $("#block").val(); //아아디 앞에는 #을 붙여야하고 클래스 앞에는 .을 붙인다
	
	
 	$.ajax({
		type :  "get",    //전송방식
		url : "${pageContext.request.contextPath}/comment/${bv.bidx}/"+block+"/commentList.aws",
		
		dataType : "json",       // json타입은 문서에서  {"키값" : "value값","키값2":"value값2"}
		success : function(result){   //결과가 넘어와서 성공했을 받는 영역
			//alert("전송성공 테스트");			
		
		if(result.moreView =="N") {
			$("#morebtn").css("display","none"); //더보기 버튼을 감춘다
		}else{
			$("#morebtn").css("display","block"); //더보기 버튼을 보여준다 
		}
		
		//alert("전송성공 테스트1");		
		
		$("#block").val(result.nextBlock);
		
		var strTr = "";
		$(result.clist).each(function(){	
				
			//alert("전송성공 테스트2"); 반복실행되는건 맞음 
			
			var btnn="";			
			 //현재로그인 사람과 댓글쓴 사람의 번호가 같을때만 나타내준다
			if (this.midx == "${midx}") {
				if (this.delyn=="N"){
					btnn= "<button type='button' onclick='commentDel("+this.cidx+");'>삭제</button>";
				}			
			}
			 
			//alert("전송성공 테스트3"); 반복실행되는건 맞음 
			 
			strTr = strTr + "<tr>"
			+"<td>"+this.cidx+"</td>"
			+"<td>"+this.cwriter+"</td>"
			+"<td class='content'>"+this.ccontents+"</td>"
			+"<td>"+this.writeday+"</td>"
			+"<td>"+btnn+"</td>"
			+"</tr>";					
		});		       
		
		var str  = "<table class='replyTable'>"
			+"<tr>"
			+"<th>번호</th>"
			+"<th>작성자</th>"
			+"<th>내용</th>"
			+"<th>날짜</th>"
			+"<th>DEL</th>"
			+"</tr>"+strTr+"</table>";		
		
		$("#commentListView").html(str);		
				
		
		
		},
		error : function(){  //결과가 실패했을때 받는 영역						
			alert("전송실패");
		}		
		
	});	
}

$(document).ready(function(){	
	
	$("#dUrl").html(getOriginalFileName("${bv.filename}")); //filename을 F로 적음

	$("#dUrl").click(function(){
		$("a#dUrl").attr("href",download());
		return;
	});
	
	//alert("dddddz");
	$.boardCommentList();//주석처리 되어있던걸 풀었음	
	
	$("#btn").click(function(){
		alert("추천버튼 클릭");		
	
		$.ajax({
			type :  "get",    //전송방식
			url : "${pageContext.request.contextPath}/board/boardRecom.aws?bidx=${bv.bidx}",
			dataType : "json",       // json타입은 문서에서  {"키값" : "value값","키값2":"value값2"}
			success : function(result){   //결과가 넘어와서 성공했을 받는 영역
			//alert("전송성공 테스트");	
			
				var str ="추천("+result.recom+")";			
				$("#btn").val(str);			
			},
			error : function(){  //결과가 실패했을때 받는 영역						
				alert("전송실패");
			}			
		});			
	});	
	
 	$("#cmtBtn").click(function(){
		//alert("ddd");
		let midx = "${midx}"; 
		//152줄 <(태그를 만들면 안되는걸 만들어 버렸음)
		
		if (midx == "" || midx == "null" || midx == null || midx == 0){
			alert("로그인을 해주세요");
			return;
		}  				
		let cwriter = $("#cwriter").val();
		let ccontents = $("#ccontents").val();
		
		if (cwriter == ""){
			alert("작성자를 입력해주세요");
			$("#cwriter").focus();
			return;		
		}else if (ccontents ==""){
			alert("내용을 입력해주세요");
			$("#ccontents").focus();
			return;
		}
		
		
		$.ajax({
			type :  "post",    //전송방식
			url : "${pageContext.request.contextPath}/comment/commentWriteAction.aws",
			data : {"cwriter" : cwriter, 
					   "ccontents" : ccontents, 
					   "bidx" : "${bv.bidx}",
					   "midx" : "${midx}"
					   },
			dataType : "json",       // json타입은 문서에서  {"키값" : "value값","키값2":"value값2"}
			success : function(result){   //결과가 넘어와서 성공했을 받는 영역
				alert("전송성공 테스트");			
				//var str ="("+result.value+")";			
				//alert(str);		
				if(result.value ==1){
					$("#ccontents").val("");
				}				
				$.boardCommentList();
			},
			error : function(){  //결과가 실패했을때 받는 영역						
				alert("전송실패");
			}			
		});			
	});	
 	
 	$("#more").click(function(){
 		$.boardCommentList();		
 		
 	})
});
</script>
</head>
<body>
<header>
	<h2 class="mainTitle">글내용</h2>
</header>

<article class="detailContents">
	<h2 class="contentTitle">${bv.subject} (조회수:${bv.viewcnt})
	<input type="button" id="btn" value="추천(${bv.recom})">
	</h2>	
	<p class="write">${bv.writer} (${bv.writeday})</p>
	<hr>
	<div class="content">
		${bv.contents}		
	</div>
	<c:if test="${!empty bv.filename}">
	<img src="${pageContext.request.contextPath}/board/displayFile.aws?fileName=${bv.filename}"><!-- 주소를 바깥에 둬서 주소를 변경해야함 --> <!-- 오타주의바람 -->
	<p>
	<a id="dUrl" href="" class="fileDown">	
	첨부파일 다운로드</a>
	</p>	
	</c:if>
	
</article>	
<div class="btnBox">
	<a class="btn aBtn" href="${pageContext.request.contextPath}/board/boardModify.aws?bidx=${bv.bidx}">수정</a>
	<a class="btn aBtn" href="${pageContext.request.contextPath}/board/boardDelete.aws?bidx=${bv.bidx}">삭제</a>
	<a class="btn aBtn" href="${pageContext.request.contextPath}/board/boardReply.aws?bidx=${bv.bidx}">답변</a>
	<a class="btn aBtn" href="${pageContext.request.contextPath}/board/boardList.aws">목록</a>
</div>
<article class="commentContents">
	<form name="frm">
		<p class="commentWriter" style="width:100px;">
		<input type="text" id="cwriter" name="cwriter" value="${memberName}" readonly="readonly" style="width:100px;border:0px;">
		</p>	
		<input type="text" id="ccontents" name="ccontents">
		<button type="button" id="cmtBtn" class="replyBtn">댓글쓰기</button>
	</form>
	
	<div id="commentListView"></div>
	
	<!-- 더보기 기능 블럭 추가 -->
	<input type="hidden" id="block" value="1">
	<div id="morebtn" style="text-align : center; line-height:50px">
		<button type="button" id="more">더보기</button>
	</div>
	
</article>
</body>
</html>