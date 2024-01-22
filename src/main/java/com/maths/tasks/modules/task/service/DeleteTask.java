package com.maths.tasks.modules.task.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maths.tasks.modules.task.repository.TaskRepository;

@Service
public class DeleteTask {
    
    @Autowired
    private TaskRepository taskRepository;
    public String deleteTaskById(UUID id) throws Exception{
        var task = this.taskRepository.findById(UUID.fromString(id.toString()));
        if (task.isEmpty()) {
            throw new Exception("Not found task.");
        }
        this.taskRepository.deleteById(id);
        String message = "Sucesso";
        return message;
    }
}
