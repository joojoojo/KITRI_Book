const form = document.getElementById("signup-form");
const usernameInput = document.getElementById("username");
const emailInput = document.getElementById("email");
const passwordInput = document.getElementById("password");
const confirmPasswordInput = document.getElementById("confirm-password");

form.addEventListener("submit", event => {
  event.preventDefault();

  const username = usernameInput.value;
  const email = emailInput.value;
  const password = passwordInput.value;
  const confirmPassword = confirmPasswordInput.value;


if (!username || !email || !password || !confirmPassword) {
    alert("모든 항목을 작성해주세요.");
    //return;
  }

if (password !== confirmPassword) {
    alert("비밀번호가 일치하지 않습니다.");
    //return;
  }

  $(document).ready(function () {
      console.log("ready까지 됐다");
//      $("#submit").click(function (e) {
      console.log("click까지 됐다")
        //e.preventDefault();
        const username = $("#username").val();
        const email = $("#email").val();
        const password = $("#password").val();
        const confirmPassword = $("#confirm-password").val();
        console.log("ajax 직전")
        $.ajax({
          type: "POST",
          url: "/signUp/post",
          data: {
            username: username,
            email: email,
            password: password,
            confirmPassword: confirmPassword
          },
          success: function (response) {
            console.log(response);
          },
          error: function (error) {
            console.log(error);
          }
        });
//      });
  });

});



//alert(`Sign-up successful!\nUsername: ${username}\nEmail: ${email}`);

var $email = $("#email");
// 아이디 정규식
		$email.on("keyup", function() { // 키보드에서 손을 땠을 때 실행
		console.log("키보드에서 손을 땠을 때 실행")
//			var regExp = /^[a-z]+[a-z0-9]{5,15}$/g;
			var regExp = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;


			if (!regExp.test($email.val())) { // id 가 정규식을 통과하지 못할경우 경우 체크
				idchk = false;
				var elements = document.getElementById("emailCheck");
                elements.innerHTML = "사용할 수 없는 아이디 형식입니다.";
				$("#emailCheck").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
				console.log("아이디가 정규식 통과 못했다")
				console.log($email.val())
			} else { // 공백아니면 중복체크
			console.log("정규식 통과 / ajax시작")
			var elements = document.getElementById("emailCheck");
                            elements.innerHTML = "사용 가능";
            				$("#emailCheck").css({
            					"color" : "blue",
            					"font-weight" : "bold",
            					"font-size" : "10px"
            				})
				$.ajax({
					type : "POST", // http 방식
					url : "/signup/checkid", // ajax 통신 url
					data : { // ajax 내용 => 파라미터 : 값 이라고 생각해도 무방
						"id" : $email.val(),
						"type" : "email"
					},
					success : function(response) {
						if (response === 1) { // 1이면 중복
						    console.log(response)
							idchk = false;
							var elements = document.getElementById("emailCheck");
                            elements.innerHTML = "이미 사용중인 아이디입니다.";
							$("#emailCheck").css({
								"color" : "#FA3E3E",
								"font-weight" : "bold",
								"font-size" : "10px"
							})
							//console.log("중복아이디");
						} else { // 아니면 중복아님
						console.log(response)
							idchk = true;
							var elements = document.getElementById("emailCheck");
                            elements.innerHTML = "사용 가능한 아이디입니다.";
                            $("#emailCheck").css({
                                "color" : "blue",
                                "font-weight" : "bold",
                                "font-size" : "10px"

                            							})
							//console.log("중복아닌 아이디");
						}
					}
				})

			}
		});

//$("#signup-form").ready(function () {
//console.log("ready까지 됐다")
//  $("#submit").click(function (e) {
//  console.log("click까지 됐다")
//    e.preventDefault();
//    const username = $("#username").val();
//    const email = $("#email").val();
//    const password = $("#password").val();
//    const confirmPassword = $("#confirm-password").val();
//    console.log("ajax 직전")
//    $.ajax({
//      type: "POST",
//      url: "/signUp/post",
//      data: {
//        username: username,
//        email: email,
//        password: password,
//        confirmPassword: confirmPassword
//      },
//      success: function (response) {
//        console.log(response);
//      },
//      error: function (error) {
//        console.log(error);
//      },
//    });
//  });
//});
