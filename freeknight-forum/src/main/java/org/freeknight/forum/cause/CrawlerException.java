
package org.freeknight.forum.cause;

public class CrawlerException
		extends RuntimeException
{
	private static final long	serialVersionUID	= 1L;

	public CrawlerException (
			final String message ) {
		super ( message );
	}

	public CrawlerException (
			final String message,
			final Throwable cause ) {
		super ( message, cause );
	}
}
