package com.springboot.jwtsecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jwtsecurity.model.Tasks;
import com.springboot.jwtsecurity.repository.TasksRepository;

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

}
