
package org.freeknight.forum.queue;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class QueueService
{

	JedisPool	jedisPool;

	public QueueService ( ) {
		jedisPool = new JedisPool ( "127.0.0.1", 6379 );
	}

	public String get ( )
	{
		Jedis jedis = jedisPool.getResource ( );

		String value = jedis.get ( "foo" );

		jedis.close ( );
		return value;
	}
}
