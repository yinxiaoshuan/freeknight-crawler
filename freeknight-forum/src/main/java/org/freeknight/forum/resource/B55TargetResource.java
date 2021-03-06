
package org.freeknight.forum.resource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.forum.cause.UnlawfulURLException;

public class B55TargetResource
		implements TargetResource
{

	Pattern	pattern	= Pattern.compile ( "([0-9]+).html" );

	@Override
	public String getTarget (
			String fsrcId, String url, int pageNum )
	{
		if ( pageNum == 1 )
		{
			return url;
		}
		final Matcher matcher = pattern.matcher ( url );
		if ( !matcher.find ( ) )
		{
			throw new UnlawfulURLException ( "Unlawful, url: " + url );
		}

		final int start = matcher.start ( 1 );
		final int end = matcher.end ( 1 );

		final String prefix = url.substring ( 0, start );
		final String posfix = url.substring ( end );
		return prefix + pageNum + posfix;
	}
}
