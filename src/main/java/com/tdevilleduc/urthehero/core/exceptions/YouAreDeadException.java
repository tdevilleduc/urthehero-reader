package com.tdevilleduc.urthehero.core.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
public class YouAreDeadException extends RuntimeException {
    public YouAreDeadException(String s) {
        super(s);
    }
}
