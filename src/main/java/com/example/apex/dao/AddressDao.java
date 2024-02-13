package com.example.apex.dao;

import com.example.apex.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao {

    @Autowired
    private AddressRepository addressRepository;
}
