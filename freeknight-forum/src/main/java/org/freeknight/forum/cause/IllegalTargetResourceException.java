
package org.freeknight.forum.cause;

public class IllegalTargetResourceException
		extends CrawlerException
{
	public IllegalTargetResourceException (
			final String message ) {
		super ( message );
	}

	public IllegalTargetResourceException (
			final String message,
			final Throwable cause ) {
		super ( message, cause );
	}

	private static final long	serialVersionUID	= 1L;
}
