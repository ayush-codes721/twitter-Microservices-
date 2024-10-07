package com.twitter.like_service.exceptions;

public class LikeNotFoundException extends RuntimeException {
    public LikeNotFoundException(String s) {
        super(s);
    }
}
