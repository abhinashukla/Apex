package com.example.apex.exception;

public class NoSearchesFound extends RuntimeException{

    public NoSearchesFound(String message) {
        super(message);
    }
}
