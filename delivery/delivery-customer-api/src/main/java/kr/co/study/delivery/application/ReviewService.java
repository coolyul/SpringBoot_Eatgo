package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.Review;
import kr.co.study.delivery.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Long restaurantId, String name, Integer score, String description) {

        Review review = Review.builder()
                .restaurantId(restaurantId)
                .name(name)
                .score(score)
                .description(description).build();

        review.setRestaurantId(restaurantId);
        return reviewRepository.save(review);

    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }
}
