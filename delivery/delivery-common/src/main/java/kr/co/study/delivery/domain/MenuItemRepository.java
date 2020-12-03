package kr.co.study.delivery.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    List<MenuItem> findAllByRestaurantId(Long restaurantId);

    void deleteById(Long id);

}
