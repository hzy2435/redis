package com.redis.example.common;

import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {

	private Class<T> clazz = null;
	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	/*
	 * 添加白名单, 解决 JSONException: autoType is not support
	 */
	static {
		
		ParserConfig.getGlobalInstance().addAccept("com.redis.example.ext1.domain.Employee");
		
	}
	
	public FastJson2JsonRedisSerializer(Class<T> clazz) {
		
		this.clazz = clazz;
		
	}
	
	@Override
	public byte[] serialize(T t) throws SerializationException {

		if(t == null) {
			return new byte[0];
		}
		
		return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
		
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {

		if(bytes == null || bytes.length <= 0) {
			return null;
		}
		
		String str = new String(bytes, DEFAULT_CHARSET);
		
		return JSON.parseObject(str, clazz);
	}

}
