package com.springboot.jwtsecurity.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jwtsecurity.enums.PriorityType;
import com.springboot.jwtsecurity.enums.Status;
import com.springboot.jwtsecurity.model.Tasks;
import com.springboot.jwtsecurity.service.TaskService;
import com.springboot.my_boot_app.exception.InvalidIdException;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	public TaskService taskService;
	
	@GetMapping("/allTasks")
	public List<Tasks> getAlltasks() {
		return taskService.getAlltasks();
	}
	
	@PostMapping("/add/tasks")
	public ResponseEntity<Tasks> addTasks(@RequestBody Tasks tasks) {
		
		Tasks newtasks = taskService.addTasks(tasks);
		return ResponseEntity.ok(newtasks);
		
	}
	
	@GetMapping("/{id}")
	public Optional<Tasks> getTaskById(@PathVariable int id) {
		return taskService.getTasksById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteTaskById(@PathVariable int id) {
		taskService.deleteTaskById(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Tasks> updateTaskById(@PathVariable int id, @RequestParam String title, @RequestParam String description,
								@RequestParam LocalDate dueDate, @RequestParam PriorityType priority, @RequestParam Status status) throws InvalidIdException {
		
		Tasks updatedTasks = taskService.updateTaskById(id, title, description, dueDate, priority, status);
		
		return ResponseEntity.ok(updatedTasks);
		
		
	}

}
