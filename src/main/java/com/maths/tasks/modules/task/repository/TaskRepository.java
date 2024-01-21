package com.maths.tasks.modules.task.repository;

import java.util.UUID;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.maths.tasks.modules.task.models.TaskModel;

import jakarta.validation.constraints.NotNull;

public interface TaskRepository extends JpaRepository<TaskModel, UUID>{
    Optional<TaskModel> findById(@NotNull UUID id);
}
