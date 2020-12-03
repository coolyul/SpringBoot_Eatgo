package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.MenuItem;
import kr.co.study.delivery.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemsRepository;


    public MenuItemService(MenuItemRepository menuItemRepository){
        this.menuItemsRepository = menuItemRepository;
    }


    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {      // 아이템들이 리스트형태가 되어야 함

        for(MenuItem menuItem : menuItems){
            update(restaurantId, menuItem);

        }

    }

    // 삭제의 경우 포함
    private void update(Long restaurantId, MenuItem menuItem) {
        if(menuItem.isDestroy()){           // destroy가 true이면 삭제
            // TODO:delete
            menuItemsRepository.deleteById(menuItem.getId());
            return;
        }

        // destroy 아닌 경우 update
        menuItem.setRestaurantId(restaurantId);
        menuItemsRepository.save(menuItem);
    }
}
