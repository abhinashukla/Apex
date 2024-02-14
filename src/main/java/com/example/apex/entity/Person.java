package com.example.apex.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

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

    @NotEmpty
    private String name;

    @Email
    @NotEmpty
//    @NaturalId
    private String email;

//    @NaturalId
//    @Size(min = 10, max = 10, message = "Phone Number must be 10 characters")
    private String phoneNum;

//    @Valid
    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "Person_Id", referencedColumnName = "id")
    private List<Address> addresses;
}
