package com.maths.tasks.modules.task.dto;

import java.time.LocalDateTime;

public record CreateTaskRequesDTO(
     String title,
     String description,
     String status,
     LocalDateTime dueDate
) {
    
}
