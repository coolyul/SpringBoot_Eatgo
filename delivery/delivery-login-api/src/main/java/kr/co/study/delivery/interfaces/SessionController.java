package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.application.UserService;
import kr.co.study.delivery.domain.User;
import kr.co.study.delivery.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/session")
    public ResponseEntity<SessionResponseDto> create(@RequestBody SessionRequestDto resource)
            throws URISyntaxException {      //sessionDto를 돌려줄거다


        String email = resource.getEmail();
        String password = resource.getPassword();

        User user = userService.authenticate(email, password);

        // restaurant 주인인걸 인증하기위해 레스토랑아이디도 추가해주자
        // restaurantOwner이면 Id를, 아니면 null을
        String accessToken = jwtUtil.createToken(user.getId(),user.getName(),
                user.isRestaurantOwner() ? user.getRestaurantId() : null);

        String url = "/session";
        return ResponseEntity.created(new URI(url))
                .body(SessionResponseDto.builder()
                    .accessToken(accessToken)
                    .build());
    }

}
