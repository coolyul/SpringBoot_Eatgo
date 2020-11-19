package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.application.RestaurantService;
import kr.co.study.delivery.domain.MenuItem;
import kr.co.study.delivery.domain.MenuItemRepository;
import kr.co.study.delivery.domain.Restaurant;
import kr.co.study.delivery.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {         // interfaces는 사용자 화면에 보여질 내용을 만드는 패키지!

    @Autowired
    private RestaurantService restaurantService;

    // 레스토랑의 리스트를 얻어오는 api
    @GetMapping("/restaurants")
    public List<Restaurant> list(){

        List<Restaurant> restaurants = restaurantService.getRestaurants();

        return restaurants;
    }




    // 가게 상세정보 얻어오는 api
    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){      // parameter에서 받아오는 것이므로 @PathVariable

        Restaurant restaurant = restaurantService.getRestaurant(id);
//        Restaurant restaurant = restaurantRepository.findById(id);

//        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
//        restaurant.setMenuItem(menuItems);

        return restaurant;
    }

}
