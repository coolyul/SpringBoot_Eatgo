package kr.co.study.delivery.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTests {

    @Test
    public void creation(){
        User user = User.builder()
                .email("tester@example.com")
                .name("tester")
                .level(100L)
                .build();

        assertThat(user.getName()).isEqualTo("tester");
        assertThat(user.isAdmin()).isEqualTo(true);
        assertThat(user.isActive()).isTrue();

        // 유저 비활성화
        user.deactive();

        assertThat(user.isActive()).isFalse();
    }

    
    @Test
    public void restaurantOwner(){      // owner인지 확인하는 테스트
        User user = User.builder()
                .level(1L)
                .build();

        assertThat(user.isRestaurantOwner()).isFalse();

        user.setRestaurantId(1004L);

        assertThat(user.isRestaurantOwner()).isTrue();

    }

}