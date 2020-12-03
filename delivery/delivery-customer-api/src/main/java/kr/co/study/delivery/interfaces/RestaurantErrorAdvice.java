package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.domain.RestaurantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice           // 요건 Exception을 처리하기 위한 컨트롤러! try-catch도 있지만 이런 방법도 있다.
public class RestaurantErrorAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)               // 바로 404 NotFound를 돌려주게 함.
    @ExceptionHandler(RestaurantNotFoundException.class)    // 예외를 처리할 핸들러 클래스 지정
    public String handleNotFound(){
        return "{}";

    }
}
