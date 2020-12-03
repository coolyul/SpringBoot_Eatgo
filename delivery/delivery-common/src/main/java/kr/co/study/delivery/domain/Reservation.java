package kr.co.study.delivery.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long restaurantId;

    private String name;


    @Setter
    @NotEmpty
    private String date;

    @Setter
    @NotEmpty
    private String time;

    @Setter
    @NotNull
    @Min(1)
    @Max(25)
    private Integer partySize;

}
