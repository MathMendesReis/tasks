package com.maths.tasks.modules.task.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maths.tasks.modules.task.dto.UpdatTaskDTO;
import com.maths.tasks.modules.task.models.TaskModel;
import com.maths.tasks.modules.task.repository.TaskRepository;

@Service
public class UpdateTask {
    @Autowired
    private TaskRepository taskRepository;
    public TaskModel updateTaskById(UUID id, UpdatTaskDTO updatTaskDTO) throws Exception{
        TaskModel task = taskRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));

         if(!updatTaskDTO.title().isBlank())((TaskModel) task).setTitle(updatTaskDTO.title());
         if(!updatTaskDTO.description().isBlank())((TaskModel) task).setDescription(updatTaskDTO.description());
         if(!updatTaskDTO.status().isBlank())((TaskModel) task).setStatus(updatTaskDTO.status());

        var newTask = taskRepository.save(task);


        return newTask;

    }

}
