package kr.co.study.delivery.domain;

import java.util.List;

public interface RestaurantRepository {
    // 레스토랑 전체검색
    List<Restaurant> findAll();

    // 레스토랑 ID로 검색
    Restaurant findById(Long id);
}
