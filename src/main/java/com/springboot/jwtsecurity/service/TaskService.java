package com.springboot.jwtsecurity.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jwtsecurity.enums.PriorityType;
import com.springboot.jwtsecurity.enums.Status;
import com.springboot.jwtsecurity.model.Tasks;
import com.springboot.jwtsecurity.repository.TasksRepository;
import com.springboot.my_boot_app.exception.InvalidIdException;

import jakarta.transaction.Transactional;

@Service
public class TaskService {

	@Autowired
	public TasksRepository tasksRepository;
	
	public List<Tasks> getAlltasks(){
		return tasksRepository.findAll();
		}

	public Tasks addTasks(Tasks tasks) {
		return tasksRepository.save(tasks);
	}

	public Optional<Tasks> getTasksById(int id) {
		return tasksRepository.findById(id);
	}

	public void deleteTaskById(int id) {
		tasksRepository.deleteById(id);
		System.out.println("Deleted taskId : " + id);
	}

	@Transactional
	public Tasks updateTaskById(int id, String title, String description, LocalDate dueDate, PriorityType priority,
			Status status) throws InvalidIdException {
		Tasks updatedtask = tasksRepository.findById(id)
				.orElseThrow(() -> new InvalidIdException("Id not found to update"));
		
		updatedtask.setTitle(title);
		updatedtask.setDescription(description);
		updatedtask.setDueDate(dueDate);
		updatedtask.setPriority(priority);
		updatedtask.setStatus(status);
		
		tasksRepository.save(updatedtask);
		
		System.out.println("Updated taskId : " + id);
		
		return updatedtask;
	}



}
