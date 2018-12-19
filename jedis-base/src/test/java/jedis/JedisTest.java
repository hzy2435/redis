package jedis;

import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisTest {

	@Test
	public void testString() {
		
		Jedis jedis = new Jedis("192.168.184.128", 6666, 5000);
		
		try {
			
			jedis.auth("123456");
			jedis.select(2);
			jedis.flushDB();
			
			jedis.set("aaa", "bbbb");
			System.out.println(jedis.get("aaa"));
			
			jedis.mset(new String[] {"a", "1", "b", "2", "c", "3"});
			System.out.println(jedis.mget(new String[] {"a", "aaa"}));
			
			System.out.println(jedis.incr("c"));
			System.out.println(jedis.del("aaa"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		
	}
	
	@Test
	public void testHash() {
		
		Jedis jedis = new Jedis("192.168.184.128", 6666, 5000);
		
		try {
			
			jedis.auth("123456");
			jedis.select(4);
			jedis.flushDB();
			
			jedis.hset("user:1:info", "name", "itlaoqi");
			jedis.hset("user:1:info", "age", "39");
			jedis.hset("user:1:info", "height", "180");
			
			System.out.println(jedis.hget("user:1:info", "name"));
			
			Map<String, String> all = jedis.hgetAll("user:1:info");
			System.out.println(all);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		
	}
	
}
