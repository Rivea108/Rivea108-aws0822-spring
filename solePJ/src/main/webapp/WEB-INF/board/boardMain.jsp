<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>커뮤니티 사이트 메인페이지</title>
<link href="/resources/css/sty.css" type="text/css" rel="stylesheet"> 
</head>
<body>

<!-- 헤더 -->
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
        <button type="button" style="background-color: #343131; color: white;" onclick="location.href='/member/memberLogin.aws'">로그인</button>
        <button type="button" style="background-color: #343131; color: white;" onclick="location.href='/notification/notificationAL.aws'">알림</button>
        <button type="button" style="background-color: #343131; color: white;" onclick="location.href='/member/memberInfor.aws'">내 정보</button>
    </div>
</header>
    

<hr>

<div class="container">
        <div class="line"></div> <!-- 왼쪽 선 -->
        <div style="flex: 50;"></div> <!-- 가운데 콘텐츠 영역 -->
        <div class="line"></div> <!-- 오른쪽 선 -->
    </div>

<!-- 채널 레이아웃 -->
<div class="channels">
    <div class="channel-row">
        <span class="channel">I 채널1 I</span>
        <span class="channel">I 채널2 I</span>
    </div>
    <div class="channel-row">
        <span class="channel">I 채널3 I</span>
        <span class="channel">I 채널4 I</span>
    </div>
</div>

<!-- 가운데 I -->
<div class="middle"></div>

</body>
</html>