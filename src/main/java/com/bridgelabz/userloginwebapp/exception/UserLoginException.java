package com.bridgelabz.userloginwebapp.exception;

public class UserLoginException extends Throwable {
    public enum ExceptionType{
        EMAIL_ID_NOT_PRESENT, LIMIT_EXCEEDED;
    }

    ExceptionType type;

    public UserLoginException(String message, ExceptionType type){
        super(message);
        this.type = type;
    }
}
