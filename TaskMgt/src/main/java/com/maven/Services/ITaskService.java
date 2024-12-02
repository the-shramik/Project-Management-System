package com.maven.Services;

import com.maven.Model.Task;
import com.maven.Model.dtos.DtoTask;
import com.maven.Model.dtos.helper.TaskDto;
import com.maven.exception.ResourceNotFoundException;

import java.util.List;
public interface ITaskService {
    Task addTask(DtoTask task);
    List<TaskDto> getAllTask();

    List<TaskDto> getAllPendingTasks();

    List<TaskDto> getAllCompletedTasks();

    List<TaskDto> getTasksByProject(Long projectId);

    List<TaskDto> getTasksByStatus(String status);

    Task updateTask(DtoTask task) throws ResourceNotFoundException;

    String deleteTask(Long taskId);
}
