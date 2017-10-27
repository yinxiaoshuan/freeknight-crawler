
package org.freeknight.forum.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class CrawlerRejectedExecutionHandler
		implements RejectedExecutionHandler
{

	@Override
	public void rejectedExecution (
			Runnable r, ThreadPoolExecutor executor )
	{
		try
		{
			// 采用put方法阻塞提交, 等待队列可放入任务.
			executor.getQueue ( ).put ( r );
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace ( );
		}
	}

}
