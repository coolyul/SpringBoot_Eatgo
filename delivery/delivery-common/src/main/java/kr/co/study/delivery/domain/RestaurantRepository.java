package kr.co.study.delivery.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    // 레스토랑 전체검색
    List<Restaurant> findAll();

    
    // 지역과 카테고리 번호로 검색
    List<Restaurant> findAllByAddressContainingAndCategoryId(String region, Long categoryId);


    // 레스토랑 ID로 검색.
    Optional<Restaurant> findById(Long id);


    // 레스토랑 저장
    Restaurant save(Restaurant restaurant);

}
