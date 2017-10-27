
package org.freeknight.forum.cause;

public class FetchException
		extends RuntimeException
{

	private static final long	serialVersionUID	= 1L;

	public FetchException (
			String message,
			Throwable cause ) {
		super ( message, cause );
	}
}
