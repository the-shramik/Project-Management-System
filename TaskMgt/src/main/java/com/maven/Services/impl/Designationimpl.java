package com.maven.Services.impl;

import com.maven.Model.Category;
import com.maven.Model.Designations;
import com.maven.Model.dtos.DtoDesignation;
import com.maven.Model.dtos.helper.DesignationDto;
import com.maven.Repository.ICategoryRepository;
import com.maven.Repository.IDesignationRepository;
import com.maven.Services.IDesignationsService;
import com.maven.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Designationimpl implements IDesignationsService {
    @Autowired
    private IDesignationRepository designationRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Designations adddesignations(DtoDesignation designations) {

        Designations d=new Designations();
        d.setName(designations.getName());
        d.setCategory(designations.getCategory());
        d.setDescription(designations.getDescription());
        d.setDate(LocalDate.now().toString());
        return designationRepository.save(d);
    }

    @Override
    public List<DesignationDto> getAllDesignations() {
        List<DesignationDto> designations=new ArrayList<>();
         designationRepository.findAll().forEach(designation -> {
               DesignationDto designationDto=new DesignationDto();
               designationDto.setName(designation.getName());
               designationDto.setId(designation.getId());
               designationDto.setDescription(designation.getDescription());

              Optional<Category> optionalCategory = categoryRepository.findById(designation.getCategory());

               if(optionalCategory.isPresent()) {
                   designationDto.setCategoryName(optionalCategory.get().getName());
               }
               else{
                   designationDto.setCategoryName(null);
               }
               designationDto.setDate(designation.getDate());
               designations.add(designationDto);
         });

         return designations;
    }

    @Override
    public Designations updateDesignation(DtoDesignation designations) throws ResourceNotFoundException {
        Designations existedDesignations=designationRepository.findById(designations.getId()).orElseThrow(
                ()->new ResourceNotFoundException("Designation is not present..!"));

        existedDesignations.setName(designations.getName());
        existedDesignations.setDescription(designations.getDescription());
        existedDesignations.setCategory(designations.getCategory());
        existedDesignations.setDate(designations.getDate());
        return designationRepository.save(existedDesignations);
    }

    @Override
    public String deleteDesignation(Long designationId) {

        designationRepository.deleteById(designationId);
        if(designationRepository.findById(designationId).isEmpty()){
            return "Designation deleted successfully..!";
        }
        else{
            return "Designation deletion failed..!";
        }
    }
}
