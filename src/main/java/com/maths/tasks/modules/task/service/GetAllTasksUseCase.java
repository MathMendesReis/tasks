package com.maths.tasks.modules.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maths.tasks.modules.task.repository.TaskRepository;

@Service
public class GetAllTasksUseCase {
    @Autowired
    private TaskRepository taskRepository;
    public void findManyTasks(){
        
    }
}
