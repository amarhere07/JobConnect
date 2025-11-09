package com.jobconnect.jobconnect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDto {
    private String title;
    private String company;
    private String location;
    private String type;
    private String salary;
    private String summary;
    private String description;
}
