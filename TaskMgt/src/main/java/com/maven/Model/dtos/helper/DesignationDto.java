package com.maven.Model.dtos.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesignationDto {

    private Long id;
    private String name;
    private String description;
    private String date;
    private String categoryName;
}
