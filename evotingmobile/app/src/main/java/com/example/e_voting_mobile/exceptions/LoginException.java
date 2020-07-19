package com.example.e_voting_mobile.exceptions;


public class LoginException extends Exception {
    String cause;

    public LoginException() {

    }

    public LoginException(String exceptionMessage, String cause) {
        super(exceptionMessage);
        this.cause = cause;
    }


    public String getExceptionCause() {
        return cause;
    }
}
