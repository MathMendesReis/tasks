package com.maths.tasks.modules.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maths.tasks.modules.task.dto.CreateTaskRequesDTO;
import com.maths.tasks.modules.task.service.CreateTaskUseCase;

@RestController
@RequestMapping("/task")
public class TaskController {
    
    @Autowired
    private CreateTaskUseCase createTaskUseCase;


    @PostMapping("/register")
    public ResponseEntity<Object> createTask(@RequestBody CreateTaskRequesDTO createTaskRequesDTO){
        try {
            var result = this.createTaskUseCase.createTask(createTaskRequesDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
