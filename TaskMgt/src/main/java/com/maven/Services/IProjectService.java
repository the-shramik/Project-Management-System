package com.maven.Services;

import com.maven.Model.Projects;
import com.maven.Model.dtos.DtoProject;
import com.maven.Model.dtos.helper.ProjectDto;
import com.maven.exception.ResourceNotFoundException;

import java.util.List;

public interface IProjectService {
    Projects addProject(DtoProject projects);

    List<ProjectDto> getAllProjects();

    List<ProjectDto> getProjectsByCategory(Long categoryId);

    List<ProjectDto> getProjectsByStatus(String status);

    Projects updateProject(DtoProject projects) throws ResourceNotFoundException;

    String deleteProject(Long projectId);
}
