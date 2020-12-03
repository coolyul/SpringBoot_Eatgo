package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.application.EmailNotExistedException;
import kr.co.study.delivery.application.PasswordWrongException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice           // 요건 Exception을 처리하기 위한 컨트롤러! try-catch도 있지만 이런 방법도 있다.
public class SessionErrorAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)               // 바로 400 에러를 돌려주게 함.
    @ExceptionHandler(EmailNotExistedException.class)    // 예외를 처리할 핸들러 클래스 지정
    public String handleEmailNotExisted(){
        return "{}";

    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)               // 바로 400 에러를 돌려주게 함.
    @ExceptionHandler(PasswordWrongException.class)    // 예외를 처리할 핸들러 클래스 지정
    public String handleNotFound(){
        return "{}";

    }
}
