package com.redis.example.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class CacheSerializerBean {

	@Bean
	public RedisCacheConfiguration redisCacheConfiguration() {

		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

		config = config.serializeValuesWith(RedisSerializationContext.SerializationPair
				.fromSerializer(new FastJson2JsonRedisSerializer<>(Object.class)));

		return config;
	}

}
