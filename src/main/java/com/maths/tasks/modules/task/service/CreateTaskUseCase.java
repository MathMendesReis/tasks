package com.maths.tasks.modules.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maths.tasks.modules.task.dto.CreateTaskRequesDTO;
import com.maths.tasks.modules.task.models.TaskModel;
import com.maths.tasks.modules.task.repository.TaskRepository;

import jakarta.validation.Valid;

@Service
public class CreateTaskUseCase {
    @Autowired
    private TaskRepository taskRepository;
    public TaskModel createTask(@Valid CreateTaskRequesDTO createTaskRequesDTO ){


        var task = new TaskModel().builder()
        .title(createTaskRequesDTO.title())
        .description(createTaskRequesDTO.description())
        .status(createTaskRequesDTO.status())
        .build();
       
        var result = this.taskRepository.save(task);
        return result;
    }
}
