package com.maths.tasks.modules.task.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maths.tasks.modules.task.dto.CreateTaskRequesDTO;
import com.maths.tasks.modules.task.dto.UpdatTaskDTO;
import com.maths.tasks.modules.task.models.TaskModel;
import com.maths.tasks.modules.task.service.CreateTaskUseCase;
import com.maths.tasks.modules.task.service.DeleteTask;
import com.maths.tasks.modules.task.service.GetAllTasksUseCase;
import com.maths.tasks.modules.task.service.UpdateTask;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/task")
@Tag(name = "task")

public class TaskController {
    
    @Autowired
    private CreateTaskUseCase createTaskUseCase;

    @Autowired
    private GetAllTasksUseCase getAllTasksUseCase;

    @Autowired
    private DeleteTask deleteTask;

    @Autowired
    private UpdateTask updateTask;

    private List<Link> getCommonLinks(UUID id) {
    return Arrays.asList(
        linkTo(methodOn(TaskController.class).findAllTask()).withRel("Tasks List"),
        linkTo(methodOn(TaskController.class).updateTask(id, null)).withRel("Update task"),
        linkTo(methodOn(TaskController.class).deletetask(id)).withRel("Delete task"),
        linkTo(methodOn(TaskController.class).getOneTask(id)).withRel("Task details")
    );
}


    @PostMapping("/register")
    public ResponseEntity<Object> createTask(@RequestBody CreateTaskRequesDTO createTaskRequesDTO){
        try {
            var result = this.createTaskUseCase.createTask(createTaskRequesDTO);
            result.add(getCommonLinks(result.getId()));
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
                    taskModel.add().add(getCommonLinks(id));
                }
                return ResponseEntity.status(HttpStatus.CREATED).body(result);
            }
            return null;
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Object> getOneTask(@PathVariable(value="id") UUID id) {
        Optional<TaskModel> result = this.getAllTasksUseCase.findOneTasks(id);
        if (!result.isEmpty()) {
            result.get().add(getCommonLinks(id));
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletetask(@PathVariable(value="id") UUID id){
        try {
            var result = this.deleteTask.deleteTaskById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable(value="id") UUID id, @RequestBody UpdatTaskDTO updatTaskDTO){
        try {
            var result = this.updateTask.updateTaskById(id,updatTaskDTO);
            result.add().add(getCommonLinks(id));
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }
	

}



