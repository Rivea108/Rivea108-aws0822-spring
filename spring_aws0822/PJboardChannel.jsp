<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 사이트 주요채널 페이지</title>
</head>
<body>
<h3>메인 페이지</h3>
<img src="Rogo.png" class="logo" alt="로고">
  <button type="button" style="background-color: #343131; color: white;">구독 채널</button>
  <button type="button" style="background-color: #343131; color: white;">주요 채널</button>
   <form action="searchResult.jsp" method="GET">
        <input type="text" id="search" name="query" placeholder="찾기">
        </form>
     <button type="button" style="background-color: #343131; color: white;">알림</button>
  <button type="button" style="background-color: #343131; color: white;">내 정보</button>  
  
   <!-- 채널 1, 채널 2 -->
    <div class="channels">
        <div class="channel-box">채널 1</div>
        <div class="channel-box">채널 2</div>
    </div>
  
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
<div style="width: 100%; height: 400px; border: 2px solid #000; margin-top: 20px; padding: 10px;">
    <!-- 네모 안에 채널 1, 채널 2 등 추가 -->
    <div style="width: 48%; height: 80px; background-color: #f2f2f2; margin: 10px; display: inline-block;">
        채널 1
    </div>
    <div style="width: 48%; height: 80px; background-color: #f2f2f2; margin: 10px; display: inline-block;">
        채널 2
    </div>
    <div style="width: 48%; height: 80px; background-color: #f2f2f2; margin: 10px; display: inline-block;">
        채널 3
    </div>
    <div style="width: 48%; height: 80px; background-color: #f2f2f2; margin: 10px; display: inline-block;">
        채널 4
    </div>
    <!-- 더 많은 채널을 여기 추가 -->
</div>


</body>
</html>