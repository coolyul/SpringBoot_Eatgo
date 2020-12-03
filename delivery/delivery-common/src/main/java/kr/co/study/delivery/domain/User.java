package kr.co.study.delivery.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @NotEmpty
    private String email;

    @Setter
    @NotEmpty
    private String name;

    @Setter
    @NotNull
    private Long level;

    private String password;


    private Long restaurantId;

    // 관리자 권한이 있나?!
    public boolean isAdmin() {
        return level>=100;
    }

    // 활성화 되어있는 계정인가??
    public boolean isActive() {
        return level > 0;       // level 0보다 클 때만 active가 true 가 됨. 0 이면 deactive 상태!
    }

    // 비활성화하기
    public void deactive() {
        level = 0L;
    }


    public void setRestaurantId(Long restaurantId){
        this.level = 50L;
        this.restaurantId = restaurantId;
    }

    public boolean isRestaurantOwner() {
        return level == 50L;
    }


}
