package com.maven.Services.impl;

import com.maven.Model.Category;
import com.maven.Model.Projects;
import com.maven.Model.TeamMember;
import com.maven.Model.dtos.DtoProject;
import com.maven.Model.dtos.helper.ProjectDto;
import com.maven.Repository.ICategoryRepository;
import com.maven.Repository.IProjectRepository;
import com.maven.Repository.ITeamMemberRepository;
import com.maven.Services.IProjectService;
import com.maven.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectImpl implements IProjectService {
    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ITeamMemberRepository teamMemberRepository;

 @Override
    public Projects addProject(DtoProject dtoProject) {

        Projects projects=new Projects();
        projects.setProjectName(dtoProject.getProjectName());
        projects.setProjectManager(dtoProject.getProjectManager());
        projects.setDate(LocalDate.now().toString());
        projects.setDescription(dtoProject.getDescription());
        projects.setStatus(dtoProject.getStatus());
        projects.setCategory(dtoProject.getCategory());
        projects.setStartDate(dtoProject.getStartDate());
        projects.setEndDate(dtoProject.getEndDate());

        List<Long> teamMembersIds=new ArrayList<>();
        dtoProject.getTeamMembers().forEach(teamMember -> {
            teamMembersIds.add(teamMember.getId());
        });

        long[] teamMembers=new long[dtoProject.getTeamMembers().size()];
        for (int i=0;i<teamMembers.length;i++){
            teamMembers[i]=teamMembersIds.get(i);
        }
        projects.setTeamMembers(teamMembers);

        return projectRepository.save(projects);
    }

    @Override
    public List<ProjectDto> getAllProjects() {
         List<ProjectDto> projects=new ArrayList<>();
         List<String> teamMembers=new ArrayList<>();
         projectRepository.findAll().forEach(project -> {
             ProjectDto projectDto=new ProjectDto();

             projectDto.setId(project.getId());
             projectDto.setProjectName(project.getProjectName());
             System.out.println("Project Name: "+project.getProjectName());
             projectDto.setProjectManager(project.getProjectManager());
             projectDto.setDate(project.getDate());
             projectDto.setStatus(project.getStatus());


             for (Long teamMemberId:project.getTeamMembers()) {
                 TeamMember fetchedTeamMember= teamMemberRepository.findById(teamMemberId).get();
                 teamMembers.add(fetchedTeamMember.getName());
             }

             projectDto.setTeamMembers(teamMembers.toArray(new String[0]));
             projectDto.setDescription(project.getDescription());

             Optional<Category> optionalCategory = categoryRepository.findById(project.getCategory());

             if (optionalCategory.isPresent()) {
                 projectDto.setCategory(optionalCategory.get().getName());
             }
             else{
                 projectDto.setCategory(null);
             }

             projectDto.setStartDate(project.getStartDate().toString());
             projectDto.setEndDate(project.getEndDate().toString());
             long duration = Duration.between(project.getStartDate().atStartOfDay(), project.getEndDate().atStartOfDay()).toDays();

             projectDto.setDuration(duration);

             projects.add(projectDto);
         });

         return projects;
    }

    @Override
    public List<ProjectDto> getProjectsByCategory(Long categoryId) {

        List<ProjectDto> projects=new ArrayList<>();
        List<String> teamMembers=new ArrayList<>();
        projectRepository.findProjectsByCategory(categoryId).forEach(project->{
            ProjectDto projectDto=new ProjectDto();
            projectDto.setId(project.getId());
            projectDto.setProjectName(project.getProjectName());
            projectDto.setProjectManager(project.getProjectManager());
            projectDto.setDate(project.getDate());
            projectDto.setStatus(project.getStatus());
            for (Long teamMemberId:project.getTeamMembers()) {
                TeamMember fetchedTeamMember= teamMemberRepository.findById(teamMemberId).get();
                teamMembers.add(fetchedTeamMember.getName());
            }
            projectDto.setTeamMembers(teamMembers.toArray(new String[0]));
            projectDto.setDescription(project.getDescription());
            projectDto.setStartDate(project.getStartDate().toString());
            projectDto.setEndDate(project.getEndDate().toString());
            long duration = Duration.between(project.getStartDate().atStartOfDay(), project.getEndDate().atStartOfDay()).toDays();
            projectDto.setDuration(duration);

            Optional<Category> optionalCategory = categoryRepository.findById(project.getCategory());

            if (optionalCategory.isPresent()) {
                projectDto.setCategory(optionalCategory.get().getName());
            }
            else{
                projectDto.setCategory(null);
            }

            projects.add(projectDto);
        } );

        return projects;
    }

    @Override
    public List<ProjectDto> getProjectsByStatus(String status) {
        List<ProjectDto> projects=new ArrayList<>();
        List<String> teamMembers=new ArrayList<>();
        projectRepository.findByStatus(status.toLowerCase()).forEach(project -> {
            ProjectDto projectDto=new ProjectDto();
            for (Long teamMemberId:project.getTeamMembers()) {
                TeamMember fetchedTeamMember= teamMemberRepository.findById(teamMemberId).get();
                teamMembers.add(fetchedTeamMember.getName());
            }
            projectDto.setTeamMembers(teamMembers.toArray(new String[0]));
            projectDto.setDescription(project.getDescription());
            projectDto.setId(project.getId());
            projectDto.setProjectName(project.getProjectName());
            projectDto.setProjectManager(project.getProjectManager());
            projectDto.setDate(project.getDate());
            projectDto.setStatus(project.getStatus());
            projectDto.setStartDate(project.getStartDate().toString());
            projectDto.setEndDate(project.getEndDate().toString());
            long duration = Duration.between(project.getStartDate().atStartOfDay(), project.getEndDate().atStartOfDay()).toDays();
            projectDto.setDuration(duration);

            Optional<Category> optionalCategory = categoryRepository.findById(project.getCategory());

            if (optionalCategory.isPresent()) {
                projectDto.setCategory(optionalCategory.get().getName());
            }
            else{
                projectDto.setCategory(null);
            }

            projects.add(projectDto);
        });

        return  projects;
    }

    @Override
    public Projects updateProject(DtoProject projects) throws ResourceNotFoundException {
        Projects existedProject=projectRepository.findById(projects.getId()).orElseThrow(
                ()->new ResourceNotFoundException("Project not present"));

        existedProject.setProjectName(projects.getProjectName());
        existedProject.setProjectManager(projects.getProjectManager());
        existedProject.setDescription(existedProject.getDescription());
        existedProject.setDate(projects.getDate());
        existedProject.setTeamMembers(existedProject.getTeamMembers());
        existedProject.setCategory(projects.getCategory());
        existedProject.setStartDate(projects.getStartDate());
        existedProject.setEndDate(projects.getEndDate());
        existedProject.setStatus(projects.getStatus());

        return projectRepository.save(existedProject);
    }

    @Override
    public String deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);

        if(projectRepository.findById(projectId).isEmpty()){
            return "Project deleted";
        }
        else{
            return "Project not deleted";
        }
    }
}
