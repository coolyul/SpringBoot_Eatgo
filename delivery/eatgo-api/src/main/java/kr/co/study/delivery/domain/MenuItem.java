package kr.co.study.delivery.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuItem {

    @Id
    private Long id;

    private Long restaurantId;

    private final String name;

    public MenuItem(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
