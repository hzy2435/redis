package jedis;

import org.junit.Test;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolTest {
	
	@Test
	public void testJedisPool() {
		
		GenericObjectPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(100);
		config.setMaxIdle(50);
		config.setMinIdle(10);
		config.setTestOnBorrow(false);	// 从连接池中借出时是否测试连接
		config.setTestOnReturn(false);	// 归还连接时是否测试连接
		config.setTestOnCreate(false);	// 生产环境下可以设置为true, 由于redis的自我保护机制, 所以开发环境下只能设置为 false。
		config.setBlockWhenExhausted(true);	// 当连接池中无可用连接时, 是否阻塞等待
		config.setMaxWaitMillis(5000);	// 等待多长时间
		
		JedisPool pool = new JedisPool(config, "192.168.184.128", 6666);
		Jedis jedis = pool.getResource();
		
		try {
			
			jedis.auth("123456");
			jedis.select(4);
			jedis.flushDB();
			
			jedis.set("aaa", "123456");
			System.out.println(jedis.get("aaa"));
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis != null) {
				jedis.close();	// 此处是将连接归还连接池, 而不是关闭
			}
		}
		
	}

}
