package com.example.apex.service;

import com.example.apex.dao.PersonDao;
import com.example.apex.dto.PersonStatsDto;
import com.example.apex.dto.PersonStatsInputDto;
import com.example.apex.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;

    public Person createPerson(Person person) {
        personDao.savePerson(person);
        return person;
    }

    public Person getPerson(int id) {
        Person person = personDao.getPerson(id);
        return person;
    }

    public Long getPersonCount() {
        return personDao.getPersonCount();
    }

    public List<String> getPersonNames() {
        return personDao.getPersonNames();
    }

    public List<Person> getAllPerson() {
        return personDao.getAllPerson();
    }

    public PersonStatsDto getPersonStats(PersonStatsInputDto input) {
        return personDao.getPersonStats(input);
    }
}
