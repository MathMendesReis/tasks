package com.maths.tasks.modules.task.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maths.tasks.modules.task.models.TaskModel;
import com.maths.tasks.modules.task.repository.TaskRepository;

@Service
public class GetAllTasksUseCase {
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskModel> findAllTasks() {
        List<TaskModel> result = taskRepository.findAll();
        return result;
    }
    public Optional<TaskModel> findOneTasks(UUID uuid) {
        Optional<TaskModel> result = taskRepository.findById(uuid);
        return result;
    }
}
