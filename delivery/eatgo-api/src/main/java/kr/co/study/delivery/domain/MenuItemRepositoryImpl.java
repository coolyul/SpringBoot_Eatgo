package kr.co.study.delivery.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuItemRepositoryImpl implements MenuItemRepository{

    private List<MenuItem> menuItems = new ArrayList<>();

    // 메뉴 추가
    public MenuItemRepositoryImpl(){
        menuItems.add(new MenuItem("Kimchi"));
    }

    
    // 메뉴들 보여주기
    @Override
    public List<MenuItem> findAllByRestaurantId(Long restaurantId) {

        return menuItems;
    }
}
