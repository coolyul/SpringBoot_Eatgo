package kr.co.study.delivery.interfaces;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponseDto {       // session의 정보를 전달해주기 위한 클래스  사용자에게 돌려줌


    private  String accessToken;

}
