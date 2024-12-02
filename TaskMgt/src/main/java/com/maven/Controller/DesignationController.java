package com.maven.Controller;

import com.maven.Model.Designations;
import com.maven.Model.dtos.DtoDesignation;
import com.maven.Repository.ICategoryRepository;
import com.maven.Repository.IDesignationRepository;
import com.maven.Services.IDesignationsService;
import com.maven.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/designation")
@CrossOrigin("*")
public class DesignationController {

    @Autowired
    private IDesignationsService designationsService;

    @Autowired
    private ICategoryRepository categoryRepository;

    @PostMapping("/addDesignation")
    public ResponseEntity<?> addDesignations(@RequestBody DtoDesignation designations){
        return ResponseEntity.ok(designationsService.adddesignations(designations));
    }

    @GetMapping("/getAllDesignations")
    public ResponseEntity<?> getAllDesignations() {
        return ResponseEntity.ok(designationsService.getAllDesignations());
    }

    @PatchMapping("/updateDesignation")
    public ResponseEntity<?> updateDesignation(@RequestBody DtoDesignation designations) throws ResourceNotFoundException {
       return ResponseEntity.ok(designationsService.updateDesignation(designations));
    }

    @DeleteMapping("/deleteDesignation")
    public ResponseEntity<?> deleteDesignation(@RequestParam Long designationId){
         return ResponseEntity.ok(designationsService.deleteDesignation(designationId));
    }

}
