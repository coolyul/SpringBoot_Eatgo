package kr.co.study.delivery.interfaces;

import lombok.Data;

@Data
public class SessionRequestDto {        // 사용자에게 이메일과 비밀번호를 요청하고 sessionResponseDto로 돌려줌

    private String email;

    private String password;

}
