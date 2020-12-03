package kr.co.study.delivery.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

    // 리뷰 전부 찾기
    List<Review> findAll();

    
    // 아이디별 리뷰 찾기
    List<Review> findAllByRestaurantId(long restaurantId);


    // 리뷰 저장 
    Review save(Review review);

}
