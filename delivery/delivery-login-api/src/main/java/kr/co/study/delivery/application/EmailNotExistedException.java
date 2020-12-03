package kr.co.study.delivery.application;

public class EmailNotExistedException extends RuntimeException{     // 이메일이 없는 경우 보내는 에러

    EmailNotExistedException(String email){

        super("Email is not registered" + email);       // 에러메세지 나타내고싶은거 표시

    }
}
