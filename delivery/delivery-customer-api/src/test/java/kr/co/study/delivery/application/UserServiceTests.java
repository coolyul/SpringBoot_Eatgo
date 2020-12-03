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

import javax.swing.*;
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
    public void registerUser(){

        String email = "tester@example.com";
        String name = "Tester";
        String password = "test";

        userService.registerUser(email, name, password);

        verify(userRepository).save(any());

    }




    @Test(expected = EmailExistedException.class)           // 이미 있는 이메일로 가입을 시도할 때 만들지 못하게 하기!
    public void registerUserWithExistedEmail(){


        String email = "tester@example.com";
        String name = "Tester";
        String password = "test";

        User user = User.builder().build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));

        userService.registerUser(email, name, password);


    }


}