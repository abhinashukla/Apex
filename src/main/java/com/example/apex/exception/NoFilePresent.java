package com.example.apex.exception;

public class NoFilePresent extends RuntimeException{

    public NoFilePresent(String message) {
        super(message);
    }
}
