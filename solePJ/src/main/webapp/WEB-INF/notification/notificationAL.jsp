<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림 페이지</title>
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
        <button type="button" style="background-color: #343131; color: white;" onclick="location.href='/notification/notificationAL.aws'">알림</button>
        <button type="button" style="background-color: #343131; color: white;" onclick="location.href='/member/memberInfor.aws'">내 정보</button>
    </div>
</header>
    

<hr>

   

    <!-- 알림 컨테이너 -->
    <div id="notification-container">
        <!-- 알림 항목이 여기에 추가됩니다 -->
    </div>


    
    <script>
        // 알림 데이터를 JavaScript로 처리 (예시 데이터)
        const notifications = [
            { message: "새로운 게시글이 작성되었습니다.", timestamp: "2024-12-03 12:00:00" },
            { message: "댓글이 달렸습니다.", timestamp: "2024-12-03 11:45:00" },
            { message: "알림 시스템이 업데이트되었습니다.", timestamp: "2024-12-03 11:30:00" },
            { message: "새로운 댓글에 답변이 달렸습니다.", timestamp: "2024-12-03 11:00:00" }
        ];

        // 알림을 컨테이너에 추가하는 함수
        function renderNotifications() {
            const container = document.getElementById('notification-container');
            container.innerHTML = ''; // 기존 알림들 초기화

            notifications.forEach(notification => {
                // 알림 항목 생성
                const notificationElement = document.createElement('div');
                notificationElement.classList.add('notification-item');
                
                // 알림 내용 추가
                const messageElement = document.createElement('p');
                messageElement.textContent = notification.message;

                // 타임스탬프 추가
                const timestampElement = document.createElement('p');
                timestampElement.classList.add('timestamp');
                timestampElement.textContent = notification.timestamp;

                // 알림 항목에 메시지와 타임스탬프 추가
                notificationElement.appendChild(messageElement);
                notificationElement.appendChild(timestampElement);

                // 알림을 컨테이너에 추가
                container.appendChild(notificationElement);
            });
        }

        // 페이지 로드 시 알림 렌더링
        window.onload = renderNotifications;
    </script>
</body>
</html>