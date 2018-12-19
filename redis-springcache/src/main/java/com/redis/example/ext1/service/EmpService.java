package com.redis.example.ext1.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.redis.example.ext1.domain.Employee;

/*
 * 测试声明式缓存 @Cacheable
 */
@Service
public class EmpService {

	/*
	 * @Cacheable 注解表示声明式缓存, 声明式缓存会将对象序列化后存放在 redis 中 如果 redis 中存在该值, 则直接从内存中获取,
	 * 不执行该方法.
	 * condition 表示缓存条件
	 */
	@Cacheable(value="employee", key="#empId", condition="#empId != 1000")
	public Employee findById(Long empId) {
		
		System.out.println("The method finById begins with [empId: " + empId + "]");
		return new Employee(empId, "laoqi", new Date(), 1000f, "Researcher");
		
	}
	
	/*
	 * 缓存集合
	 * value命名规则: 类型:信息:属性, 表示以什么属性组成的信息
	 */
	@Cacheable(value="employee:rank:salary")
	public List<Employee> findRank() {
		
		List<Employee> lists = new ArrayList<>();
		
		for(long i = 0; i < 10; i++) {
			
			lists.add(new Employee(i, "empName" + i, new Date(), 5000 + i * 1000, "dept" + i));
			
		}
		
		return lists;
		
	}
	
	/*
	 * 添加数据
	 */
	@CachePut(value="employee", key="#e.empId")
	public Employee addEmployee(Employee e) {
		
		System.out.println("正在创建");
		
		return e;
		
	}
	
	/*
	 * 更新数据
	 */
	@CachePut(value="employee", key="#e.empId")
	public Employee updateEmployee(Employee e) {
		
		System.out.println("正在更新");
		return e;
		
	}
	
	/*
	 * 删除
	 */
	@CacheEvict(value="employee", key="#empId")
	public void deleteEmployee(Long empId) {
			
		System.out.println("正在删除");
			
	}

}
