package com.maven.Model.dtos.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMemberDto {

    private Long id;
    private String name;
    private String email;
    private String contact;
    private String address;
    private String date;
    private byte[] image;
    private String designation;
}
