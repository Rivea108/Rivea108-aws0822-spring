<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%-- <%@ include file="/common/loginCheck.jsp"%> 자바에서는 로그인 체크를 사용했지만
  스프링에서는 애초에 파일을 만들지 않았기에 사용하지 않아도 된다 --%>
<% String bidx = request.getAttribute("bidx").toString(); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글삭제</title>
<link href="<%=request.getContextPath() %>/resources/css/style2.css" rel="stylesheet">
<script> 

function check() {
	  
	  // 유효성 검사하기
	  let fm = document.frm;
	  
	  if (fm.password.value == "") {
		  alert("비밀번호를 입력해주세요");
		  fm.password.focus();
		  return;
	  }
	  
	  let ans = confirm("삭제하시겠습니까?");
	  
	  if (ans == true) {
		  fm.action="<%=request.getContextPath()%>/board/boardDeleteAction.aws";
		  fm.method="post";
		  fm.submit();
	  }	  
	  
	  return;
}

</script>
</head>
<body>
<header>
	<h2 class="mainTitle">글삭제</h2>
</header>

<form name="frm">
	<input type="hidden" name="bidx" value="<%= bidx %>">
	<table class="writeTable">
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="password"></td>
		</tr>
	</table>
	
	<div class="btnBox">
		<button type="button" class="btn" onclick="check();">저장</button>
		<a class="btn aBtn" href="#" onclick="history.back();">취소</a>
	</div>	
</form>

</body>
</html>