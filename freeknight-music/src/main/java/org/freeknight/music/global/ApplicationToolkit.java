
package org.freeknight.music.global;

import org.freeknight.framework.cause.CrawlerException;

public final class ApplicationToolkit
{

	public static void sleep (
			final int millis )
	{
		final String threadName = Thread.currentThread ( ).getName ( );
		try
		{
			Thread.sleep ( millis );
		}
		catch ( final InterruptedException e )
		{
			throw new CrawlerException ( "Thread " + threadName + ", Interrupted.", e );
		}
	}

	private ApplicationToolkit ( ) {
		throw new AssertionError ( "Uninstantiable class." );
	}
}
