package com.example.apex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonStatsDto {

    private Long personCountWithMoreThanOneAddress;
    private List<String> personNamesWithTwoAddressInSameState;
    private Long personCountWithMoreThanOneAddressInGivenState;
    private List<String> personNamesWithMoreThanOneAddressInGivenState;


}
