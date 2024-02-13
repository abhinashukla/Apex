package com.example.apex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SearchDto {

    @JsonProperty(required = true)
    private String searchWord;
    private String searchedBy;
    private Date searchTime;
}
