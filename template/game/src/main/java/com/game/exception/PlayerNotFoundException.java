package com.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND) //404
public class PlayerNotFoundException extends Exception{
    public PlayerNotFoundException(String message) {
        super(message);
    }
}
