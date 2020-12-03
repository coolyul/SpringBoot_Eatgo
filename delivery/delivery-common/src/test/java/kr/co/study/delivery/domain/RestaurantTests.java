package kr.co.study.delivery.domain;

import org.junit.jupiter.api.Test;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RestaurantTests {

    @Test
    public void creation(){

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        assertThat(restaurant.getName(), is("Bob zip"));
        assertThat(restaurant.getAddress(), is("Seoul"));
        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void information(){
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        assertThat(restaurant.getInformation(), is ("Bob zip in Seoul"));
    }

}