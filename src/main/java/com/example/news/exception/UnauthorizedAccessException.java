package com.example.news.exception;

public class UnauthorizedAccessException extends Exception{

    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
