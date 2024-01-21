package com.maths.tasks.modules.task.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maths.tasks.modules.task.dto.CreateTaskRequesDTO;
import com.maths.tasks.modules.task.models.TaskModel;
import com.maths.tasks.modules.task.service.CreateTaskUseCase;
import com.maths.tasks.modules.task.service.DeleteTask;
import com.maths.tasks.modules.task.service.GetAllTasksUseCase;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/task")
public class TaskController {
    
    @Autowired
    private CreateTaskUseCase createTaskUseCase;

    @Autowired
    private GetAllTasksUseCase getAllTasksUseCase;

    @Autowired
    private DeleteTask deleteTask;


    @PostMapping("/register")
    public ResponseEntity<Object> createTask(@RequestBody CreateTaskRequesDTO createTaskRequesDTO){
        try {
            var result = this.createTaskUseCase.createTask(createTaskRequesDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<TaskModel>> findAllTask(){
            List<TaskModel> result= this.getAllTasksUseCase.findAllTasks();
            if (!result.isEmpty()) {
                for (TaskModel taskModel : result) {
                    UUID id = taskModel.getId();
                    taskModel.add(linkTo(methodOn(TaskController.class).getOneTask(id)).withSelfRel());
                }
                return ResponseEntity.status(HttpStatus.CREATED).body(result);
            }
            return null;
    }

    @GetMapping("/findOne/{id}")
	public ResponseEntity<Object> getOneTask(@PathVariable(value="id") UUID id){
        try {
            Optional<TaskModel> result= this.getAllTasksUseCase.findOneTasks(id);
            if (!result.isEmpty()) {
                result.get().add(linkTo(methodOn(TaskController.class).findAllTask()).withRel("Tasks List"));
                result.get().add(linkTo(methodOn(TaskController.class).deletetask(id)).withRel("Delete task"));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
	}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletetask(@PathVariable(value = "id") UUID id){
        try {
            this.deleteTask.deleteTaskById(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("delete task sucessful");
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }
	

}



