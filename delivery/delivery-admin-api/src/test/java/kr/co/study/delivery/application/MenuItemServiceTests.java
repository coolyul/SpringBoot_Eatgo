package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.MenuItem;
import kr.co.study.delivery.domain.MenuItemRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
class MenuItemServiceTests {

    @InjectMocks
    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Before
    public void setUp(){
        // Mock 초기화하기위함.
        MockitoAnnotations.openMocks(this);

        menuItemService = new MenuItemService(menuItemRepository);
    }

    @Test
    public void getMenuItems(){

        // 메뉴아이템 임시저장소 초기화해주기
        List<MenuItem> mockMenuItems = new ArrayList<>();

        // 일단 가짜 메뉴아이템을 생성해주기
        mockMenuItems.add(MenuItem.builder()
                .name("Kimchi")
                .build());

        // 우리가 뭘 해줄거냐면 메뉴아이템 레포에서 레스토랑 아이디 1004의 메뉴 다 요청하면 메뉴아이템들 돌려줄거다
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(mockMenuItems);

        // 1004 id의 레스토랑 아이템 메뉴들 가져오기
        List<MenuItem> menuItems = menuItemService.getMenuItems(1004L);

        // 메뉴 아이템의 1번 메뉴
        MenuItem menuItem = menuItems.get(0);
        assertThat(menuItem.getName()).isEqualTo("Kimchi");

    }





    @Test
    public void bulkUpdate(){
        List<MenuItem> menuItems = new ArrayList<MenuItem>();

        // 새로운 메뉴 추가
        menuItems.add(MenuItem.builder()
                .name("Kimchi")
                .build());
        
        // 메뉴 수정
        menuItems.add(MenuItem.builder()
                .id(12L)
                .name("Gukbob")
                .build());
        
        // 메뉴 삭제
        menuItems.add(MenuItem.builder()
                .id(1004L)
                .destroy(true)
                .build());

        menuItemService.bulkUpdate(1L, menuItems);

        // 메뉴 두개 추가할거라서 times(2) 로 두번 실행하게 함, 1번의 삭제도 같이.
        verify(menuItemRepository, times(2)).save(any());
        verify(menuItemRepository, times(1)).deleteById(eq(1004L));
    }

}