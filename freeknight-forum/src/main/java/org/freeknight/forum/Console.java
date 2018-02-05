
package org.freeknight.forum;

import org.freeknight.forum.daemon.DaemonWorker;

public class Console
{

	public static void main (
			String[ ] args )
	{
		if ( args == null || args.length < 1 )
		{
			System.err.println ( "[ERROR] Illegal args, It's less of 1." );
			System.exit ( 1 );
		}

		DaemonWorker.DaemonWorkerEngine engine = null;
		try
		{
			String forum = args [ 0 ];
			engine = DaemonWorker.DaemonWorkerEngine.getDaemonWorker ( forum );
		}
		catch ( Throwable e )
		{
			System.err.println ( "[ERROR]" + e.getMessage ( ) );
			System.exit ( 1 );
		}

		try
		{
			DaemonWorker daemon = engine.initDaemonWorker ( );
			Runtime.getRuntime ( ).addShutdownHook ( new Thread ( new Runnable ( )
			{
				@Override
				public void run ( )
				{
					System.out.println ( Thread.currentThread ( ).getName ( ) + ": [" + daemon.getName ( ) + "] shutdown." );
				}
			}, "Crawler-Shutdown" ) );

			daemon.start ( engine.getForumName ( ) );
			System.out.println ( "Daemon: " + daemon.getName ( ) + " start success." );
		}
		catch ( Throwable e )
		{
			System.err.println ( e.getMessage ( ) );
			e.printStackTrace ( );
			System.exit ( 1 );
		}

	}
}
