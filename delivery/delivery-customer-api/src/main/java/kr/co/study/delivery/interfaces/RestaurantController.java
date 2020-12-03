package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.application.RestaurantService;
import kr.co.study.delivery.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {         // interfaces는 사용자 화면에 보여질 내용을 만드는 패키지!

    @Autowired
    private RestaurantService restaurantService;



    // 레스토랑의 리스트를 얻어오는 api
    @GetMapping("/restaurants")         // 파라미터로 지역 받아주기!
    public List<Restaurant> list(
            @RequestParam("region") String region,
            @RequestParam("category") Long categoryId){

        List<Restaurant> restaurants = restaurantService.getRestaurants(region, categoryId);

        return restaurants;
    }




    // 가게 상세정보 얻어오는 api
    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){      // parameter에서 받아오는 것이므로 @PathVariable

        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
    }



}
