package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.application.ReviewService;
import kr.co.study.delivery.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;



    @GetMapping("/reviews")         // 전체 리뷰 관리
    public List<Review> list(){

        List<Review> reviews = reviewService.getReviews();

        return reviews;
    }



    @PostMapping("/restaurants/{restaurantId}/reviews") // created로 response받기위해  responseEntity<?> 형식을 써줌.
    public ResponseEntity<?> create(
            @PathVariable("restaurantId") Long restaurantId,
            @Valid @RequestBody Review resource) throws URISyntaxException {

        Review review = reviewService.addReview(restaurantId, resource);

        // /restaurants/레스토랑id/review/리뷰넘버
        String url = "/restaurants/" + restaurantId + "/reviews/" + review.getId();

        return ResponseEntity.created(new URI(url))
                .body("{}");
    }
}
