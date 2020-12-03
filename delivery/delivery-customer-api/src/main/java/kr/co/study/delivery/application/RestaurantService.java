package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private MenuItemRepository menuItemRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository, ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.reviewRepository = reviewRepository;
    }



    public List<Restaurant> getRestaurants(String region, long categoryId) {
        //TODO : categoryId 사용!


        List<Restaurant> restaurants = restaurantRepository.findAllByAddressContainingAndCategoryId(
                region, categoryId);
        return restaurants;
    }



    public Restaurant getRestaurant(Long id){

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        // 레스토랑 아이디별로 메뉴들 찾아오기
        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        List<Review> reviews = reviewRepository.findAllByRestaurantId(id);
        restaurant.setReviews(reviews);

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
