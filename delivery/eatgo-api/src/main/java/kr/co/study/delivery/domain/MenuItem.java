package kr.co.study.delivery.domain;

<<<<<<< HEAD
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue
=======
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuItem {

    @Id
>>>>>>> 08493a3b07dcd63338889df4f5168ef4621dbce5
    private Long id;

    private Long restaurantId;

<<<<<<< HEAD
    private String name;
=======
    private final String name;
>>>>>>> 08493a3b07dcd63338889df4f5168ef4621dbce5

    public MenuItem(String name) {
        this.name = name;
    }

<<<<<<< HEAD

=======
>>>>>>> 08493a3b07dcd63338889df4f5168ef4621dbce5
    public String getName(){
        return name;
    }
}
