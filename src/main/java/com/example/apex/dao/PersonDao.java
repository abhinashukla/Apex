package com.example.apex.dao;

import com.example.apex.dto.PersonStatsDto;
import com.example.apex.dto.PersonStatsInputDto;
import com.example.apex.entity.Person;
import com.example.apex.repository.PersonRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PersonDao {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EntityManager entityManager;

    public void savePerson(Person person){
        try {
            personRepository.save(person);
            return;
        }
        catch (Exception ex){
           ex.printStackTrace();
        }
    }

    public Person getPerson(int id) {
        try {
            if(personRepository.findById(id).isPresent()) {
                return personRepository.findById(id).get();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new Person();
    }


    public List<Person> getAllPerson() {
        try{
            if(!personRepository.findAll().isEmpty()){
                return personRepository.findAll();
            }
        } catch (NullPointerException ex){
            throw ex;
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public Long getPersonCount() {
        return (Long) entityManager.createNamedStoredProcedureQuery("Procedure 1").getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<String> getPersonNames() {
        return entityManager.createNamedStoredProcedureQuery("Procedure 2").getResultList();
    }


    public PersonStatsDto getPersonStats(PersonStatsInputDto input) {
        Session session = entityManager.unwrap(Session.class);
        return session.doReturningWork(connection -> {
            try (CallableStatement statement = connection.prepareCall("{call getPersonStats(?, ?)}")){
                statement.setString(1, input.getStateForCountOfPersonWithMoreThanOneAddress());
                statement.setString(2, input.getStateForNamesOfPersonWithMoreThanOneAddress());

                boolean hasResults = statement.execute();
                PersonStatsDto output = new PersonStatsDto();

                if (hasResults) {
                    try (ResultSet resultSet = statement.getResultSet()) {
                        if (resultSet.next()) {
                            output.setPersonCountWithMoreThanOneAddress(resultSet.getLong("personCount"));
                        }
                    }
                }
                if (statement.getMoreResults()) {
                    try (ResultSet resultSet = statement.getResultSet()) {
                        if (resultSet.next()) {
                            String names = resultSet.getObject("personNames", String.class);
                            String[] namesArray = names.split(",");
                            List<String> namesList = Arrays.asList(namesArray);
                            output.setPersonNamesWithTwoAddressInSameState(namesList);
                        }
                    }
                }
                if (statement.getMoreResults()) {
                    try (ResultSet resultSet = statement.getResultSet()) {
                        if (resultSet.next()) {
                            output.setPersonCountWithMoreThanOneAddressInGivenState(resultSet.getLong("personCountInGivenState"));
                        }
                    }
                }
                if (statement.getMoreResults()) {
                    try (ResultSet resultSet = statement.getResultSet()) {
                        if (resultSet.next()) {
                            String names = resultSet.getObject("personNamesInGivenState", String.class);
                            String[] namesArray = names.split(",");
                            List<String> namesList = Arrays.asList(namesArray);
                            output.setPersonNamesWithMoreThanOneAddressInGivenState(namesList);
                        }
                    }
                }

                return output;
            } catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
        });
    }



}
