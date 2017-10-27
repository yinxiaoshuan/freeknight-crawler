
package org.freeknight.forum.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CrawlerThreadFactory
		implements ThreadFactory
{

	private final ThreadGroup		group;

	private final AtomicInteger	threadNumber	= new AtomicInteger ( 1 );

	private final String				namePrefix;

	public CrawlerThreadFactory (
			String poolName ) {
		SecurityManager s = System.getSecurityManager ( );
		group = ( s != null ) ? s.getThreadGroup ( ) : Thread.currentThread ( ).getThreadGroup ( );
		namePrefix = poolName + "-";
	}

	@Override
	public Thread newThread (
			Runnable r )
	{
		Thread t = new Thread ( group, r, namePrefix + threadNumber.getAndIncrement ( ), 0 );
		if ( t.isDaemon ( ) )
			t.setDaemon ( false );
		if ( t.getPriority ( ) != Thread.NORM_PRIORITY )
			t.setPriority ( Thread.NORM_PRIORITY );
		return t;
	}
}
