package com.maven.Model.dtos.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductivityDto {

    private Long id;
    private  String productivityName;
    private  String projectName;
    private  Long duration;
    private  String status;
    private  String startDate;
    private String endDate;
}
