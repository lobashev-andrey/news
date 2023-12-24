package com.example.news.exception;

public class UnautorizedAccessException extends Exception{

    public UnautorizedAccessException(String message) {
        super(message);
    }
}
