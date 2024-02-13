package com.example.apex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @org.springframework.web.bind.annotation.ExceptionHandler(NoOccurencePresent.class)
    public Map<String, String> handleNoOccurencePresent(NoOccurencePresent ex){

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", ex.getMessage());

        return errorMap;
    }

    @ResponseStatus(HttpStatus.OK)
    @org.springframework.web.bind.annotation.ExceptionHandler(NoFilePresent.class)
    public Map<String, String> handleNoFilePresent(NoFilePresent ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", ex.getMessage());

        return errorMap;
    }

    @ResponseStatus(HttpStatus.OK)
    @org.springframework.web.bind.annotation.ExceptionHandler(NoSearchesFound.class)
    public Map<String, String> handleNoSearchesFound(NoFilePresent ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", ex.getMessage());

        return errorMap;
    }

}
