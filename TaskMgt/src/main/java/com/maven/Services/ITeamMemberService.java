package com.maven.Services;

import com.maven.Model.TeamMember;
import com.maven.Model.dtos.helper.TeamMemberDto;
import com.maven.exception.ResourceNotFoundException;

import java.io.IOException;
import java.util.List;

public interface ITeamMemberService {
    TeamMember addTeamMember(TeamMember teamMember);

    List<TeamMemberDto> getAllTeamMember();

    TeamMember updateTeamMember(TeamMember teamMember) throws ResourceNotFoundException, IOException;

    String deleteTeamMember(Long teamMemberId);
}
