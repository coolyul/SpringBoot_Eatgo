package kr.co.study.delivery.interfaces;


import kr.co.study.delivery.application.RestaurantService;
import kr.co.study.delivery.domain.MenuItem;
import kr.co.study.delivery.domain.Restaurant;
import kr.co.study.delivery.domain.RestaurantNotFoundException;
import kr.co.study.delivery.domain.Review;
import kr.co.study.delivery.interfaces.RestaurantController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)                        // Springrunner로 테스트할거다!
@WebMvcTest(RestaurantController.class)            // restaurantController를 테스트할거다!
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean           // 가짜 객체로 바꿔주는 역할. 진짜대신 가짜를 불러서 테스트
    private RestaurantService restaurantService;


    // 컨트롤러에 우리가 원하는 객체를 주입할 수 있다. 의존성 주입! 테스트에는 SpyBean
//    @SpyBean(RestaurantRepositoryImpl.class)
//    private RestaurantRepository restaurantRepository;
//
//    @SpyBean(MenuItemRepositoryImpl.class)
//    private MenuItemRepository menuItemRepository;



    @Test
    public void list() throws Exception {           // 레스토랑 리스트를 가져오는 테스트

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .categoryId(1L)
                .name("JOKER House")
                .address("Seoul")
                .build());


        // restaurantService를 실행하면 레스토랑스를 리턴할것이다! 가짜 처리를 해주는 것
        given(restaurantService.getRestaurants("Seoul", 1L))
                .willReturn(restaurants);

        mvc.perform(get("/restaurants?region=Seoul&category=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")
                ));
    }



    @Test           // 레스토랑 디테일을 가져오는 테스트. 레스토랑 정보가 있을때! 리뷰도 같이
    public void detailWithExisted() throws Exception {

        // 빌더 패턴으로 객체 만들 때 의미 잘 드러나도록 생성해주기.
        // 레스토랑 정보 추가
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("JOKER House")
                .address("Seoul")
                .build();
        
        // 레스토랑의 메뉴 추가
        MenuItem menuItem = MenuItem.builder()
                .name("Kimchi")
                .build();

        // 레스토랑의 리뷰 추가
        Review review = Review.builder()
                .name("JOKER")
                .score(5)
                .description("Great!")
                .build();


        restaurant.setMenuItems(Arrays.asList(menuItem));        // 메뉴도 추가.리스트로

        restaurant.setReviews(Arrays.asList(review));           // 리뷰도 리스트로 추가



        // 레스토랑 추가가 잘 됐는지 레스토랑 아이디로 확인하기
        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);


        // 잘 들어갔는지 mvc 확인하는 코드
        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")
                ))
                .andExpect(content().string(
                        containsString("Kimchi")
                ))
                .andExpect(content().string(
                        containsString("Great!")
                ));

    }



    @Test           // 레스토랑 디테일을 가져오는 테스트. 레스토랑 정보가 없을때!
    public void detailWithNotExisted() throws Exception {

        given(restaurantService.getRestaurant(404L)).willThrow(new RestaurantNotFoundException(404L));

        // 404 id를 가진 레스토랑을 조회하지만 404는 없을때 에러나게 만들기
        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound())          // 요런 404 에러가 나오게끔 만들어주기
                .andExpect(content().string("{}")); // 정보 없을 때 {}를 화면에 나타내겠다
    }

}