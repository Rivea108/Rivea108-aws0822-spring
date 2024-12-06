<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 사이트 목록페이지</title>
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


  <!-- 게시글 리스트 -->
<h3>게시글 리스트</h3>
<table border="1" style="width: 100%; border-collapse: collapse; text-align: center;">
    <thead>
        <tr style="background-color: #66648B;">
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성날짜</th>
            <th>조회</th>
            <th>추천</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>1</td>
            <td><a href="postDetail.jsp?id=1">게시글 제목1</a></td>
            <td>작성자1</td>
            <td>2024-11-25</td>
            <td>100</td>
            <td>10</td>
        </tr>
        <tr>
            <td>2</td>
            <td><a href="postDetail.jsp?id=2">게시글 제목2</a></td>
            <td>작성자2</td>
            <td>2024-11-24</td>
            <td>200</td>
            <td>20</td>
        </tr>
        <tr>
            <td>3</td>
            <td><a href="postDetail.jsp?id=3">게시글 제목3</a></td>
            <td>작성자3</td>
            <td>2024-11-23</td>
            <td>300</td>
            <td>30</td>
        </tr>
    </tbody>
</table>

</body>
</html>