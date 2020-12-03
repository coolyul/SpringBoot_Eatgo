package kr.co.study.delivery.domain;

public class RestaurantNotFoundException extends RuntimeException {     // 레스토랑 없을 때 돌려줄 클래스 만들어주기.


    public RestaurantNotFoundException(long id) {
        super("Could Not Find Restaurant " + id);           // 에러 메세지를 만들어주기!
    }
}
