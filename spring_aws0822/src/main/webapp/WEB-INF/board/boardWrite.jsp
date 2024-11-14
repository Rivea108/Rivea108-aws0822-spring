<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %> 
    <%
/*  String msg="";
if(request.getAttribute("msg") != null){빈값이 아닐 때
msg = (String)request.getAttribute("msg");
out.println("<script>alert('"+msg+"');</script>"); 빈값일때 
}
*/

//String msg="";
//if(request.getAttribute("msg") != null){
//msg = (String)request.getAttribute("msg");
//}
//if(msg !="") {
//	out.println("<script>alert('"+msg+"');</script>");
//}

%>

<c:if test="${!empty msg}"> <!-- 빈값이 아닐 때 --> <!-- NULL도 가능하지만 엠프티쓰자 -->
<script>alert('"+msg+"');</script> <!-- 빈값일때 -->	
</c:if>


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<link href="${pageContext.request.contextPath}/resources/css/style2.css" rel="stylesheet">
<script> 

function check() {
	  
	  // 유효성 검사하기
	  let fm = document.frm; //문서객체 안에 form 객체 생성하기
	   
	  if (fm.subject.value == "") {
		  alert("제목을 입력해주세요");
		  fm.subject.focus();
		  return;
	  } else if (fm.contents.value == "") {
		  alert("내용을 입력해주세요");
		  fm.contents.focus();
		  return;
	  } else if (fm.writer.value == "") {
		  alert("작성자를 입력해주세요");
		  fm.writer.focus();
		  return;
	  }  else if (fm.password.value == "") {
		  alert("비밀번호를 입력해주세요");
		  fm.password.focus();
		  return;
	  }
	  let ans = confirm("저장하시겠습니까?"); //함수의 값은 참과 거짓 true or false로 나눈다
	  
	  if (ans == true) {
		  fm.action="${pageContext.request.contextPath}/board/boardWriteAction.aws";
		  fm.method="post";
		  fm.enctype="multipart/form-data"; //멀티 파트로 넘긴다(보드컨트롤러로)53줄
		  fm.submit();
	  }	  
	  
	  return;
}

</script>
</head>
<body>
<header>
	<h2 class="mainTitle">글쓰기</h2>
</header>

<form name="frm">
	<table class="writeTable">
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="contents" rows="6"></textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><input type="file" name="attachfile"></td>
		</tr>
	</table>
	
	<div class="btnBox">
		<button type="button" class="btn" onclick="check();">저장</button>
		<a class="btn aBtn" onclick="history.back();">취소</a>
	</div>	
</form>

</body>
</html>