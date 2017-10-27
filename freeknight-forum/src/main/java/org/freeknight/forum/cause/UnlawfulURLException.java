
package org.freeknight.forum.cause;

import org.freeknight.framework.cause.CrawlerException;

public class UnlawfulURLException
		extends CrawlerException
{
	private static final long	serialVersionUID	= 1L;

	public UnlawfulURLException (
			final String message ) {
		super ( message );
	}

}
