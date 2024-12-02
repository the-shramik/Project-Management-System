package com.maven.Services.impl;

import com.maven.Model.Designations;
import com.maven.Model.TeamMember;
import com.maven.Model.dtos.helper.TeamMemberDto;
import com.maven.Repository.IDesignationRepository;
import com.maven.Repository.IProjectRepository;
import com.maven.Repository.ITeamMemberRepository;
import com.maven.Services.ITeamMemberService;
import com.maven.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class TeamMemberServiceImpl implements ITeamMemberService {
    @Autowired
    private ITeamMemberRepository teamMemberRepository;

    @Autowired
    private IDesignationRepository designationRepository;

    @Autowired
    private IProjectRepository projectRepository;

    @Override
    public TeamMember addTeamMember(TeamMember teamMember) {
        teamMember.setDate((LocalDate.now().toString()));
        return teamMemberRepository.save(teamMember);
    }

    @Override
    public List<TeamMemberDto> getAllTeamMember() {

        List<TeamMemberDto> teamMembers=new ArrayList<>();
         teamMemberRepository.findAll().forEach(teamMember -> {
             TeamMemberDto teamMemberDto=new TeamMemberDto();
             teamMemberDto.setId(teamMember.getId());
             teamMemberDto.setName(teamMember.getName());
             teamMemberDto.setEmail(teamMember.getEmail());
             teamMemberDto.setAddress(teamMember.getAddress());
             teamMemberDto.setImage(teamMember.getImage());
             teamMemberDto.setDate(teamMember.getDate());
             teamMemberDto.setContact(teamMember.getContact());

             Optional<Designations> d = designationRepository.findById(teamMember.getDesignation());

             if(d.isPresent()) {
                 teamMemberDto.setDesignation(d.get().getName());
             }else {
                 teamMemberDto.setDesignation(null);
             }

             teamMembers.add(teamMemberDto);
         });

         return teamMembers;
    }

    @Override
    public TeamMember updateTeamMember(TeamMember teamMember) throws ResourceNotFoundException, IOException {
        TeamMember existedTeamMember=teamMemberRepository.findById(teamMember.getId()).orElseThrow(()->new ResourceNotFoundException("Team member not present"));

        existedTeamMember.setName(teamMember.getName());
        existedTeamMember.setEmail(teamMember.getEmail());
        existedTeamMember.setDate(teamMember.getDate());
        existedTeamMember.setContact(teamMember.getContact());
        existedTeamMember.setAddress(teamMember.getAddress());
        existedTeamMember.setImage(teamMember.getImage());
        existedTeamMember.setDesignation(teamMember.getDesignation());

        return teamMemberRepository.save(teamMember);
    }

    @Transactional
    @Override
    public String deleteTeamMember(Long teamMemberId) {

        teamMemberRepository.deleteById(teamMemberId);

        if(teamMemberRepository.findById(teamMemberId).isEmpty()){
            return "Team member deleted";
        }
        else {
            return "Team not member deleted";
        }
    }
}
