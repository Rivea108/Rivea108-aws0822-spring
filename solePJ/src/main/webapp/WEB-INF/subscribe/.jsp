<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 사이트 구독페이지</title>
<link href="/resources/css/sty.css" type="text/css" rel="stylesheet"> 
</head>
<body>

<h3></h3>
<img src="../images/logo.png" class="logo" alt="로고" style="height: 30px;">
<button type="button" style="background-color: #343131; color: white;">구독 채널</button>
<button type="button" style="background-color: #343131; color: white;">주요 채널</button>
<form action="searchResult.jsp" method="GET">
    <input type="text" id="search" name="query" placeholder="찾기">
</form>
<button type="button" style="background-color: #343131; color: white;">알림</button>
<button type="button" style="background-color: #343131; color: white;">내 정보</button>  



<!-- 구독 채널 모달 -->
<div id="subscribeModal" style="display:none;">
    <div>구독 채널 내용</div>
    <button onclick="document.getElementById('subscribeModal').style.display='none'">닫기</button>
</div>

<!-- 주요 채널 모달 -->
<div id="mainChannelModal" style="display:none;">
    <div>주요 채널 내용</div>
    <button onclick="document.getElementById('mainChannelModal').style.display='none'">닫기</button>
</div>

<!-- 알림 모달 -->
<div id="notificationModal" style="display:none;">
    <div>알림 내용</div>
    <button onclick="document.getElementById('notificationModal').style.display='none'">닫기</button>
</div>

<!-- 내 정보 모달 -->
<div id="myInfoModal" style="display:none;">
    <div>내 정보 내용</div>
    <button onclick="document.getElementById('myInfoModal').style.display='none'">닫기</button>
</div>

<hr>

<div style="width: 200px; height: 150px; background-color: #343131;"></div>

<div class="ads">
    <div class="ad-box">좌측 광고</div>
    <div class="ad-box">우측 광고</div>
</div>
  
 <!-- 게시글 영역 -->
  <div style="border: 1px solid #000; padding: 15px; margin-top: 20px;">
<div class="content-box">
    <h4>게시글 제목</h4>
    <div class="post">
        <p><strong>구독한 채널</strong></p>
        <p>구독한 채널들이 여기에 표시됩니다.</p>
    	</div>
	</div>
</div>

   

    
</body>
</html>