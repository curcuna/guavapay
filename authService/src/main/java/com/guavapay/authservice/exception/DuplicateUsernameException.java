package com.guavapay.authservice.exception;

public class DuplicateUsernameException extends RuntimeException {

    public DuplicateUsernameException(String message){
        super(message);
    }

}
