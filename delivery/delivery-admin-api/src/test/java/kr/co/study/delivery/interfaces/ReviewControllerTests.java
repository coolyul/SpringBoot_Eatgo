package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.application.ReviewService;
import kr.co.study.delivery.domain.Review;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
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


    @Test           // 리뷰 리스트 확인하기 위한 테스트
    public void list() throws Exception {

        List<Review> reviews = new ArrayList<>();

        // 리뷰 추가해주기 
        reviews.add(Review.builder()
                .description("Cool!")
                .build());

        given(reviewService.getReviews()).willReturn(reviews);

        // 리뷰들 중에 Cool!이라는 멘트 들어간 리뷰 있는지 확인해주기
        mvc.perform(get("/reviews"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Cool!")));

    }
}