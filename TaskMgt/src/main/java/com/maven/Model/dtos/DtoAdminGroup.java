package com.maven.Model.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maven.Model.UserPermission;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoAdminGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String group_name;
    private String createdDate;
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "adminGroups")
    private List<UserPermission> userPermission;
}
