package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.User;
import kr.co.study.delivery.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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



    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotExistedException(email));    // 람다를 써서 해당상황에만 씀

        if (!passwordEncoder.matches(password, user.getPassword())) {  // 생 비번과 암호화된 비번 비교
            throw new PasswordWrongException();    // 비번 안맞으면 비번틀린 익셉션 돌려주기
        }

        return user;
    }

}
