package com.mk.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
public class Student {

    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("clasS")
    private Integer clasS;
    @JsonProperty("address")
    private String address;
    @JsonProperty("marks")
    private Map<String,Integer> marks;
    @JsonProperty("totalMarks")
    private Integer totalMarks;
    @JsonProperty("percentage")
    private Double percentage;
}
