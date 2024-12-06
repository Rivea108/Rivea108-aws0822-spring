<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 사이트 마이페이지</title>
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
  
   <!-- 채널 1, 채널 2 -->
    <div class="channels">
        <div class="channel-box">채널 1</div>
        <div class="channel-box">채널 2</div>
    </div>
  
 
<hr>
  

  
   <!-- 게시글 영역 -->
    <div class="content-box">
        <h4>내 게시글</h4>
        <div class="post">
            <p><strong>게시글 제목 1</strong></p>
            <p>게시글 내용이 여기에 표시됩니다. 최근 작성된 게시글입니다.</p>
        </div>
        <div class="post">
            <p><strong>게시글 제목 2</strong></p>
            <p>게시글 내용이 여기에 표시됩니다. 두 번째 게시글입니다.</p>
        </div>
    </div>

    <!-- 댓글 영역 -->
    <div class="content-box">
        <h4>내 댓글</h4>
        <div class="comment">
            <p><strong>게시글 제목 1에 대한 댓글</strong></p>
            <p>댓글 내용이 여기에 표시됩니다. 첫 번째 댓글입니다.</p>
        </div>
        <div class="comment">
            <p><strong>게시글 제목 2에 대한 댓글</strong></p>
            <p>댓글 내용이 여기에 표시됩니다. 두 번째 댓글입니다.</p>
        </div>
    </div>
  
</body>
</html>