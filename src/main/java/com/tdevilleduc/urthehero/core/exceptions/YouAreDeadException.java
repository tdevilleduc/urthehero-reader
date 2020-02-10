package com.tdevilleduc.urthehero.core.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class YouAreDeadException extends RuntimeException {
    public YouAreDeadException(String s) {
        super(s);
    }
}
