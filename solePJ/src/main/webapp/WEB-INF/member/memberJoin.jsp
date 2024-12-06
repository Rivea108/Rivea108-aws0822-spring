<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입 페이지</title>
     <link href="/resources/css/sty.css" type="text/css" rel="stylesheet">
     <script src="https://code.jquery.com/jquery-latest.min.js"></script> 
	 <script>
	 //alert("ddddd");
const email = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,4}$/i;

function check() {
    var fm = document.frm;

    if (fm.memberid.value == "") {
        alert("이메일을 입력해주세요");
        fm.memberid.focus();
        return;
    } else if (!email.test(fm.memberid.value)) {
        alert("올바른 이메일 형식이 아닙니다.");
        fm.memberid.focus();
        return;
    }

    if (fm.password.value == "") {
        alert("비밀번호를 입력해주세요");
        fm.password.focus();
        return;
    }

    if (fm.password.value != fm['confirm-password'].value) {
        alert("비밀번호가 일치하지 않습니다.");
        fm['confirm-password'].focus();
        return;
    }

    if (fm.nickname.value == "") {
        alert("닉네임을 입력해주세요");
        fm.nickname.focus();
        return;
    }

    if (fm.useProfileImage.checked && fm.profileImage.value == "") {
        alert("프로필 사진을 업로드해주세요");
        fm.profileImage.focus();
        return;
    }

    var ans = confirm("회원가입 하시겠습니까?");
    if (ans) {
        fm.action = "<%=request.getContextPath()%>/member/memberJoinAction.aws";
        fm.method = "post";
        fm.submit();
    }
}

function checkDuplicateId() {
    var memberId = $("#memberid").val();

    if (memberId == "") {
        alert("이메일을 입력해주세요.");
        $("#memberid").focus();
        return;
    }

    if (!email.test(memberId)) {
        alert("올바른 이메일 형식이 아닙니다.");
        $("#memberid").focus();
        return;
    }

    $.ajax({
        type: "post",
        url: "<%=request.getContextPath()%>/member/memberIdCheck.aws",
        dataType: "json",
        data: { "memberId": memberId },
        success: function(result) {
            if (result.cnt == 0) {
                alert("사용할 수 있는 아이디입니다.");
                $("#btn").val("Y");
            } else {
                alert("사용할 수 없는 아이디입니다.");
                $("#memberid").val("");
            }
        },
        error: function() {
            alert("전송 실패 테스트");
        }
    });
}
       




</script>

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
        <button type="button" style="background-color: #343131; color: white;" onclick="location.href='/member/memberLogin.aws'">로그인</button>
        <button type="button" style="background-color: #343131; color: white;" onclick="location.href='/notification/notificationAL.aws'">알림</button>
        <button type="button" style="background-color: #343131; color: white;" onclick="location.href='/member/memberInfor.aws'">내 정보</button>
    </div>
</header>

<hr>

    <!-- 회원가입 폼 -->
    <main>
       <form name="frm" method="post">
        <div>
            <label for="memberid">이메일</label>
            <input type="email" name="memberid" id="memberid" placeholder="이메일을 입력하세요" required style="background-color: white; border: 1px solid gray; padding: 8px; font-size: 14px; border-radius: 5px;">
        </div>
        
           <div>
    <label for="password">비밀번호:</label>
    <input type="password" id="password" name="memberpwd" placeholder="비밀번호를 입력하세요" required style="background-color: white; border: 1px solid gray; padding: 8px; font-size: 14px; border-radius: 5px;">
</div>

<div>
    <label for="confirm-password">비밀번호 확인:</label>
    <input type="password" id="confirm-password" name="confirm-password" placeholder="비밀번호를 다시 입력하세요" required style="background-color: white; border: 1px solid gray; padding: 8px; font-size: 14px; border-radius: 5px;">
</div>

            <div>
                <label for="nickname">닉네임:</label>
                <input type="text" id="nickname" name="nickname" placeholder="닉네임을 입력하세요" required style="background-color: white; border: 1px solid gray; padding: 8px; font-size: 14px; border-radius: 5px;">
            </div>

            <div>
                <label for="useProfileImage">프로필 사진을 업로드 하시겠습니까?</label>
                <input type="checkbox" id="useProfileImage" name="useProfileImage" value="yes">
            </div>

            <div id="profileImageUpload" style="display:none;">
                <label for="profileImage">프로필 사진 업로드:</label>
                <input type="file" id="profileImage" name="profileImage" accept="image/*" 
           style="background-color: white; color: black; padding: 8px; font-size: 14px; border: 1px solid gray; border-radius: 5px;">
</div>
            <div>
                <button  type="button" id="btn" onclick="check()" required style="background-color: black; border: 1px solid gray; padding: 8px; font-size: 14px; border-radius: 5px;">회원가입</button>
            </div> 
        </form>
    </main>

 <script>
        // 체크박스 상태에 따라 프로필 사진 업로드 필드를 보여주거나 숨기기
        document.getElementById('useProfileImage').addEventListener('change', function() {
            var profileImageUpload = document.getElementById('profileImageUpload');
            if (this.checked) {
                profileImageUpload.style.display = 'block'; // 체크 시 파일 업로드 필드 보이기
            } else {
                profileImageUpload.style.display = 'none'; // 체크 해제 시 파일 업로드 필드 숨기기
            }
        });
        </script>


</body>
</html>