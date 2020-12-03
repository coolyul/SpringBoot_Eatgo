package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    public List<Restaurant> getRestaurants() {

        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }


    public Restaurant getRestaurant(Long id){

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        return restaurant;
    }



    public Restaurant addRestaurant(Restaurant restaurant) {

        return restaurantRepository.save(restaurant);
    }




    public Restaurant updateRestaurant(long id, String name, String address) {
        // TODO : update restaurant...

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElse(null);
        restaurant.updateInformation(name, address);

        return restaurant;
    }
}
