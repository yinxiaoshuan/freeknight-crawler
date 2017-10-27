
package org.freeknight.forum.resource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.forum.cause.UnlawfulURLException;

public class ChinaunixTargetResource
		implements TargetResource
{

	/**
	 * 
	 * @param url
	 *          格式: http://club.topsage.com/forum-781-1.html、http://club.topsage.com/forum-abcd-1.html
	 * @param pageNum
	 *          forum-781-${pageNum}
	 * @return
	 */
	@Override
	public String getTarget (
			String fsrcId, final String url, final int pageNum )
	{
		final Pattern pattern = Pattern.compile ( "([0-9]+).html" );
		final Matcher matcher = pattern.matcher ( url );
		if ( !matcher.find ( ) )
		{
			throw new UnlawfulURLException ( "Unlawful, url: " + url );
		}

		final int start = matcher.start ( );
		final int end = matcher.end ( );

		final String prefix = url.substring ( 0, start );
		final String posfix = url.substring ( end );

		return prefix + pageNum + posfix + ".html";
	}
}
