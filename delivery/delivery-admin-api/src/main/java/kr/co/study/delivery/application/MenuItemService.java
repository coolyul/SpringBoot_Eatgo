package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.MenuItem;
import kr.co.study.delivery.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;


    public MenuItemService(MenuItemRepository menuItemRepository){
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getMenuItems(Long restaurantId){

        // 레스토랑 아이디로 메뉴아이템 저장소에서 검색해오기
        return menuItemRepository.findAllByRestaurantId(restaurantId);

    }



    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {      // 아이템들이 리스트형태가 되어야 함

        for(MenuItem menuItem : menuItems){
            update(restaurantId, menuItem);

        }

    }

    // 삭제의 경우 포함
    private void update(Long restaurantId, MenuItem menuItem) {

        if(menuItem.isDestroy()){           // destroy가 true이면 삭제

            menuItemRepository.deleteById(menuItem.getId());
            return;
        }

        // destroy 아닌 경우 update
        menuItem.setRestaurantId(restaurantId);
        menuItemRepository.save(menuItem);
    }
}
