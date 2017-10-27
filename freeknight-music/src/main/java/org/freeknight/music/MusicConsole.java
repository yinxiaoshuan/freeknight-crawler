
package org.freeknight.music;

import org.freeknight.music.daemon.DaemonWorker;
import org.freeknight.music.daemon.DaemonWorkerEngine;

public class MusicConsole
{

	public static void main (
			String[ ] args )
	{
		if ( args == null || args.length < 1 )
		{
			System.err.println ( "[ERROR] Illegal args, It's less of 1." );
			System.exit ( 1 );
		}

		DaemonWorkerEngine engine = null;
		try
		{
			String site = args [ 0 ];
			engine = DaemonWorkerEngine.getDaemonWorker ( site );
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

			daemon.start ( );
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
