package kr.co.study.delivery.interfaces;


import kr.co.study.delivery.application.MenuItemService;
import kr.co.study.delivery.domain.MenuItem;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)                        // Springrunner로 테스트할거다!
@WebMvcTest(MenuItemController.class)            // restaurantController를 테스트할거다!
class MenuItemControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MenuItemService menuItemService;


    @Test
    public void list() throws Exception {

        List<MenuItem> menuItems = new ArrayList<>();
        
        // 메뉴 아이템에 김치 추가
        menuItems.add(MenuItem.builder().name("Kimchi").build());

        given(menuItemService.getMenuItems(1L)).willReturn(menuItems);

        mvc.perform(get("/restaurants/1/menuitems"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Kimchi")));
    }


    @Test
    public void bulkUpdate() throws Exception {           // 메뉴아이템 한 번에 여러개 업데이트

        mvc.perform(patch("/restaurants/1/menuitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]"))
                .andExpect(status().isOk());

        verify(menuItemService).bulkUpdate(eq(1L), any());

    }

}