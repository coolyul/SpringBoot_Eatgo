package kr.co.study.delivery.domain;

import javax.persistence.Entity;
<<<<<<< HEAD
import javax.persistence.GeneratedValue;
=======
>>>>>>> 08493a3b07dcd63338889df4f5168ef4621dbce5
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {

    @Id
<<<<<<< HEAD
    @GeneratedValue
    private Long id;

    private String name;

=======
    private Long id;
    private String name;
>>>>>>> 08493a3b07dcd63338889df4f5168ef4621dbce5
    private String address;

    @Transient      // 이건 db에 저장하는 처리를 하지 않음. 임시로 처리하는거다
    private List<MenuItem> menuItems = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Restaurant(Long id, String name, String address){

        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getInformation() {
        return name + " in " + address;
    }

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void setMenuItem(List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems){         // menuItems 에서 가져와서 복사
            addMenuItem(menuItem);
        }
    }
}
