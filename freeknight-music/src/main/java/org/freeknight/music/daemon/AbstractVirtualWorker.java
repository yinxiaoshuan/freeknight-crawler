
package org.freeknight.music.daemon;

import java.util.concurrent.ThreadPoolExecutor;

import org.freeknight.music.cause.ShutdownException;

/**
 * 抽象的后台工作者基类, 为子类工作者提供统一的线程任务调度.
 * 
 * @author yrj
 *
 */
abstract public class AbstractVirtualWorker
		implements DaemonWorker
{

	private class VirtualThread
			extends Thread
	{
		@Override
		public void run ( )
		{
			try
			{
				init ( );

				for ( ;; )
				{
					// ApplicationToolkit.sleep ( 100 );// 避免CPU100%
					try
					{
						doProcess ( );
					}
					catch ( Throwable e )
					{
						if ( e instanceof ShutdownException )
						{
							break;
						}
						else
						{
							e.printStackTrace ( );
						}
					}
				}
			}
			finally
			{
				shutdown ( );
			}
		}
	}

	private Thread								daemon;

	protected ThreadPoolExecutor	taskExecutor;

	abstract protected void doProcess ( );

	@Override
	public synchronized void start ( )
	{
		if ( daemon == null )
		{
			daemon = new VirtualThread ( );
			daemon.setDaemon ( false );
			daemon.setName ( getName ( ) + "-Virtual-Daemon" );
		}
		daemon.start ( );
	}

	@Override
	public void shutdown ( )
	{
		if ( taskExecutor != null )
		{
			taskExecutor.shutdown ( );
		}
	}

}
