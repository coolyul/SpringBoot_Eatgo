package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.application.RestaurantService;
import kr.co.study.delivery.domain.MenuItemRepository;
import kr.co.study.delivery.domain.MenuItemRepositoryImpl;
import kr.co.study.delivery.domain.RestaurantRepository;
import kr.co.study.delivery.domain.RestaurantRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)                        // Springrunner로 테스트할거다!
@WebMvcTest(RestaurantController.class)            // restaurantController를 테스트할거다!
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @SpyBean(RestaurantService.class)       // 컨트롤러에 우리가 원하는 객체를 주입할 수 있다. 의존성 주입! 테스트에는 SpyBean
    private RestaurantService restaurantService;

    @SpyBean(RestaurantRepositoryImpl.class)
    private RestaurantRepository restaurantRepository;

    @SpyBean(MenuItemRepositoryImpl.class)
    private MenuItemRepository menuItemRepository;



    @Test
    public void list() throws Exception {           // 레스토랑 리스트를 가져오는 테스트
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
        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
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


}