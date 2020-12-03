package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.application.ReviewService;
import kr.co.study.delivery.domain.Review;
import kr.co.study.delivery.interfaces.ReviewController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @Test           // 내용이 올바르게 잘 들어간 경우
    public void createWithValidAttributes() throws Exception {

        // 테스트 때 John 이름으로 만들어진 토큰
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0.8hm6ZOJykSINHxL-rf0yV882fApL3hyQ9-WGlJUyo2A";


        given(reviewService.addReview(1L, "John", 3, "delicious")).willReturn(
                Review.builder()
                        .id(1234L)
                        .build());


        // 이름을 직접 안쓰고 Authorization 을 이름으로 인증 토큰으로 사용자 인증해서 바로 그사람이 리뷰 남기게끔 하기
        mvc.perform(post("/restaurants/1/reviews")
                .header("Authorization", "Bearer " + token)        // 인증토큰을 헤더로 넘겨주기
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"score\":3,\"description\":\"delicious\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1/reviews/1234"));


        verify(reviewService).addReview(eq(1L), eq("John"), eq(3), eq("delicious"));
    }



    @Test           // 내용이 올바르지 않은 경우
    public void createWithInvalidAttributes() throws Exception {
        mvc.perform(post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());


        // add never! 안되게하기
        verify(reviewService, never()).addReview(any(),any(),any(),any());
    }



}