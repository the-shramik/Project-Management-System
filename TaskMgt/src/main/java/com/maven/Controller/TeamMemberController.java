package com.maven.Controller;

import com.maven.Model.TeamMember;
import com.maven.Repository.IDesignationRepository;
import com.maven.Services.ITeamMemberService;
import com.maven.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/teammember")
@CrossOrigin("*")
public class TeamMemberController {

    @Autowired
    private ITeamMemberService teamMemberService;

    @Autowired
    private IDesignationRepository designationRepository;

    @PostMapping("/addTeamMember")
    public ResponseEntity<?> addTeamMember(@RequestParam("name") String name,
                                           @RequestParam("email") String email,
                                           @RequestParam("contact") String contact,
                                           @RequestParam("address") String address,
                                           @RequestParam("designation") Long designationId,
                                           @RequestParam("image") MultipartFile image) throws IOException {

        TeamMember teamMember = new TeamMember();
        teamMember.setName(name);
        teamMember.setEmail(email);
        teamMember.setContact(contact);
        teamMember.setAddress(address);
        teamMember.setImage(image.getBytes());

        teamMember.setDesignation(designationId);
        return ResponseEntity.ok(teamMemberService.addTeamMember(teamMember));
    }

    @GetMapping("/getAllTeamMember")
    public ResponseEntity<?> getAllTeamMember() {

        return ResponseEntity.ok(teamMemberService.getAllTeamMember());
    }

    @PatchMapping("/updateTeamMember")
    private ResponseEntity<?> updateTeamMember(@RequestParam("id") Long id,
                                               @RequestParam("name") String name,
                                               @RequestParam("email") String email,
                                               @RequestParam("contact") String contact,
                                               @RequestParam("address") String address,
                                               @RequestParam("designation") Long designation,
                                               @RequestParam("date") LocalDate date,
                                               @RequestParam("image") MultipartFile image) throws IOException, ResourceNotFoundException {

        System.out.println("Designation: "+designation);
        TeamMember teamMember = new TeamMember();
        teamMember.setId(id);
        teamMember.setName(name);
        teamMember.setEmail(email);
        teamMember.setContact(contact);
        teamMember.setAddress(address);
        teamMember.setImage(image.getBytes());
        teamMember.setDate(date.toString());
        teamMember.setDesignation(designation);
        return ResponseEntity.ok(teamMemberService.updateTeamMember(teamMember));
    }

    @DeleteMapping("/deleteTeamMember")
    public ResponseEntity<?> deleteTeamMember(@RequestParam Long teamMemberId) {
        return ResponseEntity.ok(teamMemberService.deleteTeamMember(teamMemberId));
    }
}
