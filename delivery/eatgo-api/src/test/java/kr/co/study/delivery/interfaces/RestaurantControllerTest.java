package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.application.RestaurantService;
import kr.co.study.delivery.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));


        // restaurantService를 실행하면 레스토랑스를 리턴할것이다! 가짜 처리를 해주는 것
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ));
    }



    @Test
    public void detail() throws Exception {                       // 레스토랑 디테일을 가져오는 테스트

        Restaurant restaurant1 = new Restaurant(1004L, "Joker House", "Seoul");
        restaurant1.addMenuItem(new MenuItem("Kimchi"));

        Restaurant restaurant2 = new Restaurant(2020L, "Cyber food", "Seoul");


        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Joker House\"")
                ))
        .andExpect(content().string(
                containsString("Kimchi")
        ));

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber food\"")
                ));
    }


    @Test
    public void create() throws Exception {

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"BeRyong\",\"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"));

        // addRestaurant에 뭘 넣었던지 잘 넣기만 하면 나오게 함. any()
         verify(restaurantService).addRestaurant(any());
    }

}