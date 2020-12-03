package kr.co.study.delivery.interfaces;


import kr.co.study.delivery.application.MenuItemService;
import kr.co.study.delivery.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(
            @PathVariable("restaurantId") Long restaurantId,
            @RequestBody List<MenuItem> menuItems){    // 입력요청 받아서 메뉴아이템 업뎃해줌

        menuItemService.bulkUpdate(restaurantId, menuItems);

        return "";
    }

}
