package kr.co.study.delivery.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private Long restaurantId;


    private String name;

    @NotNull    // ""나 null을 허용하지 않겠다는 어노테이션. 혹시 내용이 빠져서 ""로 들어온 경우도 @Valid로 걸러짐
    private Integer score;

    @NotEmpty
    private String description;




}
