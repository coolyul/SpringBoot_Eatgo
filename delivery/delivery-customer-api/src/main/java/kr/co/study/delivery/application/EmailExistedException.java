package kr.co.study.delivery.application;

public class EmailExistedException extends RuntimeException{

    EmailExistedException(String email){
        super("Email is already registered" + email);       // 에러메세지 나타내고싶은거 표시
    }

}
