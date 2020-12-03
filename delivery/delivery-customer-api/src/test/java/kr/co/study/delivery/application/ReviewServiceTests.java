package kr.co.study.delivery.application;

import kr.co.study.delivery.application.ReviewService;
import kr.co.study.delivery.domain.Review;
import kr.co.study.delivery.domain.ReviewRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
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
    public void addReview(){

        reviewService.addReview(1004L, "JOKER", 3, "Delicious");

        verify(reviewRepository).save(any());
    }

}