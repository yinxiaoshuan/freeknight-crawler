
package org.freeknight.music.cause;

public class ShutdownException
		extends MusicException
{

	public ShutdownException (
			String message ) {
		super ( message );
	}

	public ShutdownException (
			String message,
			Throwable cause ) {
		super ( message, cause );
	}

	private static final long	serialVersionUID	= 1L;

}
