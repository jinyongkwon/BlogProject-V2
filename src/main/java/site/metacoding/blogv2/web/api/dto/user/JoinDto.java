package site.metacoding.blogv2.web.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.blogv2.domain.user.User;

// DTO : Data Transper Object (통신으로 전달하거나 받는 오브젝트)

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JoinDto {
    private String username;
    private String password;
    private String email;
    private String addr;

    public User toEntity() { // DB에 INSERT하기 위해 변환해줌
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setAddr(addr);
        return user;
        // return new User(null, username, password, email, addr, null);
    }
}
