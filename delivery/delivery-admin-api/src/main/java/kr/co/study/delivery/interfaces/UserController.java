package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.application.UserService;
import kr.co.study.delivery.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;



    // 1. user 리스트 받아오기

    @GetMapping("/users")
    public List<User> list(){
        List<User> users = userService.getUsers();

        return users;
    }





    // 2. user 생성 -> 회원가입
    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {

        String email = resource.getEmail();
        String name = resource.getName();

        User user = userService.addUser(email, name);

        String url = "/users/" + user.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }





    // 3. user 정보 업데이트

    @PatchMapping("/users/{id}")
    public String update(
            @PathVariable Long id,      //{id}를 받아오기 위해 pathVariable
            @RequestBody User resource){

        String email = resource.getEmail();
        String name = resource.getName();
        Long level = resource.getLevel();

        userService.updateUser(id, email, name, level);

        return "{}";

    }


    // 4. user 삭제 -> 실제 삭제가 아니라 권한 0으로 만들어서 아무것도 못하게 만드는 것.
    // 권한 레벨: 1:손님 2:가게주인 3:관리자

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") Long id){

        userService.deactiveUser(id);

        return "{}";
    }


}
