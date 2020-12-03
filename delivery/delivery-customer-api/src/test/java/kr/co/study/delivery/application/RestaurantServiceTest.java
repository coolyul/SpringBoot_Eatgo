package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp(){

        MockitoAnnotations.openMocks(this); //@Mock 이 붙어있는거 초기화

        mockRestaurantRepository();
        mockMenuItemRepository();
        mockReviewRepository();

        restaurantService = new RestaurantService(
                restaurantRepository, menuItemRepository, reviewRepository);
    }


    // 레스토랑정보 임시 저장소
    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .categoryId(1L)
                .name("Bob zip")
                .address("Seoul")
                .build();
        restaurants.add(restaurant);

        // 지역이 Seoul 인거, 카테고리가 1번인거 찾기
        given(restaurantRepository.findAllByAddressContainingAndCategoryId("Seoul", 1L))
                .willReturn(restaurants);

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

    }



    // 메뉴아이템 임시 저장소
    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder()
                .name("Kimchi")
                .build());

        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
    }



    // 리뷰 임시 저장소
    private void mockReviewRepository() {

        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder()
                .name("BeRyong")
                .score(1)
                .description("Bad")
                .build());

        given(reviewRepository.findAllByRestaurantId(1004L))
                .willReturn(reviews);

    }


    @Test
    public void getRestaurants(){
        String region = "Seoul";
        Long categoryId = 1L;

        List<Restaurant> restaurants = restaurantService.getRestaurants(region, categoryId);

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(1004L));
    }



    @Test
    public void getRestaurantWithExisted(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
        verify(reviewRepository).findAllByRestaurantId(eq(1004L));

        assertThat(restaurant.getId(), is(1004L));

        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertThat(menuItem.getName(), is("Kimchi"));

        Review review = restaurant.getReviews().get(0);
        assertThat(review.getDescription(), is("Bad"));
    }



    @Test(expected = RestaurantNotFoundException.class)     // 요청 발생만해도 NotFoundException으로 나오게하겠다
    public void getRestaurantNotExisted(){
        Restaurant restaurant = restaurantService.getRestaurant(404L);

    }





    @Test
    public void addRestaurant(){
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .name("BeRyong")
                .address("Busan")
                .build();


        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertThat(created.getId(), is(1234L));
    }


    @Test
    public void updateRestaurant(){

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));

        Restaurant updated = restaurantService.updateRestaurant(1004L, "Sool zip", "Busan");

        assertThat(updated.getName(), is("Sool zip"));
        assertThat(updated.getAddress(), is("Busan"));
    }

}