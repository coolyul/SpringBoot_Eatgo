package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.User;
import kr.co.study.delivery.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {


    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;    // password 암호화하는 기본 함수 이용해서 안보이게 인코딩

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public User registerUser(String email, String name, String password) {

        Optional<User> existed = userRepository.findByEmail(email);     // optional해서 존재하는지 여부도 확인

        // 만약존재한다면 에러메시지 나가게 설정
        if(existed.isPresent()){
            throw new EmailExistedException(email);
        }

             // 비밀번호 암호화하는 메소드드
        String encodedPassword = passwordEncoder.encode(password);      // password 인코딩! 안보이게해줌.


        User user = User.builder()
                .id(1004L)
                .email(email)
                .name(name)
                .password(encodedPassword)
                .level(1L)
                .build();

        return userRepository.save(user);

    }

}
