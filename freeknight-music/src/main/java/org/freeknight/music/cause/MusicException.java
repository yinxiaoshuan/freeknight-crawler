
package org.freeknight.music.cause;

public class MusicException
		extends RuntimeException
{
	private static final long	serialVersionUID	= 1L;

	public MusicException (
			String message ) {
		super ( message );
	}

	public MusicException (
			String message,
			Throwable cause ) {
		super ( message, cause );
	}
}
