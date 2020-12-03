package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.application.UserService;
import kr.co.study.delivery.domain.User;
import org.apache.catalina.Group;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void list() throws Exception {     // user 목록 얻어오기

        List<User> users = new ArrayList<>();

        users.add(User.builder()
                .email("tester@example.com")
                .name("tester")
                .level(1L)
                .build());

        given(userService.getUsers()).willReturn(users);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("tester")));

    }



    @Test
    public void create() throws Exception {           // user가 계정 만드는게 아니라 관리자가 만들어주는 용도


        String email = "admin@example.com";
        String name = "Administrator";

        // User 정보 빌드!
        User user = User.builder()
                .email(email)
                .name(name)
                .build();

        // userService에 user 정보 저장해주기
        given(userService.addUser(email, name)).willReturn(user);


        // mvc에서 user 정보 잘 들어가는지 확인하기
        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"admin@example.com\",\"name\":\"Administrator\"}"))
                .andExpect(status().isCreated());


        // user 정보 잘 add 됐는지 확인하기
        verify(userService).addUser(email, name);


    }




    @Test
    public void update() throws Exception {           // user정보 업데이트

        // mvc에서 user 정보 잘 들어가는지 확인하기
        mvc.perform(patch("/users/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"admin@example.com\",\"name\":\"Administrator\"," +
                        "\"level\":100}"))
                .andExpect(status().isOk());

        Long id = 1004L;
        String email = "admin@example.com";
        String name = "Administrator";
        Long level = 100L;


        // userService에 user 정보 업데이트. id랑 level도 다 업뎃. 위에 적은 변수 정보와 같은게 들어왔는지 확인!
        verify(userService).updateUser(eq(id), eq(email), eq(name), eq(level));


    }



    @Test
    public void deactivate() throws Exception {           // 비활성화
        mvc.perform(delete("/users/1004"))
                .andExpect(status().isOk());

        verify(userService).deactiveUser(1004L);

    }


}