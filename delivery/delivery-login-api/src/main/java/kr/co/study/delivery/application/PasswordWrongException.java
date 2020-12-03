package kr.co.study.delivery.application;

public class PasswordWrongException extends RuntimeException{

    PasswordWrongException(){
        super("Password is Wrong");

    }

}
