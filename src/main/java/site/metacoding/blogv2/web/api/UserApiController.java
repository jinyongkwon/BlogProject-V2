package site.metacoding.blogv2.web.api;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.user.User;
import site.metacoding.blogv2.service.UserService;
import site.metacoding.blogv2.web.api.dto.ResponseDto;
import site.metacoding.blogv2.web.api.dto.user.JoinDto;
import site.metacoding.blogv2.web.api.dto.user.LoginDto;
import site.metacoding.blogv2.web.api.dto.user.UpdateDto;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;
    private final HttpSession session;

    // password, email, addr
    @PutMapping("/s/api/user/{id}")
    public ResponseDto<?> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {
        // 세션의 아이디와 {id}를 비교 => 공통적으로 하는것이 아니기 때문에 컨트롤러에서 처리
        User pricipal = (User) session.getAttribute("principal");
        if (pricipal.getId() != id) {
            throw new RuntimeException("권한이 없습니다!!");
        }

        System.out.println(updateDto);
        User userEntity = userService.회원수정(id, updateDto);
        session.setAttribute("pricipal", userEntity); // 세션 변경하기
        return new ResponseDto<>(1, "성공", null);
    }

    // 우리 웹브라우저에서는 현재 사용 안함. 추후 앱에서요청시에사용할 예정
    @GetMapping("/s/api/user/{id}")
    public ResponseDto<?> userInfo(@PathVariable Integer id) {
        User userEntity = userService.회원정보(id);
        return new ResponseDto<>(1, "성공", userEntity);
    }

    // 회원가입 페이지 주세요, 회원가입할께요, 로그인 페이지 주세요, 로그인 할께요
    // 로그아웃 할께요 => 이런것들은 /api 붙이기 x
    @GetMapping("/logout")
    public ResponseDto<?> logout() {
        session.invalidate();
        return new ResponseDto<>(1, "성공", null);
    }

    @PostMapping("/join")
    public ResponseDto<?> join(@RequestBody JoinDto joinDto) {
        userService.회원가입(joinDto);
        return new ResponseDto<>(1, "회원가입성공", null);
    }

    @PostMapping("/login")
    public ResponseDto<?> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        User userEntity = userService.로그인(loginDto);
        if (userEntity == null) {
            return new ResponseDto<>(-1, "로그인 실패", null);
        }

        // 쿠키 로직
        if (loginDto.getRemember().equals("on")) {
            response.addHeader("Set-Cookie", "remember=" + loginDto.getUsername() + ";path=/");
            // response.addHeader("Set-Cookie", "remember=" + loginDto.getUsername() +
            // ";path=/;HttpOnly=true");
        }

        session.setAttribute("principal", userEntity);
        return new ResponseDto<>(1, "로그인에 성공하였습니다.", null);
    }
}
