package kr.co.study.delivery.interfaces;


import kr.co.study.delivery.application.MenuItemService;
import kr.co.study.delivery.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;


    @GetMapping("/restaurants/{restaurantId}/menuitems")        // 메뉴 아이템 리스트 가져오기! 
    public List<MenuItem> list(@PathVariable Long restaurantId) {       // 레스토랑 id받아서 검색
        List<MenuItem> menuItems = menuItemService.getMenuItems(restaurantId);

        return menuItems;
    }



    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(
            @PathVariable("restaurantId") Long restaurantId,
            @RequestBody List<MenuItem> menuItems){    // 입력요청 받아서 메뉴아이템 업뎃해줌

        menuItemService.bulkUpdate(restaurantId, menuItems);

        return "";
    }

}
