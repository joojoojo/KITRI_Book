$(document).ready(function () {
    // logout 버튼 클릭시
    $('.logout').click(function () {
        $.ajax({
            type: 'POST',
            url: '/logout',
            success: function () {
                // 로그아웃 성공시 처리할 코드
                window.location.href = '/main';
            },
            error: function () {
                // 로그아웃 실패시 처리할 코드
            }
        });
    });
});