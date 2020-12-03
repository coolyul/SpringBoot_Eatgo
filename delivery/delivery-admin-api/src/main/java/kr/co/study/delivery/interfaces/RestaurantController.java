package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.application.RestaurantService;
import kr.co.study.delivery.domain.MenuItem;
import kr.co.study.delivery.domain.MenuItemRepository;
import kr.co.study.delivery.domain.Restaurant;
import kr.co.study.delivery.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
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

        return restaurant;
    }



    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant resource) throws URISyntaxException {
        // created됐는지 201번 돌려주기위해 responseEntity<?>를 씀
        // @Valid 추가하면 유효한지 검사하고나서 맨 밑의 created를 주게 됨. 검사해라!

        String name = resource.getName();
        String address = resource.getAddress();

        Restaurant restaurant = restaurantService.addRestaurant(
                Restaurant.builder()
                    .name(resource.getName())
                    .address(resource.getAddress())
                    .build());

        URI location = new URI("/restaurants/" + restaurant.getId());        // 추가되는 위치.

        // created가 됐는지 201번으로 확인하기 위해서 Response의 상태가 location에 created됐는지 확인하는 리턴.
        return ResponseEntity.created(location).body("{}");
    }


    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @RequestBody Restaurant restaurant){
        // @Valid를 써주면 들어온 정보가 유효한지를 체크한 다음에 진행하게 된다

       restaurantService.updateRestaurant(id, "JOKER Bar", "Busan");

        return "{}";
    }

}
