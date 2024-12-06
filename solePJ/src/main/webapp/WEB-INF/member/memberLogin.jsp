<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 사이트 로그인 페이지</title>
<link href="/resources/css/sty.css" type="text/css" rel="stylesheet"> 
</head>
<body>

<header>
    <h3></h3>
    <div style="display: flex; align-items: center; justify-content: space-between; width: 100%;">
       <a href="${pageContext.request.contextPath}/board/boardMain.aws">
            <img src="../images/logo.png" class="logo" alt="로고" style="height: 30px;">
        </a>
        <button type="button" style="background-color: #343131; color: white;" onclick="location.href='/subscribe/subscribeAL.aws'">구독 채널</button>
        <button type="button" style="background-color: #343131; color: white;" onclick="location.href='/channel/mainChannel.aws'">주요 채널</button>
        <form action="searchResult.jsp" method="GET" style="flex-grow: 1; display: flex;">
            <input type="text" id="search" name="query" placeholder="찾기" 
                   style="width: 200px; padding: 5px; font-size: 14px; background-color: white; color: black; border: 1px solid gray; border-radius: 5px; ">
        </form>
        <button type="button" style="background-color: #343131; color: white;" onclick="location.href='/notification/notificationAL.aws'">알림</button>
        <button type="button" style="background-color: #343131; color: white;" onclick="location.href='/member/memberInfor.aws'">내 정보</button>
    </div>
</header>
 
<hr>
<main>
    <form action="signup_action.jsp" method="POST" enctype="multipart/form-data">
        <!-- 폼 내용 -->
   <!-- <form style="color: white;"> -->
    <label for="email">이메일:</label>
    <input type="email" id="email" name="email" 
           oninput="moveToPassword()" 
           placeholder="이메일을 입력하세요" 
           required 
           style="color: black; background-color: white; border: 1px solid gray;"><br><br>
    
    <label for="password">비밀번호:</label>
    <input type="password" id="password" name="password" 
           placeholder="비밀번호를 입력하세요" 
           required 
           style="color: black; background-color: white; border: 1px solid gray;"><br><br>
           <a href="/member/memberJoin.aws" class="text-button">계정 생성하기</a>
    
    <button type="submit" style="color: white; background-color: black; border: none; padding: 5px 10px;">로그인</button>
    
    
    
</form>
</main>

</body>
</html>