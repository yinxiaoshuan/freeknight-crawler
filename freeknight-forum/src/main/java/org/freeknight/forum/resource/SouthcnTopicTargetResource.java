
package org.freeknight.forum.resource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.forum.cause.UnlawfulURLException;

public class SouthcnTopicTargetResource
		implements TargetResource
{

	private final static String		REGEX				= "([0-9]+)-([0-9]+)";

	private final static int			GROUP				= 2;

	private final static Pattern	URLPATTERN	= Pattern.compile ( REGEX );

	/**
	 * 
	 * @param url
	 *          格式: http://bbs.southcn.com/forum-25-1.html
	 * @param pageNum
	 *          50-${pageNum}.html
	 * 
	 * @return
	 */
	@Override
	public String getTarget (
			String fsrcId, final String url, final int pageNum )
	{
		final Matcher matcher = URLPATTERN.matcher ( url );
		if ( !matcher.find ( 0 ) )
		{
			throw new UnlawfulURLException ( "Unlawful Url: " + url + ", Pattern: [" + REGEX + "]" );
		}

		final int start = matcher.start ( GROUP );
		final int end = matcher.end ( GROUP );
		final String target =
				new StringBuilder ( url.substring ( 0, start ) )
						.append ( pageNum )
						.append ( url.substring ( end, url.length ( ) ) )
						.toString ( );
		return target;
	}
}
