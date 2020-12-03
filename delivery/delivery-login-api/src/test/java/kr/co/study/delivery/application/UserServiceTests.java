package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.User;
import kr.co.study.delivery.domain.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class UserServiceTests {


    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp(){

        MockitoAnnotations.openMocks(this);

        userService = new UserService(userRepository, passwordEncoder);
    }


    @Test
    public void authenticateWithValidAttributes(){      // 올바른 이메일과 비번을 이용해서 진행

        String email = "tester@example.com";
        String password = "test";


        User mockUser = User.builder()
                .email(email)
                .build();


        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        // 일단 뭐가 들어오든 맞다 틀리다를 해줘야 함
        given(passwordEncoder.matches(any(), any())).willReturn(true);

        User user = userService.authenticate(email, password);      // 검증

        assertThat(user.getEmail()).isEqualTo(email);

    }



    @Test(expected = EmailNotExistedException.class)
    public void authenticateWithNotExistedEmail(){      // 없는 이메일로 인증

        String email = "x@example.com";
        String password = "test";

        // mockUser 만들어 줄 필요가 없음 메일 자체가 없기때문에

        given(userRepository.findByEmail(email)).willReturn(Optional.empty());

        User user = userService.authenticate(email, password);      // 검증

        assertThat(user.getEmail()).isEqualTo(email);

    }



    @Test(expected = PasswordWrongException.class)
    public void authenticateWithWrongPassword(){      // 없는 비번으로 인증

        String email = "tester@example.com";
        String password = "x";

        User mockUser = User.builder()
                .email(email)
                .build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        // 뭐가 들어오든 틀려야함. 없는 비번이기때문에
        given(passwordEncoder.matches(any(), any())).willReturn(false);

        User user = userService.authenticate(email, password);      // 검증

        assertThat(user.getEmail()).isEqualTo(email);

    }

}