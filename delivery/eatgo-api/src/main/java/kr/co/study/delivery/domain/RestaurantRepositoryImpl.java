package kr.co.study.delivery.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {
    
    // 리스트로 레스토랑들을 다 컬렉션으로 모아준 다음 거기서 id로 찾아오게 만들기!
    private List<Restaurant> restaurants = new ArrayList<>();
    

    // 레스토랑 정보들 리스트에 저장
    public RestaurantRepositoryImpl(){
        restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));
        restaurants.add(new Restaurant(2020L, "Cyber food", "Seoul"));
    }



    // 레스토랑 전체검색
    @Override
    public List<Restaurant> findAll() {
        return restaurants;
    }


    
    // 레스토랑 ID로 검색
    @Override
    public Restaurant findById(Long id) {

        return restaurants.stream()
                .filter(r -> r.getId().equals(id))          // 아이디가 같은 걸 필터링해서 찾기
                .findFirst()                                // 찾은 것 중 첫번째거
                .orElse(null);
    }
}
