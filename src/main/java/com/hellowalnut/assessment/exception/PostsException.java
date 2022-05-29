package com.hellowalnut.assessment.exception;

public class PostsException extends RuntimeException {

    static final long serialVersionUID = 1L;

    public PostsException(String message) {
        super(message);
    }
}
