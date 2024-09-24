package com.springboot.jwtsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jwtsecurity.model.Tasks;

public interface TasksRepository extends JpaRepository<Tasks, Integer>{

	
	

}
