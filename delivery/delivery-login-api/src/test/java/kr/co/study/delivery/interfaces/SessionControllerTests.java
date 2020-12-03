package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.application.EmailNotExistedException;
import kr.co.study.delivery.application.PasswordWrongException;
import kr.co.study.delivery.application.UserService;
import kr.co.study.delivery.domain.User;
import kr.co.study.delivery.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
class SessionControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserService userService;



    @Test
    public void createWithValidAttributes() throws Exception {      // 모든게 올바른 경우

        //사용자가 자기 고유의 접근 토큰을 만들어줌 accessTokens
        String email = "tester@example.com";
        String password = "test";
        Long id = 1004L;
        String name = "Tester";


        User mockUser = User.builder().id(id).name(name).level(1L).build();

        given(userService.authenticate(email, password)).willReturn(mockUser);

        // jwtUtil 사용해서 토큰 create 해주면 accessToken인 "header.payload.signature" 리턴
        given(jwtUtil.createToken(id, name, null)).willReturn("header.payload.signature");

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\",\"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/session"))
                .andExpect(content().string(
                        containsString("{\"accessToken\":\"header.payload.signature\"}")));

        verify(userService).authenticate(eq(email), eq(password));     // email, password받아서 검증

    }


    @Test
    public void createRestaurantOwner() throws Exception {      // 레스토랑 주인만들기

        //사용자가 자기 고유의 접근 토큰을 만들어줌 accessTokens
        Long id = 1004L;
        String name = "Tester";
        String email = "tester@example.com";
        String password = "test";


        User mockUser = User.builder()
                .id(id)
                .name(name)
                .level(50L)
                .restaurantId(369L)
                .build();

        given(userService.authenticate(email, password)).willReturn(mockUser);


        // jwtUtil 사용해서 토큰 create 해주면 accessToken인 "header.payload.signature" 리턴
        given(jwtUtil.createToken(id, name, 369L)).willReturn("header.payload.signature");


        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\",\"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/session"))
                .andExpect(content().string(
                        containsString("{\"accessToken\":\"header.payload.signature\"}")));

        verify(userService).authenticate(eq(email), eq(password));     // email, password받아서 검증

    }




    @Test
    public void createWithNotExistedEmail() throws Exception {      // 이메일이 없는경우. 실패하는상황

        given(userService.authenticate("x@example.com","test"))
                .willThrow(EmailNotExistedException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"x@example.com\",\"password\":\"test\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("x@example.com"), eq("test"));     // email, password받아서 검증


    }




    @Test
    public void createWithWrongPassword() throws Exception {      // 비밀번호 틀린 경우 실패하는상황

        given(userService.authenticate("tester@example.com","x"))
                .willThrow(PasswordWrongException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\",\"password\":\"x\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("tester@example.com"), eq("x"));     // email, password받아서 검증

    }




}