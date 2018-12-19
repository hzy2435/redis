package com.redis.example;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.redis.example.ext1.domain.Employee;
import com.redis.example.ext1.service.EmpService;

@RunWith(SpringRunner.class)
@SpringBootTest	// 会在junit启动时, 自动创建 Spring Boot 的 IOC 容器.
public class SpringcacheRedisApplicationTests {

	@Resource
	private EmpService service;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testFindById() {
		
		Employee emp = service.findById(2000L);
		System.out.println(emp);
		
		service.findById(1000L);
		service.findById(1000L);
		service.findById(1000L);
		service.findById(1000L);
	}
	
	@Test
	public void testFindRank() {
		
		System.out.println(service.findRank().get(0));
		
	}
	
	@Test
	public void testCreate() {
		
		service.addEmployee(new Employee(10003L, "name", new Date(), 1000f, "Researcher"));
		
	}
	
	@Test
	public void testUpdate() {
		
		service.updateEmployee(new Employee(10003L, "new-name", new Date(), 6000f, "new Researcher"));
		
	}
	
	@Test
	public void testDelete() {
		
		service.deleteEmployee(10003L);
		
	}

}
