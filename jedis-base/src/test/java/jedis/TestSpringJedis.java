package jedis;

import org.junit.runner.RunWith;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestSpringJedis {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Test
	public void testSetAndGet() {
		
		redisTemplate.opsForValue().set("name", "hzy");
		String name = (String) redisTemplate.opsForValue().get("name");
		System.out.println("name: " + name);
		
	}

	@Test
	public void testObjectSerializer() {
		
		User user = new User("hzy", "dgut-hzy");
		redisTemplate.opsForValue().set("user:u1", user);
		
	}
	
	@Test
	public void testObjectUnSerializer() {
		
		User user = (User)redisTemplate.opsForValue().get("user:u1");
		System.out.println("user: " + user);
	}
	
	@Test
	public void testHashSerializer() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		User user = new User("xiaobai", "123xiaobai");
		Map map = BeanUtils.describe(user);
		redisTemplate.opsForHash().putAll("user:u3", map);
		
	}
	
	@Test
	public void testHashDeserializer() throws IllegalAccessException, InvocationTargetException {
		
		Map map = redisTemplate.opsForHash().entries("user:u3");
		System.out.println("map: " + map);
		
		User user = new User();
		BeanUtils.populate(user, map);
		System.out.println("user: " + user);
		
	}
	
	@Test
	public void testList() {
		
		for(int i = 0; i < 10; i++) {
			
			redisTemplate.opsForList().rightPush("vipUserRank", new User("user" + i, "password" + i));
			
		}
		
		List<Object> users = redisTemplate.opsForList().range("vipUserRank", 2, 7);
		users.forEach(user -> System.out.println("user: " + user));
		
	}
	
	@Test
	public void testDBFlush() {
		
		redisTemplate.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				
				connection.flushDb();
				return null;
			}
			
		});
		
	}
}
