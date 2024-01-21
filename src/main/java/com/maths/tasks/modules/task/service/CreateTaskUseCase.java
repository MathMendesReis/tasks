package com.maths.tasks.modules.task.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maths.tasks.modules.task.dto.CreateTaskRequesDTO;
import com.maths.tasks.modules.task.models.TaskModel;
import com.maths.tasks.modules.task.repository.TaskRepository;

@Service
public class CreateTaskUseCase {
    @Autowired
    private TaskRepository taskRepository;
    public TaskModel createTask(CreateTaskRequesDTO createTaskRequesDTO ){
        var task = new TaskModel();
        BeanUtils.copyProperties(createTaskRequesDTO, task);
        var result = this.taskRepository.save(task);
        return result;
    }
}
