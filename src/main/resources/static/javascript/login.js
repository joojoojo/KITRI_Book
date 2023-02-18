const form = document.getElementById("login-form");
const emailInput = document.getElementById("email");
const passwordInput = document.getElementById("password");

form.addEventListener("submit", event => {
  event.preventDefault();

  const email = emailInput.value;
  const password = passwordInput.value;

  if (!email && !password) {
    alert("아이디와 이메일을 작성해주세요");
    return;
  }

//  if (!email && password){
//  alert("아이디를 작성하세요");
//  $('#email').focus()
//  }
//
//    if (!password && email){
//    alert("비밀번호를 작성하세요");
//    $('#password').focus()
//    }

  $(document).ready(function () {
          const email = $("#email").val();
          const password = $("#password").val();

          $.ajax({
            type: "POST",
            url: "/login/post",
            data: {
              email: email,
              password: password
            },
            success: function (response) {
              console.log(response);
              alert("로그인 되었습니다.")
              location.replace("/main")
            },
            error: function (error) {
               alert("아이디와 비밀번호를 확인하세요")
              console.log(error);
            }
          });
  });



//signUpButton.addEventListener("click", () => {
//  alert("회원가입 페이지로 이동합니다.");
//    });
});