  // 1. 이벤트 리스너
  $("#btn-join").click(() => {
    join();
  });

  $("#btn-login").click(() => {
    login();
  });

  // 2. 기능

  // 회원가입 요청 메서드
  let join = async () => {
    // (1) username, password, email ,addr을 찾아서 오브젝트로 만든다
    let joinDto = {
      username: $("#username").val(),
      password: $("#password").val(),
      email: $("#email").val(),
      addr: $("#addr").val(),
    }
    // (2) fetch 요청한다. (json으로 변환해서)
    let response = await fetch("/api/join", {
      method: "POST",
      headers: {
        "Content-Type": "application/json;charset=utf-8"
      },
      body: JSON.stringify(joinDto)
    });
    let responseJson = await response.json();
    // (3) 회원가입이 잘되면 알림창 띄우고 로그인페이지로 이동한다
    if (responseJson.code == 1) {
      alert("회원가입에 성공하였습니다.");
      location.href = "/loginForm";
    } else {
      alert("회원가입에 실패하였습니다.")
    }
  }

  // 로그인 요청 메서드
  let login = async () => {
    let loginDto = {
      username: $("#username").val(),
      password: $("#password").val(),
    }
    let response = await fetch("/api/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json;charset=utf-8"
      },
      body: JSON.stringify(loginDto)
    });
    let responseJson = await response.json();
    if (responseJson.code == 1) {
      alert("로그인에 성공하였습니다");
      location.href = "/";
    } else {
      alert("아이디나 비밀번호가 틀립니다.");
    }
  }
