<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 학습하기</title>
</head>
<body>
<c:if test= "${!empty sessionScope.midx}">
${memberName} &nbsp; 
<a href="${pageContext.request.contextPath}/member/memberLogout.aws">로그아웃</a>
</c:if>


<%-- <br>
<a href="${pageContext.request.contextPath}/member/memberJoin.aws">회원가입 페이지</a>
<br>
<a href="${pageContext.request.contextPath}/member/memberLogin.aws">회원로그인 페이지</a>
<br>
<a href="${pageContext.request.contextPath}/board/boardList.aws">게시판 페이지</a>
<br>
<a href="${pageContext.request.contextPath}/board/boardMain.aws">메인 페이지</a> --%>

<script>
location.href = "${pageContext.request.contextPath}/board/redirectBoardMain.aws"
<!--이건 페이지 연결이 가능할때 해제-->
</script>
</body>	
</html>	
