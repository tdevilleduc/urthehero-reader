package com.tdevilleduc.urthehero.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StoryNotFoundException extends RuntimeException {
    public StoryNotFoundException(String s) {
        super(s);
    }
}
