package kr.co.study.delivery.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    // 레스토랑 전체검색
    List<Restaurant> findAll();

    // 레스토랑 ID로 검색.
    Optional<Restaurant> findById(Long id);

    Restaurant save(Restaurant restaurant);

}
