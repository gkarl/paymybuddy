package com.payMyBuddy.pay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Parameters to save user are invalid")
public class NotCreateUserPossibleException extends Exception{
    public NotCreateUserPossibleException(String message) {
        super(message);
    }
}
