package kr.co.study.delivery.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private Long restaurantId;

    private String name;

    @Transient      // db 에 넣지 않음.
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)       // JSon에는 false가 아닐때만 보이게 넣어주세요. default=false
    private boolean destroy;

}
