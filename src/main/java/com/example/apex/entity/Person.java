package com.example.apex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Person")
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name = "Procedure 1", procedureName = "getPersonCount"),
        @NamedStoredProcedureQuery(name = "Procedure 2", procedureName = "getPersonName"),
        @NamedStoredProcedureQuery(name = "Procedure 3", procedureName = "getPersonStats",
        parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, name = "countForState", type = String.class),
                      @StoredProcedureParameter(mode = ParameterMode.IN, name = "namesForState", type = String.class)})})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String phoneNum;

    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "Person_Id", referencedColumnName = "id")
    private List<Address> addresses;
}
