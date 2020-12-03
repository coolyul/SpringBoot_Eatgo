package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.User;
import kr.co.study.delivery.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;



    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }



    // user 목록 가져오기
    public List<User> getUsers() {

        List<User> users = userRepository.findAll();

        return users;

    }

    
    // user Add
    public User addUser(String email, String name) {

        //addUser 메소드 호출하면 user에 정보 저장해주기!
        User user = User.builder()
                .email(email)
                .name(name)
                .level(1L)
                .build();

        // UserRepo에 저장
        return userRepository.save(user);

    }


    public User updateUser(Long id, String email, String name, Long level) {

        // TODO: restaurantService 예외 처리 참고
        // 있으면 돌려달라 해서 Optional<User> 써도 되고, .orElse(null) 해도 됨.
        User user = userRepository.findById(id).orElse(null);

        user.setName(name);
        user.setEmail(email);
        user.setLevel(level);

        return user;
    }

    public User deactiveUser(long id) {

        User user = userRepository.findById(id).orElse(null);

        user.deactive();        // user level을 0으로 만들어주기. 권리도 빼앗기

        return user;
    }
}
