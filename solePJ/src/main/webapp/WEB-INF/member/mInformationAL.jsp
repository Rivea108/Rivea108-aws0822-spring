<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 사이트 구독페이지</title>
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



<div style="width: 200px; height: 150px; background-color: #343131;"></div>
  
 <!-- 게시글 영역 -->
  <div style="border: 1px solid #000;">
<div style="border: 1px solid white; class="content-box">
    <h4>게시글 제목</h4>
    <div class="post">
        <p><strong>구독한 채널</strong></p>
        <p>구독한 채널들이 여기에 나타납니다.</p>
    	</div>
	</div>
</div>

   

    
</body>
</html>