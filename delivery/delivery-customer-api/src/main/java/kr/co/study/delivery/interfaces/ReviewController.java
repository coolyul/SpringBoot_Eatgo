package kr.co.study.delivery.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.study.delivery.application.ReviewService;
import kr.co.study.delivery.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews") // created로 response받기위해  responseEntity<?> 형식을 써줌.
    public ResponseEntity<?> create(
            Authentication authentication,          // 인증된 정보 가져오기 위함
            @PathVariable("restaurantId") Long restaurantId,
            @Valid @RequestBody Review resource) throws URISyntaxException {


        // 이름 안쓰고 자동으로 가져오기 위해 claims를 가져오기
        Claims claims = (Claims) authentication.getPrincipal();

        // claims에서 이름 추출
        String name = claims.get("name", String.class);     // name을 String 타입으로 받아옴
        Integer score = resource.getScore();
        String description = resource.getDescription();

        Review review = reviewService.addReview(restaurantId, name, score, description);

        // /restaurants/레스토랑id/review/리뷰넘버
        String url = "/restaurants/" + restaurantId + "/reviews/" + review.getId();

        return ResponseEntity.created(new URI(url))
                .body("{}");
    }



    @GetMapping("/reviews")         // 전체 리뷰 조회
    public List<Review> list(){

        List<Review> reviews = reviewService.getReviews();

        return reviews;
    }
    
}
