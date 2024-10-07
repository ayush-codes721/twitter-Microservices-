package com.twitter.follow_service.exception;

public class InvalidFollowRequestException extends RuntimeException{
    public InvalidFollowRequestException(String s) {
        super(s);
    }
}
