package com.springboot.jwtsecurity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.jwtsecurity.model.Tasks;
import com.springboot.jwtsecurity.service.TaskService;


@SpringBootTest
class JwtsecurityApplicationTests {
	
	public TaskService taskService;

	@Test
	void contextLoads() {
	}

	@Test
	public void getAlltasks() {
		Tasks t1 = new Tasks();
		t1.setId(1);
		t1.setTitle("titel1");
		t1.setDescription("Desc1");
		
		Tasks t2 = new Tasks();
		t2.setId(1);
		t2.setTitle("titel2");
		t2.setDescription("Desc2");
		
		Tasks t3 = new Tasks();
		t3.setId(1);
		t3.setTitle("titel3");
		t3.setDescription("Desc3");
		
		List<Tasks> taskList = taskService.getAlltasks();
		 int expectedval = taskList.size();
		 int actualval = 3;
		 
		 assertEquals(expectedval,actualval);
		
}
}
