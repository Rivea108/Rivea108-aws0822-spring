<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 사이트 글쓰기 페이지(운영자)</title>
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



<!-- 자전거 채널 구독 버튼 -->
<button type="button" style="background-color: #343131; color: white;">자전거 채널 &nbsp; <span>(구독 중)</span></button>
<hr>

<!-- 글쓰기 섹션 -->
<h4>글쓰기</h4>

<!-- 글쓰기 주제 선택 -->
<label for="topic">주제 선택:</label>
<select id="topic" name="topic">
    <option value="질문">질문</option>
    <option value="대답">대답</option>
    <option value="공지">공지</option>
</select>

<!-- 제목 입력 -->
<br><br>
<label for="title">제목:</label>
<input type="text" id="title" name="title" placeholder="글 제목을 입력하세요">
<br><br>

<!-- 에디터(간단한 텍스트 에디터) -->
<label for="content">내용:</label>
<textarea id="content" name="content" rows="10" cols="50" placeholder="글 내용을 입력하세요"></textarea>

<br><br>

<!-- 시간 입력 -->
<label for="postTime">시간:</label>
<input type="datetime-local" id="postTime" name="postTime">
<br><br>

<!-- 공지 여부 -->
<input type="hidden" id="isNotice" name="isNotice" value="false">

<!-- 글쓰기 버튼 -->
<button type="button" style="background-color: #343131; color: white;">작성</button>
<hr>

</body>
</html>