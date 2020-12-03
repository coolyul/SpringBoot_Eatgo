package kr.co.study.delivery.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue
    @Setter
    private Long id;

    @NotNull           // 꼭 같이 넣어서 만들도록 강제
    private Long categoryId;


    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    @Transient      // 이건 db에 저장하는 처리를 하지 않음. 임시로 처리하는거다
    @JsonInclude(JsonInclude.Include.NON_NULL)   // null이 아닐 때만 처리를 해줘라
    private List<MenuItem> menuItems;


    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)  // null이 아닐 때만 처리를 해줘라
    private List<Review> reviews;


    public String getInformation() {
        return name + " in " + address;
    }


    public void updateInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }


    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);        // menuItems 에서 가져와서 복사
    }


    public void setReviews(List<Review> reviews) {
        this.reviews = new ArrayList<>(reviews);
    }
}
