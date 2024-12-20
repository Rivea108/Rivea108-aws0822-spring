<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %> 
   <%--  <%
    세션정보를 꺼내서 담겨있지 않으면 로그인 화면으로 넘긴다.
    if (session.getAttribute("midx") == null) { //로그인이 되어있지 않다면 로그인을 하라고 로그인 페이지로 밀어냄
    out.println("<script>alert('로그인을 해주세요');location.href='"
    +request.getContextPath()+
    "/member/memberLogin.aws';</script>");
    	}
    %> --%>
    
    <!-- c:if문 -->
    <c:if test="${empty midx}">
    <script>alert("로그인을 해주세요");location.href="${pageContext.request.contextPath}"/member/memberLogin.aws;</script>
    <!-- 모디파이 들어가기 전에 인터셉터로 로그인창을 띄우기에 알럿창을 보지 못한다. -->
    </c:if>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정</title>
<link href="${pageContext.request.contextPath}/resources/css/style2.css" rel="stylesheet">
<script> 

function check() {
	  
	  // 유효성 검사하기 해결
	  let fm = document.frm;
	  
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
	  }else if (fm.password.value == "") {
			  alert("비밀번호를 입력해주세요");
			  fm.password.focus();
			  return;
	  }
	  
	  let ans = confirm("저장하시겠습니까?");
	  
	  if (ans == true) {
		  fm.action="${pageContext.request.contextPath}/board/boardModifyAction.aws";
		  fm.method="post";
		  fm.enctype="multipart/form-data"; //이미지와 문자열을 모두 담아서 넘길수 있게 바이너리타입으로 멀티파트로 넘긴다.
		  fm.submit();
	  }	  
	  
	  return;
}

</script>
</head>
<body>
<header>
	<h2 class="mainTitle">글수정</h2>
</header>

<form name="frm">
<input type="hidden" name="bidx" value="${bv.bidx}">

	<table class="writeTable">
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject" value="${bv.subject}"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="contents" rows="6"> ${bv.contents}</textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer" value="${bv.writer}"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><input type="file" name=attachfile></td>
		</tr>
	</table>
	
	<div class="btnBox">
		<button type="button" class="btn" onclick="check();">저장</button>
		<a class="btn aBtn" onclick="history.back();">취소</a>
	</div>	
</form>

</body>
</html>