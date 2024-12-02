package com.maven.Services;

import com.maven.Model.Designations;
import com.maven.Model.dtos.DtoDesignation;
import com.maven.Model.dtos.helper.DesignationDto;
import com.maven.exception.ResourceNotFoundException;

import java.util.List;

public interface IDesignationsService {
    Designations adddesignations(DtoDesignation designations);
    List<DesignationDto> getAllDesignations();

    Designations updateDesignation(DtoDesignation designations) throws ResourceNotFoundException;

    String deleteDesignation(Long designationId);
}
