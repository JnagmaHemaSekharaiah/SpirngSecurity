package com.dbauthentication.exception;


public class UserAlreadyExistsException extends RuntimeException {

    UserAlreadyExistsException (String message)
    {
        super(message);
    }


}
