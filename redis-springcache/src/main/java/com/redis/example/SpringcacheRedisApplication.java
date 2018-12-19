package com.redis.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching	// 开启声明式缓存
public class SpringcacheRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcacheRedisApplication.class, args);
	}
}
