package com.example.apex.controller;

import com.example.apex.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RestControllerAdvice
public class AddressController {

    @Autowired
    private AddressService addressService;
}
