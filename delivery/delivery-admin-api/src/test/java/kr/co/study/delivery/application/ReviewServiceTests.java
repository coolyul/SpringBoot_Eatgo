package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.Review;
import kr.co.study.delivery.domain.ReviewRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
class ReviewServiceTests {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp(){
        //Mock 초기화
        MockitoAnnotations.openMocks(this);

        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void getReviews(){

        List<Review> mockReviews = new ArrayList<>();
        mockReviews.add(Review.builder()
                .description("Cool!")
                .build());

        given(reviewRepository.findAll()).willReturn(mockReviews);

        List<Review> reviews =  reviewService.getReviews();

        Review review = reviews.get(0);

        assertThat(review.getDescription(), is("Cool!"));

    }



}