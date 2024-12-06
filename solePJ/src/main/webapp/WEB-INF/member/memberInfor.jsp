<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 사이트 회원정보수정 페이지</title>
<link href="/resources/css/sty.css" type="text/css" rel="stylesheet"> 
</head>
<body>
<h3></h3>
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
  
<div style="width: 100px; height: 10px; background-color: #343131;"></div>
  
      
        <h2>회원 정보 수정</h2>
    <form action="/update-user" method="POST" enctype="multipart/form-data">

        <label for="profile-picture">프로필 사진</label>
        <input type="file" id="profile-picture" name="profile-picture"><br><br>

        <label for="username">아이디</label>
        <input type="text" id="username" name="username" value="현재아이디" required><br><br>

        <label for="nickname">닉네임</label>
        <input type="text" id="nickname" name="nickname" value="현재닉네임" required><br><br>

        <button type="submit">정보 수정</button>
    </form>

</body>
</html>