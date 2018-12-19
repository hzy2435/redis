package com.redis.common;

import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/*
 * JSON 序列化对象
 */
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {

	private Class<T> clazz;
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	/*
	 * 添加白名单, 解决 JSONException: autoType is not support
	 */
	static {
		ParserConfig.getGlobalInstance().addAccept("jedis.User");
	}
	
	public FastJson2JsonRedisSerializer(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}
	
	public byte[] serialize(T t) throws SerializationException {
		
		if(t == null) {
			return new byte[0];
		}
		
		return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
	}

	public T deserialize(byte[] bytes) throws SerializationException {
		
		if(bytes == null || bytes.length <= 0) {
			return null;
		}
		
		String str = new String(bytes, DEFAULT_CHARSET);
		return (T)JSON.parseObject(str, clazz);
	}

}
