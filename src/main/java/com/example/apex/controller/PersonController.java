package com.example.apex.controller;

import com.example.apex.dto.PersonStatsDto;
import com.example.apex.dto.PersonStatsInputDto;
import com.example.apex.entity.Person;
import com.example.apex.service.PersonService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RestControllerAdvice
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/create-person")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        return new ResponseEntity(personService.createPerson(person), HttpStatus.OK);
    }

    @GetMapping("/get-person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable int id){
        return new ResponseEntity(personService.getPerson(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Person>> getAllPerson(){
        return new ResponseEntity(personService.getAllPerson(), HttpStatus.OK);
    }

    @GetMapping("/get-count")
    public ResponseEntity<Long> getPersonCount(){
        return new ResponseEntity(personService.getPersonCount(), HttpStatus.OK);
    }

    @GetMapping("/get-names")
    public ResponseEntity<List<String>> getPersonNames(){
        return new ResponseEntity(personService.getPersonNames(), HttpStatus.OK);
    }

    @PostMapping("/get-person-stats")
    public ResponseEntity<PersonStatsDto> getPersonStats(@RequestBody PersonStatsInputDto input){
        return new ResponseEntity(personService.getPersonStats(input), HttpStatus.OK);
    }


}
