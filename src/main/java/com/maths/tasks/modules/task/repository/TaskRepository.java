package com.maths.tasks.modules.task.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maths.tasks.modules.task.models.TaskModel;

public interface TaskRepository extends JpaRepository<TaskModel, UUID>{
    
}
