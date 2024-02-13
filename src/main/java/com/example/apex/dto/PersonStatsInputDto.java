package com.example.apex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonStatsInputDto {

    private String stateForCountOfPersonWithMoreThanOneAddress;
    private String stateForNamesOfPersonWithMoreThanOneAddress;
}
