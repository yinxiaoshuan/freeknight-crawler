
package org.freeknight.framework.cause;

/**
 * 抓取程序异常基类.
 * 
 * @author yrj
 *
 */
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
