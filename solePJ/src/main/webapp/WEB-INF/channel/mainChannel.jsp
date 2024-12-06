<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 사이트 회원정보수정 페이지</title>
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

     <h2>채널 페이지</h2>
    <div class="channel-container">
        <!-- 상단 3개의 채널 -->
        <div class="row">
            <div class="channel-box">채널 1</div>
            <div class="channel-box">채널 2</div>
            <div class="channel-box">채널 3</div>
        </div>
        
        <!-- 그 아래로 이어지는 추가 채널들 -->
        <div class="more-channels">
            <div class="channel-box">채널 4</div>
            <div class="channel-box">채널 5</div>
            <div class="channel-box">채널 6</div>
            <div class="channel-box">채널 7</div>
            <div class="channel-box">채널 8</div>
            <div class="channel-box">채널 9</div>
            <!-- 필요에 따라 추가 채널을 계속 배치 -->
        </div>
    </div>
  
  
  
  
</body>
</html>