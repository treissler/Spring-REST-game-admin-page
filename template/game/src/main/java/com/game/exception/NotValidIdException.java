package com.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST) //400
public class NotValidIdException extends Exception{
    public NotValidIdException(String message) {
        super(message);
    }
}
