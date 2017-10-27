
package org.freeknight.music.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class DefaultCrawlerRejectedExecutionHandler
		implements RejectedExecutionHandler
{

	@Override
	public void rejectedExecution (
			final Runnable r, final ThreadPoolExecutor executor )
	{
	}

}
