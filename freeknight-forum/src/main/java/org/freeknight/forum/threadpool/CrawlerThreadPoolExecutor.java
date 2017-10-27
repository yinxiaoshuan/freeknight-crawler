
package org.freeknight.forum.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CrawlerThreadPoolExecutor
		extends ThreadPoolExecutor
{

	public CrawlerThreadPoolExecutor (
			String poolName,
			int corePoolSize,
			int maximumPoolSize,
			long keepAliveTime,
			TimeUnit unit,
			BlockingQueue< Runnable > workQueue ) {
		this (
				corePoolSize,
				maximumPoolSize,
				keepAliveTime,
				unit,
				workQueue,
				new CrawlerThreadFactory ( poolName ),
				new CrawlerRejectedExecutionHandler ( ) );
	}

	public CrawlerThreadPoolExecutor (
			int corePoolSize,
			int maximumPoolSize,
			long keepAliveTime,
			TimeUnit unit,
			BlockingQueue< Runnable > workQueue,
			ThreadFactory threadFactory,
			RejectedExecutionHandler handler ) {
		super ( corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler );
	}

	@Override
	protected void beforeExecute (
			Thread t, Runnable r )
	{
	}

	@Override
	protected void afterExecute (
			Runnable r, Throwable t )
	{
	}

}
