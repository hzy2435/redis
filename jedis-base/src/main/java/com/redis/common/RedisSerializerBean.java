package com.redis.common;

import org.springframework.data.redis.serializer.RedisSerializer;

public class RedisSerializerBean {

	public RedisSerializer<Object> fastJson2JsonRedisSerializer() {
		return new FastJson2JsonRedisSerializer<Object>(Object.class);
	}
	
}
