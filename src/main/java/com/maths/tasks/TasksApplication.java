package com.maths.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maths.tasks.docs.ResponseAPIDocsRecordDTO;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@RestController
@OpenAPIDefinition(info = @Info(title = "Tasks", version = "1", description = "API desenvolvida para gerenciamento de tarefas."))
public class TasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

	@GetMapping
	public ResponseEntity<Object> docs(){
		new ResponseAPIDocsRecordDTO();
		var links = ResponseAPIDocsRecordDTO.builder()
		.current_task_url( "/task/findAll")
		.build();
		return ResponseEntity.status(HttpStatus.OK).body(links);
	}


}

