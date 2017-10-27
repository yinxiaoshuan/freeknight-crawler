
package org.freeknight.framework.crawler.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class CacheServiceProxy
{

	String					host				= Protocol.DEFAULT_HOST;

	int							port				= Protocol.DEFAULT_PORT;

	JedisPoolConfig	poolConfig	= new JedisPoolConfig ( );

	public CacheServiceProxy ( ) {
		final JedisPool jedisPool = new JedisPool ( poolConfig, "" );
	}

	private void Jedis ( )
	{
		final JedisPool jedisPool = new JedisPool ( poolConfig, "" );

		final redis.clients.jedis.Jedis jedis = jedisPool.getResource ( );
		jedis.lpush ( "music:singer:xxxxzzzz", "" );
		//
		// jedis.lpop ( key );
	}
}
