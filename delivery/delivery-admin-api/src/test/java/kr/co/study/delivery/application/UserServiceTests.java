package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.CategoryRepository;
import kr.co.study.delivery.domain.User;
import kr.co.study.delivery.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        userService = new UserService(userRepository);
    }


    @Test
    public void getUsers(){         // user 목록 불러오기

        List<User> mockUsers = new ArrayList<>();


        // 가짜 User객체에 정보 일단 저장해보기
        mockUsers.add(User.builder()
                .email("tester@example.com")
                .name("tester")
                .level(1L)
                .build());


        // user Repo에 저장된 user들 저장
        given(userRepository.findAll()).willReturn(mockUsers);


        // userService 에서 불러오기
        List<User> users = userService.getUsers();

        User user = users.get(0);

        assertThat(user.getName()).isEqualTo("tester");
    }



    @Test
    public void addUser(){
        
        String email = "admin@example.com";
        String name = "Administrator";

        // 테스트 할 가짜 User 정보 빌드!
        User mockUser = User.builder()
                .email(email)
                .name(name)
                .build();


        // user repository에 저장하면 mockUser로 만들어져서 리턴될것이다
        given(userRepository.save(any())).willReturn(mockUser);

        // adduser 하고 얻는 진짜 user 정보
        User user = userService.addUser(email, name);

        assertThat(user.getName()).isEqualTo(name);
    }



    @Test
    public void updateUser(){


        // 바꾸고자 하는 user 정보
        Long id = 1004L;
        String email = "admin@example.com";
        String name = "Superman";
        Long level = 100L;


        // 기존 user 정보 build
        User mockUser = User.builder()
                .id(id)
                .email(email)
                .name("Administrator")
                .level(1L)
                .build();



        // 원래 user 정보
        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));


        // 바뀐 내용으로 update
        User user = userService.updateUser(id, email, name, level);

        // 내용 잘 바껴있는지 확인
        verify(userRepository).findById(eq(id));


        assertThat(user.getName()).isEqualTo("Superman");
        assertThat(user.isAdmin()).isTrue();
    }


    @Test
    public void deactiveUser(){
        
        // 비활성화 하고자 하는 user id
        Long id = 1004L;


        // 기존 user 정보 build
        User mockUser = User.builder()
                .id(id)
                .email("admin@example.com")
                .name("Administrator")
                .level(100L)
                .build();



        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.deactiveUser(1004L);

        verify(userRepository).findById(1004L);

        assertThat(user.isAdmin()).isFalse();
        assertThat(user.isActive()).isFalse();
    }

}